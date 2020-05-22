package com.blood.imageloader.base;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView.ScaleType;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;

import com.bumptech.glide.Priority;

import java.io.File;

/**
 * 构建对象，请使用 HSImageOption.Builder
 */
public class HSImageOption {

    // 加载图片bitmap
    Bitmap bitmap;
    // 加载图片uri
    Uri uri;
    // 加载图片本地文件
    File file;
    // 加载图片网络url
    String url;
    // 加载图片本地资源文件
    int drawableResId;
    // 指定宽度
    int targetWidth;
    // 指定高度
    int targetHeight;
    // 加载失败
    int errorResId;
    // 加载失败，在请求的url/model为 null 时展示
    int fallbackResId;
    // 占位图
    int placeholderResId;
    // 占位图
    Drawable placeholderDrawable;
    // 是否跳过内存缓存
    boolean skipMemoryCache;
    // 是否跳过磁盘缓存
    boolean skipDiskCache;
    // 图片圆角角度
    int round;
    // 是否圆形图片
    boolean circle;
    // 是否高斯模糊
    boolean blur;
    // 渲染的模糊程度, 25是最大模糊度
    int blurRadius;
    // 图片缩放比例
    int blurSampling;
    // 过渡渐变动画
    int fadeDuration;
    // 缩放类型
    ScaleType scaleType;
    // 优先级
    Priority priority;
    // 圆形边框宽度（单位 px）
    int circleBorderWidth;
    // 圆形边框颜色
    int circleBorderColor;

    private HSImageOption(Builder builder) {
        this.bitmap = builder.bitmap;
        this.uri = builder.uri;
        this.file = builder.file;
        this.url = builder.url;
        this.drawableResId = builder.drawableResId;
        this.targetWidth = builder.targetWidth;
        this.targetHeight = builder.targetHeight;
        this.errorResId = builder.errorResId;
        this.fallbackResId = builder.fallbackResId;
        this.placeholderResId = builder.placeholderResId;
        this.placeholderDrawable = builder.placeholderDrawable;
        this.round = builder.bitmapAngle;
        this.skipMemoryCache = builder.skipMemoryCache;
        this.skipDiskCache = builder.skipDiskCache;
        this.circle = builder.circle;
        this.blur = builder.blur;
        this.blurRadius = builder.blurRadius;
        this.blurSampling = builder.blurSampling;
        this.fadeDuration = builder.fadeDuration;
        this.scaleType = builder.scaleType;
        this.priority = builder.priority;
        this.circleBorderWidth = builder.circleBorderWidth;
        this.circleBorderColor = builder.circleBorderColor;
    }

    public static final class Builder {

        // 加载图片bitmap
        Bitmap bitmap;
        // 加载图片uri
        private Uri uri;
        // 加载图片本地文件
        private File file;
        // 加载图片网络url
        private String url;
        // 加载图片本地资源文件
        private int drawableResId;
        // 指定宽度
        private int targetWidth;
        // 指定高度
        private int targetHeight;
        // 加载失败
        private int errorResId;
        // 加载失败，在请求的url/model为 null 时展示
        private int fallbackResId;
        // 占位图
        private int placeholderResId;
        // 占位图
        private Drawable placeholderDrawable;
        // 圆角角度
        private int bitmapAngle;
        // 是否跳过内存缓存
        private boolean skipMemoryCache;
        // 是否跳过磁盘缓存
        private boolean skipDiskCache;
        // 是否圆形图片
        private boolean circle;
        // 是否高斯模糊
        private boolean blur;
        // 渲染的模糊程度, 25是最大模糊度
        private int blurRadius;
        // 图片缩放比例
        private int blurSampling;
        // 过渡渐变动画
        private int fadeDuration;
        // 缩放类型
        private ScaleType scaleType;
        // 优先级
        private Priority priority;
        // 圆形边框宽度（单位 px）
        private int circleBorderWidth;
        // 圆形边框颜色
        private int circleBorderColor;

        public Builder() {
            scaleType = ScaleType.FIT_CENTER;
        }

        public Builder bitmap(Bitmap bitmap) {
            this.bitmap = bitmap;
            return this;
        }

        public Builder uri(Uri uri) {
            this.uri = uri;
            return this;
        }

        public Builder file(File file) {
            this.file = file;
            return this;
        }

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder drawableResId(@DrawableRes int drawableResId) {
            this.drawableResId = drawableResId;
            return this;
        }

        public Builder placeholder(@DrawableRes int placeholderResId) {
            this.placeholderResId = placeholderResId;
            return this;
        }

        public Builder placeholder(Drawable placeholder) {
            this.placeholderDrawable = placeholder;
            return this;
        }

        public Builder error(@DrawableRes int errorResId) {
            this.errorResId = errorResId;
            return this;
        }

        public Builder fallback(int fallbackResId) {
            this.fallbackResId = fallbackResId;
            return this;
        }

        public Builder resize(int targetWidth, int targetHeight) {
            this.targetWidth = targetWidth;
            this.targetHeight = targetHeight;
            return this;
        }

        public Builder round(int bitmapAngle) {
            this.bitmapAngle = bitmapAngle;
            return this;
        }

        public Builder skipMemoryCache(boolean skipMemoryCache) {
            this.skipMemoryCache = skipMemoryCache;
            return this;
        }

        public Builder skipDiskCache(boolean skipDiskCache) {
            this.skipDiskCache = skipDiskCache;
            return this;
        }

        public Builder circle() {
            this.circle = true;
            return this;
        }

        public Builder blur(int radius, int sampling) {
            this.blur = true;
            this.blurRadius = radius;
            this.blurSampling = sampling;
            return this;
        }

        public Builder fadeDuration(int fadeDuration) {
            this.fadeDuration = fadeDuration;
            return this;
        }

        public Builder scaleType(ScaleType scaleType) {
            this.scaleType = scaleType;
            return this;
        }

        public Builder priority(@NonNull Priority priority) {
            this.priority = priority;
            return this;
        }

        public Builder circleBorder(int circleBorderWidth, int circleBorderColor) {
            this.circle = true;
            this.circleBorderWidth = circleBorderWidth;
            this.circleBorderColor = circleBorderColor;
            return this;
        }

        public HSImageOption build() {
            return new HSImageOption(this);
        }
    }
}
