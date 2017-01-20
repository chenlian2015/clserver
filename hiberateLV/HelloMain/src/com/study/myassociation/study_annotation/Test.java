package com.study.myassociation.study_annotation;

import com.study.myassociation.common.Common;
import com.study.myassociation.entity.Address;
import com.study.myassociation.entity.Person;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Created by chenlian on 16/6/20.
 */
public class Test {

    public static void main(String[] args) {

        insertCommunity();
    }

    public static  void insertCommunity()
    {
        Session session = Common.getSessionFactoryInstance().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            Community c = new Community();
            session.save(c);
            session.flush();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

}
