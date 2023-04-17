package cn.alvasw.edu.commons.cache.lock;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-04-17
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RLock {

	String value() default "LOCK";

}
