package com.example.massagestore.util;

import android.widget.Toast;
import com.example.massagestore.base.BaseApplication;

/**
 * 说明: 显示提示语
 */
public class ToastUtils {
    public static void showTextShort(String text) {
        Toast.makeText(BaseApplication.getContext(),text,Toast.LENGTH_SHORT).show();
    }

    public static void showTextLong(String text) {
        Toast.makeText(BaseApplication.getContext(), text, Toast.LENGTH_LONG).show();
    }
}
