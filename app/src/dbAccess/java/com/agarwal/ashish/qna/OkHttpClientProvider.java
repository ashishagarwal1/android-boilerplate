package com.agarwal.ashish.qna;

/**
 * Created by ashishaggarwal on 21/01/18.
 */

public class OkHttpClientProvider {

    public okhttp3.OkHttpClient getOkHttpClient() {
        return new okhttp3.OkHttpClient.Builder()
                .addNetworkInterceptor(new com.facebook.stetho.okhttp3.StethoInterceptor())
                .build();
    }
}
