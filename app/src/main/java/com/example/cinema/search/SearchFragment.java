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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinema.R;
import com.example.cinema.homePage.Title;
import com.example.cinema.titleslist.TitlesListAdapter;

import java.util.List;

public class SearchFragment extends Fragment {
    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       return inflater.inflate(R.layout.fragment_search,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
       EditText search = view.findViewById(R.id.searchEditText);
       search.addTextChangedListener(new TextWatcher() {
           @Override
           public void beforeTextChanged(CharSequence s, int start, int count, int after) {

           }

           @Override
           public void onTextChanged(CharSequence s, int start, int before, int count) {
              SearchRepository searchRepository = new SearchRepository();
              searchRepository.getSearchResult( s.toString(), new SearchRepository.SearchCallback() {
                  @Override
                  public void onSuccess(List<Title> titles) {
                      RecyclerView searchResult = getView().findViewById(R.id.searchResult);
                      searchResult.setLayoutManager(new LinearLayoutManager(getContext()));
                      searchResult.setAdapter(new TitlesListAdapter(titles, 0));

                  }

                  @Override
                  public void onFailure() {

                  }
              });
           }

           @Override
           public void afterTextChanged(Editable s) {

           }
       });

    }
}
