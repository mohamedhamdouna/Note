package com.mohammed.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.listeners.IPickResult;

public class DetailsActivity extends AppCompatActivity implements IPickResult {
    TextView txtLocation;
    ImageView imageView;
    int PICK_IMAGE_REQUEST = 111;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        txtLocation = findViewById(R.id.txtLocation);
        txtLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DetailsActivity.this, MapsActivity2.class);
                startActivity(i);
            }
        });
        imageView = findViewById(R.id.imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
          if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                  if (checkSelfPermission(Manifest.permission.CAMERA)
                    == PackageManager.PERMISSION_GRANTED) {
                     Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                      startActivity(intent);
                      } else {
                   ActivityCompat.requestPermissions(DetailsActivity.this, new String[]{Manifest.permission.CAMERA}, 1);
              }
               } else {
                 Log.v("TAG", "Permission is revoked");
                    }
                  }
          }
        );
    }

    @Override
    public void onPickResult(PickResult r) {
        if (r.getError() == null) {
            imageView.setImageBitmap(r.getBitmap());
        } else {
            Toast.makeText(this, r.getError().getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


}
