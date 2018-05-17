package com.turing.newaomo.davinsbrush.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.turing.newaomo.davinsbrush.R;
import com.turing.newaomo.davinsbrush.activity.other.history.HistoryAdapterf;
import com.turing.newaomo.davinsbrush.activity.other.history.HistoryItem;

import java.util.ArrayList;
import java.util.List;

public class MyFragment3 extends Fragment {

    RecyclerView recyclerViewHistory;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main_3,container,false);;
        recyclerViewHistory = (RecyclerView)view.findViewById(R.id.main_history_recyclerview);
        initRecyclerView();
        return view;
    }

    private void initRecyclerView(){
        recyclerViewHistory.setLayoutManager(new LinearLayoutManager(getContext()));
        List<HistoryItem> historyList = new ArrayList<>();
        HistoryAdapterf adapter = new HistoryAdapterf(getContext(),historyList);
        recyclerViewHistory.setAdapter(adapter);

        adapter.getItems().add(new User("张三", 18));
        adapter.getItems().add(new User("李四", 28));
        adapter.getItems().add(new User("王五", 38));
    }


}
