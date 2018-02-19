package org.beyondrefuge.www;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthActionCodeException;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class SignUp extends AppCompatActivity {
    @BindView(R.id.emailsignup) EditText emailEditText;
    @BindView(R.id.passwordsignup) EditText passwordEditText;
    @BindView(R.id.confirmation) EditText confirmationEditText;
    Button btnLogin;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        mAuth=FirebaseAuth.getInstance();

       btnLogin=(Button) findViewById(R.id.buttonsignin);
       btnLogin.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent1=new Intent(SignUp.this,Login.class);
               startActivity(intent1);
           }
       });
    }

    @OnClick(R.id.createAccount)
    public  void createAccount(View view){
    String email=emailEditText.getText().toString().trim();
    String password=passwordEditText.getText().toString().trim();
    String confirmation=confirmationEditText.getText().toString().trim();
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
    if (!confirmation.equals(password)){
        confirmationEditText.setError("Please check your password");

    }
    mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {
            if (task.isSuccessful()){
              Intent intent1=new Intent(SignUp.this,Login.class);
              startActivity(intent1);
                //  Toast.makeText(getApplicationContext(),"User registered successfull",Toast.LENGTH_SHORT).show();
            }
            else {
                if(task.getException() instanceof FirebaseAuthUserCollisionException){
                    Toast.makeText(getApplicationContext(), "You are already registered", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(),task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }
    });
    }


}
