package com.turing.newaomo.davinsbrush.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class TimeUtil {
        public static String getTime(long time) {
                SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd HH:mm");
                return format.format(new Date(time));
        }


        public static Date getDateFormalFromString(String message) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = null;
                try {
                        date = simpleDateFormat.parse(message);
                } catch (ParseException e) {
                        e.printStackTrace();
                }
                return date;
        }

        private static String getShareTime(long currentDletaTime) {
                String result;
                int time = (int) (currentDletaTime / (1000 * 60));
//                LogUtil.e("差值分钟:" + time);
                if (time == 0) {
                        result = "刚刚发表";
                        return result;
                }
                if (time > 0 && time < 60) {
                        result = time + "分钟前";
                } else {
                        time = (int) (currentDletaTime / (1000 * 60 * 60));
                        if (time > 0 && time < 24) {
                                result = time + "小时前";
                        } else {
                                time = (int) (currentDletaTime / (1000 * 60 * 60 * 24));
                                if (time == 1) {
                                        result = "昨天";
                                } else if (time == 2) {
                                        result = "前天";
                                } else {
                                        result = time + "天前";
                                }
                        }
                }
//                LogUtil.e("时间拉拉：" + result);
                return result;
        }


        public static String getDateFormalFromString(int currentYear, int currentMonth, int currentDay) {
                GregorianCalendar gregorianCalendar = new GregorianCalendar(currentYear, currentMonth, currentDay);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                return simpleDateFormat.format(gregorianCalendar.getTime());
        }

}
