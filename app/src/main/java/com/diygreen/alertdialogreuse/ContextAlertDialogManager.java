/*
 * Copyright (C) 2016 jiashuangkuaizi, Inc.
 */
package com.diygreen.alertdialogreuse;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;


/**
 * Description: 提示信息显示工具类
 * <br/>Program Name: 
 * <br/>Date: 2016年2月27日
 *
 * @author 李旺成    liwangcheng@jiashuangkuaizi.com
 * @version 1.0
 */

public class ContextAlertDialogManager {

    //==========常量==========
    private static final String TAG = "ContextADManager";

    //==========普通静态变量==========
    private static AlertDialog sAlertDialog;                        // 一个Context下只产生一个AlertDialog实例
    private static AlertDialog.Builder sBuilder;                        // 一个Context下只产生一个AlertDialog.Builder实例
    private static Context sLastContext = null;

    //==========AlertDialog==========//
    public static AlertDialog displayOneBtnDialog(@NonNull Context context, String title, String msg) {
        if (TextUtils.isEmpty(msg)) return null;
        if (sAlertDialog == null) {
            TipInfo tipInfo = TipInfo.createTipInfo(title, msg);
            sAlertDialog = displayOneBtnDialog(context, tipInfo, null);
        } else {
            sAlertDialog.setTitle(title);
            sAlertDialog.setMessage(msg);
            DialogInterface.OnClickListener listener = null;
            sAlertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", listener);
            sAlertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setVisibility(View.GONE);
        }
        return sAlertDialog;
    }

    public static AlertDialog displayOneBtnDialog(@NonNull Context context, TipInfo tipInfo, DialogInterface.OnClickListener sureListener) {
        if (tipInfo == null) return null;
        AlertDialog.Builder builder = getBuilder(context, tipInfo);
        builder = addAlertDialogPositiveButton(tipInfo.sureBtnText, sureListener, builder);
        // 显示出该对话框
        sAlertDialog = builder.create();
        DialogInterface.OnClickListener listener = null;
        sAlertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "", listener);
        if (sAlertDialog.getButton(DialogInterface.BUTTON_NEGATIVE) != null) {
            sAlertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setVisibility(View.GONE);
        }
        return sAlertDialog;
    }

    public static AlertDialog displayTwoBtnDialog(@NonNull Context context, String title, String msg) {
        if (TextUtils.isEmpty(msg)) return null;
        if (sAlertDialog == null) {
            TipInfo tipInfo = TipInfo.createTipInfo(title, msg);
            sAlertDialog = displayTwoBtnDialog(context, tipInfo, null, null);
        } else {
            sAlertDialog.setTitle(title);
            sAlertDialog.setMessage(msg);
            if (sAlertDialog.getButton(DialogInterface.BUTTON_NEGATIVE) != null) {
                sAlertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setText("取消");
                sAlertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setVisibility(View.VISIBLE);
            }
        }
        return sAlertDialog;
    }

    public static AlertDialog displayTwoBtnDialog(@NonNull Context context, TipInfo tipInfo, DialogInterface.OnClickListener cancelListener, DialogInterface.OnClickListener sureListener) {
        if (tipInfo == null) return null;
        // 通过AlertDialog.Builder这个类来实例化我们的一个AlertDialog的对象
        AlertDialog.Builder builder = getBuilder(context, tipInfo);
        builder = addAlertDialogPositiveButton(tipInfo.sureBtnText, sureListener, builder);
        builder = addAlertDialogNegativeButton(tipInfo.cancelBtnText, cancelListener, builder);
        // 显示出该对话框
        sAlertDialog = builder.show();
        return sAlertDialog;
    }

    @NonNull
    private static AlertDialog.Builder getBuilder(@NonNull Context context, TipInfo tipInfo) {
        AlertDialog.Builder builder;
        if (context == sLastContext) {
            if (sBuilder != null) {
                builder = sBuilder;
            } else {
                builder = createNewBuilder(context);
            }
        } else {
            builder = createNewBuilder(context);
            sBuilder = null;
            sLastContext = null;
            sLastContext = context;
            sBuilder = builder;
        }
        // 通过AlertDialog.Builder这个类来实例化我们的一个AlertDialog的对象
        // 设置Title的图标
        builder.setIcon(tipInfo.iconResId);
        // 设置Title的内容
        builder.setTitle(tipInfo.title);
        // 设置Content来显示一个信息
        builder.setMessage(tipInfo.msg);
        return builder;
    }

    @NonNull
    private static AlertDialog.Builder createNewBuilder(@NonNull Context context) {
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(context);
        sBuilder = builder;
        return builder;
    }

    private static AlertDialog.Builder addAlertDialogPositiveButton(String btnText, DialogInterface.OnClickListener listener, final AlertDialog.Builder builder) {
        listener = getDefaultOnClickListener(listener);
        // 设置一个PositiveButton
        builder.setPositiveButton(btnText, listener);
        return builder;
    }

    private static AlertDialog.Builder addAlertDialogNegativeButton(String btnText, DialogInterface.OnClickListener listener, final AlertDialog.Builder builder) {
        listener = getDefaultOnClickListener(listener);
        // 设置一个PositiveButton
        builder.setNegativeButton(btnText, listener);
        return builder;
    }

    @NonNull
    private static DialogInterface.OnClickListener getDefaultOnClickListener(DialogInterface.OnClickListener listener) {
        if (listener != null) return listener;
        listener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.e(TAG, "AlertDialog Button Click!");
            }
        };
        return listener;
    }
    //==========AlertDialog==========//


    //==========逻辑方法==========//
    public static void destory(@NonNull Context context) {
        if (context != sLastContext) {
            context = null;
            return;
        }
        if (sAlertDialog != null) {
            sAlertDialog.cancel();
        }
        context = null;
        sLastContext = null;
        sBuilder = null;
        sAlertDialog = null;
    }

    //==========逻辑方法==========//

    //==========内部类==========
    public static final class TipInfo {

        public int iconResId = R.mipmap.ic_launcher;
        public String title = "提示";
        public String msg;
        private String sureBtnText = "确定";
        private String cancelBtnText = "取消";

        public TipInfo(int iconResId, String msg, String title) {
            if (iconResId > 0) {
                this.iconResId = iconResId;
            }
            if (!TextUtils.isEmpty(title)) {
                this.title = title;
            }
            this.msg = msg;
        }

        public static TipInfo createTipInfo(String msg) {
            return new TipInfo(-1, msg, null);
        }

        public static TipInfo createTipInfo(String title, String msg) {
            return new TipInfo(-1, msg, title);
        }

        public String getCancelBtnText() {
            return cancelBtnText;
        }

        public void setCancelBtnText(String cancelBtnText) {
            this.cancelBtnText = cancelBtnText;
        }

        public String getSureBtnText() {
            return sureBtnText;
        }

        public void setSureBtnText(String sureBtnText) {
            this.sureBtnText = sureBtnText;
        }
    }

}
