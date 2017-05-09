package com.werockstar.reactiveandroid;

import android.app.Application;

import com.crashlytics.android.Crashlytics;
import com.werockstar.reactiveandroid.di.AndroidModule;
import com.werockstar.reactiveandroid.di.ApplicationComponent;
import com.werockstar.reactiveandroid.di.DaggerApplicationComponent;
import com.werockstar.reactiveandroid.di.HttpModule;
import io.fabric.sdk.android.Fabric;


public class ReactiveApplication extends Application {

    ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());

        component = DaggerApplicationComponent.builder()
                .androidModule(new AndroidModule(this))
                .httpModule(new HttpModule())
                .build();
    }

    public ApplicationComponent getComponent() {
        return component;
    }
}
