package com.example.dineshb.contactsapp.customComponents;

import org.json.JSONException;
import org.json.JSONObject;

/**
 Created by DineshB on 10/22/2018.
 */

public class Contact {

    private String name;
    private String phone;

    public Contact(JSONObject object) {
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
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }
}
