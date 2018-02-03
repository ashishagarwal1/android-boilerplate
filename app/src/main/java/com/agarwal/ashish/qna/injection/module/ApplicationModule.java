package com.agarwal.ashish.qna.injection.module;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import com.agarwal.ashish.qna.room.database.AppDatabase;
import com.agarwal.ashish.qna.data.remote.RibotsService;
import com.agarwal.ashish.qna.injection.ApplicationContext;

/**
 * Provide application-level dependencies.
 */
@Module
public class ApplicationModule {
    protected final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    @Singleton
    RibotsService provideRibotsService() {
        return RibotsService.Creator.newRibotsService();
    }

    @Provides
    @Singleton
    AppDatabase provideDatabase() {
        return AppDatabase.getAppDatabase(mApplication);
    }

}
