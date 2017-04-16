package com.werockstar.reactiveandroid;

import android.app.Application;

import com.werockstar.reactiveandroid.di.ApplicationComponent;
import com.werockstar.reactiveandroid.di.DaggerApplicationComponent;
import com.werockstar.reactiveandroid.di.HttpModule;


public class ReactiveApplication extends Application {

    ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerApplicationComponent.builder()
                .httpModule(new HttpModule())
                .build();
    }

    public ApplicationComponent getComponent() {
        return component;
    }
}
