package com.bmw.boss.common.util;

import org.apache.commons.lang.time.DateUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 时间日期处理工具类
 * @author yd
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class TimeUtils {
    private static final Calendar calendar = new GregorianCalendar();
    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    /**
     * 返回当前时间的规定字符串格式
     * @return
     */
    public static String formatCurrentTimeMillisToString() {
        Date currentTime = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String currentTimeStr = sdf.format(currentTime);
        return currentTimeStr;
    }

    /**
     * 获取i天后指定字符串日期
     * @param i
     * @return
     */
    public static String getDateByDay(int i) {//获取前后日期 i为正数 向后推迟i天，负数时向前提前i天
        Date dat = null;
        Calendar cd = Calendar.getInstance();
        cd.add(Calendar.DATE, i);
        dat = cd.getTime();
        SimpleDateFormat dformat = new SimpleDateFormat("yyyyMMdd");
        return dformat.format(dat);
    }

    /**
     * 获取i月后指定字符串日期
     * @param i
     * @return
     */
    public static String getDateByMonth(int i) {//获取前后日期 i为正数 向后推迟i个月，负数时向前提前i个月
        Date dat = null;
        Calendar cd = Calendar.getInstance();
        cd.add(Calendar.MONTH, i);
        dat = cd.getTime();
        SimpleDateFormat dformat = new SimpleDateFormat("yyyyMMdd");
        return dformat.format(dat);
    }

    /**
     * 判断当前时间是否在某段时间内
     * @param start
     * @param end
     * @return
     */
    public static boolean isInBetweenTimeByNow(Date start, Date end) {
        boolean flag = false;
        Date now = new Date();
        if(now.getTime() >= start.getTime() && now.getTime() <= end.getTime()) {
            flag = true;
        }
        return flag;
    }

    /**
     * 判断某个时间是否在某段时间内
     * @param d
     * @param start
     * @param end
     * @return
     */
    public static boolean isInBetweenTimeByDate(Date d, Date start, Date end) {
        boolean flag = false;
        if(d.getTime() >= start.getTime() && d.getTime() <= end.getTime()) {
            flag = true;
        }
        return flag;
    }

    /**
     * 返回yyyy/MM/dd字符串格式的时间
     * @param date
     * @return
     */
    public static String getDateFormat10ByDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        return dateFormat.format(date);
    }

    /**
     * 返回yyyy-MM-dd字符串格式的时间
     * @param date
     * @return
     */
    public static String getDateFormat10ByDate1(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }

    /**
     * 获取当天的最开始时间(0点)
     * @return
     */
    public static Date getTodayZeroTime() {
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String nowStr = sdf.format(now)+" 00:00:00";
        SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date zero = null;
        try {
            zero = sdft.parse(nowStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return zero;
    }

    /**
     * 获取当天的最开始时间(0点)至30天后的时间
     * @return
     */
    public static Date getMonthTimeByTodayZero() {
        Date tZero = getTodayZeroTime();
        Calendar cal = Calendar.getInstance();
        cal.setTime(tZero);
        cal.add(Calendar.DATE, 30);
        return cal.getTime();
    }

    /**
     * 获取当天的最开始时间(0点)至365天后的时间
     * @return
     */
    public static Date getYearTimeByTodayZero() {
        Date tZero = getTodayZeroTime();
        Calendar cal = Calendar.getInstance();
        cal.setTime(tZero);
        cal.add(Calendar.DATE, 365);
        return cal.getTime();
    }

    /**
     * 获取某个时间至30天后的时间
     * @param d
     * @return
     */
    public static Date getMonthTimeByDate(Date d) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.add(Calendar.DATE, 30);
        return cal.getTime();
    }

    /**
     * 获取当天的最开始时间(0点)至365天后的时间
     * @param d
     * @return
     */
    public static Date getYearTimeByDate(Date d) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.add(Calendar.DATE, 365);
        return cal.getTime();
    }

    /**
     * 比较时间先后,返回较大的日期
     * @param day1
     * @param day2
     * @return
     */
    public static Date getLargerDate(Date day1, Date day2) {
        Calendar c1 = Calendar.getInstance();
        c1.setTime(day1);
        Calendar c2 = Calendar.getInstance();
        c2.setTime(day2);
        if(c1.before(c2)) {
            return day2;
        }else {
            return day1;
        }
    }

    /**
     * 判断时间是否大于等于当前时间
     * @param d
     * @return
     */
    public static boolean isLargerNow(Date d) {
        Date now = new Date();
        if(d.getTime() >= now.getTime()) {
            return true;
        }else {
            return false;
        }
    }

    /**
     *
     * 功能描述:
     *  判断是否是今天
     * @param date
     * @return
     * Author:
     * Date:     2016年12月13日13:44:56
     */
    public static boolean isToday(Date date){
        return DateUtils.isSameDay(date, new Date());
    }

    public static boolean isNotToday(Date date){
        return !DateUtils.isSameDay(date, new Date());
    }

    /**
     * 判断日期是否大于当前日期
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isLargerNowDay(Date date1,Date date2){
        SimpleDateFormat sd = new SimpleDateFormat("yyyyMMdd");
        String time1 = sd.format(date1);
        String time2 = sd.format(date2);
        try {
            Date dateOne = sd.parse(time1);
            Date dateTwo = sd.parse(time2);
            long  s1 = dateOne.getTime() ;//时间的毫秒
            long s2 = dateTwo.getTime() ;
            if(s1>s2){
                return true;
            }else{
                return false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static Date getDateByDay(Date date1,int i) {//获取前后日期 i为正数 向后推迟i天，负数时向前提前i天
        Date dat = null;
        Calendar cd = Calendar.getInstance();
        cd.setTimeInMillis(date1.getTime());
        cd.add(Calendar.DATE, i);
        dat = cd.getTime();
        return dat;
    }

    /**
     * 格式化日期到年
     * @param date
     * @return
     */
    public static String getYearFormatByDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
        return dateFormat.format(date);
    }

    /**
     * 格式化日期到月
     * @param date
     * @return
     */
    public static String getMonthFormatByDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM");
        return dateFormat.format(date);
    }


    /**
     *
     * 功能描述:
     *  获取两个日期之间的日期差
     * @param smdate
     * @param bdate
     * @return
     * @throws ParseException
     * Author:   jgYang
     * Date:     2016年10月31日 下午6:03:44
     */
    public static int daysBetween(Date smdate, Date bdate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        smdate = sdf.parse(sdf.format(smdate));
        bdate = sdf.parse(sdf.format(bdate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);
        return Integer.parseInt(String.valueOf(between_days));
    }



    /**
     * 功能描述:
     * 当前日期+1天
     * @param date
     * @return
     * Author:   jgYang
     * Date:     2016年12月14日 下午2:38:10
     */
    public static Date calendarAddDate(Date date){
        calendar.setTime(date);
        calendar.add(calendar.DATE,1);//把日期往后增加一天.整数往后推,负数往前移动
        date=calendar.getTime();
        return date;
    }

    /**
     * 功能描述:
     * 两个日期比较大小
     * @param startDate 起始时间
     * @param endDate 结束时间
     * @return
     * Author:   jgYang
     * Date:     2016年12月14日 下午2:43:49
     */
    public static boolean compareDate(Date startDate,Date endDate){
        return endDate.getTime() > startDate.getTime();
    }

    /**
     * 功能描述:
     * 两个日期比较大小（日期格式：yyyy-MM-dd hh:mm:ss）
     * @param startDateString 起始时间
     * @param endDateString 结束时间
     * @return
     * Author:   jgYang
     * Date:     2016年12月14日 下午2:43:49
     * @throws ParseException
     */
    public static boolean compareDateByString(String startDateString,String endDateString) throws ParseException{
        return compareDate(dateFormat.parse(startDateString),dateFormat.parse(endDateString));
    }


    public static Date getDateByFormatString(String dateString) throws ParseException{
        Date date = dateFormat.parse(dateString);
        return date;
    }

    public static void main(String[] args) throws ParseException{
//		String x = getMonthFormatByDate(new Date());
//		System.out.println(x);

        Date d = getDateByDay(new Date(),7);
        System.out.println(d);

        boolean c = isLargerNowDay(new Date(),d);
        System.out.println(c);

    }
}