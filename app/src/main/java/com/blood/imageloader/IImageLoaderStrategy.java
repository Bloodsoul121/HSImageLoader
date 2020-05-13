package com.blood.imageloader;

import android.content.Context;
import android.widget.ImageView;

import com.blood.imageloader.HSImageOption;

/**
 * 图片加载器接口（可实现，进行扩展）
 */
interface IImageLoaderStrategy {

    void display(Context context, ImageView view, HSImageOption option);

    void clearMemoryCache();

    void clearDiskCache();

}
