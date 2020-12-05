package com.example.cinema.notificationList;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinema.R;

public class NotificationViewHolder extends RecyclerView.ViewHolder {
    TextView title;
    TextView text;
    public NotificationViewHolder(@NonNull View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.notificationTitle);
        text = itemView.findViewById(R.id.notificationText);
    }
}
