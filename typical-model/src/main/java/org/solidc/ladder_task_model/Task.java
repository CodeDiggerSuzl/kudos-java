package org.solidc.ladder_task_model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Task {

    /**
     * 任务编号
     */
    private String taskName ;
    /**
     * 阈值
     */
    private Integer threshold;

    @Override
    public String toString() {
        return "Task{" + "任务名=" + taskName + ", 阈值=" + threshold + '}';
    }
}
