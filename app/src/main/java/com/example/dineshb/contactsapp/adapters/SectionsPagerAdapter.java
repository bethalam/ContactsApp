package com.example.dineshb.contactsapp.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.dineshb.contactsapp.R;
import com.example.dineshb.contactsapp.fragments.ContactsFragment;
import com.example.dineshb.contactsapp.fragments.SentMessagesFragment;


/**
 Created by DineshB on 10/22/2018.
 */

public class SectionsPagerAdapter  extends FragmentPagerAdapter {

    private Context context;

    public SectionsPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new ContactsFragment();
            case 1:
                return new SentMessagesFragment();

        }
        return null;
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return context.getString(R.string.contacts);
            case 1:
                return context.getString(R.string.sent_messages);

        }
        return null;
    }
}

