package com.tekinarslan.material.sample;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class acct_type extends AppCompatActivity {

    android.support.v7.widget.AppCompatButton sign_in, sign_up;
    Context ctx;
    VideoView vidd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acct_type);
        ctx = this;

        // android.support.v7.widget.AppCompatButton cannot be cast to com.tekinarslan.material.sample.Button
        sign_in = (android.support.v7.widget.AppCompatButton) findViewById(R.id.acct_sign_in);
        sign_up = (android.support.v7.widget.AppCompatButton) findViewById(R.id.acct_sign_up);

        vidd = (VideoView) findViewById(R.id.myvideoview);

        vidd.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.splash));

        // might work. yelp.
        vidd.start();

        vidd.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });

        sign_up.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // intent listener, when the NEXT button is clicked, initialize our new intent
                Intent intent = new Intent(ctx, SampleActivity.class);
                // now start our new activity because our previous intent was initialized
                startActivity(intent);
            }
        });

        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // intent listener, when the NEXT button is clicked, initialize our new intent
                Intent intent = new Intent(ctx, LoginActivity.class);
                // now start our new activity because our previous intent was initialized
                startActivity(intent);
            }
        });

    }

/*
    @Override
    protected void onResume() {

    }
*/

}
