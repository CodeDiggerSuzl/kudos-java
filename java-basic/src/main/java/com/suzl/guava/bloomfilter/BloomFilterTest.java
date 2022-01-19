package com.suzl.guava.bloomfilter;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.nio.charset.Charset;

/**
 * bloom filter in guava.
 * <p></p>
 * <a href="https://mp.weixin.qq.com/s/fNKseef7fPQjUcZHqHs8Zw">原文链接</a>
 *
 * <p>
 * 布隆过滤器算法主要思想就是利用 n 个哈希函数进行 hash 过后，得到不同的哈希值，</br>
 * 根据 hash 映射到数组（这个数组的长度可能会很长很长）的不同的索引位置上，然后将相应的索引位上的值设置为1。
 * <br/>
 * </p>
 * <p>
 * 判断该元素是否出现在集合中，就是利用k个不同的哈希函数计算哈希值，看哈希值对应相应索引位置上面的值是否是1，如果有1个不是1，说明该元素不存在在集合中。
 * <br/></p>
 * <p>
 * 但是也有可能判断元素在集合中，但是元素不在，这个元素所有索引位置上面的1都是别的元素设置的，这就导致一定的误判几率（这就是为什么上面是活可能在一个集合中的根本原因，因为会存在一定的 hash 冲突）。
 * 注意：误判率越低，相应的性能就会越低。
 * </p>
 *
 * @author suzailong
 * @date 2021/11/2 6:30 下午
 */
public class BloomFilterTest {
    public static void main(String[] args) {
        /*创建一个插入对象为一亿，误报率为0.01%的布隆过滤器
         * 不存在一定不存在
         * 存在不一定存在
         *  Funnel 对象：预估的元素个数，误判率
         *  mightContain ：方法判断元素是否存在
         */
        BloomFilter<CharSequence> bloomFilter = BloomFilter.create(Funnels.stringFunnel(Charset.forName("utf-8")),
                100000000, 0.0001);

        bloomFilter.put("");
        bloomFilter.put("磕");
        bloomFilter.put("Redis");
        System.out.println(bloomFilter.mightContain("Redis"));
        System.out.println(bloomFilter.mightContain("Java"));

    }

}
