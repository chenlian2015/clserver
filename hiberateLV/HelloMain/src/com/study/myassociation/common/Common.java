package com.study.myassociation.common;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by chenlian on 16/6/16.
 */
public class Common {
    private static SessionFactory factory;
    synchronized public static SessionFactory getSessionFactoryInstance()
    {
        if(null == factory) {
            factory = new Configuration().configure().buildSessionFactory();
        }
        return factory;
    }
}
