package android.android.zlibrary.activities;

import android.android.zlibrary.R;
import android.android.zlibrary.ZipzApplication;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        if (ZipzApplication.getInstance().getmSessionManager().isLoggedIn()) {
            String token = ZipzApplication.getInstance().getmSessionManager().getToken();
            Log.d("token", "token = [" + token + "]");
            Intent intent = new Intent(SplashActivity.this, MainZActivity.class);
            startActivity(intent);
            finish();
        } else {
            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }

}
