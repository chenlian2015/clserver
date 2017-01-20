package yuhuayuan.technologyexample;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;


/**
 * Created by chenlian on 16/10/24.
 */
public class ClassSpy {

    public static void run(String className) {
        try {
            
            Class<?> c = Class.forName(className);
            System.out.format("Class:%n  %s%n%n", c.getCanonicalName());

            Package p = c.getPackage();
            System.out.format("Package:%n  %s%n%n",
                    (p != null ? p.getName() : "-- No Package --"));

            printMembers(c.getConstructors(), "Constuctors");
            printMembers(c.getFields(), "Fields");
            printMembers(c.getMethods(), "Methods");
            printClasses(c);

        } catch (ClassNotFoundException x) {
            x.printStackTrace();
        }
    }

    private static void printMembers(Member[] mbrs, String s) {
        System.out.format("%s:%n", s);
        for (Member mbr : mbrs) {
            if (mbr instanceof Field)
                System.out.format("  %s%n", ((Field)mbr).toGenericString());
            else if (mbr instanceof Constructor)
                System.out.format("  %s%n", ((Constructor)mbr).toGenericString());
            else if (mbr instanceof Method)
                System.out.format("  %s%n", ((Method)mbr).toGenericString());
        }
        if (mbrs.length == 0)
            System.out.format("  -- No %s --%n", s);
        System.out.format("%n");
    }

    private static void printClasses(Class<?> c) {
        System.out.format("Classes:%n");
        Class<?>[] clss = c.getClasses();
        for (Class<?> cls : clss)
            System.out.format("  %s%n", cls.getCanonicalName());
        if (clss.length == 0)
            System.out.format("  -- No member interfaces, classes, or enums --%n");
        System.out.format("%n");
    }
}
