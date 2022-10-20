package com.example.appshowphoto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ImageView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    private ImageView imageViewSHOWCHOICE;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageViewSHOWCHOICE = findViewById(R.id.imageViewSHOWCHOICE);

        Intent intent = new Intent();
        intent = getIntent();
        intent.setType("image/jpeg");
        Uri imgUri=(Uri) intent.getParcelableExtra(Intent.EXTRA_STREAM);
        imageViewSHOWCHOICE.setImageURI(null);
        imageViewSHOWCHOICE.setImageURI(imgUri);
    }


}