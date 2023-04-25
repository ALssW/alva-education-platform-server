package cn.alvasw.edu.business.activity.listener;

import cn.alvasw.edu.business.activity.service.ActivityRedPacketRecordService;
import cn.alvasw.edu.data.activity.entity.ActivityRedPacketRecord;
import cn.alvasw.edu.data.activity.vo.input.ActivityRedPacketRecordVO;
import cn.alvasw.framework.commons.core.utils.BeanUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-04-24
 */
@Component
@Slf4j
public class OnGetRedPacketListener {

	@Resource
	private ActivityRedPacketRecordService recordService;

	@KafkaListener(topics = "activity-get-red")
	public void activityGetRed(HashMap<String, Object> recordMap) {
		ActivityRedPacketRecordVO recordVO = new ActivityRedPacketRecordVO();
		recordVO.setType((Integer) recordMap.get("type"));
		recordVO.setInfo((String) recordMap.get("info"));
		recordVO.setUserId(Long.valueOf((Integer) recordMap.get("userId")));
		recordVO.setActivityId((Long) recordMap.get("activityId"));

		log.info("[Activity Get Red Action Listener] 新增红包获取记录 recordVo -> [{}], 新增结果 -> [{}]",
				recordVO,
				recordService.save(BeanUtil.copyInstance(recordVO, ActivityRedPacketRecord.class)));
	}

}
