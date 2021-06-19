package ru.itis.javalab.server.annotations;


import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import java.util.List;

public class InterceptorAspect {

    public static void handle(InterceptorRegistry registry, List<Object> classes){
        for (Object o : classes) {
            Interceptor annotation = o.getClass().getAnnotation(Interceptor.class);
            if (annotation.pathPatterns().length!=0){
                if (annotation.order()!=-1){
                    registry.addInterceptor((HandlerInterceptor) o)
                            .addPathPatterns(annotation.pathPatterns())
                            .order(annotation.order());
                } else {
                    registry.addInterceptor((HandlerInterceptor) o)
                            .addPathPatterns(annotation.pathPatterns());
                }
            } else {
                if (annotation.order()!=-1){
                    registry.addInterceptor((HandlerInterceptor) o)
                            .order(annotation.order());
                } else {
                    registry.addInterceptor((HandlerInterceptor) o);
                }
            }
        }
    }

}
