package com.blood.imageloader.base;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import java.io.File;

/**
 * 图片加载器接口（可实现，进行扩展）
 */
public interface IImageStrategy {

    void display(@NonNull Context context, @NonNull ImageView view, @NonNull HSImageOption option);

    void load(@NonNull Context context, @NonNull HSImageOption option, Callback callback);

    void download(@NonNull Context context, @NonNull HSImageOption option, DownloadCallback callback);

    void checkExistCache(@NonNull Context context, String url, CheckExistCacheCallback callback);

    void clearMemoryCache();

    void clearDiskCache();

    interface Callback {

        void onLoadStart(String url);

        void onLoadCompleted(String url, @NonNull Drawable drawable);

        void onLoadFailed(String url);
    }

    interface DownloadCallback {

        void onLoadStart();

        void onLoadCompleted(@NonNull File file);

        void onLoadFailed();
    }

    interface CheckExistCacheCallback {

        void onCheckExistCache(boolean isExist);
    }

}
