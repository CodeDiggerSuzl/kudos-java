package kudos.jvm.oom;

import java.util.ArrayList;

/**
 * @author suzl
 */
public class HeapOom {
    public static void main(String[] args) {
        long cnt = 0;
        ArrayList<Object> list = new ArrayList<>();
        while (true) {
            list.add(new Object());
            System.out.println(++cnt);
        }
    }
}
