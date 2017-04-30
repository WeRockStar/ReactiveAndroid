package com.werockstar.reactiveandroid.di;


import com.werockstar.reactiveandroid.view.FormActivity;
import com.werockstar.reactiveandroid.view.GithubActivity;
import com.werockstar.reactiveandroid.view.MainActivity;
import com.werockstar.reactiveandroid.view.SearchActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {HttpModule.class, AndroidModule.class})
public interface ApplicationComponent {
    void inject(MainActivity activity);
    void inject(FormActivity activity);
    void inject(GithubActivity activity);
    void inject(SearchActivity activity);
}
