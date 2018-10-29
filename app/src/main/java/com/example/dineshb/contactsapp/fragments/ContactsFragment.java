package com.example.dineshb.contactsapp.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.example.dineshb.contactsapp.R;
import com.example.dineshb.contactsapp.activityes.ContactDetailsActivity;
import com.example.dineshb.contactsapp.adapters.ContactsAdapter;
import com.example.dineshb.contactsapp.customComponents.Constants;
import com.example.dineshb.contactsapp.interfaces.RecyclerItemClickListener;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactsFragment extends Fragment implements RecyclerItemClickListener {


    JSONArray contactList = new JSONArray();

    public ContactsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contacts, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        RecyclerView mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        contactList = Constants.getData();
        if (contactList != null) {
            ContactsAdapter adapter = new ContactsAdapter(getContext(), contactList, this);
            mRecyclerView.setAdapter(adapter);
        } else {
            Toast.makeText(getContext(), "Json Array Contains bad format!", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onClickListener(int position) {
        Intent intent = new Intent(getContext(), ContactDetailsActivity.class);
        try {
            intent.putExtra(Constants.EXTRA_PARAM,contactList.getJSONObject(position).toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.slide_left, R.anim.stay);
    }
}
