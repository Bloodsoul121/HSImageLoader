package com.blood.imageloader.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.SweepGradient;

import androidx.annotation.NonNull;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;

import java.security.MessageDigest;
import java.util.Arrays;

import jp.wasabeef.glide.transformations.BitmapTransformation;

public class GradientCircleBorderTransformation extends BitmapTransformation {

    private final String ID;
    private int borderWidth;
    private int[] borderColors;

    public GradientCircleBorderTransformation(int borderWidth, int[] colors) {
        this.borderWidth = borderWidth;
        this.borderColors = colors;
        ID = getClass().getName() + borderWidth + Arrays.toString(colors);
    }

    @Override
    protected Bitmap transform(@NonNull Context context, @NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth, int outHeight) {
        int size = Math.min(toTransform.getWidth(), toTransform.getHeight());

        int width = (toTransform.getWidth() - size) / 2;
        int height = (toTransform.getHeight() - size) / 2;

        Bitmap bitmap = pool.get(size, size, Bitmap.Config.ARGB_8888);
        if (bitmap == null) {
            bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        BitmapShader shader = new BitmapShader(toTransform, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);
        if (width != 0 || height != 0) {
            // source isn't square, move viewport to center
            Matrix matrix = new Matrix();
            matrix.setTranslate(-width, -height);
            shader.setLocalMatrix(matrix);
        }
        paint.setShader(shader);
        paint.setAntiAlias(true);

        float r = size / 2f;
        canvas.drawCircle(r, r, r - borderWidth, paint);

        Paint borderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        SweepGradient gradient = new SweepGradient(r, r, borderColors, null);
        borderPaint.setShader(gradient);
        borderPaint.setStrokeWidth(borderWidth);
        borderPaint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(r, r, r - borderWidth / 2f, borderPaint);
        return bitmap;
    }

    @Override
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
        messageDigest.update(ID.getBytes(CHARSET));
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof GradientCircleBorderTransformation;
    }

    @Override
    public int hashCode() {
        return ID.hashCode();
    }
}