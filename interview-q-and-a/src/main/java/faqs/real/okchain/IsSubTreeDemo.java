package faqs.real.okchain;

/**
 * 欧科云链的面试题<br/>
 * 判断一个数是不是另一个二叉树的子树
 * leet-code
 */
public class IsSubTreeDemo {
}

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public boolean isSubtree(TreeNode root, TreeNode subNode) {
        if (subNode == null) return true;   // subNode 为 null 一定都是 true
        if (root == null) return false;  // 这里 subNode 一定不为 null, 只要 root 为 null，肯定是 false
        // 递归判断 root 的子树是否和 subNode 开头的数是否为同一个树
        return isSubtree(root.left, subNode) || isSubtree(root.right, subNode) || isSameTree(root, subNode);
    }

    /**
     * 判断两棵树是否相同
     */
    public boolean isSameTree(TreeNode s, TreeNode t) {
        if (s == null && t == null) return true;
        if (s == null || t == null) return false;
        if (s.val != t.val) return false;
        return isSameTree(s.left, t.left) && isSameTree(s.right, t.right);
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}
