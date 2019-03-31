package pers.ownsky.trafficalert.dataaccess.datasource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Random;

@Component
@Aspect
public class DataSourceAspect {

    private String readBalance() {
        int rand = new Random().nextInt(2)+1;
//        int rand = 2;
        return "read"+rand;
    }

    @Pointcut("execution(* pers.ownsky.trafficalert.dataaccess..*.*(..))")
    public void dsPointCut() {
    }

    @Before("dsPointCut()")
    public void before(JoinPoint joinPoint) {
        Object target = joinPoint.getTarget();
        String context = joinPoint.getSignature().toString();
        String method = joinPoint.getSignature().getName();
//        System.out.println("asp: "+method+joinPoint.hashCode());
        Class<?> clazz = target.getClass();
        Class<?>[] ifaces = target.getClass().getInterfaces();
        Class<?>[] parameterTypes = ((MethodSignature) joinPoint.getSignature()).getMethod().getParameterTypes();
        try {
            TargetDataSource tds = null;
            Method mc = clazz.getMethod(method, parameterTypes);
            if (mc != null && mc.isAnnotationPresent(TargetDataSource.class)) {
                tds = mc.getAnnotation(TargetDataSource.class);
            } else {
                if (ifaces.length > 0) {
                    for (Class<?> i:
                         ifaces) {
                        Method mi = null;
                        try {
                             mi = i.getMethod(method, parameterTypes);
                        } catch (Exception ignored) {}
                        if (mi != null && mi.isAnnotationPresent(TargetDataSource.class)) {
                            tds = mi.getAnnotation(TargetDataSource.class);
                            break;
                        }
                    }
                }
            }
            if (tds != null) {
                String dsName = tds.value();
                if (dsName.equals("read")) {
                    dsName = readBalance();
                }
                DataSourceContextHolder.putDataSource(dsName, context);
            }
        } catch (Exception e) {
//            e.printStackTrace();
        }
    }

    @After("dsPointCut()")
    public void after(JoinPoint joinPoint) {
//        System.out.println("after asp: "+joinPoint.getSignature().getName()+joinPoint.getSignature().toString());
        DataSourceContextHolder.removeDataSource(joinPoint.getSignature().toString());
    }



}
