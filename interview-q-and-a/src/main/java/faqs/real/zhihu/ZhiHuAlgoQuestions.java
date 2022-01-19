package faqs.real.zhihu;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 知乎面试三道算法题
 *
 * @author suzl
 * @date 2021/12/30 3:21 PM
 */
public class ZhiHuAlgoQuestions {

    public static void main(String[] args) {
        ArrayList<Integer> input = Lists.newArrayList(1, 3, 2, 1, 2, 3, 1, 2, 3);
        List<Integer> result = groupSort(input);
        System.out.println(JSON.toJSONString(result));

    }

    public static List<Integer> groupSort(List<Integer> input) {
        int flag = 1; // 哨兵
        for (int i = 0; i < input.size(); i++) {
            Integer curr = input.get(i);
            for (int j = i + 1; j < input.size(); j++) {
                Integer val = input.get(j);
                if (Objects.equals(val, flag)) {
                    input.set(i, val);
                    input.set(j, curr);
                    break;
                }
            }
            // 哨兵节点做循环
            if (++flag == 4) {
                flag = 1;
            }
        }
        return input;
    }

    /**
     * Leet-code 120
     * <a href="https://leetcode-cn.com/problems/triangle/"> link</a>
     */
    public int getShortestPath(List<List<Integer>> triangle) {

        Integer shortest = Integer.MAX_VALUE;

        return 0;
    }

    /**
     * 求一个树的最小深度
     * <a href="https://leetcode-cn.com/problems/minimum-depth-of-binary-tree/submissions/">link</a>
     */
    public int minDepth(Node head) {
        if (head == null) {
            return 0;
        }
        int left = minDepth(head.left);
        int right = minDepth(head.right);
        if (left == 0 || right == 0) {
            return left + right + 1;
        }
        return left >= right ? right + 1 : left + 1;
    }

    static class Node {
        int val;
        Node left;
        Node right;
    }
}
