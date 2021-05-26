package kudos.jvm;

public class SystemGcTest {
    public static void main(String[] args) {
        System.gc();
        long l1 = System.nanoTime();
        System.out.println(l1);
        Runtime runtime = Runtime.getRuntime();
        long l = runtime.maxMemory();
        System.out.println(l);
    }
}
