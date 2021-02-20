package com.suzl.guava;

import com.google.common.primitives.Ints;
import com.suzl.utils.PrintUtils;
import org.junit.Test;

import java.util.List;

/**
 * <p>guava 对基本类型的加强</p>
 *
 * @author suzailong
 * @date 2021/2/2 9:18 下午
 */
public class PrimitivesTest {
    @Test
    public void intsTest() {
        List<Integer> integerList = Ints.asList(1, 2, 3);
        PrintUtils.pJF(integerList);
        String joinResult = Ints.join("-", 1, 2, 3, 4);
        PrintUtils.pJF(joinResult);

        // Ints list to array
        int max = Ints.max(Ints.toArray(integerList));
        PrintUtils.pJ(max);
        // contains
        boolean contains = Ints.contains(Ints.toArray(integerList), 5);
        PrintUtils.pJ(contains);
    }

    @Test
    public void boolTest() {
    }
}
