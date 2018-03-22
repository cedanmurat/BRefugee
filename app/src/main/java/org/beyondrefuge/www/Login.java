package org.beyondrefuge.www;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.beyondrefuge.www.Model.User;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Login extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.emailLogin)
    EditText emailEditText;
    @BindView(R.id.passLogin)
    EditText passwordEditText;
    private Button btnSignUp;
    private LoginButton loginButton;
    private FirebaseAuth mAuth;
    CallbackManager callbackManager;
    private FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference mReference = mDatabase.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.login).setOnClickListener(this);
        btnSignUp = (Button) findViewById(R.id.buttonsignup);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, SignUp.class);
                startActivity(intent);
            }
        });
        callbackManager = CallbackManager.Factory.create();
        loginButton = (LoginButton) findViewById(R.id.facebook_login_button);
        loginButton.setReadPermissions("email", "public_profile");
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                handleFacebookAccessToken(loginResult.getAccessToken());

            }

            @Override
            public void onCancel() {
                Toast.makeText(Login.this, "Login Cancelled", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(Login.this, "There is something wrong.", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Pass the activity result back to the Facebook SDK
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser != null) {
            finish();
            startActivity(new Intent(Login.this, MainActivity.class));

            /*DatabaseReference userControl = FirebaseDatabase.getInstance().getReference();
            Query q = userControl.child("users").orderByChild("userId");

            ValueEventListener postListener = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    User fireBaseUser = dataSnapshot.getValue(User.class);

                    if (fireBaseUser.isTagCompleted()) {
                        finish();
                        startActivity(new Intent(Login.this, MainActivity.class));
                    } else {
                        finish();
                        startActivity(new Intent(Login.this, PreferenceActivity.class));

                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Toast.makeText(Login.this, "Connection failed.",
                            Toast.LENGTH_SHORT).show();
                }
            };

            q.addListenerForSingleValueEvent(postListener);
            finish();*/
        } else {

            Toast.makeText(this, "Please Sign In", Toast.LENGTH_SHORT).show();


        }
    }

    public void userLogin() {

        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        if (email.isEmpty()) {
            emailEditText.setError("Email is required");
            emailEditText.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailEditText.setError("Please enter a valid email");
            emailEditText.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            passwordEditText.setError("Password is required");
            passwordEditText.requestFocus();
            return;
        }
        if (password.length() < 6) {
            passwordEditText.setError("Minimum length of password should be 6");
            passwordEditText.requestFocus();
            return;
        }
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    DatabaseReference userControl = FirebaseDatabase.getInstance().getReference();
                    Query q = userControl.child("users").orderByChild("userId");

                    ValueEventListener postListener = new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            User fireBaseUser = dataSnapshot.getValue(User.class);

                            if (fireBaseUser.isTagCompleted()) {
                                finish();
                                startActivity(new Intent(Login.this, MainActivity.class));
                            } else {
                                finish();
                                startActivity(new Intent(Login.this, PreferenceActivity.class));

                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            Toast.makeText(Login.this, "Connection failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    };

                    q.addListenerForSingleValueEvent(postListener);

                } else {
                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login:
                userLogin();
                break;

        }
    }

    private void handleFacebookAccessToken(AccessToken token) {

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            facebookUserControl();
                        } else {

                            Toast.makeText(Login.this, task.getException().getMessage(),
                                    Toast.LENGTH_SHORT).show();

                        }

                    }
                });
    }

    private void writUser(String uid, String uName, String uEmail, boolean uIsTagCompleted) {

        User user = new User(uid, uName, uEmail, uIsTagCompleted);

        mReference.child("users").child(uid).setValue(user);
    }

    private void facebookUserControl() {

        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        DatabaseReference userControl = FirebaseDatabase.getInstance().getReference();
        Query q = userControl.child("users").orderByChild("userId").equalTo( user.getUid());


        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                User fireBaseUser = dataSnapshot.getValue(User.class);

                if (fireBaseUser==null) {

                    writUser(user.getUid(), user.getDisplayName(), user.getEmail(), true);
                    finish();
                    startActivity(new Intent(Login.this, PreferenceActivity.class));
                } else {
                    finish();
                    startActivity(new Intent(Login.this, MainActivity.class));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(Login.this, "Connection failed.",
                        Toast.LENGTH_SHORT).show();
            }
        };

        q.addListenerForSingleValueEvent(postListener);


    }

}
