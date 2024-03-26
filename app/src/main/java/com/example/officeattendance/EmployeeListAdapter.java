package com.example.officeattendance;
import com.example.officeattendance.Employee;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class EmployeeListAdapter extends RecyclerView.Adapter<EmployeeListAdapter.EmployeeViewHolder> {

    private Context context;
    private ArrayList<Employee> employeeList;

    public EmployeeListAdapter(Context context, ArrayList<Employee> employeeList) {
        this.context = context;
        this.employeeList = employeeList;
    }

    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_employee, parent, false);
        return new EmployeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder holder, int position) {
        Employee employee = employeeList.get(position);
        holder.bind(employee);
    }

    @Override
    public int getItemCount() {
        return employeeList.size();
    }

    public class EmployeeViewHolder extends RecyclerView.ViewHolder {

        private TextView tvName, tvAddress, tvDepartment;

        public EmployeeViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvAddress = itemView.findViewById(R.id.tv_address);
            tvDepartment = itemView.findViewById(R.id.tv_department);
        }

        public void bind(Employee employee) {
            tvName.setText(employee.getName());
            tvAddress.setText(employee.getAddress());
            tvDepartment.setText(employee.getDepartment());
        }
    }
}
