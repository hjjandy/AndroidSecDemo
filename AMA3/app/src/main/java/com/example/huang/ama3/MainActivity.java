package com.example.huang.ama3;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = (Button) findViewById(R.id.click_X);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                "android.permission.SEND_SMS") != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    MainActivity.this, "android.permission.SEND_SMS")) {
                Toast.makeText(MainActivity.this,
                        "Allow Very Dangrouse Operation?", Toast.LENGTH_LONG).show();
            }
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{"android.permission.SEND_SMS"}, 1);
        } else {
            Intent intent = new Intent("com.example.huang.ama2.snd");
            startActivity(intent);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent("com.example.huang.ama2.snd");
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this,
                            "Permission Denied", Toast.LENGTH_LONG).show();

                }
                break;
            default:
                break;
        }
    }
}
