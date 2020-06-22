package android.android.zlibrary.activities;

import android.android.zlibrary.R;
import android.android.zlibrary.app.ZipzApplication;
import android.android.zlibrary.model.registration_response.AppUser;
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

    public EditText etEmail, etPassword, etFirstName, etLastName;
    public Button btnLogin;

    public static void registrationUser(String email, String firstName, String lastName) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("app_id", "7701890734418364");
        jsonObject.addProperty("app_secret", "TZdpfS4RvXxIzECimZ8BhT22LHumWfVe");
        jsonObject.addProperty("email", email);
        jsonObject.addProperty("first_name", firstName);
        jsonObject.addProperty("last_name", lastName);
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
                    String uuid = response.body().getResponse().getAppUser().getUuid();
                    String name = response.body().getResponse().getAppUser().getFirstName() + " " + response.body().getResponse().getAppUser().getLastName();
                    ZipzApplication.getInstance().getmSessionManager().setUUID(uuid);
                    ZipzApplication.getInstance().getmSessionManager().setUserName(name);
                   // Intent intent = new Intent(LoginActivity.this, MainZActivity.class);
                    ZipzApplication.getInstance().getmSessionManager().setIsLogin(true);
                    getUserInfo();
                  //  startActivity(intent);
                  //  finish();
                } else if (response.code() == 422) {

                    Log.d("aaaaaaaaaa", "error body" + response.errorBody() + "");
                    Toast.makeText(ZipzApplication.getInstance(), "Something went wrong", Toast.LENGTH_SHORT).show();

                    Converter<ResponseBody, ErrorRegistrationResponse> converter = RestClient.getRetrofit().responseBodyConverter(ErrorRegistrationResponse.class, new Annotation[0]);
                    ErrorRegistrationResponse errorModel = null;
                    try {
                        assert response.errorBody() != null;
                        errorModel = converter.convert(response.errorBody());
                        assert errorModel != null;
                        String message = errorModel.getStatus().getError().getEmail().get(0);
                        sentExceptionForEmail(message);
                        Log.d("aaaaaa", "onResponse() called with: call = [" + call + "], response = [" + message + "]");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    sentExceptionServer();
                    Toast.makeText(ZipzApplication.getInstance(), "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RegistrationResponse> call, Throwable t) {
                Log.d("registrationCall", "onFailure() called with: call = [" + call + "], t = [" + t + "]");
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etEmail = findViewById(R.id.et_email);
        etFirstName = findViewById(R.id.et_firstName);
        etLastName = findViewById(R.id.et_lastName);
        // etPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etEmail.getText() != null && etFirstName.getText() != null && etLastName.getText() != null) {
                    registration(etEmail.getText().toString(), etFirstName.getText().toString(), etLastName.getText().toString());
                } else {
                    Toast.makeText(LoginActivity.this, "Try again!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void registration(String email, String firstName, String lastName) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("app_id", "7701890734418364");
        jsonObject.addProperty("app_secret", "TZdpfS4RvXxIzECimZ8BhT22LHumWfVe");
        jsonObject.addProperty("email", email);
        jsonObject.addProperty("first_name", firstName);
        jsonObject.addProperty("last_name", lastName);
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
                    String uuid = response.body().getResponse().getAppUser().getUuid();
                    String name = response.body().getResponse().getAppUser().getFirstName() + " " + response.body().getResponse().getAppUser().getLastName();
                    ZipzApplication.getInstance().getmSessionManager().setUUID(uuid);
                    ZipzApplication.getInstance().getmSessionManager().setUserName(name);
                    Intent intent = new Intent(LoginActivity.this, MainZActivity.class);
                    ZipzApplication.getInstance().getmSessionManager().setIsLogin(true);

                    getUserInfo();
                    startActivity(intent);
                    finish();
                } else if (response.code() == 422) {

                    Log.d("aaaaaaaaaa", "error body" + response.errorBody() + "");
                    Toast.makeText(LoginActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();

                    Converter<ResponseBody, ErrorRegistrationResponse> converter = RestClient.getRetrofit().responseBodyConverter(ErrorRegistrationResponse.class, new Annotation[0]);
                    ErrorRegistrationResponse errorModel = null;
                    try {
                        assert response.errorBody() != null;
                        errorModel = converter.convert(response.errorBody());
                        assert errorModel != null;
                        String message = errorModel.getStatus().getError().getEmail().get(0);
                        sentExceptionForEmail(message);
                        Log.d("aaaaaa", "onResponse() called with: call = [" + call + "], response = [" + message + "]");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    sentExceptionServer();
                    Toast.makeText(LoginActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RegistrationResponse> call, Throwable t) {
                Log.d("registrationCall", "onFailure() called with: call = [" + call + "], t = [" + t + "]");
            }
        });
    }

    public static String getUserInfo() {
        String fullName = ZipzApplication.getInstance().getmSessionManager().getUserName();
        Log.d("userinfo", "getUserInfo() called with: appUser = [" + fullName + "]");
        Toast.makeText(ZipzApplication.getInstance(), "" + fullName+ "", Toast.LENGTH_SHORT).show();
        return fullName;
    }

    public static void sentExceptionServer() {
        String message = "Something went wrong";
        Toast.makeText(ZipzApplication.getInstance(), "" + message + "", Toast.LENGTH_SHORT).show();
    }

    public static void sentExceptionForEmail(String message) {
        Log.d("error message", "sentExceptionForEmail() message = [" + message + "]");
        Toast.makeText(ZipzApplication.getInstance(), "" + message + "", Toast.LENGTH_SHORT).show();
    }
}
