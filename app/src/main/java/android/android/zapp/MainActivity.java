package android.android.zapp;

import androidx.appcompat.app.AppCompatActivity;

import android.android.zlibrary.activities.CouponActivity;
import android.android.zlibrary.activities.LoginActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private int OPEN_MEDIA_PICKER = 1;  // Request code

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttonLogin = findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

//        Button buttonCoupons = findViewById(R.id.buttonCoupons);
//        buttonCoupons.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, CouponActivity.class);
//                startActivity(intent);
//            }
//        });

    }
}
