package com.example.norgan.recaudaciones01;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView ivfoto;
    private final int DURACION_SPLASH=7000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_USER_PORTRAIT);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        ivfoto = (ImageView)findViewById(R.id.ivfoto);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent1 = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent1);
                finish();


            };
        },DURACION_SPLASH);




    }
}
