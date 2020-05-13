package com.blood;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.blood.imageloader.HSImageLoader;
import com.blood.imageloader.HSImageOption;
import com.blood.imageloader.IImageStrategy;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.iv_ori)
    ImageView mIvOri;
    @BindView(R.id.iv_deal)
    ImageView mIvDeal;

    private HSImageOption mOption;

    private String mImageUrl = "http://img.17sing.tw/ktv_img/hsing_ktvroom_1487841535_100866.png";

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
                .placeholder(R.drawable.image11)
                .error(R.drawable.image12)
                .fallback(R.drawable.image13)
//                .skipDiskCache(true)
//                .skipMemoryCache(true)
//                .angle(DPUtil.dip2px(this, 10f))
                .circle()
//                .resize(DPUtil.dip2px(this, 50f), DPUtil.dip2px(this, 50f))
                .build();
    }

    private void initImage() {
//        HSImageLoader.getInstance().display(this, mIvOri, R.drawable.image1);
        HSImageLoader.getInstance().display(this, mIvOri, mImageUrl);
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

        HSImageLoader.getInstance().display(this, mIvDeal, "");
    }

    public void clearMemory(View view) {
        HSImageLoader.getInstance().clearMemoryCache();
    }

    public void clearDisk(View view) {
        HSImageLoader.getInstance().clearDiskCache();
    }
}
