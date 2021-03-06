package com.tourcool.core.util;

import android.text.TextUtils;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.FastDateFormat;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.lang.System.currentTimeMillis;

/**
 * @author :zhoujian
 * @description : 时间工具类
 * @company :途酷科技
 * @date 2018年03月13日下午 04:22
 * @Email: 971613168@qq.com
 */

public class DateUtil {
    private static final String TAG = "DateUtil";
    private final static String PATTERN_DATE_CHINA_TO_DAY = "yyyy年MM月dd日";
    private final static String PATTERN_DATE_CHINA_TO_MINITUS = "yyyy年MM月dd日 HH:mm";
    private final static String PATTERN = "yyyy-MM-dd HH:mm:ss";
    private final static String PATTERN_NO_SECOND = "yyyy-MM-dd HH:mm";
    private final static String PATTERN_NO_SPLIT = "yyyyMMddHHmmss";
    private final static String PATTERN_DATE = "yyyy-MM-dd";
    private final static int TIME_LENGTH = 14;
    private final static String PATTERN_HOUR_MINITUS = "HH:mm";

    /**
     * 获取当前时间戳格式的字符串
     */
    public static String getCurrentTime() {
        long timeStamp = currentTimeMillis();
        return String.valueOf(timeStamp);
    }


    /**
     * 将时间戳转换为时间格式的字符串
     */
    public static String getTimeString(String timeMillis) {
        final FastDateFormat df = FastDateFormat.getInstance(PATTERN);
        if (StringUtils.isNotEmpty(timeMillis)) {
            long time = Long.parseLong(timeMillis);
            return df.format(new Date(time));
        } else {
            return "";
        }
    }


    /**
     * 将时间戳转换为时间格式的字符串
     */
    public static String getTimeStringNoSplit(String timeMillis) {
        final FastDateFormat df = FastDateFormat.getInstance(PATTERN_NO_SPLIT);
        if (StringUtils.isNotEmpty(timeMillis)) {
            long time = Long.parseLong(timeMillis);
            return df.format(new Date(time));
        } else {
            return "";
        }
    }

    public static String parseDate(long timeMillis) {
        timeMillis = timeMillis * 1000;
        final FastDateFormat df = FastDateFormat.getInstance(PATTERN);
        try {
            return df.format(new Date(timeMillis));
        } catch (Exception e) {
            return "";
        }
    }

    public static String parseDateDay(long timeMillis) {
        final FastDateFormat df = FastDateFormat.getInstance(PATTERN_DATE);
        try {
            return df.format(new Date(timeMillis));
        } catch (Exception e) {
            return "";
        }
    }

    public static String parseDateNoSecond(long timeMillis) {
        final FastDateFormat df = FastDateFormat.getInstance(PATTERN_NO_SECOND);
        try {
            return df.format(new Date(timeMillis));
        } catch (Exception e) {
            return "";
        }
    }

    public static String parseDateNoSecond(String timeMillis) {
        final FastDateFormat df = FastDateFormat.getInstance(PATTERN_NO_SECOND);
        try {
            long time = Long.parseLong(timeMillis);
            return df.format(new Date(time));
        } catch (Exception e) {
            return "";
        }
    }

    public static String parseDateHourAndMinitue(long timeMillis) {
        final FastDateFormat df = FastDateFormat.getInstance(PATTERN_HOUR_MINITUS);
        try {
            return df.format(new Date(timeMillis));
        } catch (Exception e) {
            return "";
        }
    }


    public static String getDateString(String timeMillis) {
        final FastDateFormat df = FastDateFormat.getInstance(PATTERN_DATE);
        if (StringUtils.isNotEmpty(timeMillis)) {
            long time = Long.parseLong(timeMillis);
            return df.format(new Date(time));
        } else {
            return "";
        }
    }


    public static long parseDateLong(String timeMillis) {
        if (StringUtils.isNotEmpty(timeMillis)) {
            try {
                BigDecimal bd = new BigDecimal(timeMillis);
                String callBackScore = bd.toPlainString();
                return Long.parseLong(callBackScore);
            } catch (NumberFormatException e) {
                return 0;
            }
        } else {
            return 0;
        }
    }


    public static String getDateString(Date date) {
        final FastDateFormat df = FastDateFormat.getInstance(PATTERN_DATE);
        try {
            return df.format(date);
        } catch (NumberFormatException e) {
            return "";
        }
    }

    public static String getTimeChineseCharacter(String timeValue) {
        if (TextUtils.isEmpty(timeValue)) {
            return "";
        }
        if (timeValue.length() == TIME_LENGTH) {
            StringBuilder sb = new StringBuilder("");
            sb.append(timeValue.substring(0, 4));
            sb.append("年");
            sb.append(timeValue.substring(4, 6));
            sb.append("月");
            sb.append(timeValue.substring(6, 8));
            sb.append("日");
            sb.append(timeValue.substring(8, 10));
            sb.append("时");
            sb.append(timeValue.substring(10, 12));
            sb.append("分");
            sb.append(timeValue.substring(12, 14));
            sb.append("秒");
            return sb.toString();
        }
        return "";
    }


    /**
     * 精确到日
     *
     * @param timeMillis
     * @return
     */
    public static String getTimeStringChinaToDay(String timeMillis) {
        final FastDateFormat df = FastDateFormat.getInstance(PATTERN_DATE_CHINA_TO_DAY);
        if (StringUtils.isNotEmpty(timeMillis)) {
            try {
                long time = Long.parseLong(timeMillis);
                return df.format(new Date(time));
            } catch (NumberFormatException e) {
                return "";
            }
        } else {
            return "";
        }
    }

    public static String getTimeStringChinaToMinitus(long timeMillis) {
        final FastDateFormat df = FastDateFormat.getInstance(PATTERN_DATE_CHINA_TO_MINITUS);
        try {
            return df.format(new Date(timeMillis));
        } catch (Exception e) {
            return "";
        }
    }


    public static String formatDateToShort(String str) {
        SimpleDateFormat sf1 = new SimpleDateFormat(PATTERN_DATE);
        SimpleDateFormat sf2 = new SimpleDateFormat("MM-dd");
        String formatStr;
        try {
            formatStr = sf2.format(sf1.parse(str));
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
        return formatStr;
    }
    public static String formatDateToMonthAndDaySlash(String str) {
        SimpleDateFormat sf1 = new SimpleDateFormat(PATTERN_DATE);
        SimpleDateFormat sf2 = new SimpleDateFormat("MM/dd");
        String formatStr;
        try {
            formatStr = sf2.format(sf1.parse(str));
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
        return formatStr;
    }

    public static String friendlyTime(Date time) {
        //获取time距离当前的秒数
        int ct = (int)((System.currentTimeMillis() - time.getTime())/1000);

        if(ct == 0) {
            return "刚刚";
        }

        if(ct > 0 && ct < 60) {
            return ct + "秒前";
        }

        if(ct >= 60 && ct < 3600) {
            return Math.max(ct / 60,1) + "分钟前";
        }
        if(ct >= 3600 && ct < 86400)
            return ct / 3600 + "小时前";
        if(ct >= 86400 && ct < 2592000){ //86400 * 30
            int day = ct / 86400 ;
            return day + "天前";
        }
        if(ct >= 2592000 && ct < 31104000) { //86400 * 30
            return ct / 2592000 + "月前";
        }
        return ct / 31104000 + "年前";
    }



    /**
     * 转换为时间（天,时:分:秒.毫秒）
     * @param timeMillis
     * @return
     */
    public static String formatDateTimeChinese(long timeMillis){
        long day = timeMillis/(24*60*60*1000);
        long hour = (timeMillis/(60*60*1000)-day*24);
        long min = ((timeMillis/(60*1000))-day*24*60-hour*60);
        long s = (timeMillis/1000-day*24*60*60-hour*60*60-min*60);
//        long sss = (timeMillis-day*24*60*60*1000-hour*60*60*1000-min*60*1000-s*1000);
        return (day>0?day+",":"")+hour+"小时"+min+"分钟"+s+"秒";
    }

    public static String formatDateTime(Date date){
        final FastDateFormat df = FastDateFormat.getInstance("yyyymmdd");
        try {
            return df.format(date);
        } catch (Exception e) {
            return "";
        }
    }

    public static String formatDateTimeChina(Date date){
        final FastDateFormat df = FastDateFormat.getInstance("yyyy/MM/dd");
        try {
            return df.format(date);
        } catch (Exception e) {
            return "";
        }
    }

}
