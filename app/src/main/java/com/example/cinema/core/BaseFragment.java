package com.example.cinema.core;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.cinema.R;

public abstract class BaseFragment extends Fragment {
    protected void showError(String onFailureNote) {
        TextView text = getView().findViewById(R.id.onFailureNote);
        Button retryButton = getView().findViewById(R.id.retryButton);
        LinearLayout errorContainer = getView().findViewById(R.id.errorContainer);
        text.setText(onFailureNote);
        errorContainer.setVisibility(View.VISIBLE);
        retryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRetryClicked();
                errorContainer.setVisibility(View.INVISIBLE);
            }
        });
    }

    public abstract void onRetryClicked();
}
