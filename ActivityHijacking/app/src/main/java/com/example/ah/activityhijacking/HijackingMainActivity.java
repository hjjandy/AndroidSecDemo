package com.example.ah.activityhijacking;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;

public class HijackingMainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hijacking_main);
        Intent intent = new Intent(getApplicationContext(), HijackingService.class);
        startService(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        HijackingMainActivity.this.finish();
    }
}
