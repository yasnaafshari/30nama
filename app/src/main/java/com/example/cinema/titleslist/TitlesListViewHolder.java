package com.example.cinema.titleslist;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinema.R;
import com.example.cinema.details.DetailsActivity;

public class TitlesListViewHolder extends RecyclerView.ViewHolder {
    ImageView imageView;
    TextView textView;
String url;
    public TitlesListViewHolder(@NonNull View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.itemText);
        imageView = itemView.findViewById(R.id.itemImage);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(imageView.getContext(), DetailsActivity.class);
                i.putExtra("url", url);
                imageView.getContext().startActivity(i);
            }
        });
    }

}
