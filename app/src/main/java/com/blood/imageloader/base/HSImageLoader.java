package com.blood.imageloader.base;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.widget.ImageView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;

import com.blood.MainApplication;
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
     * 可以使用 Application Context（就不会监听Activity的生命周期做出变化）
     * 不一定需要外部传入的 Context
     *
     * @return Context
     */
    private Context getApplicationContext() {
        return MainApplication.getApplication();
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
            throw new IllegalStateException("ImageLoader Strategy cannot be null.");
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
    public void display(@NonNull Context context, @NonNull ImageView view, @NonNull HSImageOption option) {
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
    public void load(@NonNull Context context, @NonNull HSImageOption option, Callback callback) {
        mImageLoader.load(context, option, callback);
    }

    /**
     * 同步加载图片（必须在子线程调用）
     *
     * @param context 上下文
     * @param option  配置，可参考默认配置{@link HSImageDefaultOptions}
     * @return Bitmap
     */
    @Override
    public Bitmap loadSync(@NonNull Context context, @NonNull HSImageOption option) {
        return mImageLoader.loadSync(context, option);
    }

    /**
     * 下载图片
     *
     * @param context  上下文
     * @param option   配置，可参考默认配置{@link HSImageDefaultOptions}
     * @param callback 回调
     */
    @Override
    public void download(@NonNull Context context, @NonNull HSImageOption option, DownloadCallback callback) {
        mImageLoader.download(context, option, callback);
    }

    /**
     * 检查是否有缓存资源，包括内存和磁盘
     *
     * @param context  上下文
     * @param url      图片url
     * @param callback 回调
     */
    @Override
    public void checkExistCache(@NonNull Context context, String url, CheckExistCacheCallback callback) {
        mImageLoader.checkExistCache(context, url, callback);
    }

    /**
     * 清除内存缓存
     */
    @Override
    public void clearMemoryCache() {
        if (mImageLoader != null) {
            mImageLoader.clearMemoryCache();
        }
    }

    /**
     * 清除磁盘缓存
     */
    @Override
    public void clearDiskCache() {
        if (mImageLoader != null) {
            mImageLoader.clearDiskCache();
        }
    }

    public void load(Uri uri, Callback callback) {
        load(getApplicationContext(), uri, callback);
    }

    public void load(Context context, Uri uri, Callback callback) {
        HSImageOption option = new HSImageOption.Builder().uri(uri).build();
        load(context, option, callback);
    }

    public void load(File file, Callback callback) {
        load(getApplicationContext(), file, callback);
    }

    public void load(Context context, File file, Callback callback) {
        HSImageOption option = new HSImageOption.Builder().file(file).build();
        load(context, option, callback);
    }

    public void load(String url, Callback callback) {
        load(getApplicationContext(), url, callback);
    }

    public void load(Context context, String url, Callback callback) {
        HSImageOption option = new HSImageOption.Builder().url(url).build();
        load(context, option, callback);
    }

    public void load(@DrawableRes int resourceId, Callback callback) {
        load(getApplicationContext(), resourceId, callback);
    }

    public void load(Context context, @DrawableRes int resourceId, Callback callback) {
        HSImageOption option = new HSImageOption.Builder().drawableResId(resourceId).build();
        load(context, option, callback);
    }

    public Bitmap loadSync(Uri uri) {
        return loadSync(getApplicationContext(), uri);
    }

    public Bitmap loadSync(Context context, Uri uri) {
        HSImageOption option = new HSImageOption.Builder().uri(uri).build();
        return loadSync(context, option);
    }

    public Bitmap loadSync(File file) {
        return loadSync(getApplicationContext(), file);
    }

    public Bitmap loadSync(Context context, File file) {
        HSImageOption option = new HSImageOption.Builder().file(file).build();
        return loadSync(context, option);
    }

    public Bitmap loadSync(String url) {
        return loadSync(getApplicationContext(), url);
    }

    public Bitmap loadSync(Context context, String url) {
        HSImageOption option = new HSImageOption.Builder().url(url).build();
        return loadSync(context, option);
    }

    public Bitmap loadSync(@DrawableRes int resourceId) {
        return loadSync(getApplicationContext(), resourceId);
    }

    public Bitmap loadSync(Context context, @DrawableRes int resourceId) {
        HSImageOption option = new HSImageOption.Builder().drawableResId(resourceId).build();
        return loadSync(context, option);
    }

    public void display(ImageView view, Uri uri) {
        display(getApplicationContext(), view, uri);
    }

    public void display(Context context, ImageView view, Uri uri) {
        display(context, view, uri, null);
    }

    public void display(ImageView view, File file) {
        display(getApplicationContext(), view, file);
    }

    public void display(Context context, ImageView view, File file) {
        display(context, view, file, null);
    }

    public void display(ImageView view, String url) {
        display(getApplicationContext(), view, url);
    }

    public void display(Context context, ImageView view, String url) {
        display(context, view, url, null);
    }

    public void display(ImageView view, Bitmap bitmap) {
        display(getApplicationContext(), view, bitmap);
    }

    public void display(Context context, ImageView view, Bitmap bitmap) {
        display(context, view, bitmap, null);
    }

    public void display(ImageView view, @DrawableRes int resourceId) {
        display(getApplicationContext(), view, resourceId);
    }

    public void display(Context context, ImageView view, @DrawableRes int resourceId) {
        display(context, view, resourceId, null);
    }

    public void display(ImageView view, Uri uri, HSImageOption option) {
        display(getApplicationContext(), view, uri, option);
    }

    public void display(ImageView view, File file, HSImageOption option) {
        display(getApplicationContext(), view, file, option);
    }

    public void display(ImageView view, String url, HSImageOption option) {
        display(getApplicationContext(), view, url, option);
    }

    public void display(ImageView view, @DrawableRes int resourceId, HSImageOption option) {
        display(getApplicationContext(), view, resourceId, option);
    }

    public void display(ImageView view, Bitmap bitmap, HSImageOption option) {
        display(getApplicationContext(), view, bitmap, option);
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

    public void display(Context context, ImageView view, Bitmap bitmap, HSImageOption option) {
        if (context == null) throw new IllegalStateException("Context is required");
        if (view == null) throw new IllegalStateException("ImageView is required");
        if (option == null) {
            option = new HSImageOption.Builder().bitmap(bitmap).build();
        }
        option.bitmap = bitmap;
        display(context, view, option);
    }

}
