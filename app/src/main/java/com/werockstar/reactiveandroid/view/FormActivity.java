package com.werockstar.reactiveandroid.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jakewharton.rxbinding2.widget.RxTextView;
import com.werockstar.reactiveandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;

public class FormActivity extends AppCompatActivity {

    @BindView(R.id.edtUsername)
    EditText edtUsername;

    @BindView(R.id.edtPassword)
    EditText edtPassword;

    @BindView(R.id.edtAge)
    EditText edtAge;

    @BindView(R.id.btnSubmit)
    Button btnSubmit;

    private final CompositeDisposable disposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        ButterKnife.bind(this);

        Observable<String> usernameObs = RxTextView.textChanges(edtUsername)
                .map(username -> username.toString());

        Observable<String> passwordObs = RxTextView.textChanges(edtPassword)
                .map(password -> password.toString());

        Observable<Integer> ageObs = RxTextView.textChanges(edtAge)
                .map(age -> age.toString())
                .map(age -> age.equalsIgnoreCase("") ? "0" : age)
                .map(age -> Integer.parseInt(age));

        disposable.add(Observable.combineLatest(usernameObs, passwordObs, ageObs,
                (u, p, a) -> u.length() > 5 && p.length() > 3 && a > 0)
                .retry(2)
                .subscribe(
                        enable -> btnSubmit.setEnabled(enable),
                        throwable -> {
                            Toast.makeText(this, throwable.getMessage(), Toast.LENGTH_LONG).show();
                        }));

    }

    @Override
    protected void onDestroy() {
        disposable.clear();
        super.onDestroy();
    }
}
