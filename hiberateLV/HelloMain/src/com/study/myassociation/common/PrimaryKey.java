package com.study.myassociation.common;

import java.lang.annotation.*;

/**
 * Created by chenlian on 16/6/20.
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface PrimaryKey {
    String columnName() default "";

    boolean autoIncrement() default true;

    int primaryKey() default 1;
}
