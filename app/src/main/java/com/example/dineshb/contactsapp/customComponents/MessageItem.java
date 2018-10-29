package com.example.dineshb.contactsapp.customComponents;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by DineshB on 10/24/2018.
 */

public class MessageItem {
   String name;
    String phone;
    String message;
    String time;

    public MessageItem(JSONObject object) {
        try {
            name = object.getString(APIConstants.ContactJSON.name);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            phone = object.getString(APIConstants.ContactJSON.phone);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            message = object.getString(APIConstants.ContactJSON.messages);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            time = Constants.timeStampToDate(object.getLong(APIConstants.ContactJSON.time_stamp),"MMM dd yyyy - hh:mm a");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getMessage() {
        return message;
    }

    public String getTime() {
        return time;
    }
}
