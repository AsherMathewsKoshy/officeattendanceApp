package com.example.officeattendance;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.officeattendance.R;
import com.example.officeattendance.LeaveApplication;

import java.util.ArrayList;

public class LeaveApplicationAdapter extends RecyclerView.Adapter<LeaveApplicationAdapter.LeaveApplicationViewHolder> {

    private Context context;
    private ArrayList<LeaveApplication> leaveApplications;

    public LeaveApplicationAdapter(Context context, ArrayList<LeaveApplication> leaveApplications) {
        this.context = context;
        this.leaveApplications = leaveApplications;
    }

    @NonNull
    @Override
    public LeaveApplicationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_leave_application, parent, false);
        return new LeaveApplicationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LeaveApplicationViewHolder holder, int position) {
        LeaveApplication leaveApplication = leaveApplications.get(position);
        holder.bind(leaveApplication);
    }

    @Override
    public int getItemCount() {
        return leaveApplications.size();
    }

    public class LeaveApplicationViewHolder extends RecyclerView.ViewHolder {

        private TextView tvLeaveDate, tvReason;

        public LeaveApplicationViewHolder(@NonNull View itemView) {
            super(itemView);
            tvLeaveDate = itemView.findViewById(R.id.tv_leave_date);
            tvReason = itemView.findViewById(R.id.tv_reason);
        }

        public void bind(LeaveApplication leaveApplication) {
            tvLeaveDate.setText(leaveApplication.getLeaveDate());
            tvReason.setText(leaveApplication.getReason());
        }
    }
}
