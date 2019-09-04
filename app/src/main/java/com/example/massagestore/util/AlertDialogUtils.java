package com.example.massagestore.util;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

/**
 * Created by 老表 on 2019/8/27.
 */

public class AlertDialogUtils {

    private static AlertDialogUtils dialogUtils = null;
    private AlertDialog.Builder alertDialog;
    private DialogClickListener dialogClickListener;

    public void setDialogClickListener(DialogClickListener dialogClickListener) {
        this.dialogClickListener = dialogClickListener;
    }

    public interface DialogClickListener {
        void onPositiveListener(DialogInterface dialog);

        void onNegativeListener(DialogInterface dialog);
    }

    private AlertDialogUtils() {

    }

    public static AlertDialogUtils getSingle() {
        if (null == dialogUtils) {
            synchronized (AlertDialogUtils.class) {
                if (null == dialogUtils) {
                    dialogUtils = new AlertDialogUtils();
                }
            }
        }
        return dialogUtils;
    }

    public void dialogMessage(Context context, String message, final DialogClickListener dialogClickListener) {
        alertDialog = new AlertDialog.Builder(context);

        alertDialog.setTitle("操作")
                .setMessage(message)
                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogClickListener.onPositiveListener(dialogInterface);
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogClickListener.onNegativeListener(dialogInterface);
                    }
                }).show();
    }
}
