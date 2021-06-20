package kudos.jvm.ch3gc;

import org.junit.Test;

public class ReferenceCountingTest {
    private static final int _1MB = 1024 * 1024;
    public Object instance = null;
    private byte[] bigSize = new byte[2 * _1MB];

    public static void testGc() {
        ReferenceCountingTest objA = new ReferenceCountingTest();
        ReferenceCountingTest objB = new ReferenceCountingTest();
        objA.instance = objB;
        objB.instance = objA;

        objA = null;
        objB = null;
        System.gc();
    }

    @Test
    public void test() {
        testGc();
    }
}
