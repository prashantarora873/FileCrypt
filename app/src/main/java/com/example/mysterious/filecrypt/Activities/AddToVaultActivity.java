package com.example.mysterious.filecrypt.Activities;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.mysterious.filecrypt.R;

public class AddToVaultActivity extends AppCompatActivity {
    private int PICK_IMAGE_REQUEST = 1;


    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_addtovault);


        Button buttonLoadImage = (Button) findViewById(R.id.buttonLoadPicture);
            buttonLoadImage.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View arg0) {

                    Intent intent = new Intent();

                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);

                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
                }
            });
        }


        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);

            if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

                Uri uri = data.getData();
                String[] projection = { MediaStore.Images.Media.DATA };

                Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
                cursor.moveToFirst();


                int columnIndex = cursor.getColumnIndex(projection[0]);
                String picturePath = cursor.getString(columnIndex);
                cursor.close();

                ImageView imageView = (ImageView) findViewById(R.id.imgView);
                imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
            }
        }
}
