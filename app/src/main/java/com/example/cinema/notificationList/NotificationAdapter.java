package com.example.cinema.notificationList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinema.R;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationViewHolder> {
    public NotificationAdapter(List<NotificationModel> notificationModels) {
        yNotificationModels = notificationModels;
    }

    List<NotificationModel> yNotificationModels;


    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_notification, parent, false);
        return new NotificationViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position) {
        holder.title.setText(yNotificationModels.get(position).title);
        holder.text.setText(yNotificationModels.get(position).text);
        int notReadColor = holder.itemView.getContext().getColor(R.color.notifcation_background_not_read);
        int readColor = holder.itemView.getContext().getColor(R.color.notifcation_background_read);
        if (yNotificationModels.get(position).is_read) {
            holder.itemView.setBackgroundColor(readColor);
            holder.button.setVisibility(View.INVISIBLE);
        } else {
            holder.itemView.setBackgroundColor(notReadColor);
            holder.button.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return yNotificationModels.size();
    }
}
