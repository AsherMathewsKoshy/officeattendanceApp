package com.example.officeattendance;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.officeattendance.EmployeeListAdapter; // Import the adapter directly
import com.example.officeattendance.Employee;
import com.example.officeattendance.FirestoreHelper;

import java.util.ArrayList;

public class AdminDashboardActivity extends AppCompatActivity {

    private static final String TAG = "AdminDashboardActivity";

    private RecyclerView recyclerView;
    private EmployeeListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        recyclerView = findViewById(R.id.recycler_view_employee_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadEmployeeList();
    }

    private void loadEmployeeList() {
        FirestoreHelper firestoreHelper = new FirestoreHelper();

        // Get list of employees from Firestore
        firestoreHelper.getEmployees(new FirestoreHelper.EmployeeListCallback() {
            @Override
            public void onCallback(ArrayList<Employee> employeeList) {
                // Populate RecyclerView with employee data
                adapter = new EmployeeListAdapter(AdminDashboardActivity.this, employeeList);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e(TAG, "Error getting employee list: " + e.getMessage());
                Toast.makeText(AdminDashboardActivity.this, "Failed to retrieve employee list", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
