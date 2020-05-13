package com.blood.imageloader;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.annotation.NonNull;

/**
 * 图片加载器接口（可实现，进行扩展）
 */
public interface IImageStrategy {

    void display(Context context, ImageView view, HSImageOption option);

    void load(Context context, HSImageOption option, Callback callback);

    void clearMemoryCache();

    void clearDiskCache();

    interface Callback {

        void onLoadCompleted(@NonNull Drawable drawable);

        void onLoadFailed();
    }

}
