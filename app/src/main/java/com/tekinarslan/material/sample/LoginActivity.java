package com.tekinarslan.material.sample;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Build.VERSION_CODES;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;




public class LoginActivity extends ActionBarActivity {
    // create global null pointers to all of the resources that we will use in this activity
    Button sign_button;
    com.google.android.gms.common.SignInButton plus_button;

    EditText psswd;
    AutoCompleteTextView email;
    Context ctx;

    // TODO learn to import this in other activities
    private static String HttpGet(String url) {

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

            e.printStackTrace();
            Log.d("hi.rd", "ERROR in HTTPGet");

        }

        return null;
    }


    @Override
    // on the creation of this context, create the content views, initialize instances and set content view from R.java
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // set the content view to the activity_main xml file
        setContentView(R.layout.activity_login);
        ctx = this;
        sign_button = (Button) findViewById(R.id.email_sign_in_button);
        plus_button = (com.google.android.gms.common.SignInButton) findViewById(R.id.plus_sign_in_button);
        psswd = (EditText) findViewById(R.id.password);
        email = (AutoCompleteTextView) findViewById(R.id.email);
        // small hack for dealing with blocking http
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        sign_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input_email = email.getText().toString();
                String input_pass = psswd.getText().toString();

                Log.w("hi.rd", "received form inputs, " + input_email + " : " + input_pass);
                // create a JSON obj
                String temp_url = "http://45.55.243.40/validate_credentials?email=" + input_email + "&pass=" + input_pass;

                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(ctx, temp_url, duration);
                toast.show();


                try {

                    String return_val = HttpGet(temp_url) ;


                    Log.d("hi.rd" , return_val);
                    JSONObject temp = new JSONObject(return_val);

                    if ((boolean)temp.get("valid") == true){
                        toast = Toast.makeText(ctx, "welcome, "+input_email, duration);
                        toast.show();
                        // credentials valid, render new activity, set logged in param
                        Global user = new Global(true, input_email);

                        Intent intent = new Intent(ctx, SampleActivity.class);
                        Bundle tempBundle = new Bundle();
                        tempBundle.putBoolean("auth", true);
                        tempBundle.putString("username", input_email);
                        intent.putExtra("user", tempBundle);

                        startActivity(intent);

                    } else {
                        toast = Toast.makeText(ctx, "Invalid user or email, please retry.", duration);
                        toast.show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    toast = Toast.makeText(ctx, "No internet access", duration);
                    toast.show();

                }

            }
        });

        plus_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // credentials valid render new activity
                Intent intent = new Intent(ctx, SampleActivity.class);
                startActivity(intent);
            }

        });

    }

}




