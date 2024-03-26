package com.example.officeattendance;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.officeattendance.R;
import com.example.officeattendance.LeaveApplicationAdapter;
import com.example.officeattendance.LeaveApplication;
import com.example.officeattendance.FirestoreHelper;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class LeaveApprovalActivity extends AppCompatActivity {

    private static final String TAG = "LeaveApprovalActivity";

    private RecyclerView recyclerView;
    private LeaveApplicationAdapter adapter;
    private FirestoreHelper firestoreHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_approval);

        firestoreHelper = new FirestoreHelper();

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

        firestoreHelper.getPendingLeaveApplications(new FirestoreHelper.LeaveApplicationListCallback() {
            @Override
            public void onCallback(ArrayList<LeaveApplication> leaveApplications) {
                adapter = new LeaveApplicationAdapter(LeaveApprovalActivity.this, leaveApplications);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e(TAG, "Error getting pending leave applications: " + e.getMessage());
                Toast.makeText(LeaveApprovalActivity.this, "Failed to retrieve pending leave applications", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
