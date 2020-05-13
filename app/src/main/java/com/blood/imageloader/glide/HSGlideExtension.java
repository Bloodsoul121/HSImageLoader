package com.blood.imageloader.glide;

import com.bumptech.glide.annotation.GlideExtension;
import com.bumptech.glide.annotation.GlideOption;
import com.bumptech.glide.request.BaseRequestOptions;

@GlideExtension
public class HSGlideExtension {

    private HSGlideExtension() {
    }

    /**
     * 高斯模糊
     */
    @GlideOption
    public static BaseRequestOptions<?> blur(BaseRequestOptions<?> options, int radius) {
        return options.transform(new BlurTransformation(radius));
    }
}
