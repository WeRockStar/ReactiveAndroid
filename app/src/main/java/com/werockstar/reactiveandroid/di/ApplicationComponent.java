package com.werockstar.reactiveandroid.di;


import com.werockstar.reactiveandroid.view.FormActivity;
import com.werockstar.reactiveandroid.view.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = HttpModule.class)
public interface ApplicationComponent {
    void inject(MainActivity activity);
    void inject(FormActivity activity);
}
