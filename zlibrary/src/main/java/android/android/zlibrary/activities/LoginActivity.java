package android.android.zlibrary.activities;

import android.android.zlibrary.R;
import android.android.zlibrary.ZipzApplication;
import android.android.zlibrary.model.login_response.LoginResponse;
import android.android.zlibrary.retrofit.RestClient;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.JsonObject;

import java.net.HttpURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

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
                if (etEmail.getText() != null && etPassword.getText() != null) {
                    login(etEmail.getText().toString(), etPassword.getText().toString());
                } else {
                    Toast.makeText(LoginActivity.this, "Try again!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void login(String email, String password) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("app_id", "2089815967985202");
        jsonObject.addProperty("app_secret", "ApUYnceKv09kaXQFuzbVRXz1CNTj4W3J");
        jsonObject.addProperty("email", email);
        jsonObject.addProperty("password", password);
        Call<LoginResponse> loginCall = RestClient.getInstance().service.
                emailLogin(jsonObject);
        loginCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                Log.d("login code", "response code" + response.code() + "");
                Log.d("login error", "error body" + response.errorBody() + "");
                if (response.isSuccessful() && response.code() == HttpURLConnection.HTTP_OK) {
                    LoginResponse loginResponse = response.body();
                    String token = loginResponse.getResponse().getToken();
                    Log.d("login token", "token = " + token + "");
                    Intent intent = new Intent(LoginActivity.this, MainZActivity.class);
                    ZipzApplication.getInstance().getmSessionManager().setToken(token);
                    ZipzApplication.getInstance().getmSessionManager().setIsLogin(true);

                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.d("login", "onFailure() called with: call = [" + call + "], t = [" + t + "]");
            }
        });
    }
}
