package com.tekinarslan.material.sample;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


// import statement for the r file of this app. 
// "the build of your app that accesses all declared resources "

public class LoginActivity extends ActionBarActivity {
    // create global null pointers to all of the resources that we will use in this activity
    Button blue;
    EditText input;
    TextView output;
    Context ctx;

    @Override  // on the creation of this context, create the content views, initialize instances and set content view from R.java
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        // set the content view to the activity_main xml file
        setContentView(R.layout.activity_login);
        ctx = this;

        blue = (Button)findViewById(R.id.email_sign_in_button);


        blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // intent listener, when the NEXT button is clicked, initialize our new intent
                Intent intent = new Intent(ctx,SampleActivity.class);
                // now start our new activity because our previous intent was initialized
                startActivity(intent);
            }
        });


    }

}