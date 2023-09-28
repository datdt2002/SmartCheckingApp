package vn.skymapglobal.smartcheckingapp.adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vn.skymapglobal.smartcheckingapp.R;
import vn.skymapglobal.smartcheckingapp.fragments.HistoryFragment;
import vn.skymapglobal.smartcheckingapp.models.Checking_Log;

public class CheckingLogAdapter extends RecyclerView.Adapter<CheckingLogAdapter.CheckingLogHolder> {

    private HistoryFragment context;
    private List<Checking_Log> checkingLogs;

    public CheckingLogAdapter(HistoryFragment context) {
        this.context = context;
    }

    public void setData(List<Checking_Log> list) {
        this.checkingLogs = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CheckingLogHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_checking_log,parent,false);
        return new CheckingLogHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CheckingLogHolder holder, int position) {
        Checking_Log checking_log = checkingLogs.get(position);
        if (checking_log==null) return;
        holder.txtName.setText(checking_log.getUsername());
        holder.txtCheckingTime.setText(checking_log.getTime().toString());
        if (checking_log.getCheckingTypeId()==1) {
            holder.itemView.setBackgroundColor(Color.parseColor("#00ff00"));
        }
        else if (checking_log.getCheckingTypeId()==2) {
            holder.itemView.setBackgroundColor(Color.parseColor("#FFFF00"));
        }
    }

    @Override
    public int getItemCount() {
        if (!checkingLogs.isEmpty()) return checkingLogs.size();
        return 0;
    }

    public class CheckingLogHolder extends RecyclerView.ViewHolder {

        private TextView txtName;
        private TextView txtCheckingTime;
        public CheckingLogHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtCheckingTime = itemView.findViewById(R.id.txtCheckingTime);
        }
    }
}