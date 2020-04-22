package android.android.zlibrary.retrofit;

import android.android.zlibrary.model.LoginResponse;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST(Constants.LOGIN)
    Call<LoginResponse> emailLogin(@Body JsonObject body);

}
