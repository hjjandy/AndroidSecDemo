package com.example.huang.ama2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;
import android.app.*;
import android.view.*;
import android.content.*;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv = (TextView) findViewById(R.id.sample_text);
        Button btn = (Button) findViewById(R.id.click_me);
        btn.setOnClickListener(this);
    }

    public void onClick(View view) {
        Intent intent = new Intent();
        intent.setAction("com.example.huang.ama2.snd");
        startActivity(intent);
    }
}
