package com.blood.imageloader;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import androidx.annotation.DrawableRes;

import java.io.File;

public class HSImageLoader implements IImageLoaderStrategy {

    private IImageLoaderStrategy mImageLoader;

    private HSImageLoader() {
    }

    public static HSImageLoader getInstance() {
        return Holder.INSTANCE;
    }

    private static final class Holder {
        private static final HSImageLoader INSTANCE = new HSImageLoader();
    }

    /**
     * 初始化配置
     */
    public void init() {
        HSImageOptions.initDefaultOptions();
        if (mImageLoader == null) {
            mImageLoader = new GlideLoaderStrategy();
        }
    }

    /**
     * 可以动态切换图片加载器
     */
    public void setImageLoader(IImageLoaderStrategy loader) {
        if (loader == null) {
            throw new NullPointerException("ImageLoader Strategy cannot be null.");
        }
        mImageLoader = loader;
    }

    /**
     * 展示图片，其他方法都是重载
     */
    @Override
    public void display(Context context, ImageView view, HSImageOption option) {
        if (context == null) throw new IllegalStateException("Context is required");
        if (view == null) throw new IllegalStateException("ImageView is required");
        if (option == null) throw new IllegalStateException("HSImageOption is required");
        mImageLoader.display(context, view, option);
    }

    @Override
    public void clearMemoryCache() {
        mImageLoader.clearMemoryCache();
    }

    @Override
    public void clearDiskCache() {
        mImageLoader.clearDiskCache();
    }

    public void display(Context context, ImageView view, Uri uri) {
        display(context, view, uri, null);
    }

    public void display(Context context, ImageView view, File file) {
        display(context, view, file, null);
    }

    public void display(Context context, ImageView view, String url) {
        display(context, view, url, null);
    }

    public void display(Context context, ImageView view, @DrawableRes int resourceId) {
        display(context, view, resourceId, null);
    }

    public void display(Context context, ImageView view, Uri uri, HSImageOption option) {
        if (context == null) throw new IllegalStateException("Context is required");
        if (view == null) throw new IllegalStateException("ImageView is required");
        if (option == null) {
            option = new HSImageOption.Builder().uri(uri).build();
        }
        option.uri = uri;
        display(context, view, option);
    }

    public void display(Context context, ImageView view, File file, HSImageOption option) {
        if (context == null) throw new IllegalStateException("Context is required");
        if (view == null) throw new IllegalStateException("ImageView is required");
        if (option == null) {
            option = new HSImageOption.Builder().file(file).build();
        }
        option.file = file;
        display(context, view, option);
    }

    public void display(Context context, ImageView view, String url, HSImageOption option) {
        if (context == null) throw new IllegalStateException("Context is required");
        if (view == null) throw new IllegalStateException("ImageView is required");
        if (option == null) {
            option = new HSImageOption.Builder().url(url).build();
        }
        option.url = url;
        display(context, view, option);
    }

    public void display(Context context, ImageView view, @DrawableRes int resourceId, HSImageOption option) {
        if (context == null) throw new IllegalStateException("Context is required");
        if (view == null) throw new IllegalStateException("ImageView is required");
        if (option == null) {
            option = new HSImageOption.Builder().drawableResId(resourceId).build();
        }
        option.drawableResId = resourceId;
        display(context, view, option);
    }

}
