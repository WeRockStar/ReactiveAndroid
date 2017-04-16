package com.werockstar.reactiveandroid.di;


import com.werockstar.reactiveandroid.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = HttpModule.class)
public interface ApplicationComponent {
    void inject(MainActivity activity);
}
