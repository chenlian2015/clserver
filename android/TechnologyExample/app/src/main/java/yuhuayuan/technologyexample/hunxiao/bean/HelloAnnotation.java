package yuhuayuan.technologyexample.hunxiao.bean;

/**
 * Created by chenlian on 16/10/19.
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/***
 * Retention:保持、保留
 * RetentionPolicy：政策、方针
 *
 * @author huawei
 * @Retention 1、指示注释类型的注释要保留多久。如果注释类型声明中不存在 Retention 注释，则保留策略默认为 RetentionPolicy.CLASS
 * 2、有三种取值(代表三个阶段)：
 * RetentionPolicy.SOURCE:保留注解到java源文件阶段，例如Override、SuppressWarnings
 * RetentionPolicy.CLASS:保留注解到class文件阶段,例如
 * RetentionPolicy.RUNTIME:保留注解到运行时阶段即内存中的字节码,例如Deprecated
 */
//元注解：表示的是注解的注解，（同义词有元信息、元数据）
//如果不加,javac会把这无用的注解丢掉
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})//指定该注解使用的用处：用在class上和用在方法体上。
public @interface HelloAnnotation {
    //返回值是String类型
    String color() default "蓝色";//方法，但是相当于注解的属性，即：当成属性赋值，当成方法调用。

    //默认value
    String value();

    String author() default "默认给定了属性";

    //返回值是数组对象
    int[] arrayAttr() default {1};
    //返回值是注解类型
}