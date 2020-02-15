package org.cloud.note.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * * -------       Calendar.***    --------------
 * <p>
 * 将日期加1，这通过cal1.add(Calendar.DATE,1)可以实现将天数加一
 * cal1.add(Calendar.MONTH,1)将月数加一  如果 -1 则为减
 * cal1.add(Calendar.YEAR,1）将年加一
 * <p>
 * <p>
 * -------       DAY_OF_***    --------------
 * DAY_OF_MONTH的主要作用是cal.get(DAY_OF_MONTH)，用来获得这一天在是这个月的第多少天
 * Calendar.DAY_OF_YEAR的主要作用是cal.get(DAY_OF_YEAR)，用来获得这一天在是这个年的第多少天。
 * 同样，还有DAY_OF_WEEK，用来获得当前日期是一周的第几天
 *
 * @author wangqianlong
 * @create 2019-07-30 9:54
 */

public class DateUtils {
    static ThreadLocal<Calendar> calendarThreadLocal = new ThreadLocal<Calendar>() {
        @Override
        protected Calendar initialValue() {
            return Calendar.getInstance();
        }
    };

    /**
     * @param date 一月之后时间
     * @return
     */
    public static Date DataAddMonth(Date date) {
        Calendar calendar = calendarThreadLocal.get();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);
        Date date1 = calendar.getTime();
        return date1;
    }


    /**
     * @param date 一周以后时间
     * @return
     */
    public static Date DataAddOneWeek(Date date) {
        Calendar calendar = calendarThreadLocal.get();
        calendar.setTime(date);
        //一周之前
        // calendar.add(Calendar.WEEK_OF_MONTH, -1);
        calendar.add(Calendar.WEEK_OF_MONTH, 1);
        Date date1 = calendar.getTime();
        return date1;
    }

    /**
     * @param date 一天以前
     * @return
     */
    public static Date yesterDay(Date date) {
        Calendar calendar = calendarThreadLocal.get();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -1);
        //calendar.add(Calendar.DAY_OF_MONTH, -1);
        //calendar.add(Calendar.DAY_OF_YEAR,-1);
        //calendar.add(Calendar.DAY_OF_WEEK, -1);
        Date date1 = calendar.getTime();
        return date1;
    }


}
