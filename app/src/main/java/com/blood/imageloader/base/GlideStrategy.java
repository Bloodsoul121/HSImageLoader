package com.blood.imageloader.base;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.blood.MainApplication;
import com.blood.imageloader.glide.CircleBorderTransformation;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import jp.wasabeef.glide.transformations.BlurTransformation;

public class GlideStrategy implements IImageStrategy {

    @Override
    public void display(@NonNull Context context, @NonNull ImageView view, @NonNull HSImageOption option) {
        RequestBuilder<Bitmap> requestBuilder = setup(context, option);
        if (requestBuilder == null) {
            view.setImageDrawable(null);
            return;
        }
        requestBuilder.into(view);
    }

    @Override
    public void load(@NonNull Context context, @NonNull HSImageOption option, Callback callback) {
        RequestBuilder<Bitmap> requestBuilder = setup(context, option);
        if (requestBuilder == null) {
            if (callback != null) {
                callback.onLoadFailed(option.url);
            }
            return;
        }
        requestBuilder.into(new CustomTarget<Bitmap>() {
            @Override
            public void onLoadStarted(@Nullable Drawable placeholder) {
                if (callback != null) {
                    callback.onLoadStart(option.url);
                }
            }

            @Override
            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                if (callback != null) {
                    callback.onLoadCompleted(option.url, resource);
                }
            }

            @Override
            public void onLoadCleared(@Nullable Drawable placeholder) {

            }

            @Override
            public void onLoadFailed(@Nullable Drawable errorDrawable) {
                if (callback != null) {
                    callback.onLoadFailed(option.url);
                }
            }
        });
    }

    @Override
    public Bitmap loadSync(@NonNull Context context, @NonNull HSImageOption option) {
        RequestBuilder<Bitmap> requestBuilder = setup(context, option);
        if (requestBuilder == null) {
            return null;
        }
        try {
            return requestBuilder.submit().get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void download(@NonNull Context context, @NonNull HSImageOption option, DownloadCallback callback) {
        int targetWidth = Target.SIZE_ORIGINAL;
        int targetHeight = Target.SIZE_ORIGINAL;
        if (option.targetWidth > 0 && option.targetHeight > 0) {
            targetWidth = option.targetWidth;
            targetHeight = option.targetHeight;
        }
        Glide.with(context).downloadOnly().load(option.url).override(targetWidth, targetHeight).into(new CustomTarget<File>() {

            @Override
            public void onLoadStarted(@Nullable Drawable placeholder) {
                if (callback != null) {
                    callback.onLoadStart();
                }
            }

            @Override
            public void onResourceReady(@NonNull File resource, @Nullable Transition<? super File> transition) {
                if (callback != null) {
                    callback.onLoadCompleted(resource);
                }
            }

            @Override
            public void onLoadCleared(@Nullable Drawable placeholder) {

            }

            @Override
            public void onLoadFailed(@Nullable Drawable errorDrawable) {
                if (callback != null) {
                    callback.onLoadFailed();
                }
            }
        });
    }

    @Override
    public void checkExistCache(@NonNull Context context, String url, CheckExistCacheCallback callback) {
        if (TextUtils.isEmpty(url)) {
            if (callback != null) {
                callback.onCheckExistCache(false);
            }
            return;
        }
        Glide.with(context).load(url).onlyRetrieveFromCache(true).into(new CustomTarget<Drawable>() {
            @Override
            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                if (callback != null) {
                    callback.onCheckExistCache(true);
                }
            }

            @Override
            public void onLoadCleared(@Nullable Drawable placeholder) {

            }

            @Override
            public void onLoadFailed(@Nullable Drawable errorDrawable) {
                if (callback != null) {
                    callback.onCheckExistCache(false);
                }
            }
        });
    }

    private RequestBuilder<Bitmap> setup(Context context, HSImageOption option) {
        RequestBuilder<Bitmap> requestBuilder = Glide.with(context).asBitmap();
        if (option.uri != null) {
            requestBuilder = requestBuilder.load(option.uri);
        } else if (option.file != null) {
            requestBuilder = requestBuilder.load(option.file);
        } else if (option.drawableResId > 0) {
            requestBuilder = requestBuilder.load(option.drawableResId);
        } else if (!TextUtils.isEmpty(option.url)) {
            requestBuilder = requestBuilder.load(option.url);
        } else if (option.bitmap != null) {
            requestBuilder = requestBuilder.load(option.bitmap);
        } else {
            requestBuilder = requestBuilder.load("");
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
            requestBuilder = requestBuilder.transition(BitmapTransitionOptions.withCrossFade(option.fadeDuration));
        }
        if (option.scaleType == ImageView.ScaleType.FIT_CENTER) {
            requestBuilder = requestBuilder.fitCenter();
        } else if (option.scaleType == ImageView.ScaleType.CENTER_CROP) {
            requestBuilder = requestBuilder.centerCrop();
        } else if (option.scaleType == ImageView.ScaleType.CENTER_INSIDE) {
            requestBuilder = requestBuilder.centerInside();
        }
        if (option.priority != null) {
            requestBuilder = requestBuilder.priority(option.priority);
        }
        // 图片转换器部分
        List<Transformation<Bitmap>> transformations = new ArrayList<>();
        RequestOptions requestOptions = new RequestOptions();
        if (option.round > 0) {
            transformations.add(new RoundedCorners(option.round));
        }
        if (option.circle) {
            if (option.circleBorderWidth > 0) {
//                transformations.add(new GradientCircleBorderTransformation(option.circleBorderWidth, new int[]{0xff833ab4,0xfffd1d1d,0xfffcb045,0xfffd1d1d,0xff833ab4}));
                transformations.add(new CircleBorderTransformation(option.circleBorderWidth, option.circleBorderColor));
            } else {
                transformations.add(new CircleCrop());
            }
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
        new Thread(Glide.get(MainApplication.getApplication())::clearDiskCache).start();
    }
}
