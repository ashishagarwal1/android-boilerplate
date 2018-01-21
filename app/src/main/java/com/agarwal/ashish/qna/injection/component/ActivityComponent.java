package com.agarwal.ashish.qna.injection.component;

import dagger.Subcomponent;
import com.agarwal.ashish.qna.injection.PerActivity;
import com.agarwal.ashish.qna.injection.module.ActivityModule;
import com.agarwal.ashish.qna.ui.main.MainActivity;

/**
 * This component inject dependencies to all Activities across the application
 */
@PerActivity
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);

}
