package com.handsome.auction.test;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * by wangrongjun on 2017/7/13.
 */
public class Test {

    public static void main(String[] a) {
        String str = DecimalFormat.getNumberInstance().format(1245600000);
        String currecy = NumberFormat.getCurrencyInstance().format(1245600000);
        System.out.println(currecy);
        System.out.println(str);
    }

}
