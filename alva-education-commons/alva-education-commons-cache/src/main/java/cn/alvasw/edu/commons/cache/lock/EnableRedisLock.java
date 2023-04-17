package cn.alvasw.edu.commons.cache.lock;

import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-04-17
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(RedisLockAspect.class)
public @interface EnableRedisLock {
}
