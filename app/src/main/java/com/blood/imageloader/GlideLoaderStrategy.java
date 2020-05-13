package com.blood.imageloader;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.blood.MainApplication;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

public class GlideLoaderStrategy implements IImageLoaderStrategy {

    @Override
    public void display(Context context, ImageView view, HSImageOption option) {
        RequestManager requestManager = Glide.with(context);
        RequestBuilder<Drawable> requestBuilder;
        if (option.uri != null) {
            requestBuilder = requestManager.load(option.uri);
        } else if (option.file != null) {
            requestBuilder = requestManager.load(option.file);
        } else if (option.drawableResId > 0) {
            requestBuilder = requestManager.load(option.drawableResId);
        } else if (!TextUtils.isEmpty(option.url)) {
            requestBuilder = requestManager.load(option.url);
        } else {
            throw new IllegalStateException("HSImageOption load file is required");
        }
        if (option.placeholderResId > 0) {
            requestBuilder = requestBuilder.placeholder(option.placeholderResId);
        }
        if (option.placeholderDrawable != null) {
            requestBuilder = requestBuilder.placeholder(option.placeholderDrawable);
        }
        if (option.errorResId > 0) {
            requestBuilder = requestBuilder.error(option.errorResId);
        }
        if (option.fallbackResId > 0) {
            requestBuilder = requestBuilder.fallback(option.fallbackResId);
        }
        if (option.targetWidth > 0 && option.targetHeight > 0) {
            requestBuilder = requestBuilder.override(option.targetWidth, option.targetHeight);
        }
        if (option.bitmapAngle > 0) {
            requestBuilder = requestBuilder.apply(RequestOptions.bitmapTransform(new RoundedCorners(option.bitmapAngle)));
        }
        if (option.circle) {
            requestBuilder = requestBuilder.apply(RequestOptions.bitmapTransform(new CircleCrop()));
        }
        requestBuilder = requestBuilder.skipMemoryCache(option.skipMemoryCache);
        requestBuilder = requestBuilder.diskCacheStrategy(option.skipDiskCache ? DiskCacheStrategy.NONE : DiskCacheStrategy.AUTOMATIC);
        requestBuilder.into(view);
    }

    @Override
    public void clearMemoryCache() {
        Glide.get(MainApplication.getApplication()).clearMemory();
    }

    @Override
    public void clearDiskCache() {
//        ThreadPool.getInstance().executeShortTask(() -> Glide.get(MainApplication.getApplication()).clearDiskCache());
    }
}
