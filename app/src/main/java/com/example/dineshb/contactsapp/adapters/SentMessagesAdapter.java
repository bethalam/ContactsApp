package com.example.dineshb.contactsapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dineshb.contactsapp.R;
import com.example.dineshb.contactsapp.customComponents.Constants;
import com.example.dineshb.contactsapp.customComponents.MessageItem;
import com.example.dineshb.contactsapp.interfaces.RecyclerItemClickListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 Created by DineshB on 10/22/2018.
 */

public class SentMessagesAdapter extends RecyclerView.Adapter<SentMessagesAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<String> contactList;
    private RecyclerItemClickListener listener;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView mDate;
        TextView mFileNameText;
        TextView mPhone;
        TextView mMessage;
        int position;

        public MyViewHolder(View view) {
            super(view);
            this.mDate = (TextView) view.findViewById(R.id.date);
            this.mFileNameText = (TextView) view.findViewById(R.id.file_name_text);
            this.mPhone = (TextView) view.findViewById(R.id.phone);
            this.mMessage = (TextView) view.findViewById(R.id.message);
        }
    }


    public SentMessagesAdapter(Context context, ArrayList<String> contactList, RecyclerItemClickListener listener) {
        this.context = context;
        this.listener = listener;
        this.contactList = contactList;

//        this.contactListFiltered = contactList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.lay_messages_sent_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        try {
            JSONObject object = new JSONObject(contactList.get(position));
            MessageItem item  = new MessageItem(object);

            holder.mDate.setText(item.getTime());
            if (!Constants.TextUtils_isEmpty(item.getName())){
                holder.mFileNameText.setText(item.getName());
            }else {
                holder.mFileNameText.setText(item.getPhone());
            }
            holder.mPhone.setText(item.getPhone());
            holder.mMessage.setText(item.getMessage());

        } catch (JSONException e) {
            e.printStackTrace();
        }


        holder.position = holder.getAdapterPosition();
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

}