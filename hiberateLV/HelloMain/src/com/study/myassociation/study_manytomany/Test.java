package com.study.myassociation.study_manytomany;

import com.study.myassociation.common.Common;
import com.study.myassociation.entity.Address;
import com.study.myassociation.entity.Person;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Created by chenlian on 16/6/17.
 */
public class Test {

    public static void main(String[] args)
    {
        Session session = Common.getSessionFactoryInstance().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            Teacher t = new Teacher();
            t.setTeacherName("huanglaoshi_x");

            Teacher t2 = new Teacher();
            t2.setTeacherName("huanglaoshi_y");

            Teacher t3 = new Teacher();
            t3.setTeacherName("huanglaoshi_z");
            session.save(t);
            session.save(t2);
            session.save(t3);

            Student s = new Student();
            s.setStudentName("chenlian");
            session.save(s);

            Student s2 = new Student();
            s2.setStudentName("chenlian2");
            session.save(s2);

            Student s3 = new Student();
            s3.setStudentName("chenlian3");
            session.save(s3);

            s.getTeachers().add(t);
            s.getTeachers().add(t2);
            s.getTeachers().add(t3);


            t.getStudents().add(s);
            t.getStudents().add(s2);
            t.getStudents().add(s3);

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



