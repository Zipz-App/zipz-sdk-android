package android.android.zlibrary.retrofit;


import android.android.zlibrary.app.ZipzApplication;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient {

    private static RestClient instance = null;

    public final ApiInterface service;
    private static Retrofit retrofit;

    private RestClient() {

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okClient = new OkHttpClient.Builder()
                .addInterceptor(
                        new Interceptor() {
                            @Override
                            public Response intercept(Interceptor.Chain chain) throws IOException {
                                Request original = chain.request();
                                Request.Builder requestBuilder = original.newBuilder()
                                        .header("Accept", "application/json")
                                        .header("Authorization", "Bearer " + ZipzApplication.
                                                getInstance().getmSessionManager().getToken())
                                        .method(original.method(), original.body());
                                //if logged in add header access_token
//                                if (ZipzApplication.getInstance().getmSessionManager().isLoggedIn()) {
//                                    requestBuilder.header("Accept", "application/json")
//                                            .header("Authorization", "Bearer " + ZipzApplication.
//                                                    getInstance().getmSessionManager().getToken())
//                                            .method(original.method(), original.body());
//
//                                }


                                Request request = requestBuilder.build();
                                return chain.proceed(request);

                            }

                        })
                .addNetworkInterceptor(loggingInterceptor)
                .build();

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();
        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okClient)
                .baseUrl(Constants.BASE_URL)
                .build();

        service = retrofit.create(ApiInterface.class);
    }

    public static RestClient getInstance() {
        if (instance == null) {
            instance = new RestClient();
        }
        return instance;
    }

    public static Retrofit getRetrofit() {
        return retrofit;
    }
}
