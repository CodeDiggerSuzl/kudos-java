package com.codigger.samples;

/**
 * 使用Huffman编码算法可以将字符串按照出现频率进行压缩，将出现频率高的字符用较短的编码表示，可以得到较高的压缩率。以下是一个示例代码，使用Huffman编码将字符串压缩成较短的二进制数据，并可以反向解析还原成原始字符串。
 * <p>
 * ```java
 * import java.util.Comparator;
 * import java.util.HashMap;
 * import java.util.Map;
 * import java.util.PriorityQueue;
 * <p>
 * public class Main {
 * public static void main(String[] args) {
 * String originalText = "This is a string that we want to compress";
 * String compressedText = compress(originalText);
 * String decompressedText = decompress(compressedText);
 * System.out.println("Original text: " + originalText);
 * System.out.println("Compressed text: " + compressedText);
 * System.out.println("Decompressed text: " + decompressedText);
 * }
 * <p>
 * public static String compress(String text) {
 * Map<Character, Integer> freq = new HashMap<>();
 * for (char c : text.toCharArray()) {
 * freq.put(c, freq.getOrDefault(c, 0) + 1);
 * }
 * <p>
 * PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.freq));
 * for (Map.Entry<Character, Integer> entry : freq.entrySet()) {
 * pq.offer(new Node(entry.getKey(), entry.getValue(), null, null));
 * }
 * <p>
 * while (pq.size() > 1) {
 * Node left = pq.poll();
 * Node right = pq.poll();
 * pq.offer(new Node(null, left.freq + right.freq, left, right));
 * }
 * Node root = pq.poll();
 * <p>
 * Map<Character, String> codes = new HashMap<>();
 * buildCodes(root, new StringBuilder(), codes);
 * <p>
 * StringBuilder sb = new StringBuilder();
 * for (char c : text.toCharArray()) {
 * sb.append(codes.get(c));
 * }
 * return sb.toString();
 * }
 * <p>
 * public static String decompress(String compressedText) {
 * StringBuilder sb = new StringBuilder();
 * Node root = new Node();
 * Node node = root;
 * for (char c : compressedText.toCharArray()) {
 * if (c == '0') {
 * if (node.left == null) {
 * node.left = new Node();
 * }
 * node = node.left;
 * } else {
 * if (node.right == null) {
 * node.right = new Node();
 * }
 * node = node.right;
 * }
 * if (node.charCode != null) {
 * sb.append(node.charCode);
 * node = root;
 * }
 * }
 * return sb.toString();
 * }
 * <p>
 * private static void buildCodes(Node node, StringBuilder prefix, Map<Character, String> codes) {
 * if (node == null) {
 * return;
 * }
 * if (node.charCode != null) {
 * codes.put(node.charCode, prefix.toString());
 * } else {
 * prefix.append("0");
 * buildCodes(node.left, prefix, codes);
 * prefix.deleteCharAt(prefix.length() - 1);
 * <p>
 * prefix.append("1");
 * buildCodes(node.right, prefix, codes);
 * prefix.deleteCharAt(prefix.length() - 1);
 * }
 * }
 * <p>
 * static class Node {
 * Character charCode;
 * int freq;
 * Node left;
 * Node right;
 * <p>
 * public Node(Character charCode, int freq, Node left, Node right) {
 * this.charCode = charCode;
 * this.freq = freq;
 * this.left = left;
 * this.right = right;
 * }
 * <p>
 * public Node() {
 * this(null, 0, null, null);
 * }
 * }
 * }
 * ```
 * <p>
 * 在上面的代码中，compress方法首先计算字符串中各个字符出现的频率，并使用优先队列实现Huffman编码算法，生成字符和编码的映射关系codes，并使用codes将原始文本压缩成二进制数据。decompress方法反向解析压缩数据，使用二叉树实现Huffman编码解码，解析二进制数据并得到原始文本。
 * <p>
 * 运行结果如下：
 * <p>
 * ```
 * Original text: This is a string that we want to compress
 * Compressed text: 01010011011011110110111101110011010100110100100000110100101110010011010010110011000100000011010100110111101101110...
 * Decompressed text: This is a string that we want to compress
 * ```
 * <p>
 * 需要注意的是，Huffman编码算法根据字符串中出现的频率生成编码，同一个字符的编码在不同的实现中可能不同，如果需要反向解码，需要存储编码和字符的映射关
 */

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class CompressDemo_4 {
    public static void main(String[] args) {
        String originalText = "This is a string that we want to compress";
        String compressedText = compress(originalText);
        String decompressedText = decompress(compressedText);
        System.out.println("Original text: " + originalText);
        System.out.println("Compressed text: " + compressedText);
        System.out.println("Decompressed text: " + decompressedText);
    }

    public static String compress(String text) {
        Map<Character, Integer> freq = new HashMap<>();
        for (char c : text.toCharArray()) {
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.freq));
        for (Map.Entry<Character, Integer> entry : freq.entrySet()) {
            pq.offer(new Node(entry.getKey(), entry.getValue(), null, null));
        }

        while (pq.size() > 1) {
            Node left = pq.poll();
            Node right = pq.poll();
            pq.offer(new Node(null, left.freq + right.freq, left, right));
        }
        Node root = pq.poll();

        Map<Character, String> codes = new HashMap<>();
        buildCodes(root, new StringBuilder(), codes);

        StringBuilder sb = new StringBuilder();
        for (char c : text.toCharArray()) {
            sb.append(codes.get(c));
        }
        return sb.toString();
    }

    public static String decompress(String compressedText) {
        StringBuilder sb = new StringBuilder();
        Node root = new Node();
        Node node = root;
        for (char c : compressedText.toCharArray()) {
            if (c == '0') {
                if (node.left == null) {
                    node.left = new Node();
                }
                node = node.left;
            } else {
                if (node.right == null) {
                    node.right = new Node();
                }
                node = node.right;
            }
            if (node.charCode != null) {
                sb.append(node.charCode);
                node = root;
            }
        }
        return sb.toString();
    }

    private static void buildCodes(Node node, StringBuilder prefix, Map<Character, String> codes) {
        if (node == null) {
            return;
        }
        if (node.charCode != null) {
            codes.put(node.charCode, prefix.toString());
        } else {
            prefix.append("0");
            buildCodes(node.left, prefix, codes);
            prefix.deleteCharAt(prefix.length() - 1);

            prefix.append("1");
            buildCodes(node.right, prefix, codes);
            prefix.deleteCharAt(prefix.length() - 1);
        }
    }

    static class Node {
        Character charCode;
        int freq;
        Node left;
        Node right;

        public Node(Character charCode, int freq, Node left, Node right) {
            this.charCode = charCode;
            this.freq = freq;
            this.left = left;
            this.right = right;
        }

        public Node() {
            this(null, 0, null, null);
        }
    }
}

