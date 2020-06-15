package android.android.zlibrary.activities;

import android.android.zlibrary.R;
import android.android.zlibrary.ZipzApplication;
import android.android.zlibrary.model.registration_response.ErrorRegistrationResponse;
import android.android.zlibrary.model.registration_response.RegistrationResponse;
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

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.HttpURLConnection;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
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
                   // login(etEmail.getText().toString(), etPassword.getText().toString());
                    registration(etEmail.getText().toString(), etPassword.getText().toString());
                } else {
                    Toast.makeText(LoginActivity.this, "Try again!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

//    private void login(String email, String password) {
//        JsonObject jsonObject = new JsonObject();
//        jsonObject.addProperty("app_id", "7701890734418364");
//        jsonObject.addProperty("app_secret", "TZdpfS4RvXxIzECimZ8BhT22LHumWfVe");
//        jsonObject.addProperty("email", email);
//        jsonObject.addProperty("password", password);
//        Call<LoginResponse> loginCall = RestClient.getInstance().service.
//                emailLogin(jsonObject);
//        loginCall.enqueue(new Callback<LoginResponse>() {
//            @Override
//            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
//                Log.d("login code", "response code" + response.code() + "");
//                Log.d("login error", "error body" + response.errorBody() + "");
//                if (response.isSuccessful() && response.code() == HttpURLConnection.HTTP_OK) {
//                    LoginResponse loginResponse = response.body();
//                    String token = loginResponse.getResponse().getToken();
//                    Log.d("login token", "token = " + token + "");
//                    Intent intent = new Intent(LoginActivity.this, MainZActivity.class);
//                    ZipzApplication.getInstance().getmSessionManager().setToken(token);
//                    ZipzApplication.getInstance().getmSessionManager().setIsLogin(true);
//
//                    startActivity(intent);
//                    finish();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<LoginResponse> call, Throwable t) {
//                Log.d("login", "onFailure() called with: call = [" + call + "], t = [" + t + "]");
//            }
//        });
//    }


    private void registration(String email, String password) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("app_id", "7701890734418364");
        jsonObject.addProperty("app_secret", "TZdpfS4RvXxIzECimZ8BhT22LHumWfVe");
        jsonObject.addProperty("email", "andjela+11@zipzapp.com");
        jsonObject.addProperty("first_name", "Andjela");
        jsonObject.addProperty("last_name", "Stojancevic");
        jsonObject.addProperty("gender", "female");
        jsonObject.addProperty("date_of_birth", "1992-08-31");
        jsonObject.addProperty("cpf", "11223344556");
        jsonObject.addProperty("phone", "06912345685");
        Call<RegistrationResponse> registrationCall = RestClient.getInstance().service.
                registration(jsonObject);
        registrationCall.enqueue(new Callback<RegistrationResponse>() {
            @Override
            public void onResponse(Call<RegistrationResponse> call, Response<RegistrationResponse> response) {
                Log.d("registrationCall code", "response code" + response.code() + "");
                Log.d("registrationCall error", "error body" + response.errorBody() + "");
                if (response.isSuccessful() && response.code() == HttpURLConnection.HTTP_OK) {
                    RegistrationResponse registrationCall = response.body();
                    assert response.body() != null;
                    String uuid =response.body().getResponse().getAppUser().getUuid();
                    ZipzApplication.getInstance().getmSessionManager().setUUID(uuid);
                    Intent intent = new Intent(LoginActivity.this, MainZActivity.class);
                    ZipzApplication.getInstance().getmSessionManager().setIsLogin(true);

                    startActivity(intent);
                    finish();
                } if (response.code()==422){
                    Log.d("aaaaaaaaaa", "error body" + response.errorBody() + "");

                    Converter<ResponseBody, ErrorRegistrationResponse> converter = RestClient.getRetrofit().responseBodyConverter(ErrorRegistrationResponse.class, new Annotation[0]);
                    ErrorRegistrationResponse errorModel = null;
                    try {
                        assert response.errorBody() != null;
                        errorModel = converter.convert(response.errorBody());
                        assert errorModel != null;
                        String message = errorModel.getStatus().getError().getEmail().get(0);
                        Log.d("aaaaaa", "onResponse() called with: call = [" + call + "], response = [" + message + "]");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<RegistrationResponse> call, Throwable t) {
                Log.d("registrationCall", "onFailure() called with: call = [" + call + "], t = [" + t + "]");
            }
        });
    }
}
