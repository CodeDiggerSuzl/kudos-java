package org.solidc.ladder_task_model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Action {

    private String actionName;

    private Integer expPoints;

    @Override
    public String toString() {
        return "Action{" + "动作名=" + actionName + ", 动作分值=" + expPoints + '}';
    }
}