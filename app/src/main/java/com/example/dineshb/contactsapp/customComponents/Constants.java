package com.example.dineshb.contactsapp.customComponents;

import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

/**
 Created by DineshB on 10/22/2018.
 */

public class Constants {
    public static final String EXTRA_PARAM="extra_param";
    public static final String COLOR_CODE="color_code";
    public static final String DATA =
            "[{\n" +
                    "        \"name\": \"Dinesh Bethalam\",\n" +
                    "        \"phone\": \"9182220770\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "        \"name\": \"Ravi Varma\",\n" +
                    "        \"phone\": \"91822207701\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "        \"name\": \"Tom Hardy\",\n" +
                    "        \"phone\": \"9640956784\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "        \"name\": \"Johnny Depp\",\n" +
                    "       \"phone\": \"9000143054\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "        \"name\": \"Sukumar roy\",\n" +
                    "        \"phone\": \"9971792703\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "        \"name\": \"Ankur Saxena\",\n" +
                    "        \"phone\": \"9182220770\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "        \"name\": \"Bharath Raju\",\n" +
                    "        \"phone\": \"9182220770\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "        \"name\": \"Emma Watson\",\n" +
                    "        \"phone\": \"9182220771\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "        \"name\": \"Prabhas Raju\",\n" +
                    "        \"phone\": \"9182220772\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "        \"name\": \"Santhosh Kumar\",\n" +
                    "        \"phone\": \"9182220773\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "        \"name\": \"Mahesh Reddy\",\n" +
                    "        \"phone\": \"9182220774\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "        \"name\": \"Anmol Deepak\",\n" +
                    "        \"phone\": \"9182220770\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "        \"name\": \"Mohit Sharma\",\n" +
                    "        \"phone\": \"9182220770\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "        \n" +
                    "        \"phone\": \"9182220776\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "        \"name\": \"Alka Kanwar\",\n" +
                    "        \"phone\": \"9182220770\"\n" +
                    "    }\n" +
                    "]";
    public static JSONArray getData() {
        try {
            return new JSONArray(DATA);
        } catch (JSONException e) {
            e.printStackTrace();

        }

        return null;
    }

    public static boolean TextUtils_isEmpty(CharSequence s)
    {
        return TextUtils.isEmpty(s) || s.equals("null");
    }

   public static String OTP(int len)
    {
        String otp = "";
        // Using numeric values
        String numbers = "0123456789";

        // Using random method
        Random rndm_method = new Random();


        for (int i = 0; i < len; i++)
        {
            // Use of charAt() method : to get character value
            // Use of nextInt() as it is scanning the value as int
            otp +=
                    numbers.charAt(rndm_method.nextInt(numbers.length()));
        }
        return otp;
    }

    public static String timeStampToDate(long l, String s){
        SimpleDateFormat dateFormat = new SimpleDateFormat(s);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(l);
        return dateFormat.format(calendar.getTime());
    }
}
