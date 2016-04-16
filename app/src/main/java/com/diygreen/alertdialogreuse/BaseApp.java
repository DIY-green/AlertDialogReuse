/*
 * Copyright (C) 2016 jiashuangkuaizi, Inc.
 */
package com.diygreen.alertdialogreuse;

import android.app.Application;

/**
 * Description:
 * <br/>Program Name:
 * <br/>Date: 2016年3月7日
 *
 * @author 李旺成
 * @version 1.0
 */

public class BaseApp extends Application {

    private static BaseApp sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }

    public static BaseApp getInstance() {
        return sInstance;
    }

}
