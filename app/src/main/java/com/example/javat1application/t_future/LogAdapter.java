package com.example.javat1application.t_future;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.javat1application.R;

import java.util.ArrayList;
import java.util.List;

public class LogAdapter extends RecyclerView.Adapter<LogAdapter.LogViewHolder> {
    private List<LogEntry> logs = new ArrayList<>();



    @NonNull
    @Override
    public LogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_log, parent, false);
        return new LogViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LogViewHolder holder, int position) {
        LogEntry log = logs.get(position);
        holder.bind(log);
    }

    @Override
    public int getItemCount() {
        return logs.size();
    }

    public List<LogEntry> getLogs() {
        return logs;
    }
    public void addLog(LogEntry log) {
        logs.add(0, log);  // 최신 로그를 위에 추가
        notifyItemInserted(0);
    }

    public void setLogs(List<LogEntry> logs) {
        this.logs = logs;
        notifyDataSetChanged();
    }



    static class LogViewHolder extends RecyclerView.ViewHolder {
        private final TextView timestampText;
        private final TextView actionText;
        private final TextView valueText;

        public LogViewHolder(@NonNull View itemView) {
            super(itemView);
            timestampText = itemView.findViewById(R.id.logTimestamp);
            actionText = itemView.findViewById(R.id.logAction);
            valueText = itemView.findViewById(R.id.logValue);
        }

        public void bind(LogEntry log) {
            timestampText.setText(log.getTimestamp());
            actionText.setText(log.getAction());
            valueText.setText(log.getValue());
        }
    }
}