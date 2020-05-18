package com.blood.imageloader.base;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.blood.MainApplication;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.glide.transformations.BlurTransformation;

public class GlideStrategy implements IImageStrategy {

    @Override
    public void display(Context context, ImageView view, HSImageOption option) {
        RequestBuilder<Drawable> requestBuilder = setup(context, option);
        if (requestBuilder == null) {
            view.setImageDrawable(null);
            return;
        }
        requestBuilder.into(view);
    }

    @Override
    public void load(Context context, HSImageOption option, Callback callback) {
        RequestBuilder<Drawable> requestBuilder = setup(context, option);
        if (requestBuilder == null) {
            if (callback != null) {
                callback.onLoadFailed();
            }
            return;
        }
        requestBuilder.into(new CustomTarget<Drawable>() {
            @Override
            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                if (callback != null) {
                    callback.onLoadCompleted(resource);
                }
            }

            @Override
            public void onLoadCleared(@Nullable Drawable placeholder) {
                if (callback != null) {
                    callback.onLoadFailed();
                }
            }
        });
    }

    private RequestBuilder<Drawable> setup(Context context, HSImageOption option) {
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
            return null;
        }
        // 缓存
        requestBuilder = requestBuilder.skipMemoryCache(option.skipMemoryCache);
        requestBuilder = requestBuilder.diskCacheStrategy(option.skipDiskCache ? DiskCacheStrategy.NONE : DiskCacheStrategy.AUTOMATIC);
        // 配置
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
        if (option.fadeDuration > 0) {
            requestBuilder = requestBuilder.transition(DrawableTransitionOptions.withCrossFade(option.fadeDuration));
        }
        // 图片转换器部分
        List<Transformation<Bitmap>> transformations = new ArrayList<>();
        RequestOptions requestOptions = new RequestOptions();
        if (option.round > 0) {
            transformations.add(new RoundedCorners(option.round));
        }
        if (option.circle) {
            transformations.add(new CircleCrop());
        }
        if (option.blur) {
            transformations.add(new BlurTransformation(option.blurRadius, option.blurSampling));
        }
        if (transformations.size() > 0) {
            requestOptions = requestOptions.transform(new MultiTransformation<>(transformations));
        }
        requestBuilder = requestBuilder.apply(requestOptions);
        return requestBuilder;
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
