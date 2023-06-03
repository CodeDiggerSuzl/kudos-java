package com.codedigger.spaghetti.bad;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ShiftOld {
    // 开始时间
    private Date startTime;
    // 结束时间
    private Date endTime;

    private String biz;
}
