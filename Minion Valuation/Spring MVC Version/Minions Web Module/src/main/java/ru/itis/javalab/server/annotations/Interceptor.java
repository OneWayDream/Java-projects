package ru.itis.javalab.server.annotations;



import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;


@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface Interceptor {

    @AliasFor(annotation = Component.class)
    String value() default "";
    String[] pathPatterns() default {};
    int order() default -1;
}
