package com.example.cinema.search;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinema.R;
import com.example.cinema.core.BaseFragment;
import com.example.cinema.core.DataCallBack;
import com.example.cinema.details.DetailsFragment;
import com.example.cinema.homePage.Title;
import com.example.cinema.titleslist.TitlesListAdapter;
import com.example.cinema.titleslist.TitlesListViewHolder;

import java.util.List;

public class SearchFragment extends BaseFragment {
    SearchViewModel searchViewModel;
    EditText search;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        searchViewModel = new ViewModelProvider(SearchFragment.this).get(SearchViewModel.class);
        search = view.findViewById(R.id.searchEditText);
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                search(s.toString());

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        searchViewModel.getErrorMutableLiveData().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                showError(s);
            }
        });

    }

    private void search(String s) {
        searchViewModel.getSearchResults(s).observe(getViewLifecycleOwner(), new Observer<List<Title>>() {
            @Override
            public void onChanged(List<Title> titles) {
                RecyclerView searchResult = getView().findViewById(R.id.searchResult);
                searchResult.setLayoutManager(new LinearLayoutManager(getContext()));
                searchResult.setAdapter(new TitlesListAdapter(titles, 0, new TitlesListViewHolder.OnItemListener() {
                    @Override
                    public void onItemClick(String url, int titleType) {
                        FragmentTransaction ft = getParentFragmentManager().beginTransaction();
                        ft.replace(R.id.fragmentView, DetailsFragment.newInstance(url, titleType)).addToBackStack("details fragment");
                        ft.commit();
                    }
                }));

            }
        });
    }

    @Override
    public void onRetryClicked() {
        search(search.getText().toString());
    }
}
