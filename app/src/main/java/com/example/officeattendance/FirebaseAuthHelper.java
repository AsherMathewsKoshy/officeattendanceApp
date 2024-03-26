package com.example.officeattendance;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FirebaseAuthHelper {
    private FirebaseAuth mAuth;
    private Context mContext;

    public FirebaseAuthHelper(Context context) {
        mAuth = FirebaseAuth.getInstance();
        mContext = context;
    }

    // Method to check if a user is currently signed in
    public boolean isUserSignedIn() {
        return mAuth.getCurrentUser() != null;
    }

    // Method to get the currently signed-in user
    public FirebaseUser getCurrentUser() {
        return mAuth.getCurrentUser();
    }

    // Method to sign out the current user
    public void signOut() {
        mAuth.signOut();
    }

    // Method to handle authentication result
    public void handleAuthResult(@NonNull Task<AuthResult> task) {
        if (task.isSuccessful()) {
            // Sign in success, update UI with the signed-in user's information
            FirebaseUser user = mAuth.getCurrentUser();
            // You can redirect to the appropriate activity here
        } else {
            // If sign in fails, display a message to the user.
            Toast.makeText(mContext, "Authentication failed.", Toast.LENGTH_SHORT).show();
        }
    }

    // Method to start the Google Sign-In flow
    public void startGoogleSignIn(Activity activity) {
        // Implement Google Sign-In flow here
    }

    // Method to handle the result of Google Sign-In
    public void handleGoogleSignInResult(Intent data) {
        // Handle Google Sign-In result here
    }
}
