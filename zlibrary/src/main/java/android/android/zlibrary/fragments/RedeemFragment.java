package android.android.zlibrary.fragments;

import android.android.zlibrary.R;
import android.android.zlibrary.app.ZipzApplication;
import android.android.zlibrary.model.error_response.ErrorResponse;
import android.android.zlibrary.model.redeem_transaction.RTResponse;
import android.android.zlibrary.model.redeem_transaction.RedeemTransaction;
import android.android.zlibrary.model.redeem_transaction.RedeemTransactionResponse;
import android.android.zlibrary.retrofit.RestClient;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RedeemFragment extends Fragment {

    public static RedeemFragment newInstance() {
        RedeemFragment redeemFragment = new RedeemFragment();
        return redeemFragment;
    }

    ;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_transaction, container, false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getQRCode();
            }
        }, 1000);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public static String getQRCode() {
        return ZipzApplication.getInstance().getmSessionManager().getQRCode();
    }

    public static RedeemTransaction getRedeemTransaction() {
        return ZipzApplication.getInstance().getmSessionManager().getRedeemTransaction();
    }

    public static ErrorResponse getMessageErrorRedeemTransaction() {
        return ZipzApplication.getInstance().getmSessionManager().getMessageErrorRedeemOffer();
    }

    public static void redeemTransaction(String uuid, String qrCode, final String expirationTime) {
//        if (checkIfReservedOfferExpired(expirationTime)) {
            final JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("uuid", uuid);
            jsonObject.addProperty("qr_code", qrCode);

            Call<RedeemTransactionResponse> redeemTransactionResponseCall = RestClient.getInstance().service
                    .redeemTransaction(jsonObject);

            redeemTransactionResponseCall.enqueue(new Callback<RedeemTransactionResponse>() {
                @Override
                public void onResponse(Call<RedeemTransactionResponse> call, Response<RedeemTransactionResponse> response) {
                    if (response.isSuccessful() && response.code() == HttpURLConnection.HTTP_OK) {
                        Log.e("redeem offer", "onResponse() called with: call = [" + call + "], response = [" + response + "]");
                        RedeemTransactionResponse redeemTransactionResponse = response.body();
                        if (redeemTransactionResponse.getStatus().getSuccess()) {
                            RTResponse rtResponse = response.body().getResponse();
                            if (rtResponse != null) {
                                RedeemTransaction redeemTransaction = rtResponse.getRedeemTransaction();
                                String errorMessage = "Success.";
                                ZipzApplication.getInstance().getmSessionManager().insertRedeemTransaction(redeemTransaction);
                            }

                        }


                    }
                    else {
                        if (!response.isSuccessful() && response.code() != HttpURLConnection.HTTP_OK)
                        {
                            Gson gson = new Gson();
                            try {
                                ErrorResponse errorResponse = gson.fromJson(response.errorBody().string(), ErrorResponse.class);
                                ZipzApplication.getInstance().getmSessionManager().saveMessageErrorRedeemOffer(errorResponse);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                    }

                }

                @Override
                public void onFailure(Call<RedeemTransactionResponse> call, Throwable t) {
                    Log.e("reserve Response", "onFailure() called with: call = [" + call + "], t = [" + t + "]");
                }
            });

    }

    private static boolean checkIfReservedOfferExpired(String expirationTime) {
        if (expirationTime != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
            String currentDateAndTime = sdf.format(new Date());
            if (expirationTime.compareTo(currentDateAndTime) > 0) {
                return true;
            }

        }
        return false;
    }
}

