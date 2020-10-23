package android.android.zapp;

import android.android.zlibrary.activities.LoginActivity;
import android.android.zlibrary.activities.SplashActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

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
                String email = "andjela1204@zipzapp.com";
                String firstName = "Andjela";
                String lastName = "Stojancevic";
                String appId = "7701890734418364";
                String appSecret = "TZdpfS4RvXxIzECimZ8BhT22LHumWfVe";
                String gender = "male";
                String dateOfBirth ="1992-08-31";
                String cpf= "11223344556";
                String phone = "06912345685";
                LoginActivity.registrationUser(appId, appSecret,email, firstName, lastName,gender,dateOfBirth,cpf, phone);
                Intent intent = new Intent(MainActivity.this, SplashActivity.class);
                startActivity(intent);
            }
        });

    }
}
