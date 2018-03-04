package learn.b.ocr_test;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Environment;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

public class MainActivity extends AppCompatActivity {
//http://www.thecodecity.com/2016/09/creating-ocr-android-app-using-tesseract.html

    private MyTessOCR mTessOCR;
    private Button btnCapturePic;
    private static final int CAMERA_REQUEST = 1888; // field
    private ImageView imgTaken;
    private TextView tvOCR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnCapturePic = (Button) findViewById(R.id.button);
        imgTaken=(ImageView) findViewById(R.id.imgTaken);
        tvOCR= (TextView) findViewById(R.id.tvOCR);
       // tvOCR.setText(Environment.getExternalStorageDirectory()+"");
        btnCapturePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTessOCR = new MyTessOCR(MainActivity.this);
                Intent cameraIntent = new  Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            Bitmap picture = (Bitmap) data.getExtras().get("data");//this is your bitmap image and now you can do whatever you want with this
            imgTaken.setImageBitmap(picture); //for example I put bmp in an ImageView
            String temp = mTessOCR.getOCRResult(picture);
            tvOCR.setText(temp);
        }
    }

}
