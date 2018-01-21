package com.agarwal.ashish.qna;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.GlideModule;

import java.io.InputStream;

/**
 * Created by ashishaggarwal on 21/01/18.
 */

public class StethoOkHttpGlideModule implements GlideModule {
    @Override
    public void applyOptions(Context context, GlideBuilder builder) { }

    @Override
    public void registerComponents(Context context, Glide glide) {
        okhttp3.OkHttpClient client = new okhttp3.OkHttpClient.Builder()
                .addNetworkInterceptor(new com.facebook.stetho.okhttp3.StethoInterceptor())
                .build();
        glide.register(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory(client));
    }
}