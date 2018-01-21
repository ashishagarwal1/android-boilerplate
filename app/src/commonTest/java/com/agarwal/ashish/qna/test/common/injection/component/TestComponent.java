package com.agarwal.ashish.qna.test.common.injection.component;

import javax.inject.Singleton;

import dagger.Component;
import com.agarwal.ashish.qna.injection.component.ApplicationComponent;
import com.agarwal.ashish.qna.test.common.injection.module.ApplicationTestModule;

@Singleton
@Component(modules = ApplicationTestModule.class)
public interface TestComponent extends ApplicationComponent {

}
