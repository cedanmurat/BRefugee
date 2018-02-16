package org.beyondrefuge.www;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import butterknife.BindView;


public class SignUp extends AppCompatActivity {
    @BindView(R.id.emailsignup) EditText firstEditText;
    @BindView(R.id.passwordsignup) EditText secondEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }
}
