package com.example.chinago;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Signup extends AppCompatActivity {

    private EditText email, password, confirmPassword;
    private Button btnSignup;
    private FirebaseAuth firebaseAuth;
    private ProgressBar progressBar;
    private TextView loginLink;


    private TextInputLayout emailLayout, passwordLayout, confirmPasswordLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        email = findViewById(R.id.emailSignupField);
        password = findViewById(R.id.passwordSignupField);
        confirmPassword = findViewById(R.id.confirmPasswordField);
        btnSignup = findViewById(R.id.btnSignup);
        progressBar = findViewById(R.id.progressBarSignup);
        firebaseAuth = FirebaseAuth.getInstance();
        loginLink = findViewById(R.id.loginLink);
        emailLayout = findViewById(R.id.emailSignupLayout);
        passwordLayout = findViewById(R.id.passwordSignupLayout);
        confirmPasswordLayout = findViewById(R.id.confirmPasswordLayout);

        // check if user has already login or not
        if (firebaseAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }

        // email field validation handler
        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    emailLayout.setError("Email is required");
                } else {
                    emailLayout.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        // password field validation handler
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    passwordLayout.setError("Password is required");
                } else if (s.length() < 6 || s.length() > 16) {
                    passwordLayout.setError("Password minimum length is 6 and maximum length is 16");
                } else {
                    passwordLayout.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        // confirm password field validation handler
        confirmPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    confirmPasswordLayout.setError("Confirm password is required");
                } else {
                    confirmPasswordLayout.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email = email.getText().toString().trim();
                String Password = password.getText().toString().trim();
                String ConfirmPassword = confirmPassword.getText().toString().trim();

                if (Email.isEmpty() || Password.isEmpty() || ConfirmPassword.isEmpty()) {
                    Toast.makeText(Signup.this, "Please enter valid email and password", Toast.LENGTH_SHORT).show();
                } else if (!ConfirmPassword.matches(Password)) {
                    Toast.makeText(Signup.this, "Confirm password did not match your password", Toast.LENGTH_SHORT).show();
                    confirmPasswordLayout.setError(" ");
                } else {
                    progressBar.setVisibility(View.VISIBLE);

                    firebaseAuth.createUserWithEmailAndPassword(Email, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(Signup.this, "Successfully login", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            } else {
                                Toast.makeText(Signup.this, "Invalid email and password or email already be taken", Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.INVISIBLE);
                            }
                        }
                    });
                }
            }
        });

        loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Login.class));
            }
        });
    }
}