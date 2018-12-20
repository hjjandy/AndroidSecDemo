package victim.rede.perm.prvictim;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class VictimMainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mMSG;
    private EditText mNUM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_victim_main);
        mNUM = (EditText) findViewById(R.id.number);
        mMSG = (EditText) findViewById(R.id.message);
        Button btn = (Button) findViewById(R.id.send_btn);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        intent.setClass(getApplicationContext(), SmsActivity.class);
        intent.putExtra("num", mNUM.getText().toString());
        intent.putExtra("info", mMSG.getText().toString());
        startActivity(intent);

    }
}
