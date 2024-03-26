package com.example.officeattendance;

public class LeaveApplication {
    private String leaveDate;
    private String reason;
    private String status; // Pending, Approved, Rejected

    // Default constructor required for Firestore
    public LeaveApplication() {
    }

    public LeaveApplication(String leaveDate, String reason) {
        this.leaveDate = leaveDate;
        this.reason = reason;
        this.status = "Pending"; // Default status is pending
    }

    // Getters and setters
    public String getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(String leaveDate) {
        this.leaveDate = leaveDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
