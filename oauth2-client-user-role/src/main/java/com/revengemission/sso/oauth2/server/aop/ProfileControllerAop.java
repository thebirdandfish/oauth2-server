package com.revengemission.sso.oauth2.server.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @Program: oauth2-server
 * @Create: 2019-12-04 21:56
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
@Aspect
@Component
public class ProfileControllerAop {

    private Logger log = LoggerFactory.getLogger(this.getClass());


    // 对ProfileController下的info方法进行重写。
    private final String ExpGetResultDataPoint = "execution(public * com..*.ProfileController.info(..))";

    @AfterReturning(returning = "result", pointcut = ExpGetResultDataPoint)
    public Map<String, Object> doAfterInfoReturning(JoinPoint joinPoint, Map<String, Object> result) {
        System.out.println("返回值最初为：" + result);
        try {
            if (result != null) {
                result = (Map<String, Object>) result;
                result.put("clientUserPrivilege", 1);
            }
        } catch (Exception e) {
            if (log.isInfoEnabled()) {
                log.info("/user/me Aspect exception ".concat(joinPoint.getSignature().toString()), e);
            }
            Map<String, Object> errorResult = new HashMap<>(16);

            errorResult.put("status", 0);
            errorResult.put("message", "infoAspect切面错误，认证失败。 ");
            return errorResult;
        }

        return result;
    }
}
