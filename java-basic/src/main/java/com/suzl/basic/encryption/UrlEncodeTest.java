package com.suzl.basic.encryption;

import org.junit.Test;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * @author Suz1
 * @date 2020/8/15 4:49 下午
 */
public class UrlEncodeTest {

    /**
     * URL编码
     * URL编码是浏览器发送数据给服务器时使用的编码，它通常附加在URL的参数部分，例如：
     * <p>
     * https://www.baidu.com/s?wd=%E4%B8%AD%E6%96%87
     * <p>
     * 之所以需要URL编码，是因为出于兼容性考虑，很多服务器只识别ASCII字符。但如果URL中包含中文、日文这些非ASCII字符怎么办？不要紧，URL编码有一套规则：
     * <p>
     * 如果字符是A~Z，a~z，0~9以及-、_、.、*，则保持不变；
     * 如果是其他字符，先转换为UTF-8编码，然后对每个字节以%XX表示。
     */
    @Test
    public void urlEncodeTest() {
        String encoded = URLEncoder.encode("中文!", StandardCharsets.UTF_8);
        System.out.println(encoded);

        String decoded = URLDecoder.decode("%E4%B8%AD%E6%96%87%21", StandardCharsets.UTF_8);
        System.out.println("decoded = " + decoded);
    }


}
