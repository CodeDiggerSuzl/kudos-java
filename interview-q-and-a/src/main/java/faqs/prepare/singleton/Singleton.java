package faqs.prepare.singleton;

/**
 * 单例模式
 *
 * @author suzl
 */
public class Singleton {
    private static volatile Singleton instance = null;

    private Singleton() {

    }

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }


}
