package com.suzl.basic;

import java.text.MessageFormat;
import java.util.Locale;

/**
 * @author Suz1
 * @date 2021/1/16 12:59 上午
 */
public class InnerClass {
    public static void main(String[] args) {
        double price = 123.5;
        int number = 10;
        Object[] arguments = {price, number};
        MessageFormat mfUS = new MessageFormat("Pay {0,number,currency} for {1} books.", Locale.US);
        System.out.println(mfUS.format(arguments));
        MessageFormat mfZH = new MessageFormat("{1}本书一共{0,number,currency}。", Locale.CHINA);
        System.out.println(mfZH.format(arguments));
    }
}

