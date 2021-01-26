package com.kudos.schedule;

import org.springframework.stereotype.Component;

/**
 * @author Suz1
 * @date 2021/1/16 1:37 上午
 */
@Component
public class ScheduleTaskRunner {
    // @Scheduled(initialDelay = 60_0, fixedRate = 60_0)
    public void checkSystemStatusEveryMinute() {
        //        System.out.println("schedule runner   .|." + new Date());
        System.out.println("h");
    }
}
