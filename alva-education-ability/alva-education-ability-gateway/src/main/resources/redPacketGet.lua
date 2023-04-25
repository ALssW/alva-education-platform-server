local function appendResultMsg(resultMsg, msg)
	resultMsg = resultMsg .. msg .. ","
	return resultMsg
end

local function completeResultMsg(resultMsg, msg)
	resultMsg = resultMsg .. msg .. "}"
	return resultMsg
end


local function getCash(activityTypeListKey, activityId)
	local cashHashKey = "activity:cash:" .. activityId

	
	local type = tonumber(redis.call("hget", cashHashKey, "type"))
	
	local cash = tonumber(redis.call("hget", cashHashKey, "cash"))
	
	local nums = tonumber(redis.call("hget", cashHashKey, "nums"))

	
	local acquired = 0

	
	if nums == 1 then
		
		redis.call("lrem", activityTypeListKey, 0, 0)
		
		redis.call("del", cashHashKey)
		
		return acquired
	else
		
		if type == 0 then
			
			acquired = cash / nums
		else
			
			acquired = math.random(cash / nums * 2 - 1)
		end
	end

	
	redis.call("hset", cashHashKey, "cash", cash - acquired, "nums", nums - 1)
	
	return acquired
end


local function getCoupon(activityTypeListKey, activityId)
	
	local couponSetKey = "activity:coupon:" .. activityId
	local couponNumsHashKey = couponSetKey .. ":nums"
	
	local couponId = redis.call("srandmember", couponSetKey)
	
	local nums = tonumber(redis.call("hget", couponNumsHashKey, couponId))

	
	if nums ~= -1 then
		
		local newNums = nums - 1
		redis.call("hset", couponNumsHashKey, couponId, newNums)
		
		if newNums == 0 then
			
			redis.call("srem", couponSetKey, couponId)
			
			if redis.call("scard", couponSetKey) == 0 then
				
				redis.call("lrem", activityTypeListKey, 0, 1)
			end
		end
	end

	return couponId
end

local resultMsg = "{"


local activityId = KEYS[1]



local activityHashKey = "activity:action:" .. activityId


local activityExists = redis.call("exists", activityHashKey)
if activityExists == 0 then
	
	return completeResultMsg(resultMsg, "\"code\":701")
end


local beginTime = tonumber(redis.call("hget", activityHashKey, "beginTime"))
local endTime = tonumber(redis.call("hget", activityHashKey, "endTime"))
local now = redis.call("time")[1] * 1000

if now < beginTime then
	
	return completeResultMsg(resultMsg, "\"code\":702")
end

if now > endTime then
	
	return completeResultMsg(resultMsg, "\"code\":703")
end


local activityTypeListKey = activityHashKey .. ":type"
local redPacketType = tonumber(redis.call("lpop", activityTypeListKey))
if redPacketType == nil then
	
	return completeResultMsg(resultMsg, "\"code\":704")
end

redis.call("rpush", activityTypeListKey, redPacketType)


resultMsg = appendResultMsg(resultMsg, "\"code\":200")
resultMsg = appendResultMsg(resultMsg, "\"type\":" .. redPacketType)

if redPacketType == 0 then
	
	resultMsg = completeResultMsg(resultMsg, "\"content\":" .. getCash(activityTypeListKey, activityId))
elseif redPacketType == 1 then
	
	resultMsg = completeResultMsg(resultMsg, "\"content\":" .. getCoupon(activityTypeListKey, activityId))
end

return resultMsg