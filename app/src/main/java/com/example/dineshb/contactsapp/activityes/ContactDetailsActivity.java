package com.example.dineshb.contactsapp.activityes;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dineshb.contactsapp.R;
import com.example.dineshb.contactsapp.customComponents.APIConstants;
import com.example.dineshb.contactsapp.customComponents.Constants;
import com.example.dineshb.contactsapp.customComponents.Contact;
import com.example.dineshb.contactsapp.db.DatabaseHandler;
import com.example.dineshb.contactsapp.fragments.OtpDialogFragment;
import com.example.dineshb.contactsapp.interfaces.InterfaceSheetShowing;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

public class ContactDetailsActivity extends AppCompatActivity implements InterfaceSheetShowing, View.OnClickListener {
    OtpDialogFragment otpDialogFragment;
    private TextView mError;
    boolean isShow = false;
    private TextView mName;
    private TextView mPhone;
    private FrameLayout mProgress;
    DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);
        db = new DatabaseHandler(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        otpDialogFragment = OtpDialogFragment.newInstance();

        mError = (TextView) findViewById(R.id.error);
        mName = (TextView) findViewById(R.id.name);
        mPhone = (TextView) findViewById(R.id.phone);
        mProgress = (FrameLayout) findViewById(R.id.progress);

        try {
            JSONObject object = new JSONObject(getIntent().getStringExtra(Constants.EXTRA_PARAM));
            Contact contact = new Contact(object);
            if (!Constants.TextUtils_isEmpty(contact.getName())) {
                mName.setText(contact.getName());
            } else {
                mName.setText("Un Known");
            }
            mPhone.setText(contact.getPhone());
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void onMessagebuttonClick(View view) {
        if (!isShow) {
            otpDialogFragment.showSheet(getSupportFragmentManager(), this, this);
        }
    }

    private void sentMessage(final String message) {
        mProgress.setVisibility(View.VISIBLE);
        // Twilio,  Nexmo API's Not Working Properly so I create Dummy SentMessages

        JSONObject object = new JSONObject();
        try {
            object.put(APIConstants.ContactJSON.name,mName.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            object.put(APIConstants.ContactJSON.phone,mPhone.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            object.put(APIConstants.ContactJSON.messages, message);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            object.put(APIConstants.ContactJSON.time_stamp, Calendar.getInstance().getTimeInMillis());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        db.insertMessage(String.valueOf(object));

        new Handler().postDelayed(new Runnable() {
            public void run () {
                mProgress.setVisibility(View.GONE);
                Toast.makeText(ContactDetailsActivity.this, "Message sent Successfully!", Toast.LENGTH_SHORT).show();
                finish();
            }
        }, 1000);

    }


    @Override
    public void notifyShowing(boolean showing) {
        isShow = showing;
    }

    @Override
    public void onClick(View v) {
        otpDialogFragment.dismiss();
        String message = ((TextView) v).getText().toString();
        sentMessage(message);
    }


}
