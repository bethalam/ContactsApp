package com.example.dineshb.contactsapp.adapters;

import android.content.Context;
import android.graphics.PorterDuff;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dineshb.contactsapp.R;
import com.example.dineshb.contactsapp.customComponents.Constants;
import com.example.dineshb.contactsapp.customComponents.Contact;
import com.example.dineshb.contactsapp.interfaces.RecyclerItemClickListener;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.Random;


/**
 Created by DineshB on 10/22/2018.
 */

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.MyViewHolder>{
    private Context context;
    private JSONArray contactList;
    private int colors[] = {R.color.user_icon_1,R.color.user_icon_2,R.color.user_icon_3,R.color.user_icon_4,R.color.user_icon_5
            , R.color.user_icon_6, R.color.user_icon_7, R.color.user_icon_8,R.color.user_icon_default_gray};
    //    private List<Contact> contactListFiltered;
    private RecyclerItemClickListener listener;
    Random rand = new Random();

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, name;
        public ImageView img;
        public FrameLayout lay_profile;
        int position;
        int colorCode;

        public MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.title);
            name = view.findViewById(R.id.name);
            img = view.findViewById(R.id.img);
            lay_profile = view.findViewById(R.id.lay_profile);


            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // send selected contact in callback
                    listener.onClickListener(position);
                }
            });
        }
    }


    public ContactsAdapter(Context context, JSONArray contactList, RecyclerItemClickListener listener) {
        this.context = context;
        this.listener = listener;
        this.contactList = contactList;

//        this.contactListFiltered = contactList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.lay_contact_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder,  int position) {
        try {
            Contact contact = new Contact(contactList.getJSONObject(holder.getAdapterPosition()));
            if (!Constants.TextUtils_isEmpty(contact.getName())) {
                holder.name.setText(contact.getName());
                holder.title.setText(String.valueOf(contact.getName().charAt(0)).toUpperCase());
                holder.title.setVisibility(View.VISIBLE);
                holder.img.setVisibility(View.GONE);
            } else {
                try {
                    holder.name.setText(contact.getPhone());
                } catch (Exception e) {
                    e.printStackTrace();
                    holder.name.setText(R.string.un_known_contact);
                }
                holder.title.setVisibility(View.GONE);
                holder.img.setVisibility(View.VISIBLE);
            }

        } catch (JSONException e) {
            e.printStackTrace();

        }

        holder.colorCode = context.getResources().getColor(colors[rand.nextInt(colors.length)]);
        holder.lay_profile.getBackground().setColorFilter(holder.colorCode, PorterDuff.Mode.MULTIPLY);
        holder.position = holder.getAdapterPosition();
    }

    @Override
    public int getItemCount() {
        return contactList.length();
    }


}