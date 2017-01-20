package com.study.myassociation.test;

import bean.News;
import com.study.myassociation.common.Common;
import com.study.myassociation.entity.Address;
import com.study.myassociation.entity.GraduateSchool;
import com.study.myassociation.entity.Person;
import com.study.myassociation.entity.Phone;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Iterator;
import java.util.List;

/**
 * Created by chenlian on 16/6/16.
 */
public class Test {
    public static void main(String[] args) {

       insertPhone();
      //  listPhone();
     //   showPerson("1");
      //  showPerson("4");
    }

    public static void deletePerson(String id)
    {
        Session session = Common.getSessionFactoryInstance().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List person = session.createQuery("from Person where id="+id).list();
            for (Iterator iterator =
                 person.iterator(); iterator.hasNext(); ) {
                Person myPhone = (Person) iterator.next();
                session.delete(myPhone);
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    public static void showPerson(String id) {
        Session session = Common.getSessionFactoryInstance().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List person = session.createQuery("from Person where id="+id).list();
            for (Iterator iterator =
                 person.iterator(); iterator.hasNext(); ) {
                Person myPhone = (Person) iterator.next();
                System.out.println("id: " + myPhone.toString());
                System.out.println();
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static void insertPhone() {
        Session session = Common.getSessionFactoryInstance().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            Person person = new Person();
            person.setName("zhouli");
            Address scda = new Address("scda");
            scda.persons = person;
            person.getAddress().add(scda);
            Address bjdx = new Address("bjdx");
            bjdx.persons = person;
            person.getAddress().add(bjdx);
           // GraduateSchool gs = new GraduateSchool();
         //   gs.name="防灾科技";



           // person.setGraduateSchool(gs);

            session.persist(person);

           /// Phone phone = new Phone("123-456-7890");
            //phone.setPerson(person);
           // session.persist(phone);

            session.flush();
            //phone.setPerson(null);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static void listPhone() {
        Session session = Common.getSessionFactoryInstance().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List phones = session.createQuery("from Phone").list();
            for (Iterator iterator =
                 phones.iterator(); iterator.hasNext(); ) {
                Phone myPhone = (Phone) iterator.next();
                System.out.print("title: " + myPhone.getId());
                System.out.print("content: " + myPhone.getNumber());
                if (null != myPhone.getPerson()) {
                    System.out.println("id: " + myPhone.getPerson().toString());
                    System.out.println();
                }

            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

}
