package ru.itis.javalab.data.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.itis.javalab.data.annotations.Log;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class LoggerAspect {

    @Pointcut("execution(public * *(..))")
    public void publicMethod() {}

    @Pointcut("execution(* *(..))")
    public void method(){}

    @Pointcut("@within(ru.itis.javalab.data.annotations.Log)")
    public void aspectAnnotation(){}

    @Pointcut("@within(org.springframework.stereotype.Controller) || " +
            "@within(org.springframework.web.bind.annotation.RestController)")
    public void controllerAnnotation(){}

    @Pointcut("@within(org.springframework.web.bind.annotation.ControllerAdvice)")
    public void controllerAdviceAnnotation(){};

    @Pointcut("@annotation(org.springframework.web.bind.annotation.ExceptionHandler)")
    public void exceptionHandlerMethods(){};

    @Pointcut("@within(org.springframework.stereotype.Service)")
    public void serviceAnnotation(){}

    @Pointcut("@within(org.springframework.stereotype.Repository)")
    public void repositoryAnnotation(){}

    @Pointcut(
                    "@annotation(org.springframework.web.bind.annotation.RequestMapping) || " +
                    "@annotation(org.springframework.web.bind.annotation.PostMapping) || " +
                    "@annotation(org.springframework.web.bind.annotation.GetMapping) || " +
                    "@annotation(org.springframework.web.bind.annotation.DeleteMapping) || " +
                    "@annotation(org.springframework.web.bind.annotation.PatchMapping) || " +
                    "@annotation(org.springframework.web.bind.annotation.PutMapping)"
    )
    public void controllerMethodAnnotation(){}

    @Around("aspectAnnotation() && controllerAnnotation() && controllerMethodAnnotation() && publicMethod()")
    public Object logControllers(ProceedingJoinPoint joinPoint) throws Throwable{
        UserDetails user;
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        try{
            user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            log.info("The user " + user.getUsername() + " has contacted the " + className + " to method " + methodName + ".");
        } catch (Exception ex){
            log.info("Unidentified user has contacted the " + className + " to method " + methodName + ".");
        }
        Object result = joinPoint.proceed();
        try{
            user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            log.info("The user " + user.getUsername() + " got a response from the " + className + " to method " + methodName + ".");
        } catch (Exception ex){
            log.info("Unidentified user got a response from the " + className + " to method " + methodName + ".");
        }
        return result;
    }

    @Around("aspectAnnotation() && serviceAnnotation() && publicMethod()")
    public Object logService(ProceedingJoinPoint joinPoint) throws Throwable {
        UserDetails user;
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        try{
            user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            log.info("The thread of " + user.getUsername() + " called the method " + methodName + " of  " + className + " class.");
        } catch (Exception ex){
            log.info("Unidentified user's thread called the method " + methodName + " of  " + className + " class.");
        }
        Object result = joinPoint.proceed();
        try{
            user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            log.info("The thread of " + user.getUsername() + " got a response from the method " + methodName + " of  " + className + " class.");
        } catch (Exception ex){
            log.info("Unidentified user got a response from the method " + methodName + " of  " + className + " class.");
        }
        return result;
    }

    @Around("aspectAnnotation() && repositoryAnnotation() && publicMethod()")
    public Object logRepository(ProceedingJoinPoint joinPoint) throws Throwable {
        UserDetails user;
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        try{
            user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            log.info("The thread of " + user.getUsername() + " called the method " + methodName + " of  " + className + " class.");
        } catch (Exception ex){
            log.info("Unidentified user's thread called the method " + methodName + " of  " + className + " class.");
        }
        Object result = joinPoint.proceed();
        try{
            user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            log.info("The thread of " + user.getUsername() + " got a response from the method " + methodName + " of  " + className + " class.");
        } catch (Exception ex){
            log.info("Unidentified user got a response from the method " + methodName + " of  " + className + " class.");
        }
        return result;
    }

    @Around("method() && @annotation(logAnn)")
    public Object logMethod(ProceedingJoinPoint joinPoint, Log logAnn) throws Throwable {
        UserDetails user;
        String methodName = joinPoint.getSignature().getName();
        Object result = null;
        switch (logAnn.methodType()){
            case CONTROLLER:
                try{
                    user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                    log.info("The user " + user.getUsername() + " has contacted the " + methodName + " method.");
                } catch (Exception ex){
                    log.info("Unidentified user has contacted the " + methodName + " method.");
                }
                result = joinPoint.proceed();
                try{
                    user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                    log.info("The user " + user.getUsername() + " got a response from the " + methodName + " method.");
                } catch (Exception ex){
                    log.info("Unidentified user got a response from the " + methodName + " method.");
                }
                break;
            case SERVICE:
            case REPOSITORY:
                try{
                    user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                    log.info("Method" + methodName + " was called by " + user.getUsername() + " .");
                } catch (Exception ex){
                    log.info("Method" + methodName + " was called by undefined user.");
                }
                result = joinPoint.proceed();
                try{
                    user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                    log.info("Method" + methodName + " responded to " + user.getUsername() + " .");
                } catch (Exception ex){
                    log.info("Method" + methodName + " responded to undefined user.");
                }
                break;
            case DEFAULT:
                log.info("Method " + methodName + " was called.");
                result = joinPoint.proceed();
                break;
        }
        return result;
    }

    @Around("aspectAnnotation() && controllerAdviceAnnotation() && exceptionHandlerMethods() && publicMethod()")
    public Object logAdviceController(ProceedingJoinPoint joinPoint) throws Throwable {
        Throwable throwable = (Throwable) joinPoint.getArgs()[0];
        Object result = joinPoint.proceed();
        try{
            UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            log.info("The user " + user.getUsername() + " got exception " + throwable.getClass().getSimpleName() + " .");
        } catch (Exception ex){
            log.info("Unidentified user got exception " + throwable.getClass().getSimpleName() + " .");
        }
        if (throwable.getStackTrace()!=null){
            log.info(Arrays.toString(throwable.getStackTrace()).replace(",", "\n"));
        }
        return result;
    }

}
