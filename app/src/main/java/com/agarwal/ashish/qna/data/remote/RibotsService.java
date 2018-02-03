package com.agarwal.ashish.qna.data.remote;

import com.agarwal.ashish.qna.OkHttpClientProvider;
import com.agarwal.ashish.qna.room.constants.AppConstants;
import com.agarwal.ashish.qna.room.model.Ribot;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface RibotsService {

    @GET("ribots")
    Observable<List<Ribot>> getRibots();

    /******** Helper class that sets up a new services *******/
    class Creator {

        public static RibotsService newRibotsService() {
            Retrofit retrofit = new Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(AppConstants.BASE_URL)
                    .client(new OkHttpClientProvider().getOkHttpClient())
                    .build();
            return retrofit.create(RibotsService.class);
        }

    }
}
