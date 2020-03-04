package android.android.zlibrary.activities;

import android.android.zlibrary.R;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity  extends AppCompatActivity {

    public EditText etEmail, etPassword;
    public Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etEmail.getText().toString().equals("bruno@zipzapp.com") && etPassword.getText().toString().equals("zipzapp")) {
                    Intent intent = new Intent(LoginActivity.this, MainZActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(LoginActivity.this, "Try again!", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    public static void test(Context c, String message) {
        Toast.makeText(c, message, Toast.LENGTH_SHORT).show();
    }
}
