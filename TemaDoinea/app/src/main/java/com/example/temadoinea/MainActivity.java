package com.example.temadoinea;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {

    private Button buttonCAT;
    private Button buttonDOG;


    // the activity result code
    int SELECT_PICTURE = 200;
    private ImageView imageViewCAT;
    private ImageView imageViewDOG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        buttonCAT = findViewById(R.id.buttonCAT);
        imageViewDOG = findViewById(R.id.imageViewDOG);
        imageViewCAT = findViewById(R.id.imageViewCAT);


        buttonCAT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    shareImage(imageViewCAT);
            }
        });

        buttonDOG = findViewById(R.id.buttonDOG);
        buttonDOG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                shareImage(imageViewDOG);
            }
        });

    }


    public void shareImage(ImageView imageView) {

        BitmapDrawable bitmapDrawable = (BitmapDrawable) imageView.getDrawable();
        Bitmap bitmap = bitmapDrawable.getBitmap();

        Bitmap icon = bitmap;
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("image/jpeg");

        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "title");
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
        Uri uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                values);

        OutputStream outstream;
        try {
            outstream = getContentResolver().openOutputStream(uri);
            icon.compress(Bitmap.CompressFormat.JPEG, 100,  outstream);
            outstream.close();
        } catch (Exception e) {
            System.err.println(e.toString());
        }

        share.putExtra(Intent.EXTRA_STREAM, uri);
        share.setData(uri);
        startActivity(Intent.createChooser(share, "Share Image"));
    }
}