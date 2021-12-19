package faqs.real.tanma;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * n 叉树遍历
 */
public class Q2_MultiTree {
    List<Long> result = Lists.newArrayList();

    /**
     * 本质是多叉树的遍历
     *
     * @param head       head
     * @param nameToFind 需要发现的名字
     */
    public void multiBranchSearch(Department head, String nameToFind) {
        if (head == null) {
            return;
        }

        if (nameToFind.equals(head.name)) {
            result.add(head.id);
        }

        for (Department child : head.children) {
            multiBranchSearch(child, nameToFind);
        }
    }
}

class Department {
    Long id;
    String name;
    List<Department> children;
}