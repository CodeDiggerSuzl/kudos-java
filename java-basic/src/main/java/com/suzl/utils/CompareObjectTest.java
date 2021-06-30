package com.suzl.utils;

import com.github.dadiyang.equator.Equator;
import com.github.dadiyang.equator.FieldInfo;
import com.github.dadiyang.equator.GetterBaseEquator;
import com.suzl.pojo.Coin;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>对比</p>
 *
 * @author suzailong
 * @date 2021/5/21 1:36 下午
 */
public class CompareObjectTest {
    @Test
    public void sameTest() {
        Coin bitCoin = new Coin("bitCoin", 1);
        Coin dollar = new Coin("$", 1);
        List<Coin> coins = new ArrayList<>();
        coins.add(bitCoin);

        List<Coin> d = new ArrayList<>();
        coins.add(dollar);
        Equator equator = new GetterBaseEquator();
        List<FieldInfo> diff = equator.getDiffFields(coins, d);
        if (diff.size() > 0) {
            for (FieldInfo info : diff) {
                System.out.printf("filedName=%s \t\t oldVal=>%s \t newVal => %s", info.getFieldName(), info.getFirstVal(), info.getSecondVal());
                System.out.println();
            }
        }
    }


}
