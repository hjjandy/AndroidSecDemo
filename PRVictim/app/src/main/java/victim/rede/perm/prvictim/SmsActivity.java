package victim.rede.perm.prvictim;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class SmsActivity extends AppCompatActivity implements Handler.Callback, Runnable {

    private TextView mMSG;
    private TextView mNUMTV;
    private TextView mINFOTV;
    private Handler mHANDLER;
    private String mINFO;
    private String mNUM;
    private String[] mPermissions = {"android.permission.SEND_SMS", "android.permission.READ_PHONE_STATE"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);
        Intent intent = getIntent();
        mNUM = intent.getStringExtra("num");
        mINFO = intent.getStringExtra("info");
        mMSG = (TextView) findViewById(R.id.sms_msg);
        mNUMTV = (TextView) findViewById(R.id.sms_num);
        mINFOTV = (TextView) findViewById(R.id.sms_info);
        mHANDLER = new Handler(this);
        if (mINFO != null && mNUM != null) {
            Thread thread = new Thread(this);
            thread.start();
        }

    }

    @Override
    public boolean handleMessage(Message msg) {
        int what = msg.what;
        if (what == 1) {
            mMSG.setText("MSG Sending Succeeds:");
            mNUMTV.setText(mNUM);
            mINFOTV.setText(mINFO);
        } else {
            mMSG.setText("MSG Sending Fails");
        }
        return true;
    }

    private void sendSMS() {
        try {
            SmsManager mgr = SmsManager.getDefault();
            mgr.sendTextMessage(mNUM, null, mINFO, null, null);
            mHANDLER.sendEmptyMessage(1);
        } catch (Exception e) {
            mHANDLER.sendEmptyMessage(0);
        }
    }

    @Override
    public void run() {
        boolean allGranted = true;
        for (int i = 0; i < mPermissions.length; i++) {
            if (ContextCompat.checkSelfPermission(SmsActivity.this, mPermissions[i])
                    != PackageManager.PERMISSION_GRANTED) {
                allGranted = false;
                break;
            }
        }
        if (!allGranted) {
            ActivityCompat.requestPermissions(SmsActivity.this, mPermissions, 1);
        } else {
            sendSMS();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    sendSMS();
                }
                else {
                    Toast.makeText(SmsActivity.this,
                            "Permission Denied", Toast.LENGTH_LONG).show();

                }
                break;
            default:
                break;
        }
    }
}
