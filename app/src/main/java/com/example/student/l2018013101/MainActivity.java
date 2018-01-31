package com.example.student.l2018013101;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img=findViewById(R.id.imageView);
    }
    public void click1(View v)
    {
        Intent it = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(it,123);
    }

    public void click2(View v)
    {
        Intent it = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File f = new File(getExternalFilesDir("PHOTO"),"myphoto.jpg");
        it.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
        startActivityForResult(it,456);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 123)
        {
            if (resultCode == RESULT_OK)
            {
                Bundle bundle = data.getExtras();
                Bitmap bmp = (Bitmap) bundle.get("data");
                img.setImageBitmap(bmp);
            }
        }

        if (requestCode == 456)
        {
            if (resultCode == RESULT_OK)
            {

                //古早的方式  不過現在BMP檔案太大 使用此法記憶體容量易不足
                File f = new File(getExternalFilesDir("PHOTO"),"myphoto.jpg");
                Bitmap bmp = BitmapFactory.decodeFile(f.getAbsolutePath());
                img.setImageBitmap(bmp);
            }
        }
    }
}
