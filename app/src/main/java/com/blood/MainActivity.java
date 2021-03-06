package com.blood;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.blood.imageloader.base.HSImageLoader;
import com.blood.imageloader.base.HSImageOption;
import com.blood.imageloader.base.IImageStrategy;
import com.blood.util.DPUtil;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.iv_ori)
    ImageView mIvOri;
    @BindView(R.id.iv_deal)
    ImageView mIvDeal;

    private HSImageOption mOption;

    private String mImageUrl = "http://img.17sing.tw/ktv_img/hsing_ktvroom_1487841535_100866.png";
    private HSImageOption mDownloadOption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initOption();
        initImage();
    }

    private void initOption() {
        mOption = new HSImageOption.Builder()
//                .placeholder(R.drawable.image11)
                .error(R.drawable.image12)
                .fallback(R.drawable.image13)
//                .skipDiskCache(true)
//                .skipMemoryCache(true)
//                .round(DPUtil.dip2px(this, 50f))
                .circle()
                .circleBorder(DPUtil.dip2px(this, 5f), getResources().getColor(R.color.colorAccent))
//                .resize(DPUtil.dip2px(this, 50f), DPUtil.dip2px(this, 50f))
//                .blur(25, 1)
//                .fadeDuration(2000)
                .scaleType(ImageView.ScaleType.CENTER_CROP)
                .build();

        mDownloadOption = new HSImageOption.Builder()
//                .url("http://img.17sing.tw/uc/img/util_6639214_1589884217.png")
                .url("http://img.17sing.tw/uc/img/util_9656998_1589530342.png")
                .skipMemoryCache(true)
                .resize(400, 400)
                .build();
    }

    private void initImage() {
        HSImageLoader.getInstance().display(this, mIvOri, R.drawable.image1);
//        HSImageLoader.getInstance().display(this, mIvOri, mImageUrl);
//        HSImageLoader.getInstance().display(this, mIvDeal, mImageUrl, mOption);
//        HSImageLoader.getInstance().display(this, mIvOri, mImageUrl, mOption);
    }

    public void loadImage(View view) {
//        HSImageLoader.getInstance().display(this, mIvDeal, R.drawable.image1, mOption);
//        HSImageLoader.getInstance().display(this, mIvDeal, mImageUrl);
//        HSImageLoader.getInstance().display(this, mIvDeal, mImageUrl, mOption);

        Log.i("bloodsoul", "loadImage");
//        Glide.with(this).load(mImageUrl).into(new CustomTarget<Drawable>() {
//            @Override
//            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
//                Log.i("bloodsoul", "loadImage onResourceReady");
//                mIvDeal.setImageDrawable(resource);
//            }
//
//            @Override
//            public void onLoadCleared(@Nullable Drawable placeholder) {
//
//            }
//        });

//        HSImageLoader.getInstance().load(this, mImageUrl, new IImageStrategy.Callback() {
//            @Override
//            public void onLoadCompleted(@NonNull Drawable drawable) {
//                Log.i("bloodsoul", "loadImage onResourceReady");
//                mIvDeal.setImageDrawable(drawable);
//            }
//
//            @Override
//            public void onLoadFailed() {
//                Log.i("bloodsoul", "loadImage onLoadFailed");
//            }
//        });

        Log.i("bloodsoul", "loadImage end");

//        String url = null;
//        HSImageLoader.getInstance().display(this, mIvDeal, url);

//        HSImageLoader.getInstance().display(this, mIvDeal, mImageUrl, mOption);
//        HSImageLoader.getInstance().display(this, mIvDeal, "", mOption);
//        HSImageLoader.getInstance().display(this, mIvDeal, mImageUrl, mOption);

//        HSImageLoader.getInstance().load(this, mDownloadOption, new AdImageCallback(mIvDeal));

//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.image1);
//        HSImageOption option = new HSImageOption.Builder().blur(5, 8).scaleType(ImageView.ScaleType.CENTER_CROP).build();
//        HSImageLoader.getInstance().display(this, mIvDeal, bitmap, option);

//        FutureTarget<Bitmap> futureBitmap = Glide.with(this).asBitmap()
//                .load(mImageUrl)
//                .submit();
//        try {
//            Bitmap bitmap = futureBitmap.get();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

//        Bitmap bitmap = ImageLoader.getInstance().loadImageSync(mImageUrl);
//        Log.i("bloodsoul", "loadImageSync -> success " + (bitmap != null));

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
////                Bitmap bitmap = ImageLoader.getInstance().loadImageSync(mImageUrl);
//                Bitmap bitmap = HSImageLoader.getInstance().loadSync(MainActivity.this, mImageUrl);
//                Log.i("bloodsoul", "loadSync -> success " + (bitmap != null));
//            }
//        }).start();

//        Bitmap bitmap = ImageLoader.getInstance().loadImageSync("drawable://" + R.drawable.image15);
//        Bitmap bitmap = HSImageLoader.getInstance().loadSync(R.drawable.image15);
//        Log.i("bloodsoul", "loadSync -> success " + (bitmap != null));

//        try {
//            Glide.with(this)
//                    .asBitmap()
//                    .load(R.drawable.image15)
//                    .submit()
//                    .get();
//        } catch (ExecutionException | InterruptedException e) {
//            e.printStackTrace();
//        }

//        Glide.with(this)
//                .asBitmap()
//                .load(R.drawable.image15)
//                .into(mIvDeal);
    }

    public void downloadImageSync(View view) {
        Bitmap bitmap = loadSync();
        Log.i("bloodsoul", "downloadImageSync -> success " + (bitmap != null));
    }

    private Bitmap loadSync() {
//        FutureTask<Bitmap> future = new FutureTask<Bitmap>(new Callable<Bitmap>() {
//            @Override
//            public Bitmap call() throws Exception {
//                return Glide.with(MainActivity.this)
//                        .asBitmap()
//                        .load(mImageUrl)
//                        .submit().get();
//            }
//        });
//        new Thread(future).start();

        ExecutorService threadPool = Executors.newCachedThreadPool();
        Future<Bitmap> future = threadPool.submit(new Callable<Bitmap>() {
            @Override
            public Bitmap call() throws Exception {
                return Glide.with(MainActivity.this)
                        .asBitmap()
                        .load(mImageUrl)
                        .submit().get();
            }
        });

        try {
            return future.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void downloadImage(View view) {
        HSImageLoader.getInstance().download(this, mDownloadOption, new IImageStrategy.DownloadCallback() {
            @Override
            public void onLoadStart() {

            }

            @Override
            public void onLoadCompleted(@NonNull File file) {
                Log.i("HSImageLoader", file.getAbsolutePath());
            }

            @Override
            public void onLoadFailed() {
                Log.i("HSImageLoader", "onLoadFailed");
            }
        });
    }

    public void clearMemory(View view) {
        HSImageLoader.getInstance().clearMemoryCache();
    }

    public void clearDisk(View view) {
        HSImageLoader.getInstance().clearDiskCache();
    }

    public void checkDisk(View view) {
        new Thread(() -> {
            String url = "http://img.17sing.tw/uc/img/util_9656998_1589530342.png";
            try {
                File file = Glide.with(MainActivity.this).downloadOnly().load(url).apply(new RequestOptions().onlyRetrieveFromCache(true)).submit().get();
                if (file != null && file.exists()) {
                    runOnUiThread(() -> Toast.makeText(MainActivity.this, "file exists", Toast.LENGTH_SHORT).show());
                } else {
                    runOnUiThread(() -> Toast.makeText(MainActivity.this, "file not exists", Toast.LENGTH_SHORT).show());
                }
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
                runOnUiThread(() -> Toast.makeText(MainActivity.this, "error : file not exists", Toast.LENGTH_SHORT).show());
            }
        }).start();
    }

    public void checkCache(View view) {
        HSImageLoader.getInstance().checkExistCache(this, mImageUrl, new IImageStrategy.CheckExistCacheCallback() {
            @Override
            public void onCheckExistCache(boolean isExist) {
                Log.i("bloodsoul", "onCheckExistCache : " + isExist);
                Toast.makeText(MainActivity.this, "file exists " + isExist, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private static class AdImageCallback implements IImageStrategy.Callback {

        private final WeakReference<ImageView> mImageViewWeakReference;

        AdImageCallback(ImageView imageView) {
            mImageViewWeakReference = new WeakReference<>(imageView);
        }

        @Override
        public void onLoadStart(String url) {

        }

        @Override
        public void onLoadCompleted(String url, @NonNull Bitmap bitmap) {
            if (mImageViewWeakReference.get() == null) {
                return;
            }
            mImageViewWeakReference.get().setImageBitmap(bitmap);
        }

        @Override
        public void onLoadFailed(String url) {

        }
    }
}
