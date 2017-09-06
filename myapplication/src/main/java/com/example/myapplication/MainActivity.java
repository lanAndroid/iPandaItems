package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    String url = "http://vod.cntv.lxdns.com/flash/mp4video61/TMS/2017/07/13/19e07c13e3b8463399a5c10b132f090b_h264418000nero_aac32.mp4";
    private Button tiao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tiao = (Button) findViewById(R.id.tiao);
        tiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                intent.putExtra("url",url);
                startActivity(intent);
            }
        });
    }
}
