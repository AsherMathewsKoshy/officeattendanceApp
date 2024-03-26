package com.example.officeattendance;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.officeattendance.LeaveApplicationAdapter;
import com.example.officeattendance.LeaveApplication;
import com.example.officeattendance.FirestoreHelper;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class EmployeePortalActivity extends AppCompatActivity {

    private static final String TAG = "EmployeePortalActivity";

    private RecyclerView recyclerView;
    private LeaveApplicationAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_portal);

        recyclerView = findViewById(R.id.recycler_view_leave_applications);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadLeaveApplications();
    }

    private void loadLeaveApplications() {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser == null) {
            // User not logged in
            return;
        }

        FirestoreHelper firestoreHelper = new FirestoreHelper();

        // Get leave applications for the current user from Firestore
        firestoreHelper.getLeaveApplications(currentUser.getUid(), new FirestoreHelper.LeaveApplicationListCallback() {
            @Override
            public void onCallback(ArrayList<LeaveApplication> leaveApplications) {
                // Populate RecyclerView with leave application data
                adapter = new LeaveApplicationAdapter(EmployeePortalActivity.this, leaveApplications);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e(TAG, "Error getting leave applications: " + e.getMessage());
                Toast.makeText(EmployeePortalActivity.this, "Failed to retrieve leave applications", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
