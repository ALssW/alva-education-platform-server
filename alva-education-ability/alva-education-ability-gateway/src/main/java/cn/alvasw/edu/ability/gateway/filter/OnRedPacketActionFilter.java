package cn.alvasw.edu.ability.gateway.filter;

import cn.alvasw.edu.ability.gateway.utils.ResponseUtil;
import cn.alvasw.framework.commons.base.result.Rs;
import cn.alvasw.framework.commons.base.result.RsCodes;
import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.Ordered;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-04-19
 */
@Component
@Slf4j
public class OnRedPacketActionFilter extends AbstractGatewayFilterFactory<Object> implements GatewayFilter, Ordered {

	@Resource
	private StringRedisTemplate                        stringRedisTemplate;
	@Resource
	private KafkaTemplate<String, Map<String, Object>> kafkaTemplates;

	@Override
	public GatewayFilter apply(Object config) {
		return this;
	}

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		ServerHttpRequest request = exchange.getRequest();

		// 用户id
		String userId = "1";
		// 活动id
		String activityId = request.getQueryParams().getFirst("id");
		log.debug("[Get RedPacket] 用户 -> [{}] 活动 -> [{}]", userId, activityId);

		if (StringUtils.isEmpty(activityId)) {
			return ResponseUtil.response(exchange, Rs.error("活动ID为空"));
		}

		// 执行lua脚本获得抢红包的结果
		String result = stringRedisTemplate.execute(
				new DefaultRedisScript<>(GET_RED_PACKET_SCRIPT, String.class),
				Collections.singletonList(activityId),
				userId);
		RedisResponse response = JSON.parseObject(result, RedisResponse.class);
		log.debug("[Get RedPacket] response - [{}]", response);

		RsCodes codes;
		if (response == null) {
			// 红包获取异常
			return ResponseUtil.response(exchange, Rs.error("活动异常"));
		} else if ((codes = RsCodes.getValue(response.getCode())) != RsCodes.OK) {
			// 红包获取失败
			return ResponseUtil.response(exchange, Rs.fail(codes));
		}

		// 红包获取成功
		if (response.getType().equals(0)) {
			// 抢到了现金红包
			double getCash = Double.parseDouble(response.getContent());
			// 转换成元
			response.setContent(BigDecimal.valueOf(getCash)
					.divide(BigDecimal.valueOf(100), 2, RoundingMode.FLOOR)
					.toString());
		}
		// 抢到了红包
		Rs<RedisResponse> res = Rs.ok("获取红包成功", response);

		// 将抢到的红包信息发送给 Kafka 红包类型，内容，用户id
		Map<String, Object> map = new HashMap<>(4);
		map.put("type", response.getType());
		map.put("info", response.getContent());
		map.put("userId", Long.parseLong(userId));
		map.put("activityId", Long.parseLong(activityId));

		// 放入Kafka中
		kafkaTemplates.send("activity-get-red", map);
		return ResponseUtil.response(exchange, res);
	}

	@Override
	public String name() {
		return "OnRedPacketAction";
	}

	@Override
	public int getOrder() {
		return 100;
	}


	@Data
	private static class RedisResponse {
		private Integer code;
		private Integer type;
		private String  content;
	}

	private final String GET_RED_PACKET_SCRIPT =
			"local function appendResultMsg(resultMsg, msg)\n" +
					"\tresultMsg = resultMsg .. msg .. \",\"\n" +
					"\treturn resultMsg\n" +
					"end\n" +
					"\n" +
					"local function completeResultMsg(resultMsg, msg)\n" +
					"\tresultMsg = resultMsg .. msg .. \"}\"\n" +
					"\treturn resultMsg\n" +
					"end\n" +
					"\n" +
					"--抢现金红包的方法\n" +
					"local function getCash(activityTypeListKey, activityId)\n" +
					"\tlocal cashHashKey = \"activity:cash:\" .. activityId\n" +
					"\n" +
					"\t--获取红包类型\n" +
					"\tlocal type = tonumber(redis.call(\"hget\", cashHashKey, \"type\"))\n" +
					"\t--获取红包的总额度\n" +
					"\tlocal cash = tonumber(redis.call(\"hget\", cashHashKey, \"cash\"))\n" +
					"\t--获取红包的数量\n" +
					"\tlocal nums = tonumber(redis.call(\"hget\", cashHashKey, \"nums\"))\n" +
					"\n" +
					"\t-- 抢到红包的金额\n" +
					"\tlocal acquired = 0\n" +
					"\n" +
					"\t-- 判断自己是否为最后一个抢到现金红包的用户\n" +
					"\tif nums == 1 then\n" +
					"\t\t-- 移除所有现金红包的类型\n" +
					"\t\tredis.call(\"lrem\", activityTypeListKey, 0, 0)\n" +
					"\t\t-- 移除现金红包的hash结构\n" +
					"\t\tredis.call(\"del\", cashHashKey)\n" +
					"\t\t-- 直接返回所有的现金红包\n" +
					"\t\treturn acquired\n" +
					"\telse\n" +
					"\t\t-- 说明不是最后一个抢到现金红包的用户\n" +
					"\t\tif type == 0 then\n" +
					"\t\t\t--固定红包\n" +
					"\t\t\tacquired = cash / nums\n" +
					"\t\telse\n" +
					"\t\t\t--随机红包\n" +
					"\t\t\tacquired = math.random(cash / nums * 2 - 1)\n" +
					"\t\tend\n" +
					"\tend\n" +
					"\n" +
					"\t--修改redis中现金红包的相关参数\n" +
					"\tredis.call(\"hset\", cashHashKey, \"cash\", cash - acquired, \"nums\", nums - 1)\n" +
					"\t--返回抢到的金额\n" +
					"\treturn acquired\n" +
					"end\n" +
					"\n" +
					"--抢优惠券红包的方法\n" +
					"local function getCoupon(activityTypeListKey, activityId)\n" +
					"\t--优惠券列表的key\n" +
					"\tlocal couponSetKey = \"activity:coupon:\" .. activityId\n" +
					"\tlocal couponNumsHashKey = couponSetKey .. \":nums\"\n" +
					"\t--从set集合中随机返回一个优惠券id\n" +
					"\tlocal couponId = redis.call(\"srandmember\", couponSetKey)\n" +
					"\t--再去扣减对应的优惠券的数量\n" +
					"\tlocal nums = tonumber(redis.call(\"hget\", couponNumsHashKey, couponId))\n" +
					"\n" +
					"\t--判断优惠券的数量\n" +
					"\tif nums ~= -1 then\n" +
					"\t\t--修改优惠券的数量\n" +
					"\t\tlocal newNums = nums - 1\n" +
					"\t\tredis.call(\"hset\", couponNumsHashKey, couponId, newNums)\n" +
					"\t\t--判断优惠券的数量是否为空\n" +
					"\t\tif newNums == 0 then\n" +
					"\t\t\t--从set集合中将当前的优惠券移除掉\n" +
					"\t\t\tredis.call(\"srem\", couponSetKey, couponId)\n" +
					"\t\t\t--判断set集合中是否还存在优惠券的id，如果不存在了，说明所有的优惠券都被抢完了\n" +
					"\t\t\tif redis.call(\"scard\", couponSetKey) == 0 then\n" +
					"\t\t\t\t--将优惠券类型从类型列表中移除掉\n" +
					"\t\t\t\tredis.call(\"lrem\", activityTypeListKey, 0, 1)\n" +
					"\t\t\tend\n" +
					"\t\tend\n" +
					"\tend\n" +
					"\n" +
					"\treturn couponId\n" +
					"end\n" +
					"\n" +
					"local resultMsg = \"{\"\n" +
					"\n" +
					"--获得活动id\n" +
					"local activityId = KEYS[1]\n" +
					"--获取用户id\n" +
					"--local uid = ARGV[1]\n" +
					"--活动的相关key值\n" +
					"local activityHashKey = \"activity:action:\" .. activityId\n" +
					"\n" +
					"--判断当前活动是否上架\n" +
					"local activityExists = redis.call(\"exists\", activityHashKey)\n" +
					"if activityExists == 0 then\n" +
					"\t--活动还未上架\n" +
					"\treturn completeResultMsg(resultMsg, \"\\\"code\\\":701\")\n" +
					"end\n" +
					"\n" +
					"--判断活动是否开始\n" +
					"local beginTime = tonumber(redis.call(\"hget\", activityHashKey, \"beginTime\"))\n" +
					"local endTime = tonumber(redis.call(\"hget\", activityHashKey, \"endTime\"))\n" +
					"local now = redis.call(\"time\")[1] * 1000\n" +
					"\n" +
					"if now < beginTime then\n" +
					"\t--活动还未开始\n" +
					"\treturn completeResultMsg(resultMsg, \"\\\"code\\\":702\")\n" +
					"end\n" +
					"\n" +
					"if now > endTime then\n" +
					"\t--活动已经结束\n" +
					"\treturn completeResultMsg(resultMsg, \"\\\"code\\\":703\")\n" +
					"end\n" +
					"\n" +
					"--从list集合中获得一个应该抢到的红包类型\n" +
					"local activityTypeListKey = activityHashKey .. \":type\"\n" +
					"local redPacketType = tonumber(redis.call(\"lpop\", activityTypeListKey))\n" +
					"if redPacketType == nil then\n" +
					"\t--所有类型的红包都被抢完了\n" +
					"\treturn completeResultMsg(resultMsg, \"\\\"code\\\":704\")\n" +
					"end\n" +
					"-- 将 type 重新放入 list 中\n" +
					"redis.call(\"rpush\", activityTypeListKey, redPacketType)\n" +
					"\n" +
					"-- 进行抢红包的逻辑\n" +
					"resultMsg = appendResultMsg(resultMsg, \"\\\"code\\\":200\")\n" +
					"resultMsg = appendResultMsg(resultMsg, \"\\\"type\\\":\" .. redPacketType)\n" +
					"\n" +
					"if redPacketType == 0 then\n" +
					"\t--现金红包\n" +
					"\tresultMsg = completeResultMsg(resultMsg, \"\\\"content\\\":\" .. getCash(activityTypeListKey, activityId))\n" +
					"elseif redPacketType == 1 then\n" +
					"\t--优惠券红包\n" +
					"\tresultMsg = completeResultMsg(resultMsg, \"\\\"content\\\":\" .. getCoupon(activityTypeListKey, activityId))\n" +
					"end\n" +
					"\n" +
					"return resultMsg";

}
