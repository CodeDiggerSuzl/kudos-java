package faqs.real.tanma;


import java.util.PriorityQueue;

public class Q4_QueueSort {
    // * 1000 个有序数组, 每个数组至少有 1 个元素, 找出最大的 10 个元素
    PriorityQueue<Integer> queue = new PriorityQueue<>(3);

    public static void main(String[] args) {
    }

    void addToQueue(int i) {
        // 假定数组的头是最大元素
        // int largestInTheArr = arr[0];
        if (queue.size() < 3) {
            queue.add(i);
        } else {
            Integer poll = queue.poll();
            System.out.println(poll);
            queue.add(i);
        }
    }
}
