package org.solidc.ladder_task_model;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class TaskCalculator {

    private static int __finExpPoint__ = 0;

    /**
     * this can only be used for forward calculation. can't process reverse calculation
     *
     * @param action   action with exp points
     * @param taskList config task list
     */
    public static void calForward(Action action, List<Task> taskList) {
        Integer finPtr = findCurrPtr(taskList, __finExpPoint__);
        if (finPtr >= taskList.size() - 1) {
            log.info("已经完成了所有任务,无法再完成动作:{}", action);
            return;
        }
        log.info("完成动作前:{},现有经验值:{},现在的指针:{}", action, __finExpPoint__, finPtr);
        Integer actionExp = action.getExpPoints();
        Integer prePtr = findCurrPtr(taskList, __finExpPoint__);
        Integer currPtr = findCurrPtr(taskList, __finExpPoint__ + actionExp);
        List<Task> partTaskList = getPartActRuleListByRangeIdx(taskList, prePtr, currPtr);
        __finExpPoint__ = __finExpPoint__ + actionExp;
        log.info("完成动作后:{},现有经验值:{},现在的指针:{},完成了{}个任务", action, __finExpPoint__, currPtr, partTaskList.size());
        log.info("完成的任务列表:{}", partTaskList);
        log.info("--------------------------------------------------");
    }


    private static int finMaxPtr = -1;
    private static int validExp = 0;

    private static int maxExp = 0;

    /**
     * Go up and also can go down
     *
     * @param action   action with expPoints and actionName
     * @param taskList all config task list
     */
    public static void calForwardAndReverse(Action action, List<Task> taskList) {
        Integer finPtr = findCurrPtr(taskList, validExp);
        int taskMax = taskList.size() - 1;
        if (finPtr >= taskMax) {
            log.info("已经完成了所有任务,无法再完成动作:{}", action);
            return;
        }
        if (finMaxPtr >= taskMax) {
            log.info("已经完成了所有任务,无法再完成动作:{}", action);
        }

        log.info("完成动作前:{},现有经验值:{},现在的指针:{},完成最大指针:{}", action, validExp, finPtr, finMaxPtr);
        Integer expPoints = action.getExpPoints();
        int expAfter = validExp + expPoints;
        if (expAfter < 0 && validExp <= 0) {
            return;
        }
        if (expPoints < 0) {
            validExp = Math.max(expAfter, 0); // 减分,this Math.max() is not necessary,but for special cases.
            log.info("分值小于 0 的情况: 完成动作后:{},现有经验值:{},完成最大指针:{}", action, validExp, finMaxPtr);
            log.info("--------------------------------------------------");
            return;
        }
        Integer currPtr = findCurrPtr(taskList, expAfter);
        if (currPtr <= finMaxPtr) {
            validExp = expAfter; // 加分
            maxExp = maxExp + expPoints;
            return;
        }
        List<Task> partTaskList = getPartActRuleListByRangeIdx(taskList, finMaxPtr, currPtr);
        if (CollectionUtils.isEmpty(partTaskList)) {
            log.info("不发奖励");
        } else {
            log.info("ues maxFinPtr 发奖:{}", partTaskList.stream().map(Task::getTaskName).collect(Collectors.toList()));
        }
        // ❌❌❌ the next below is not right,cause the decrement might lower than the mixFinPtr ❌❌❌
          List<Task> usingFinPtrNotMaxFinPtr = getPartActRuleListByRangeIdx(taskList, finPtr, currPtr);
          if (CollectionUtils.isEmpty(usingFinPtrNotMaxFinPtr)) {
              log.info("不发奖励");
          } else {
              log.info("use finPtr 发奖:{}", usingFinPtrNotMaxFinPtr.stream().map(Task::getTaskName).collect(Collectors.toList()));
          }
        validExp = validExp + expPoints;
        finMaxPtr = currPtr;
        maxExp = maxExp + expPoints;
        log.info("完成动后:{},现有经验值:{},完成最大指针:{},maxExp={}", action, validExp, finMaxPtr, maxExp);
        log.info("--------------------------------------------------");
    }


    /**
     * 根据计算完成了哪一个节点
     *
     * @param taskList     all task config list
     * @param currExpPoint currExpPoint
     * @return currPtr, -1 if not found
     */
    public static Integer findCurrPtr(final List<Task> taskList, final Integer currExpPoint) {
        if (taskList == null || currExpPoint == null || currExpPoint < 0 || taskList.isEmpty()) {
            throw new RuntimeException("findCurrPtr check your input ...");
        }

        List<Task> cpyList = Lists.newArrayList(taskList);
        cpyList.sort(Comparator.comparing(Task::getThreshold));
        for (int i = 0; i < cpyList.size(); i++) {
            if (currExpPoint < cpyList.get(i).getThreshold()) {
                if (i == 0) {
                    return -1;
                }
                return i - 1;
            }
            if (i == cpyList.size() - 1) {
                return i;
            }
        }
        return -1;
    }

    /**
     * find tasks finished by pre and curr
     *
     * @param taskRuleList all task config list
     * @param start        start
     * @param end          end
     * @return part task list
     */
    public static List<Task> getPartActRuleListByRangeIdx(final List<Task> taskRuleList, final int start, final Integer end) {
        // log.info("getPartActRuleList, start={},end={}", start, end);
        if (start > end) {
            throw new RuntimeException("start should smaller than end");
        }
        if (start < 0 && end < 0) {
            return Lists.newArrayList();
        }
        if (start == end) {
            return Lists.newArrayList();
        }

        List<Task> cpyList = Lists.newArrayList(taskRuleList);
        cpyList.sort(Comparator.comparing(Task::getThreshold));
        if (start < 0) {
            return cpyList.subList(0, end + 1);
        }
        // last method findCurrPtr return max value is list.size-1, so it will not throw IndexOutOfBoundsException
        return cpyList.subList(start + 1, end + 1);
    }

}
