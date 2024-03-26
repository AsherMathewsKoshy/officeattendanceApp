package com.example.officeattendance;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.officeattendance.R;
import com.example.officeattendance.LeaveApplication;
import com.example.officeattendance.FirestoreHelper;

public class LeaveApplicationActivity extends AppCompatActivity {

    private EditText etLeaveDate, etReason;
    private Button btnSubmit;
    private FirestoreHelper firestoreHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_application);

        firestoreHelper = new FirestoreHelper();

        etLeaveDate = findViewById(R.id.et_leave_date);
        etReason = findViewById(R.id.et_leave_reason);
        btnSubmit = findViewById(R.id.btn_submit_leave);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitLeaveApplication();
            }
        });
    }

    private void submitLeaveApplication() {
        String leaveDate = etLeaveDate.getText().toString().trim();
        String reason = etReason.getText().toString().trim();

        if (TextUtils.isEmpty(leaveDate)) {
            Toast.makeText(this, "Enter leave date", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(reason)) {
            Toast.makeText(this, "Enter reason for leave", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create a new LeaveApplication object
        LeaveApplication leaveApplication = new LeaveApplication(leaveDate, reason);

        // Submit leave application to Firestore
        firestoreHelper.submitLeaveApplication(leaveApplication, new FirestoreHelper.SubmitLeaveApplicationCallback() {
            @Override
            public void onSuccess() {
                Toast.makeText(LeaveApplicationActivity.this, "Leave application submitted successfully", Toast.LENGTH_SHORT).show();
                finish(); // Close activity after successful submission
            }

            @Override
            public void onFailure(Exception e) {
                Toast.makeText(LeaveApplicationActivity.this, "Failed to submit leave application: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
