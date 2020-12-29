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
    Button likeButton;
    Button downloadButton;
    Button watchListButton;
    int id;
    String nonce;
    int titleType;

    public TitlesListViewHolder(@NonNull View itemView, int titleType,OnItemListener mOnItemListener) {
        super(itemView);
        this.titleType = titleType;
        this.mOnItemListener = mOnItemListener;

        textView = itemView.findViewById(R.id.itemText);
        imageView = itemView.findViewById(R.id.itemImage);
        likeButton = itemView.findViewById(R.id.likeButton);
        downloadButton = itemView.findViewById(R.id.downloadListButton);
        watchListButton = itemView.findViewById(R.id.watchListButton);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(itemView.getContext());
        String token = preferences.getString("token", null);

        downloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TitleActionsService titleActionsService = Network.retrofit.create(TitleActionsService.class);
                titleActionsService.addToDownload(token, id, nonce).enqueue(new Callback<Object>() {
                    @Override
                    public void onResponse(Call<Object> call, Response<Object> response) {

                    }

                    @Override
                    public void onFailure(Call<Object> call, Throwable t) {

                    }
                });
            }
        });
        likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setBackground(v.getContext().getDrawable(R.drawable.ic_button_like));
                TitleActionsService titleActionsService = Network.retrofit.create(TitleActionsService.class);
                titleActionsService.addToFavourite(token, id, nonce).enqueue(new Callback<Object>() {
                    @Override
                    public void onResponse(Call<Object> call, Response<Object> response) {

                    }

                    @Override
                    public void onFailure(Call<Object> call, Throwable t) {

                    }
                });
            }
        });
        watchListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TitleActionsService titleActionsService = Network.retrofit.create(TitleActionsService.class);
                titleActionsService.addToWatchList(token,id, nonce).enqueue(new Callback<Object>() {
                    @Override
                    public void onResponse(Call<Object> call, Response<Object> response) {

                    }

                    @Override
                    public void onFailure(Call<Object> call, Throwable t) {

                    }
                });
            }
        });
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
