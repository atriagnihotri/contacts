package com.example.content_providers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CallAcitvity extends AppCompatActivity {
TextView user_title,user_data;
ImageView call;
final static int REQUEST_CALL_PHONE=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_acitvity);
        user_data=findViewById(R.id.data);
        user_title=findViewById(R.id.text);
        call=findViewById(R.id.call);
        String data= getIntent().getStringExtra("data");



        user_title.setText(getIntent().getStringExtra("title"));
        user_data.setText(getIntent().getStringExtra("data"));




        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String phoneNumber = "tel:"+data;

                // Check for runtime permission on devices running Android 23 or higher
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (ContextCompat.checkSelfPermission(CallAcitvity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        // Permission is not granted, request it
                        ActivityCompat.requestPermissions(CallAcitvity.this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL_PHONE);
                    } else {
                        // Permission is already granted, make the call
                        makePhoneCall(phoneNumber);
                    }
                } else {
                    // For devices below Android 23, no runtime permission is required
                    makePhoneCall(phoneNumber);
                }



            }
        });



    }

    private void makePhoneCall(String phoneNumber) {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse(phoneNumber));

        // Check if the device can make calls before starting the intent
        if (callIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(callIntent);
        } else {
            // Handle the case where the device cannot make calls
            Toast.makeText(this, "Device cannot make calls", Toast.LENGTH_SHORT).show();
        }
    }

}