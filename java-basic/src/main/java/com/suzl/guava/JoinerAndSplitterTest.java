package com.suzl.guava;

import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import org.junit.Test;

/**
 * <p>joiner and split</p>
 * <a href="https://mp.weixin.qq.com/s/AdSFzWUMVeCfnXpaaz-BIw">link</a>
 *
 * @author suzailong
 * @date 2021/2/2 7:07 下午
 */
public class JoinerAndSplitterTest {
    private static final Joiner      JOINER_FOR_NULL  = Joiner.on(",").useForNull(")-(");
    private static final Joiner      JOINER_SKIP_NULL = Joiner.on(",").skipNulls();
    private static final CharMatcher ANY_MATCHER      = CharMatcher.any();

    @Test
    public void joinerTest() {
        // replace null
        String join = JOINER_FOR_NULL.join(Lists.newArrayList("1", "2", null));
        System.out.println("join = " + join);
        // skip null
        String nullPass = JOINER_SKIP_NULL.join(Lists.newArrayList("a", null, "b"));
        System.out.println("nullPass = " + nullPass);
    }

    @Test
    public void splitterTest() {
        Splitter splitter = Splitter.on(",").trimResults().omitEmptyStrings();
        Iterable<String> split = splitter.split("a,4,,d,v");
        for (String s : split) {
            System.out.println("s = " + s);
        }
    }

    @Test
    public void matcherTest() {
        CharMatcher digit = CharMatcher.digit();

    }
}
