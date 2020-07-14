package android.android.zlibrary.activities;

import android.android.zlibrary.R;
import android.android.zlibrary.adapter.OffersAdapter;
import android.android.zlibrary.app.ZipzApplication;
import android.android.zlibrary.help.Helper;
import android.android.zlibrary.model.VenueListModel;
import android.android.zlibrary.model.offerdetails_response.Offer;
import android.android.zlibrary.model.offerdetails_response.OfferDResponse;
import android.android.zlibrary.model.offerdetails_response.OfferDetailsResponse;
import android.android.zlibrary.model.venueclusterdetails_response.Venue;
import android.android.zlibrary.retrofit.RestClient;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.JsonObject;

import java.net.HttpURLConnection;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OfferActivity extends Activity {

    TextView txtVenueName, txtVenueType, txtVenueAdrress;
    ImageView imgVenue;
    private OffersAdapter offersAdapter;
    private ArrayList<VenueListModel> venueListModels;
    RecyclerView rvOffers;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.offer_activity);

        imgVenue = findViewById(R.id.imgVenue);
        imgVenue.getLayoutParams().width = Helper.getImgDimen(this);
        imgVenue.getLayoutParams().height = Helper.getImgDimen(this);
        Intent intent = getIntent();
        if (intent.hasExtra("name")) {
            txtVenueName = findViewById(R.id.txtVenueName);
            txtVenueName.setText(intent.getStringExtra("name"));
        }
        if (intent.hasExtra("type")) {
            txtVenueType = findViewById(R.id.txtVenueType);
            txtVenueType.setText(intent.getStringExtra("type"));
        }
        if (intent.hasExtra("address")) {
            txtVenueAdrress = findViewById(R.id.txtVenueAdrress);
            txtVenueAdrress.setText(intent.getStringExtra("address"));
        }
        if (intent.hasExtra("uuid")) {
            String uuid = intent.getStringExtra("uuid");
            offerDetailsRequest(uuid);
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getOffer();
            }
        }, 1000);


    }

    public static void offerDetailsRequest(String uuid) {
        final JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("uuid", uuid);
        Call<OfferDetailsResponse> offerDetailsResponseCall = RestClient.getInstance().service.
                offerDetails(jsonObject);
        offerDetailsResponseCall.enqueue(new Callback<OfferDetailsResponse>() {
            @Override
            public void onResponse(Call<OfferDetailsResponse> call, Response<OfferDetailsResponse> response) {
                String errorMessage;
                if (response.isSuccessful() && response.code() == HttpURLConnection.HTTP_OK) {
                    Log.d("offer details", "onResponse() called with: call = [" + call + "], response = [" + response + "]");
                    OfferDetailsResponse offerDetailsResponse = response.body();
                    if (offerDetailsResponse.getStatus().getSuccess()) {
                        OfferDResponse offerDResponse = response.body().getResponse();
                        if (offerDResponse != null) {
                            Offer offer = offerDResponse.getOffer();
                            Venue venue = offerDResponse.getVenue();
                            errorMessage = "Success.";
                            ZipzApplication.getInstance().getmSessionManager().saveMessageOffer(200, errorMessage);
                            checkRequestCode();
                            checkMessage();
                            ZipzApplication.getInstance().getmSessionManager().insertOffer(offer);
                            getOffer();
                        }

                    }


                } else if (response.code() == 422) {
                    errorMessage = "The uuid field is required.";
                    ZipzApplication.getInstance().getmSessionManager().saveMessageOffer(422, errorMessage);
                    checkRequestCode();
                    checkMessage();
                } else if (response.code() == 500) {
                    errorMessage = "Something went wrong.";
                    ZipzApplication.getInstance().getmSessionManager().saveMessageOffer(500, errorMessage);
                    checkRequestCode();
                    checkMessage();
                }
            }

            @Override
            public void onFailure(Call<OfferDetailsResponse> call, Throwable t) {
                Log.d("offer Response", "onFailure() called with: call = [" + call + "], t = [" + t + "]");
            }
        });


    }

    public static Offer getOffer() {
        return ZipzApplication.getInstance().getmSessionManager().getOffer();
    }

    public static int checkRequestCode() {
        return ZipzApplication.getInstance().getmSessionManager().getRequestCodeOffer();
    }

    public static String checkMessage() {
        return ZipzApplication.getInstance().getmSessionManager().getMessageOffer();
    }
}
