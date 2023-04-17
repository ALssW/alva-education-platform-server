package cn.alvasw.edu.commons.cache.lock;

import cn.alvasw.edu.commons.cache.utils.RedisLock;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import javax.annotation.Resource;
import java.lang.reflect.Method;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-04-17
 */
@Aspect
@Slf4j
@Order(-1)
public class RedisLockAspect {

	private final ExpressionParser parser = new SpelExpressionParser();

	private final LocalVariableTableParameterNameDiscoverer discover = new LocalVariableTableParameterNameDiscoverer();

	public final String LOCK_PREFIX = "LOCK:";

	@Around("@annotation(RLock)")
	public Object lockAround(ProceedingJoinPoint joinPoint) throws Throwable {
		try {
			log.debug("[Redis Lock Around] 开始进行分布式锁");
			MethodSignature signature = (MethodSignature) joinPoint.getSignature();
			Method          method    = signature.getMethod();
			String          keyName   = method.getAnnotation(RLock.class).value();

			String[]          parameterNames = discover.getParameterNames(method);
			Object[]          args           = joinPoint.getArgs();
			EvaluationContext context        = new StandardEvaluationContext();
			for (int i = 0; i < args.length; i++) {
				context.setVariable(parameterNames[i], args[i]);
			}

			Expression expression = parser.parseExpression(keyName);
			keyName = LOCK_PREFIX + expression.getValue(context);

			RedisLock.lock(keyName);
			return joinPoint.proceed();
		} finally {
			RedisLock.unlock();
		}
	}

}
