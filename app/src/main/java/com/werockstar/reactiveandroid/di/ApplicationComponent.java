package com.werockstar.reactiveandroid.di;


import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = HttpModule.class)
public class ApplicationComponent {
}
