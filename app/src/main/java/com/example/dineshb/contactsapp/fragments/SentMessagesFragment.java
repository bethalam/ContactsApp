package com.example.dineshb.contactsapp.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dineshb.contactsapp.R;
import com.example.dineshb.contactsapp.adapters.SentMessagesAdapter;
import com.example.dineshb.contactsapp.customComponents.Constants;
import com.example.dineshb.contactsapp.db.DatabaseHandler;

import java.util.ArrayList;
import java.util.Collections;


/**
 * A simple {@link Fragment} subclass.
 */
public class SentMessagesFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {


    private RecyclerView mRecyclerView;
    TextView message;
    DatabaseHandler db;
    SwipeRefreshLayout swipeRefreshLayout;
    ArrayList<String> list = new ArrayList<>();
    SentMessagesAdapter adapter;

    public SentMessagesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sent_messages, container, false);
        db = new DatabaseHandler(getContext());
        initView(view);
        return view;
    }

    private void initView(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        message = view.findViewById(R.id.message);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(R.color.user_icon_1,R.color.user_icon_2,R.color.user_icon_3);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new SentMessagesAdapter(getContext(),list,null);
        mRecyclerView.setAdapter(adapter);
        list.clear();
        list.addAll(db.getAllMessages());
        if (list.size()>0){
            adapter.notifyDataSetChanged();
            message.setVisibility(View.GONE);

        }else {
            message.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void onRefresh() {
        list.clear();
        list.addAll(db.getAllMessages());
        adapter.notifyDataSetChanged();
        swipeRefreshLayout.setRefreshing(false);
    }
}
