package com.example.dineshb.contactsapp.customComponents;

/**
  Created by DineshB on 10/22/2018.
 */

public class APIConstants {
    public static final String url = "http://rest.nexmo.com/sms/json";
    public static final String NEXMO_API_KEY = "98a86b05";
    public static final String NEXMO_API_SECRET = "*************";

    public final static class ContactJSON {
        public final static String name = "name";
        public final static String phone = "phone";
        public final static String messages = "messages";
        public final static String status = "status";
        public final static String error_text = "error-text";
        public static String time_stamp = "time_stamp";
    }
}