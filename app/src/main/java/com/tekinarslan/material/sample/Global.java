package com.tekinarslan.material.sample;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by david on 9/13/15.
 */
public class Global {

    // session like user data
    public static boolean auth = true;
    public static String user = "";
    public static String[] history = new String[20];

    public Global(boolean auth, String user) {
        this.auth = auth;
        this.user = user;
    }

    public static String HttpGet(String url) {

        try {

            HttpURLConnection urlConnection = (HttpURLConnection) new URL(url).openConnection();
            InputStream is = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));

            String line = "";
            String response = "";
            while ((line = rd.readLine()) != null) {
                response += line;
            }

            return response;

        } catch (Exception e) {

            Log.d("hi.rd", "ERROR: HTTP GET for url :" + url);
            e.printStackTrace();

        }
        return null;
    }
}
