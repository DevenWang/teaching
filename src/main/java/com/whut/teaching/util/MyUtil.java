package com.whut.teaching.util;

import com.whut.teaching.vo.Empty;
import com.whut.teaching.vo.VO;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wpc on 2017/5/19.
 */
public class MyUtil {
    /**
     * @return String
     * @discription 获取一个时间串
     */
    public static String getStringID() {
        String id = null;
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        id = sdf.format(date);
        return id;
    }

    public static VO<Empty> emptyReturn() {
        return new VO<>(new Empty());
    }


    private static double EARTH_RADIUS = 6378.137;

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    /**
     * 得到两点间的距离 米
     *
     * @param lat1
     * @param long1
     * @param lat2
     * @param long2
     * @return
     */
    public static double GetDistance(double long1, double lat1, double long2, double lat2) {
        double a, b, d, sa2, sb2;
        lat1 = rad(lat1);
        lat2 = rad(lat2);
        a = lat1 - lat2;
        b = rad(long1 - long2);

        sa2 = Math.sin(a / 2.0);
        sb2 = Math.sin(b / 2.0);
        d = 2 * EARTH_RADIUS * Math.asin(Math.sqrt(sa2 * sa2 + Math.cos(lat1) * Math.cos(lat2) * sb2 * sb2));
        return d * 1000;
    }

}
