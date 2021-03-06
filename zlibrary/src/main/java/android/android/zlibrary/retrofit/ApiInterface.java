package android.android.zlibrary.retrofit;

import android.android.zlibrary.model.AdverIdResponse;
import android.android.zlibrary.model.offerdetails_response.OfferDetailsResponse;
import android.android.zlibrary.model.init_response.InitResponse;
import android.android.zlibrary.model.redeem_transaction.RedeemTransactionResponse;
import android.android.zlibrary.model.registration_response.RegistrationResponse;
import android.android.zlibrary.model.reserve_offer_response.ReserveOfferResponse;
import android.android.zlibrary.model.transactions_response.TransactionResponse;
import android.android.zlibrary.model.venue_response.VenuesResponse;
import android.android.zlibrary.model.venuecluster_response.VenueCLustersResponse;
import android.android.zlibrary.model.venueclusterdetails_response.VenueClusterDetailsResponse;
import android.android.zlibrary.model.venuedetails_response.VenuesDetailsResponse;

import com.google.gson.JsonObject;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
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
    Call<VenueCLustersResponse> venueClusters(@Body JsonObject jsonObject);

    @POST(Constants.VENUE_CLUSTERS_DETAILS)
    Call<VenueClusterDetailsResponse> venueClustersDetails(@Body JsonObject jsonObject);

    @POST(Constants.VENUES)
    Call<VenuesResponse> venues(@Body JsonObject jsonObject);

    @POST(Constants.VENUES_DETAILS)
    Call<VenuesDetailsResponse> venueDetails(@Body JsonObject jsonObject);

    @POST(Constants.OFFER_DETAILS)
    Call<OfferDetailsResponse> offerDetails(@Body JsonObject jsonObject);

    @POST(Constants.RESERVE_OFFER)
    Call<ReserveOfferResponse> reserveOffer(@Body JsonObject jsonObject);

    @GET(Constants.TRANSACTIONS)
    Call<TransactionResponse> transactions();

    @POST(Constants.REDEEM_TRANSACTION)
    Call<RedeemTransactionResponse> redeemTransaction(@Body JsonObject jsonObject);
}

