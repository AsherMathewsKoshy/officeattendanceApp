package com.example.officeattendance;

public class Employee {
    private String name;
    private String address;
    private String department;

    // Default constructor required for Firestore
    public Employee() {
    }

    public Employee(String name, String address, String department) {
        this.name = name;
        this.address = address;
        this.department = department;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
