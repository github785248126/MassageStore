package com.example.massagestore.util;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.logging.Logger;

/**
 * Created by 老表 on 2019/8/26.
 */

public class AmountUtils {
    /**
     * 格式化字符串保留两位小数
     *
     * @param value
     * @return
     */
    public static String format(String value) {
        if (value == null || value.equals("")) {
            value = "0.00";
        }
        DecimalFormat df = new DecimalFormat("0.00");
        df.setRoundingMode(RoundingMode.HALF_UP);
        return df.format(Float.valueOf(value));
    }

    /**
     * 格式化字符串去除符号添加符号
     *
     * @param value
     * @return
     */
    public static String formatMoney(String value) {
        if (value == null || value.equals("")) {
            value = "￥0.00";
        }

        if (value.contains("￥")) {
            value = value.substring(1);
        } else {
            value = "￥" + format(value);
        }
        return value;
    }
}
