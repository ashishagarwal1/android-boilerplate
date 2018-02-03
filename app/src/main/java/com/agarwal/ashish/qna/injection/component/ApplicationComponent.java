package com.agarwal.ashish.qna.injection.component;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import com.agarwal.ashish.qna.data.DataManager;
import com.agarwal.ashish.qna.data.SyncService;
import com.agarwal.ashish.qna.data.local.PreferencesHelper;
import com.agarwal.ashish.qna.room.database.AppDatabase;
import com.agarwal.ashish.qna.data.remote.RibotsService;
import com.agarwal.ashish.qna.injection.ApplicationContext;
import com.agarwal.ashish.qna.injection.module.ApplicationModule;
import com.agarwal.ashish.qna.util.RxEventBus;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(SyncService syncService);

    @ApplicationContext Context context();
    Application application();
    RibotsService ribotsService();
    PreferencesHelper preferencesHelper();
    AppDatabase getDatabase();
    DataManager dataManager();
    RxEventBus eventBus();

}
