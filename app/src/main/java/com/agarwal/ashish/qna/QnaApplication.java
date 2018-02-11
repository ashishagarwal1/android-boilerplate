package com.agarwal.ashish.qna;

import android.app.Application;
import android.content.Context;

import com.agarwal.ashish.qna.jobscheduler.MyJobCreator;
import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;
import timber.log.Timber;
import com.agarwal.ashish.qna.injection.component.ApplicationComponent;
import com.agarwal.ashish.qna.injection.component.DaggerApplicationComponent;
import com.agarwal.ashish.qna.injection.module.ApplicationModule;
import com.evernote.android.job.JobManager;

public class QnaApplication extends Application  {

    ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initialiseJobCreator();
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
            Fabric.with(this, new Crashlytics());
        }
    }

    protected void initialiseJobCreator() {
        JobManager.create(this).addJobCreator(new MyJobCreator());
    }

    public static QnaApplication get(Context context) {
        return (QnaApplication) context.getApplicationContext();
    }

    public ApplicationComponent getComponent() {
        if (mApplicationComponent == null) {
            mApplicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .build();
        }
        return mApplicationComponent;
    }

    // Needed to replace the component with a test specific one
    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }
}
