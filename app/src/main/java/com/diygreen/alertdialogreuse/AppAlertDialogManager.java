/*
 * Copyright (C) 2016 jiashuangkuaizi, Inc.
 */
package com.diygreen.alertdialogreuse;

import android.app.AlertDialog;
import android.view.WindowManager;

/**
 * Description:
 * <br/>Program Name:
 * <br/>Date: 2016年3月7日
 *
 * @author 李旺成
 * @version 1.0
 */

public class AppAlertDialogManager {

    private static AlertDialog sAlertDialog;

    public static AlertDialog displayOneBtnDialog(String title, String msg) {
        if (sAlertDialog != null) {
            sAlertDialog.setTitle(title);
            sAlertDialog.setMessage(msg);
        } else {
            sAlertDialog = new AlertDialog.Builder(BaseApp.getInstance())
                    .setTitle(title)
                    .setMessage(msg)
                    .setNegativeButton("取消", null)
                    .setPositiveButton("确定", null)
                    .create();
            sAlertDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        }
        return sAlertDialog;
    }

}
