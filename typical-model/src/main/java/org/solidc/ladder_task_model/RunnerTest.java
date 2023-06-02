package org.solidc.ladder_task_model;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.Lists;
import org.junit.Test;

import java.util.List;

@Slf4j
public class RunnerTest {
    @Test
    public void calUpTest() {
        List<Task> taskList = Lists.newArrayList();
        taskList.add(new Task("1⃣️", 1));
        taskList.add(new Task("2⃣️", 2));
        taskList.add(new Task("3⃣️", 4));
        taskList.add(new Task("4⃣️", 7));
        taskList.add(new Task("5⃣️", 9));
        taskList.add(new Task("6⃣️", 13));
        taskList.add(new Task("7⃣️", 16));

        log.info("任务列表:{}", JSON.toJSONString(taskList, true));

        TaskCalculator.calForward(new Action("完单", 1), taskList);
        TaskCalculator.calForward(new Action("买卡", 2), taskList);
        TaskCalculator.calForward(new Action("乘车", 5), taskList);
        TaskCalculator.calForward(new Action("作弊", 2), taskList);
    }

    @Test
    public void calForwardAndReverse() {
        List<Task> taskList = Lists.newArrayList();
        taskList.add(new Task("1⃣️", 1));
        taskList.add(new Task("2⃣️", 2));
        taskList.add(new Task("3⃣️", 4));
        taskList.add(new Task("4⃣️", 7));
        taskList.add(new Task("5⃣️", 9));
        taskList.add(new Task("6⃣️", 13));
        taskList.add(new Task("7⃣️", 16));


        TaskCalculator.calForwardAndReverse(new Action("完单", 1), taskList); // 1 0
        TaskCalculator.calForwardAndReverse(new Action("买卡", 2), taskList); // 3 1
        TaskCalculator.calForwardAndReverse(new Action("作弊", -2), taskList); // 1 1
        TaskCalculator.calForwardAndReverse(new Action("上班", 1), taskList);// 2 1
        TaskCalculator.calForwardAndReverse(new Action("上把", 2), taskList); // 4 2

        TaskCalculator.calForwardAndReverse(new Action("退号", -3), taskList); // 1 2
        TaskCalculator.calForwardAndReverse(new Action("注册", 5), taskList); // 6 2
        TaskCalculator.calForwardAndReverse(new Action("上把", 2), taskList); // 8  3
        TaskCalculator.calForwardAndReverse(new Action("完单", -5), taskList); // 3 3
        TaskCalculator.calForwardAndReverse(new Action("买卡", 12), taskList); // 15 5
        TaskCalculator.calForwardAndReverse(new Action("买卡", 3), taskList); // 18 6





    }

}
