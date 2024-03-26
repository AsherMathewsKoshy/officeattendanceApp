package com.example.officeattendance;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class FirestoreHelper {

    private FirebaseFirestore db;
    private CollectionReference leaveApplicationsCollection;
    private CollectionReference employeesCollection;

    public FirestoreHelper() {
        db = FirebaseFirestore.getInstance();
        leaveApplicationsCollection = db.collection("leave_applications");
        employeesCollection = db.collection("employees");
    }

    // Method to fetch leave applications for a specific user
    public void getLeaveApplications(String userId, final LeaveApplicationListCallback callback) {
        leaveApplicationsCollection.whereEqualTo("userId", userId).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ArrayList<LeaveApplication> leaveApplications = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                LeaveApplication leaveApplication = document.toObject(LeaveApplication.class);
                                leaveApplications.add(leaveApplication);
                            }
                            callback.onCallback(leaveApplications);
                        } else {
                            callback.onFailure(task.getException());
                        }
                    }
                });
    }

    // Method to fetch list of employees
    public void getEmployees(final EmployeeListCallback callback) {
        employeesCollection.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    ArrayList<Employee> employeesList = new ArrayList<>();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Employee employee = document.toObject(Employee.class);
                        employeesList.add(employee);
                    }
                    callback.onCallback(employeesList);
                } else {
                    callback.onFailure(task.getException());
                }
            }
        });
    }

    // Method to submit a leave application to Firestore
    public void submitLeaveApplication(LeaveApplication leaveApplication, final SubmitLeaveApplicationCallback callback) {
        leaveApplicationsCollection.add(leaveApplication)
                .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        if (task.isSuccessful()) {
                            callback.onSuccess();
                        } else {
                            callback.onFailure(task.getException());
                        }
                    }
                });
    }

    // Method to get pending leave applications
    public void getPendingLeaveApplications(final LeaveApplicationListCallback callback) {
        leaveApplicationsCollection.whereEqualTo("status", "pending").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ArrayList<LeaveApplication> leaveApplications = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                LeaveApplication leaveApplication = document.toObject(LeaveApplication.class);
                                leaveApplications.add(leaveApplication);
                            }
                            callback.onCallback(leaveApplications);
                        } else {
                            callback.onFailure(task.getException());
                        }
                    }
                });
    }

    // Interface for handling leave application list callback
    public interface LeaveApplicationListCallback {
        void onCallback(ArrayList<LeaveApplication> leaveApplications);
        void onFailure(Exception e);
    }

    // Interface for handling employee list callback
    public interface EmployeeListCallback {
        void onCallback(ArrayList<Employee> employeesList);
        void onFailure(Exception e);
    }

    // Interface for handling leave application submission callback
    public interface SubmitLeaveApplicationCallback {
        void onSuccess();
        void onFailure(Exception e);
    }
}
