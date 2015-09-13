package com.tekinarslan.material.sample;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;



// import statement for the r file of this app. 
// "the build of your app that accesses all declared resources "

public class LoginActivity extends ActionBarActivity {
    // create global null pointers to all of the resources that we will use in this activity
    Button sign_button;
    com.google.android.gms.common.SignInButton plus_button;

    EditText psswd;
    AutoCompleteTextView email;
    Context ctx;


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

            System.out.println("exception: " + e);
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


        sign_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input_email = email.getText().toString();
                String input_pass = psswd.getText().toString();

                Log.w("hi.rd", "received form inputs, " + input_email + " : " + input_pass);

                // create a JSON obj
                String temp_url = "http://45.55.243.40?email=" + input_email + "&passwd=" + input_pass;

                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(ctx, temp_url, duration);
                toast.show();


                try {
                    String return_val = HttpGet("http://45.55.243.40") ;

                    Log.d("a" , return_val);
                    JSONObject temp = new JSONObject(return_val);

                    if ((boolean) temp.get("valid") == true) {
                        // credentials valid render new activity
                        Intent intent = new Intent(ctx, SampleActivity.class);
                        startActivity(intent);

                    } else {
                        toast = Toast.makeText(ctx, "Invalid user or email, please retry.", duration);
                        toast.show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.w("hi.rd", "UNABLE TO MAKE JSON REQUEST");
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




