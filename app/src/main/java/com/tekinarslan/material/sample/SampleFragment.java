package com.tekinarslan.material.sample;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

public class SampleFragment extends Fragment {

    private static final String ARG_POSITION = "position";

    private int position;

    public static SampleFragment newInstance(int position, Bundle userData) {
        SampleFragment f = new SampleFragment();
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION, position);
        b.putAll(userData);
        f.setArguments(b);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        position = getArguments().getInt(ARG_POSITION);
        final View rootView = inflater.inflate((R.layout.page + (position+1)), container, false);

        /*********************** REGISTERING HANDLERS **************************/
        switch (position) {
            case 0: // profile fragment
                View temp = rootView.findViewById(R.id.upload_resume_btn);
                final android.widget.Button upload_resume_btn = (android.widget.Button)temp;
                upload_resume_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(v.getId() == R.id.upload_resume_btn) {
                            // This button actually does not make sense.
                            // I refuse to do any more work until it makes sense.
                        }
                    }
                });
                break;

            case 1: // send email fragment
                final android.widget.Button send_resume_btn = (android.widget.Button) rootView.findViewById(R.id.send_resume_btn);
                send_resume_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(v.getId() == R.id.send_resume_btn) {
                            EditText email_field = (EditText) rootView.findViewById(R.id.recruit_mail_email_field);
                            EditText subject_field = (EditText) rootView.findViewById(R.id.recruit_mail_subject_field);
                            EditText body_field = (EditText) rootView.findViewById(R.id.recruit_mail_body_field);

                            //These are strings for the fields used to send the email
                            String email_addr = email_field.getText().toString();
                            String email_subj = subject_field.getText().toString();
                            String email_body = body_field.getText().toString();
                            String from = (String) getArguments().get("username");
                            Log.d("USERNAME", from);

                            Global.HttpGet("http://45.55.243.40/send_mail?from="+from+"&to="+email_addr+"&subj="+email_subj+"&body="+email_body);
                        }
                    }
                });
                break;

            case 2: // boradcast fragment
                final android.widget.Button broadcast_btn = (android.widget.Button) rootView.findViewById(R.id.broadcast_btn);
                broadcast_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(v.getId() == R.id.broadcast_btn) {

                            //Pull the email from the recruiter from the NFC here

                        }
                    }
                });
                break;

            case 3: // history
                ;
                break;
            default:
                ;
        }

/*        ProgressBarCircular progressBarCircular = (ProgressBarCircular) rootView.findViewById(R.id.progress);
        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fabButton);
        fab.setDrawableIcon(getResources().getDrawable(R.drawable.plus));
        switch (position) {
            case 0:
                fab.setBackgroundColor(getResources().getColor(R.color.material_deep_teal_500));
                progressBarCircular.setBackgroundColor(getResources().getColor(R.color.material_deep_teal_500));
                break;
            case 1:
                fab.setBackgroundColor(getResources().getColor(R.color.red));
                progressBarCircular.setBackgroundColor(getResources().getColor(R.color.red));

                break;
            case 2:
                progressBarCircular.setBackgroundColor(getResources().getColor(R.color.blue));
                fab.setBackgroundColor(getResources().getColor(R.color.blue));

                break;
            case 3:
                fab.setBackgroundColor(getResources().getColor(R.color.material_blue_grey_800));
                progressBarCircular.setBackgroundColor(getResources().getColor(R.color.material_blue_grey_800));

                break;
        }
*/
        return rootView;
    }
}