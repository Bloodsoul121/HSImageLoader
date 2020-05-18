package com.blood.imageloader.base;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import androidx.annotation.DrawableRes;

import com.blood.imageloader.HSImageDefaultOptions;

import java.io.File;

public class HSImageLoader implements IImageStrategy {

    private IImageStrategy mImageLoader;

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
        setImageLoader(new GlideStrategy());
        HSImageDefaultOptions.initDefaultOptions();
    }

    /**
     * 配置图片加载器
     *
     * @param loader 图片加载器，可扩展
     */
    private void setImageLoader(IImageStrategy loader) {
        if (loader == null) {
            throw new NullPointerException("ImageLoader Strategy cannot be null.");
        }
        mImageLoader = loader;
    }

    /**
     * 主方法，展示图片，其他方法都是重载
     *
     * @param context 上下文
     * @param view    要加载的view
     * @param option  配置，可参考默认配置{@link HSImageDefaultOptions}
     */
    @Override
    public void display(Context context, ImageView view, HSImageOption option) {
        if (context == null) throw new IllegalStateException("Context is required");
        if (view == null) throw new IllegalStateException("ImageView is required");
        if (option == null) throw new IllegalStateException("HSImageOption is required");
        mImageLoader.display(context, view, option);
    }

    /**
     * 主方法，只加载图片，其他方法都是重载
     *
     * @param context  上下文
     * @param option   配置，可参考默认配置{@link HSImageDefaultOptions}
     * @param callback 回调
     */
    @Override
    public void load(Context context, HSImageOption option, Callback callback) {
        if (context == null) throw new IllegalStateException("Context is required");
        if (option == null) throw new IllegalStateException("HSImageOption is required");
        if (callback == null) throw new IllegalStateException("Callback is required");
        mImageLoader.load(context, option, callback);
    }

    @Override
    public void clearMemoryCache() {
        if (mImageLoader != null) {
            mImageLoader.clearMemoryCache();
        }
    }

    @Override
    public void clearDiskCache() {
        if (mImageLoader != null) {
            mImageLoader.clearDiskCache();
        }
    }

    public void load(Context context, Uri uri, Callback callback) {
        HSImageOption option = new HSImageOption.Builder().uri(uri).build();
        load(context, option, callback);
    }

    public void load(Context context, File file, Callback callback) {
        HSImageOption option = new HSImageOption.Builder().file(file).build();
        load(context, option, callback);
    }

    public void load(Context context, String url, Callback callback) {
        HSImageOption option = new HSImageOption.Builder().url(url).build();
        load(context, option, callback);
    }

    public void load(Context context, @DrawableRes int resourceId, Callback callback) {
        HSImageOption option = new HSImageOption.Builder().drawableResId(resourceId).build();
        load(context, option, callback);
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
