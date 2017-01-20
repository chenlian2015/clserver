package com.study.myassociation.study_base;

import com.study.myassociation.common.Common;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Date;

/**
 * Created by chenlian on 16/6/17.
 */
public class Test {


    public static void main(String[] args) {
        testElementCollection();
    }


    public static void testElementCollection() {
        Session session = Common.getSessionFactoryInstance().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            Shop s = new Shop();
            s.getMyGoods().add("美特斯邦威01");
            s.getMyGoods().add("七匹狼");
            s.getOrders().add(new Long(123));
            s.getOrders().add(new Long(234));
            s.getOrders().add(new Long(345));
            s.getScores().put("1236", 90.0f);
            s.getScores().put("1234", 80.0f);
            s.getScores().put("1235", 100.0f);

            session.save(s);
            session.flush();
            session.clear();
            Shop sget = session.load(Shop.class, new Long(69));
            System.out.println(sget.toString());
            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

    }

    public static void testEnum()
    {   Session session = Common.getSessionFactoryInstance().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            MyOrder o = new MyOrder();
            session.save(o);
            o.setHapperSeason(MyOrder.Season.wintter);
            o.setBackComment("请尽快送到");
            o.setModifyTime(new Date());
            session.flush();
            session.clear();
            tx.commit();
            System.out.println(o.getDateTime());
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }}

    public static void testLoad_Merge_save() {
        Session session = Common.getSessionFactoryInstance().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            Goods gs = new Goods();
            gs.setName("老人与海");
            gs.setPrice(20.5);
            Long id = (Long) session.save(gs);
            //save已经将gs对象变成持久化状态
            gs.setName("巴黎圣母院");
            System.out.println("save generated id:" + id);

            Goods g = session.load(Goods.class, id, LockMode.READ);
            g.setName("草房子");
            System.out.println(g.toString());


            session.flush();

            Goods gx = new Goods();
            gx.setName("简爱");
            session.merge(gx);
            //而merge却只将gx的副本持久状态化,而gx还是瞬态
            gx.setName("呼啸山庄");

            session.flush();
            session.clear();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}



