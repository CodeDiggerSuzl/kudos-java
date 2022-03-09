package com.codigger.samples;

/**
 * https://segmentfault.com/a/1190000040144424
 *
 * mock private method demo
 *
 * @author suzailong
 * @date 2022/3/9-1:43 下午
 */
public class MockPrivateMethodDemo {

    public boolean isTrue_3() {
        return privateReturnTrue();
    }

    private boolean privateReturnTrue() {
        return true;
    }
}
