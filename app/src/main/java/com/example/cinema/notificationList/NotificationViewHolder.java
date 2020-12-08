package com.example.cinema.notificationList;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinema.R;

public class NotificationViewHolder extends RecyclerView.ViewHolder {
    TextView title;
    TextView text;
    Button button;
    public NotificationViewHolder(@NonNull View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.notificationTitle);
        text = itemView.findViewById(R.id.notificationText);
        button = itemView.findViewById(R.id.notificationButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int readColor = itemView.getContext().getColor(R.color.notifcation_background_read);
                itemView.setBackgroundColor(readColor);
                v.setVisibility(View.INVISIBLE);
            }
        });
    }
}
