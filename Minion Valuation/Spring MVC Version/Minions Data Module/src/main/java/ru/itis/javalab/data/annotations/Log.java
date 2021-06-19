package ru.itis.javalab.data.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Log {

    MethodType methodType() default MethodType.DEFAULT;

    enum MethodType{
        CONTROLLER, SERVICE, REPOSITORY, DEFAULT
    }
}
