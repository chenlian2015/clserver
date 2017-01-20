import bean.News;
import com.study.myassociation.common.Common;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.Iterator;
import java.util.List;

/**
 * Created by chenlian on 16/6/10.
 */
public class HelloMain {


    public static void main(String[] args) {
//        try {
//            Session session =  Common.getSessionFactoryInstance().openSession();
//            int n=1;
//            while (n-->0) {
//                addNews();
//            }
//            listEmployees();
//        } catch (Throwable ex) {
//            System.err.println("Failed to create sessionFactory object." + ex);
//            throw new ExceptionInInitializerError(ex);
//        }
    }

    public static String addNews() {
        Session session = Common.getSessionFactoryInstance().openSession();
        Transaction tx = null;
        String employeeID = null;
        try {
            tx = session.beginTransaction();
            bean.News n = new bean.News();
            n.setTitle("cycle in far future");
            n.setContent("magic");
            n.setFullTime("时间");
            n.getBaoshes().add("xinhua");
            n.getBaoshes().add("renming");
            n.getBaoshes().add("xiandai");

            n.getDianshitai().add("CCTV");
            n.getDianshitai().add("DIANYING");

            n.getScores().put("1", 1.23f);
            n.getScores().put("2", 5.68f);
            employeeID = (String) session.save(n);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return employeeID;
    }

    /* Method to  READ all the employees */
    public static void listEmployees() {
        Session session = Common.getSessionFactoryInstance().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List employees = session.createQuery("from News").list();
            for (Iterator iterator =
                 employees.iterator(); iterator.hasNext(); ) {
                News employee = (News) iterator.next();
                System.out.print("title: " + employee.getTitle());
                System.out.print("content: " + employee.getContent());
                System.out.println("id: " + employee.getId());
                Iterator<String> it =  employee.getBaoshes().iterator();
                while(it.hasNext()) {
                    System.out.println(it.next());
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

    /* Method to UPDATE salary for an employee */
    public static void updateNews(Integer newsId, String title) {
        Session session = Common.getSessionFactoryInstance().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            News employee =
                    (News) session.get(News.class, newsId);
            employee.setTitle(title);
            session.update(employee);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /* Method to DELETE an employee from the records */
    public static void deleteEmployee(Integer EmployeeID) {
        Session session = Common.getSessionFactoryInstance().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            News employee =
                    (News) session.get(News.class, EmployeeID);
            session.delete(employee);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

}
