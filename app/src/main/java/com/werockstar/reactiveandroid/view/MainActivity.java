package com.werockstar.reactiveandroid.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.werockstar.reactiveandroid.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
    }

    @OnClick({R.id.btnForm, R.id.btnRequestApi, R.id.btnTyping})
    void onClickGoToExample(View view) {
        switch (view.getId()) {
            case R.id.btnForm:
                startActivity(new Intent(this, FormActivity.class));
                break;
            case R.id.btnTyping:
                startActivity(new Intent(this, SearchActivity.class));
                break;
            case R.id.btnRequestApi:
                startActivity(new Intent(this, GithubActivity.class));
                break;
        }
    }
}
