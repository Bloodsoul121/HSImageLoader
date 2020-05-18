package com.blood;

import android.app.Application;

import com.blood.imageloader.base.HSImageLoader;

public class MainApplication extends Application {

    private static MainApplication myApplication = null;

    public static MainApplication getApplication() {
        if (myApplication == null) {
            myApplication = new MainApplication();
        }
        return myApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        HSImageLoader.getInstance().init();
    }
}
