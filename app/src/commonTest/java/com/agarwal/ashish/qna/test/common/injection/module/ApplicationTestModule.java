package com.agarwal.ashish.qna.test.common.injection.module;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import com.agarwal.ashish.qna.data.DataManager;
import com.agarwal.ashish.qna.data.remote.RibotsService;
import com.agarwal.ashish.qna.injection.ApplicationContext;
import com.agarwal.ashish.qna.room.database.AppDatabase;

import static org.mockito.Mockito.mock;

/**
 * Provides application-level dependencies for an app running on a testing environment
 * This allows injecting mocks if necessary.
 */
@Module
public class ApplicationTestModule {

    private final Application mApplication;

    public ApplicationTestModule(Application application) {
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

    /************* MOCKS *************/

    @Provides
    @Singleton
    DataManager provideDataManager() {
        return mock(DataManager.class);
    }

    @Provides
    @Singleton
    RibotsService provideRibotsService() {
        return mock(RibotsService.class);
    }

    @Provides
    @Singleton
    AppDatabase provideDatabase() {
        return AppDatabase.getAppDatabase(mApplication);
    }
}
