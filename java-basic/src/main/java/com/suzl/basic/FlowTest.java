package com.suzl.basic;

import org.junit.Test;

import java.util.Random;

public class FlowTest {

    public Boolean loop(int k) {
        for (int i = 0; i < k; i++) {
            System.out.println(i);
            int i1 = new Random().nextInt(k);
            if (i1 == i) {
                return true;
            }
            continue;
        }
        return false;
    }

    @Test
    public void testFlow() {
        Boolean loop = this.loop(4);
        System.out.println(loop);
    }
}
