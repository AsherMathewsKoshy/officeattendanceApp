package com.example.officeattendance;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Button btnAdmin, btnEmployee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        btnAdmin = findViewById(R.id.btn_admin);
        btnEmployee = findViewById(R.id.btn_employee);

        btnAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInAsAdmin();
            }
        });

        btnEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInAsEmployee();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            redirectToDashboard(currentUser);
        }
    }

    private void signInAsAdmin() {
        // Implement admin sign-in logic here
        // For example, you might use Firebase Authentication to authenticate admins
        // After successful authentication, redirect to AdminDashboardActivity
        Toast.makeText(this, "Admin Sign In", Toast.LENGTH_SHORT).show();
        // Temporary redirection
        startActivity(new Intent(MainActivity.this, AdminDashboardActivity.class));
    }

    private void signInAsEmployee() {
        // Implement employee sign-in logic here
        // For example, you might use Firebase Authentication to authenticate employees
        // After successful authentication, redirect to EmployeePortalActivity
        Toast.makeText(this, "Employee Sign In", Toast.LENGTH_SHORT).show();
        // Temporary redirection
        startActivity(new Intent(MainActivity.this, EmployeePortalActivity.class));
    }

    private void redirectToDashboard(FirebaseUser user) {
        if (userIsAdmin(user)) {
            startActivity(new Intent(MainActivity.this, AdminDashboardActivity.class));
        } else {
            startActivity(new Intent(MainActivity.this, EmployeePortalActivity.class));
        }
        finish(); // Finish the MainActivity so the user can't navigate back to it
    }

    private boolean userIsAdmin(FirebaseUser user) {
        // Implement logic to determine if the user is an admin
        // For example, you might check user roles in Firestore database
        return false; // Change this according to your implementation
    }
}
