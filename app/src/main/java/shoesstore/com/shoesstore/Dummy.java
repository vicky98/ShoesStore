package shoesstore.com.shoesstore;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Dummy extends AppCompatActivity {

    private static String TAG = "ShoesStore/Dummy";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dummy);

        Intent intent = getIntent();
        String userEmail = intent.getStringExtra("userEmail");
        String userUid = intent.getStringExtra("userUID");

        Log.d(TAG, "User email: " + userEmail + " UserUid: " + userUid);

        TextView tvEmail = (TextView) findViewById(R.id.dummy_tv_uEmail);

        tvEmail.setText(userEmail);

        Button btnLogOut = (Button) findViewById(R.id.dummy_btn_logout);
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dummy.this, Main.class);
                startActivityForResult(intent, 12);
            }
        });
    }
}
