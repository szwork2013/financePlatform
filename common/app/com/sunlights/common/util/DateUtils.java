package com.sunlights.common.util;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by yuan on 10/8/14.
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {
    private DateUtils() {
    }


    public static Date getNow() {
        return new Date();
    }

    // Refactor method name, but can not remove old method(getNow)
    public static Date currentDateTime() {
        return getNow();
    }

    /**
     *
     * @param date
     * @param format
     * @return
     *
     */
    public static String dateToString(Date date, String format) {
        if (date == null){
            return "";
        }
        return new SimpleDateFormat(format).format(date);
    }


    /**
     *
     * default format patten is: yyyy-MM-dd.
     *
     * @param date
     * @return
     *
     */
    public static String dateToString(Date date) {
        return dateToString(date, PATTEN_DATE_FORMAT_DEFAULT);
    }


    /**
     *
     * @param dateString
     * @param pattern
     * @return
     *
     */
    public static Date stringToDate(String dateString, String pattern) {
        SimpleDateFormat df = new SimpleDateFormat();
        df.applyPattern(pattern);
        return df.parse(dateString, new ParsePosition(0));
    }


    /**
     *
     * @param dateString
     * @return
     *
     */
    public static Date stringToDate(String dateString) {
        return stringToDate(dateString, PATTEN_DATE_FORMAT_DEFAULT);
    }


    /**
     *
     * @param date
     * @return
     */
    public static boolean isToday(Date date) {
        Calendar now = Calendar.getInstance();
        Calendar then = Calendar.getInstance();


        then.setTime(date);
        return isSameDay(now, then);
    }


    /**
     *
     * @param calendar
     * @return
     */
    public static boolean isToday(Calendar calendar) {
        Calendar now = Calendar.getInstance();
        return isSameDay(now, calendar);
    }


    /**
     *
     * @param dayOfWeek
     * @return
     */
    public static String getDayOfWeekStr(int dayOfWeek) {
        String result = "";
        switch (dayOfWeek) {
            case Calendar.MONDAY:
                result = "Monday";
                break;
            case Calendar.TUESDAY:
                result = "Tuesday";
                break;
            case Calendar.WEDNESDAY:
                result = "Wednesday";
                break;
            case Calendar.THURSDAY:
                result = "Thursday";
                break;
            case Calendar.FRIDAY:
                result = "Friday";
                break;
            case Calendar.SATURDAY:
                result = "Saturday";
                break;
            case Calendar.SUNDAY:
                result = "Sunday";
                break;

            default:
                break;
        }
        return result;
    }


    /**
     *
     * @param now
     * @param then
     * @return
     *
     */
    public static long dateDiff(Date now, Date then) {
        if (now == null) {
            now = get("00000000000000000");
        }
        if (then == null) {
            then = get("00000000000000000");
        }
        return now.getTime() - then.getTime();
    }


    /**
     *
     * @param now
     * @param then
     * @return
     */
    public static int daysDiff(Date now, Date then) {
        return Integer.parseInt(String.valueOf(Math.abs(dateDiff(now, then) / MILLISECOND_DAY)));
    }


    /**
     *
     * @param now
     * @param then
     * @return
     */
    @SuppressWarnings("deprecation")
    public static int monthsDiff(Date now, Date then) {
        return Math.abs(now.getMonth() - then.getMonth());
    }


    /**
     *
     * @param date
     * @param days
     * @return
     */
    public static Date getDaysAfterOrBefore(Date date, int days) {
        Calendar now = Calendar.getInstance();
        now.setTime(date);

        now.set(Calendar.DATE, now.get(Calendar.DATE) + days);
        return now.getTime();
    }

    /**
     *
     * @param date
     * @return
     *
     */
    private static Date get(String date) {
        String year = date.substring(0, 4);
        String month = date.substring(4, 6);
        String day = date.substring(6, 8);
        String hour = date.substring(8, 10);
        String minute = date.substring(10, 12);
        String second = date.substring(12, 14);
        String millisecond = date.substring(14, 17);

        Calendar c = Calendar.getInstance();
        c.set(Integer.parseInt(year), Integer.parseInt(month) - 1, Integer.parseInt(day), Integer.parseInt(hour),
                Integer.parseInt(minute), Integer.parseInt(second));
        c.set(Calendar.MILLISECOND, Integer.parseInt(millisecond));
        return c.getTime();
    }

    /**
     *
     * get weekday in week.
     *
     * @param appointedDate
     *                      user appointed date
     * @param weekday
     *                      user appointed weekday
     *
     * @return
     *                      date of weekday
     *
     */
    public static Date getWeekdayInWeek(Date appointedDate, int weekday) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(appointedDate);

        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        calendar.add(Calendar.DAY_OF_MONTH, weekday - dayOfWeek);

        return calendar.getTime();
    }

    /**
     *
     * @param date
     * @return
     */
    public static Date getPlainDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        return cal.getTime();
    }

    /**
     *
     * @param date
     * @return
     */
    public static Date getPlainTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.YEAR, 1970);
        cal.set(Calendar.MONTH, 0);
        cal.set(Calendar.DATE, 1);

        return cal.getTime();
    }

    /**
     * 日期加一天
     * @param date
     * @return
     */
    public static Date addOneDay(Date date){
        Calendar c =  Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_YEAR, 1);
        return c.getTime();
    }

    /**
     * 日期减一天
     * @param date
     * @return
     */
    public static Date cutOneDay(Date date){
        Calendar c =  Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DATE, c.get(Calendar.DATE) - 1);
        return c.getTime();
    }

    /**
     *
     * <p>Description: </p>
     * @return
     */
    public static int getYear(){
        Calendar cl = Calendar.getInstance();
        return cl.get(Calendar.YEAR);
    }

    public static final long MILLISECOND_DAY = 24L * 60L * 60L * 1000L;

    public static final String PATTEN_DATE_FORMAT_DEFAULT = "yyyy-MM-dd";

    public static final String PATTEN_DATE_FORMAT_SLASH = "yyyy/MM/dd";

    public static final String PATTEN_DATE_FORMAT_DATETIME = "yyyy-MM-dd HH:mm:ss";

    public static final String PATTEN_DATE_FORMAT_DATETIME_PLUS = "yyyy-MM-dd HH:mm:ss:SSS";


    public static String timestamp2String(Timestamp timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String str = sdf.format(timestamp);
        return str;
    }

    /**
     * 获得date日期前/后min分钟的时间
     * @param date
     * @param min
     * @return
     */
    public static Timestamp getNMin(Timestamp date, long min) {
        if (date == null) {
            return null;
        }
        long fTime = date.getTime() + min * 60000;
        return new Timestamp(fTime);
    }

    /**
     * 获取2个日期之间的小时数，向上取整
     */
    public static long getBetweenHours(Timestamp endTime, Timestamp startTime){
        double time = (endTime.getTime() - startTime.getTime())/(double)(1000 * 60);
        BigDecimal bigtime = new BigDecimal(time).setScale(0, BigDecimal.ROUND_UP);
        return Long.valueOf(bigtime.toString());
    }

    public static Timestamp getCurrentTime() {
        return new Timestamp(System.currentTimeMillis());
    }
}
