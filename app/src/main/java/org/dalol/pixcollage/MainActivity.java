package org.dalol.pixcollage;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import org.dalol.pixcollage.mask.ArtDrawable;

import java.io.FileNotFoundException;

public class MainActivity extends AppCompatActivity {

    private static final int RESULT_LOAD_IMAGE = 1000;
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private ImageView imageView;
    private ArtDrawable artDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonLoadImage = (Button) findViewById(R.id.buttonLoadPicture);
        Button buttonTakeImage = (Button) findViewById(R.id.buttonTakePicture);

        imageView = (ImageView) findViewById(R.id.imgView);
        buttonLoadImage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });

        artDrawable = new ArtDrawable();

        buttonTakeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.adidas);
                Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.star).extractAlpha();

                artDrawable.setPictureBitmap(bitmap1);
                artDrawable.setMaskBitmap(bitmap2);
                imageView.setImageDrawable(artDrawable);

                artDrawable.setAlpha(128);
                imageView.invalidate();


                //dispatchTakePictureIntent();
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri targetUri = data.getData();

            Bitmap bitmap;
            try {
                bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(targetUri));
                imageView.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageView.setImageBitmap(imageBitmap);
        }
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }
}
