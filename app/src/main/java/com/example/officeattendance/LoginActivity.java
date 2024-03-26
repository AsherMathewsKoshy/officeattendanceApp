package com.example.officeattendance;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.officeattendance.AdminDashboardActivity;
import com.example.officeattendance.EmployeePortalActivity;
import com.example.officeattendance.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";

    private EditText inputEmail, inputPassword;
    private Button btnLogin;
    private ProgressBar progressBar;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();

        inputEmail = findViewById(R.id.email);
        inputPassword = findViewById(R.id.password);
        btnLogin = findViewById(R.id.btn_login);
        progressBar = findViewById(R.id.progressBar);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                // Authenticate user with email and password
                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(LoginActivity.this, task -> {
                            progressBar.setVisibility(View.GONE);
                            if (!task.isSuccessful()) {
                                // Authentication failed
                                Log.e(TAG, "signInWithEmail:failure", task.getException());
                                Toast.makeText(LoginActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                // Authentication successful
                                FirebaseUser user = auth.getCurrentUser();
                                if (user != null) {
                                    // Determine user role and redirect accordingly
                                    if (userIsAdmin(user)) {
                                        startActivity(new Intent(LoginActivity.this, AdminDashboardActivity.class));
                                    } else {
                                        startActivity(new Intent(LoginActivity.this, EmployeePortalActivity.class));
                                    }
                                    finish();
                                }
                            }
                        });
            }
        });
    }

    private boolean userIsAdmin(FirebaseUser user) {
        // Add your logic here to determine if the user is an admin
        // For example, you might check user roles in Firestore database
        return false; // Change this accordingly
    }
}
