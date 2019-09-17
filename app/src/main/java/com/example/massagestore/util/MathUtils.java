package com.example.massagestore.util;

import java.math.BigDecimal;

/**
 * @name BusAppForFuyou
 * @class name：com.dm.busapp.utils
 * @anthor Lenovo E-mail:
 * @time 2018/9/11
 */
public class MathUtils {

    private static MathUtils sMathUtils = null;

    private MathUtils() {
    }

    public static synchronized MathUtils getInstance() {
        if (sMathUtils == null) {
            synchronized (MathUtils.class) {
                if (sMathUtils == null) {
                    sMathUtils = new MathUtils();
                }
            }
        }
        return sMathUtils;
    }

    //   加法
    public String getAddBigDecimal(String str1, String str2) {
        String result = new BigDecimal(str1).add(new BigDecimal(str2)).toString();
        return result;
    }

    //   减法
    public String getSubtractBigDecimal(String str1, String str2) {
        String result = new BigDecimal(str1).subtract(new BigDecimal(str2)).toString();
        return result;
    }

    //  乘法
    public String getMultiplyBigDecimal(String str1, String str2) {
        String result = new BigDecimal(str1).multiply(new BigDecimal(str2)).toString();
        return result;
    }

    //  除法
    public String getDivideBigDecimal(String str1, String str2) {
        String result = new BigDecimal(str1).divide(new BigDecimal(str2)).toString();
        return result;
    }
}
