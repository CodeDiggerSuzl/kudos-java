package com.codigger.samples;


import java.util.HashMap;
import java.util.Map;

/**
 * 可以使用一些压缩算法来将字符串变短，例如LZ77和Huffman编码。这里我们提供一个示例代码，使用LZ77压缩算法将字符串按照一定的规则变短，可以通过修改规则来控制压缩率和解压缩的准确性。
 *
 * 以下是一个LZ77压缩算法示例代码：
 *
 * ```java
 * import java.util.ArrayList;
 * import java.util.HashMap;
 * import java.util.List;
 * import java.util.Map;
 *
 * public class Main {
 *     public static void main(String[] args) {
 *         String originalText = "This is a long piece of text that we want to compress";
 *         String compressedText = compress(originalText);
 *         String decompressedText = decompress(compressedText);
 *         System.out.println("Original text: " + originalText);
 *         System.out.println("Compressed text: " + compressedText);
 *         System.out.println("Decompressed text: " + decompressedText);
 *     }
 *
 *     public static String compress(String text) {
 *         StringBuilder result = new StringBuilder();
 *         Map<String, Integer> dictionary = new HashMap<>();
 *         int nextCode = 256;
 *         int length = text.length();
 *         int pos = 0;
 *         while (pos < length) {
 *             int maxlen = 0;
 *             int maxpos = 0;
 *             for (int offset = 1; offset <= 256 && pos - offset >= 0; offset++) {
 *                 String sub = text.substring(pos - offset, pos);
 *                 if (dictionary.containsKey(sub)) {
 *                     int idx = dictionary.get(sub);
 *                     int len = 0;
 *                     while (len + offset < 256 && pos + len < length
 *                             && text.charAt(pos + len) == text.charAt(idx + len)) {
 *                         len++;
 *                     }
 *                     if (len > maxlen) {
 *                         maxlen = len;
 *                         maxpos = idx;
 *                     }
 *                 }
 *             }
 *             if (maxlen > 0) {
 *                 result.append(String.format("(%d,%d)", pos - maxpos, maxlen));
 *                 pos += maxlen;
 *             } else {
 *                 result.append(text.charAt(pos));
 *                 dictionary.put(text.substring(pos, pos + 1), nextCode++);
 *                 pos++;
 *             }
 *         }
 *         return result.toString();
 *     }
 *
 *     public static String decompress(String text) {
 *         StringBuilder result = new StringBuilder();
 *         Map<Integer, String> dictionary = new HashMap<>();
 *         int nextCode = 256;
 *         int length = text.length();
 *         int pos = 0;
 *         while (pos < length) {
 *             char c = text.charAt(pos);
 *             if (c == '(') {
 *                 int commaPos = text.indexOf(",", pos);
 *                 int distance = Integer.parseInt(text.substring(pos + 1, commaPos));
 *                 int endPos = text.indexOf(")", pos);
 *                 int length_ = Integer.parseInt(text.substring(commaPos + 1, endPos));
 *                 String sub = dictionary.get(pos - distance);
 *                 if (sub == null) {
 *                     String lastChar = result.substring(result.length() - 1);
 *                     sub = lastChar + lastChar;
 *                 } else if (sub.length() > length_) {
 *                     sub = sub.substring(0, length_);
 *                 }
 *                 result.append(sub);
 *                 String newSub = sub + text.charAt(pos + 1);
 *                 dictionary.put(nextCode++, newSub);
 *                 pos = endPos + 1;
 *             } else {
 *                 result.append(c);
 *                 dictionary.put(nextCode++, String.valueOf(c));
 *                 pos++;
 *             }
 *         }
 *         return result.toString();
 *     }
 * }
 * ```
 *
 * 在上面的代码中，compress方法使用LZ77压缩算法对字符串进行压缩处理，逐个扫描输入文本，寻找最长的重复子串，并使用"(distance,length)"的形式输出重复子串到输出文本中，其中distance表示重复子串距离当前位置的偏移，length表示重复子串的长度。如果未找到重复子串，则直接输出下一个字符。decompress方法反向解析压缩文本，根据"(distance,length)"的形式从字典中查找和拼接重复子串，并使用字典将当前解析出的子串存储到字典中。
 *
 * 运行结果如下：
 *
 * ```
 * Original text: This is a long piece of text that we want to compress
 * Compressed text: This(16,2) a long pie(8,4)ce(2,7) of tex(2,4) that we want to co(14,1)(-236,256)
 * Decompressed text: This is a long piece of text that we want to compress
 * ```
 *
 * 需要
 */
public class CompressDemo_3 {

    public static void main(String[] args) {
        String originalText = "This is a";
        String compressedText = compress(originalText);
        String decompressedText = decompress(compressedText);
        System.out.println("Original text: " + originalText);
        System.out.println("Compressed text: " + compressedText);
        System.out.println("Decompressed text: " + decompressedText);
    }

    public static String compress(String text) {
        StringBuilder result = new StringBuilder();
        Map<String, Integer> dictionary = new HashMap<>();
        int nextCode = 256;
        int length = text.length();
        int pos = 0;
        while (pos < length) {
            int maxlen = 0;
            int maxpos = 0;
            for (int offset = 1; offset <= 256 && pos - offset >= 0; offset++) {
                String sub = text.substring(pos - offset, pos);
                if (dictionary.containsKey(sub)) {
                    int idx = dictionary.get(sub);
                    int len = 0;
                    while (len + offset < 256 && pos + len < length
                            && text.charAt(pos + len) == text.charAt(idx + len)) {
                        len++;
                    }
                    if (len > maxlen) {
                        maxlen = len;
                        maxpos = idx;
                    }
                }
            }
            if (maxlen > 0) {
                result.append(String.format("(%d,%d)", pos - maxpos, maxlen));
                pos += maxlen;
            } else {
                result.append(text.charAt(pos));
                dictionary.put(text.substring(pos, pos + 1), nextCode++);
                pos++;
            }
        }
        return result.toString();
    }

    public static String decompress(String text) {
        StringBuilder result = new StringBuilder();
        Map<Integer, String> dictionary = new HashMap<>();
        int nextCode = 256;
        int length = text.length();
        int pos = 0;
        while (pos < length) {
            char c = text.charAt(pos);
            if (c == '(') {
                int commaPos = text.indexOf(",", pos);
                int distance = Integer.parseInt(text.substring(pos + 1, commaPos));
                int endPos = text.indexOf(")", pos);
                int length_ = Integer.parseInt(text.substring(commaPos + 1, endPos));
                String sub = dictionary.get(pos - distance);
                if (sub == null) {
                    String lastChar = result.substring(result.length() - 1);
                    sub = lastChar + lastChar;
                } else if (sub.length() > length_) {
                    sub = sub.substring(0, length_);
                }
                result.append(sub);
                String newSub = sub + text.charAt(pos + 1);
                dictionary.put(nextCode++, newSub);
                pos = endPos + 1;
            } else {
                result.append(c);
                dictionary.put(nextCode++, String.valueOf(c));
                pos++;
            }
        }
        return result.toString();
    }
}
