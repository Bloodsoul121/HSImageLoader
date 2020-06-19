package com.blood;

import android.app.Application;
import android.graphics.Bitmap;

import com.blood.imageloader.base.HSImageLoader;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

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

        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .imageScaleType(ImageScaleType.EXACTLY)
                .cacheInMemory(false)
                .cacheOnDisk(false)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .memoryCacheSize(60 * 1024 * 1024)
                .diskCacheSize(300 * 1024 * 1024)
                .tasksProcessingOrder(QueueProcessingType.FIFO)
                .defaultDisplayImageOptions(defaultOptions)
                .build();

        ImageLoader.getInstance().init(config);
    }
}
