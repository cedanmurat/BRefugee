package org.beyondrefuge.www;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;

public class Login extends AppCompatActivity implements View.OnClickListener{
    @BindView(R.id.emailLogin) EditText emailEditText;
    @BindView(R.id.passLogin) EditText passwordEditText;
    Button btnLogin,btnSignUp;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth=FirebaseAuth.getInstance();
        emailEditText=(EditText)findViewById(R.id.emailLogin);
        passwordEditText=(EditText)findViewById(R.id.passLogin);

        findViewById(R.id.login).setOnClickListener(this);
        btnSignUp=(Button)findViewById(R.id.buttonsignup);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login.this,SignUp.class);
                startActivity(intent);
            }
        });

    }
public void useerLogin(){

    String email=emailEditText.getText().toString().trim();
    String password=passwordEditText.getText().toString().trim();
    if(email.isEmpty()){
        emailEditText.setError("Email is required");
        emailEditText.requestFocus();
        return;
    }
    if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
        emailEditText.setError("Please enter a valid email");
        emailEditText.requestFocus();
        return;
    }
    if(password.isEmpty()){
        passwordEditText.setError("Password is required");
        passwordEditText.requestFocus();
        return;
    }
    if(password.length()<6){
        passwordEditText.setError("Minimum length of password should be 6");
        passwordEditText.requestFocus();
        return;
    }
    mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {
            if (task.isSuccessful()){

            }else {
                Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    });

}

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.login:
                useerLogin();
                break;

    }
}
}
