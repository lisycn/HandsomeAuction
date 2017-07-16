package com.handsome.auction.util;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * by wangrongjun on 2017/7/12.
 */
public class DateUtil {

    public static String toDateTimeText(Date date) {
        SimpleDateFormat sdf = (SimpleDateFormat) SimpleDateFormat.getDateTimeInstance();
        sdf.applyPattern("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    /**
     * @param dateText 格式如：yyyy-MM-dd HH:mm:ss 或 yyyy-MM-dd
     */
    public static Date toDate(String dateText) throws DateTextSyntaxErrorException {
        Calendar calendar = Calendar.getInstance();

        if (!dateText.matches("[\\d]+-[\\d]+-[\\d]+") &&
                !dateText.matches("[\\d]+-[\\d]+-[\\d]+ [\\d]+:[\\d]+:[\\d]+")) {
            throw new DateTextSyntaxErrorException(dateText);
        }

        try {
            if (dateText.contains(" ")) {
                String[] strings = dateText.split(" ");
                String[] dateList = strings[0].split("-");
                int year = Integer.parseInt(dateList[0]);
                int month = Integer.parseInt(dateList[1]);
                int day = Integer.parseInt(dateList[2]);
                String[] timeList = strings[1].split(":");
                int hour = Integer.parseInt(timeList[0]);
                int minute = Integer.parseInt(timeList[1]);
                int second = Integer.parseInt(timeList[2]);
                calendar.set(year, month - 1, day, hour, minute, second);
            } else {
                String[] dateList = dateText.split("-");
                int year = Integer.parseInt(dateList[0]);
                int month = Integer.parseInt(dateList[1]);
                int day = Integer.parseInt(dateList[2]);
                calendar.set(year, month - 1, day, 0, 0, 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new DateTextSyntaxErrorException(dateText);
        }
        return calendar.getTime();
    }

    public static class DateTextSyntaxErrorException extends Exception {
        DateTextSyntaxErrorException(String date) {
            super(date);
        }
    }

    @Test
    public void testToDate() throws DateTextSyntaxErrorException {
        assertEquals("2015-12-03 12:34:10", toDateTimeText(toDate("2015-12-03 12:34:10")));//正常
        assertEquals("2017-02-03 00:54:30", toDateTimeText(toDate("2017-02-03 00:54:30")));//正常

        try {
            toDateTimeText(toDate("2015-01-03_12:34:10"));//格式错误，应该抛异常
            fail("should throw DateTextSyntaxErrorException, actually doesn't throw");
        } catch (DateTextSyntaxErrorException e) {
            e.printStackTrace();
        }
        try {
            toDateTimeText(toDate("2015-01-a3 12:34:10"));//格式错误，应该抛异常
            fail("should throw DateTextSyntaxErrorException, actually doesn't throw");
        } catch (DateTextSyntaxErrorException e) {
            e.printStackTrace();
        }
    }

}
