package yuhuayuan.technologyexample.javagarbagerecycle;

/**
 * Created by cl on 2017/3/7.
 */


public class ReferenceTest {
    /** Creates a new instance of ReferenceTest */
    public ReferenceTest() {
    }

    // 消耗大量内存
    public static void drainMemory() {
        String[] array = new String[1024 * 100];
        for(int i = 0; i < 1024 * 100; i++) {
            for(int j = 'a'; j <= 'z'; j++) {
                array[i] += (char)j;
            }
        }
    }
}
