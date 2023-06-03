package com.codedigger.spaghetti.bad;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.json.JsonMapper;

import java.util.Date;

public class SpaghettiCase {

    public static void main(String[] args) {

        ShiftOld origin = new ShiftOld(new Date(), new Date(), "origin");
        ShiftOld cpy = origin;
        System.out.println("JSON.toJSONString(origin) = " + JSON.toJSONString(origin));
        origin.setBiz("afternoon");
        System.out.println("JSON.toJSONString(origin) = " + JSON.toJSONString(origin));
        System.out.println("JSON.toJSONString(cpy) = " + JSON.toJSONString(cpy));
        test(origin, cpy);
        test(origin, origin);

    }


    public static void test(ShiftOld one, ShiftOld tow) {
        System.out.println("one.getBiz() = " + one.getBiz());
        System.out.println("tow.getBiz() = " + tow.getBiz());
    }
    public static boolean checkIn(String biz, Integer userId) {
        ShiftOld shift = getShift();
        Integer bizTolerance = getBizTolerance(biz);
        Date curr = new Date();
        if (curr.getTime() > shift.getStartTime().getTime() + bizTolerance * 60) {
            return false;
        } else {
            return true;
        }
    }

    static ShiftOld getShift() {
        return new ShiftOld(new Date(), new Date(), "origin");
    }

    static Integer getBizTolerance(String biz) {
        if ("morning".equals(biz)) {
            return 1;
        } else if ("afternoon".equals(biz)) {
            return 2;
        } else if ("night".equals(biz)) {
            return 3;
        } else {
            return 0;
        }
    }
}
