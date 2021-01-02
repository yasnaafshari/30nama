package com.example.cinema.titleslist;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinema.R;
import com.example.cinema.core.Network;
import com.example.cinema.details.DetailsFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TitlesListViewHolder extends RecyclerView.ViewHolder {
    OnItemListener mOnItemListener;
    ImageView imageView;
    TextView textView;
    String url;
    int id;
    String nonce;
    int titleType;

    public TitlesListViewHolder(@NonNull View itemView, int titleType,OnItemListener mOnItemListener) {
        super(itemView);
        this.titleType = titleType;
        this.mOnItemListener = mOnItemListener;

        textView = itemView.findViewById(R.id.itemText);
        imageView = itemView.findViewById(R.id.itemImage);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(itemView.getContext());
        String token = preferences.getString("token", null);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemListener.onItemClick(url,titleType);

            }
        });
    }

public interface OnItemListener{
        void onItemClick(String url,int titleType);
}
}
