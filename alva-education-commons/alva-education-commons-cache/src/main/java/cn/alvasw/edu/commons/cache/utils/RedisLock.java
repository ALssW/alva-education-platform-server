package cn.alvasw.edu.commons.cache.utils;

import cn.alvasw.framework.commons.core.utils.ApplicationUtil;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-04-17
 */
@Slf4j
public class RedisLock {

	private static final RedissonClient CLIENT;

	private static final ThreadLocal<RLock> LOCK = new ThreadLocal<>();

	static {
		CLIENT = ApplicationUtil.getBean(Redisson.class);
	}

	public static void lock(String key) {
		RLock rLock;
		if ((rLock = LOCK.get()) != null && rLock.isLocked()) {
			return;
		}
		log.debug("[RedisLock] lock key -> {}", key);
		rLock = CLIENT.getLock(key);
		rLock.lock();
		LOCK.set(rLock);
	}


	public static void unlock() {
		try {
			RLock rLock;
			if ((rLock = LOCK.get()) == null || !rLock.isLocked()) {
				return;
			}
			log.debug("[RedisLock] unlock - {}", rLock.getName());

			rLock.unlock();
		} finally {
			LOCK.remove();
		}
	}

}
