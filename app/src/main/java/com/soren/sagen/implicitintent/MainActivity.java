package com.soren.sagen.implicitintent;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

public class MainActivity extends AppCompatActivity {

    FloatingActionMenu floatingActionMenu;
    FloatingActionButton fabBtnMap, fabBtnMail, fabBtnPhncall, fabBtnTele, fabBtnCamera;


    //Camera Function
    private static final int CAMERA_REQUEST = 123;
    ImageView imgCamera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        floatingActionMenu = findViewById(R.id.Fab_menu);

        fabBtnMap = findViewById(R.id.floating_map_btn);
        fabBtnMail = findViewById(R.id.floating_mail_btn);
        fabBtnPhncall = findViewById(R.id.floating_phn_btn);
        fabBtnTele = findViewById(R.id.floating_tele_btn);
        fabBtnCamera = findViewById(R.id.floating_camera_btn);


        /*-----Map-----*/
        fabBtnMap.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                Intent intent = new Intent(Intent.ACTION_VIEW);

                intent.setData(Uri.parse("geo: 47.4925,19.0513"));

                Intent chooser = Intent.createChooser(intent, "Lunch Maps");
                startActivity(chooser);
            }
        });
        /*----End Map-----*/


        /*---Mail------*/
        fabBtnMail.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setData(Uri.parse("email"));
                intent.setType("message/rfc822");
                Intent chooser = Intent.createChooser(intent, " launch Email");
                startActivity(chooser);

            }
        });




        fabBtnPhncall.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {



            }
        });



        fabBtnTele.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:123456789"));
                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(intent);

            }
        });

        // Camera Function
        imgCamera = findViewById(R.id.img_camera);
        fabBtnCamera.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, CAMERA_REQUEST);

            }
        });


    }
    public void onActivityResult(int requestCode, int resultCode ,Intent data ){
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK){
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imgCamera.setImageBitmap(photo);
        }
    }
}
