package android.android.zlibrary.retrofit;

import android.android.zlibrary.model.AdverIdResponse;
import android.android.zlibrary.model.init_response.InitResponse;
import android.android.zlibrary.model.registration_response.RegistrationResponse;
import android.android.zlibrary.model.venuecluster_response.VenueCLustersResponse;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST(Constants.REGISTRATION)
    Call<RegistrationResponse> registration(@Body JsonObject body);

    @POST(Constants.INIT)
    Call<InitResponse> init(@Body JsonObject jsonObject);

    @POST(Constants.ADVERTISING_ID)
    Call<AdverIdResponse> advertisingId(@Body JsonObject jsonObject);

    @POST(Constants.VENUE_CLUSTERS)
    Call<VenueCLustersResponse> venueClusters(@Body JsonObject jsonObject, @Header("Authorization") String token);

}

