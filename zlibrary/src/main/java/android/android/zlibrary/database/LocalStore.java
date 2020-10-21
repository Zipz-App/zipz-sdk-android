package android.android.zlibrary.database;

import android.android.zlibrary.app.ZipzApplication;
import android.android.zlibrary.model.error_response.ErrorResponse;
import android.android.zlibrary.model.offerdetails_response.Offer;
import android.android.zlibrary.model.redeem_transaction.RedeemTransaction;
import android.android.zlibrary.model.registration_response.AppUser;
import android.android.zlibrary.model.error_response.ErrorMessage;
import android.android.zlibrary.model.reserve_offer_response.ReserveOffer;
import android.android.zlibrary.model.transactions_response.Transaction;
import android.android.zlibrary.model.venuecluster_response.VenueCluster;
import android.android.zlibrary.model.venueclusterdetails_response.Venue;
import android.android.zlibrary.model.venuedetails_response.PrivateOffer;
import android.android.zlibrary.model.venuedetails_response.PublicOffer;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class LocalStore {
    private static final String PREF_NAME = "zipzsdk";

    private SharedPreferences sharedPreferences;
    private final SharedPreferences.Editor editor;
    private static LocalStore instance;

    private static final String KEY_TOKEN = "token";

    private static final String KEY_USER = "user";
    private static final String KEY_UUID = "uuid";
    private static final String IS_LOGIN = "isLoggedIn";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_VENUE_CLUSTER = "venueclusters";
    private static final String KEY_VENUE_CLUSTER_DETAILS = "venueclustersdetails";
    private static final String KEY_VENUE = "venue";
    private static final String KEY_REQUEST_CODE = "requestCode";
    private static final String KEY_MESSAGE = "message";
    private static final String KEY_REQUEST_CODE_VENUE_DETAILS = "requestCodeVenueDetails";
    private static final String KEY_MESSAGE_VENUE_DETAILS = "messageVenueDetails";
    private static final String KEY_VENUE_OBJECT = "venueObject";
    private static final String KEY_PRIVATE_OFFER = "privateoffer";
    private static final String KEY_PUBLIC_OFFER = "publicoffer";
    private static final String KEY_REQUEST_CODE_OFFER = "requestCodeOffer";
    private static final String KEY_MESSAGE_OFFER = "messageOffer";
    private static final String KEY_OFFER = "offer";
    private static final String KEY_TRANSACTIONS = "transactions";
    private static final String KEY_RESERVED_OFFER = "reserve_offer";
    private static final String KEY_QR_CODE = "qr_code";
    private static final String KEY_REDEEM_TRANSACTION = "redeem_transaction";
    private static final String KEY_ERROR_VENUE_CLUSTER = "error_venue_cluster";
    private static final String KEY_ERROR_VENUE_CLUSTER_DETAILS = "error_venue_cluster_details";
    private static final String KEY_ERROR_VENUE = "error_venue";
    private static final String KEY_ERROR_VENUE_DETAILS = "error_venue_details";
    private static final String KEY_ERROR_TRANSACTIONS = "error_transactions";
    private static final String KEY_ERROR_RESERVED_OFFER = "error_reserved_offer";
    private static final String KEY_ERROR_REDEEM_TRANSACTION = "error_redeem_transaction";
    private static final String KEY_ERROR_OFFER = "error_offer";
    private static final String KEY_ERROR_OFFER_DETAILS = "error_offer_details";

    public LocalStore(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, 0);
        editor = sharedPreferences.edit();
        editor.apply();
    }

    public static LocalStore getInstance(Context context) {
        if (instance == null) {
            instance = new LocalStore(context);
        }
        return instance;
    }


    public void setToken(String token) {
        editor.putString(KEY_TOKEN, token);
        editor.commit();
    }

    public String getToken() {
        return sharedPreferences.getString(KEY_TOKEN, "");
    }


    public void insertUser(AppUser user) {
        Gson gson = new Gson();
        String userJson = gson.toJson(user);
        editor.putString(KEY_USER, userJson);
        editor.apply();
    }

    public AppUser getUser() {
        Gson gson = new Gson();
        String json = sharedPreferences.getString(KEY_USER, "");
        return gson.fromJson(json, AppUser.class);

    }

    public void insertVenueCluster(List<VenueCluster> venueClusters) {
        Gson gson = new Gson();
        String venueClusterJson = gson.toJson(venueClusters);
        editor.putString(KEY_VENUE_CLUSTER, venueClusterJson);
        editor.apply();
    }

    public List<VenueCluster> getVenueClusterList() {
        Gson gson = new Gson();
        String json = sharedPreferences.getString(KEY_VENUE_CLUSTER, "");
        Type type = new TypeToken<List<VenueCluster>>() {
        }.getType();
        List<VenueCluster> venueClusterList = gson.fromJson(json, type);
        return venueClusterList;
    }

    public List<Venue> getVenueClusterDetailsList() {
        Gson gson = new Gson();
        String json = sharedPreferences.getString(KEY_VENUE_CLUSTER_DETAILS, "");
        Type type = new TypeToken<List<Venue>>() {
        }.getType();
        List<Venue> venueClusterList = gson.fromJson(json, type);
        return venueClusterList;
    }

    public void insertVenueClusterDetails(List<Venue> venue) {
        Gson gson = new Gson();
        String venueClusterJson = gson.toJson(venue);
        editor.putString(KEY_VENUE_CLUSTER_DETAILS, venueClusterJson);
        editor.apply();
    }

    public List<Venue> getVenuesList() {
        Gson gson = new Gson();
        String json = sharedPreferences.getString(KEY_VENUE, "");
        Type type = new TypeToken<List<Venue>>() {
        }.getType();
        List<Venue> venueClusterList = gson.fromJson(json, type);
        return venueClusterList;
    }

    public void insertVenues(List<Venue> venue) {
        Gson gson = new Gson();
        String venueClusterJson = gson.toJson(venue);
        editor.putString(KEY_VENUE, venueClusterJson);
        editor.apply();
    }

    public void insertPrivateOfferList(List<PrivateOffer> privateOffers) {
        Gson gson = new Gson();
        String privateOfferList = gson.toJson(privateOffers);
        editor.putString(KEY_PRIVATE_OFFER, privateOfferList);
        editor.apply();
    }

    public List<PrivateOffer> getPrivateOfferList() {
        Gson gson = new Gson();
        String json = sharedPreferences.getString(KEY_PRIVATE_OFFER, "");
        Type type = new TypeToken<List<PrivateOffer>>() {
        }.getType();
        List<PrivateOffer> privateOffers = gson.fromJson(json, type);
        return privateOffers;
    }

    public void insertPublicOfferList(List<PublicOffer> publicOffers) {
        Gson gson = new Gson();
        String publicOfferList = gson.toJson(publicOffers);
        editor.putString(KEY_PUBLIC_OFFER, publicOfferList);
        editor.apply();
    }

    public List<PublicOffer> getPublicOfferList() {
        Gson gson = new Gson();
        String json = sharedPreferences.getString(KEY_PUBLIC_OFFER, "");
        Type type = new TypeToken<List<PublicOffer>>() {
        }.getType();
        List<PublicOffer> publicOffers = gson.fromJson(json, type);
        return publicOffers;
    }

    public boolean isLoggedIn() {
        return sharedPreferences.getBoolean(IS_LOGIN, false);
    }

    public void setIsLogin(boolean isLogin) {
        Log.d("token", "setIsLogin() called with: isLogin = [" + isLogin + "]");
        editor.putBoolean(IS_LOGIN, isLogin);
        editor.commit();

    }

    public void saveUser(AppUser user) {
        Gson gson = new Gson();
        String jsonFavorites = gson.toJson(user);

        editor.putString("USER", jsonFavorites);

        editor.apply();
    }

    public AppUser getSavedUser() {
        AppUser user;
        sharedPreferences = ZipzApplication.getInstance().getApplicationContext().
                getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        String jsonUser = sharedPreferences.getString("USER", null);
        Gson gson = new Gson();
        user = gson.fromJson(jsonUser, AppUser.class);
        return user;
    }

    public void logoutUser() {
        editor.clear();
        editor.apply();
    }

    public void setUUID(String uuid) {
        editor.putString(KEY_UUID, uuid);
        editor.commit();
    }

    public String getUUID() {
        return sharedPreferences.getString(KEY_UUID, "");
    }

    public void setUserName(String name) {
        editor.putString(KEY_USERNAME, name);
        editor.commit();
    }

    public String getUserName() {
        return sharedPreferences.getString(KEY_USERNAME, "");
    }

    public void saveMesssage(int code, ErrorMessage message) {
        Gson gson = new Gson();
        String errorJson = gson.toJson(message);
        editor.putString(KEY_MESSAGE, errorJson);
        editor.putInt(KEY_REQUEST_CODE, code);
        editor.commit();
    }

    public void saveMessageVenueDetails(int code, String message) {
        editor.putInt(KEY_REQUEST_CODE_VENUE_DETAILS, code);
        editor.putString(KEY_MESSAGE_VENUE_DETAILS, message);
        editor.commit();
    }

    public void saveMessageOffer(int code, String message) {
        editor.putInt(KEY_REQUEST_CODE_OFFER, code);
        editor.putString(KEY_MESSAGE_OFFER, message);
        editor.commit();
    }

    public ErrorMessage getMessage() {
        Gson gson = new Gson();
        String json = sharedPreferences.getString(KEY_MESSAGE, "");
        return gson.fromJson(json, ErrorMessage.class);
    }

    public String getMessageVenueDetails() {
        return sharedPreferences.getString(KEY_MESSAGE_VENUE_DETAILS, "");
    }

    public String getMessageOffer() {
        return sharedPreferences.getString(KEY_MESSAGE_OFFER, "");
    }

    public int getRequestCode() {
        return sharedPreferences.getInt(KEY_REQUEST_CODE, 0);
    }

    public int getRequestCodeVenueDetails() {
        return sharedPreferences.getInt(KEY_REQUEST_CODE_VENUE_DETAILS, 0);
    }


    public int getRequestCodeOffer() {
        return sharedPreferences.getInt(KEY_REQUEST_CODE_OFFER, 0);
    }

    public void insertOffer(Offer offer) {
        Gson gson = new Gson();
        String offerJson = gson.toJson(offer);
        editor.putString(KEY_OFFER, offerJson);
        editor.apply();
    }

    public Offer getOffer() {
        Gson gson = new Gson();
        String json = sharedPreferences.getString(KEY_OFFER, "");
        return gson.fromJson(json, Offer.class);
    }

    public void insertReservedOffer(ReserveOffer reserveOffer) {
        Gson gson = new Gson();
        String reservedOffer = gson.toJson(reserveOffer);
        editor.putString(KEY_RESERVED_OFFER, reservedOffer);
        editor.apply();
    }

    public ReserveOffer getReservedOffer() {
        Gson gson = new Gson();
        String json = sharedPreferences.getString(KEY_RESERVED_OFFER, "");
        return gson.fromJson(json, ReserveOffer.class);
    }


    public void insertVenue(Venue venue) {
        Gson gson = new Gson();
        String venueJson = gson.toJson(venue);
        editor.putString(KEY_VENUE_OBJECT, venueJson);
        editor.apply();
    }

    public Venue getVenue() {
        Gson gson = new Gson();
        String json = sharedPreferences.getString(KEY_VENUE_OBJECT, "");
        return gson.fromJson(json, Venue.class);

    }

    public void insertTransaction(List<Transaction> transaction) {
        Gson gson = new Gson();
        String jsonTransaction = gson.toJson(transaction);
        editor.putString(KEY_TRANSACTIONS, jsonTransaction);
        editor.apply();
    }

    public List<Transaction> getTransactionList() {
        Gson gson = new Gson();
        String json = sharedPreferences.getString(KEY_TRANSACTIONS, "");
        Type type = new TypeToken<List<Transaction>>() {
        }.getType();
        return gson.fromJson(json, type);
    }

    public void insertQRCode(String qrCode) {
        editor.putString(KEY_QR_CODE, qrCode);
        editor.apply();
    }

    public String getQRCode() {
        return sharedPreferences.getString(KEY_QR_CODE, "").replaceAll("^\"|\"$", "");
    }

    public void insertRedeemTransaction(RedeemTransaction redeemTransaction) {
        Gson gson = new Gson();
        String jsonRedeem = gson.toJson(redeemTransaction);
        editor.putString(KEY_REDEEM_TRANSACTION, jsonRedeem);
        editor.apply();
    }

    public RedeemTransaction getRedeemTransaction() {
        Gson gson = new Gson();
        String json = sharedPreferences.getString(KEY_REDEEM_TRANSACTION, "");
        return gson.fromJson(json, RedeemTransaction.class);
    }

    public void saveMessageErrorVenueCluster(ErrorResponse message) {
        Gson gson = new Gson();
        String errorJson = gson.toJson(message);
        editor.putString(KEY_ERROR_VENUE_CLUSTER, errorJson);
        editor.commit();
    }
    public ErrorResponse getMessageErrorVenueCluster() {
        Gson gson = new Gson();
        String json = sharedPreferences.getString(KEY_ERROR_VENUE_CLUSTER, "");
        return gson.fromJson(json, ErrorResponse.class);
    }

    public void saveMessageErrorVenueClusterDetails(ErrorResponse message) {
        Gson gson = new Gson();
        String errorJson = gson.toJson(message);
        editor.putString(KEY_ERROR_VENUE_CLUSTER_DETAILS, errorJson);
        editor.commit();
    }
    public ErrorResponse getMessageErrorVenueClusterDetails() {
        Gson gson = new Gson();
        String json = sharedPreferences.getString(KEY_ERROR_VENUE_CLUSTER_DETAILS, "");
        return gson.fromJson(json, ErrorResponse.class);
    }

    public void saveMessageErrorVenue(ErrorResponse message) {
        Gson gson = new Gson();
        String errorJson = gson.toJson(message);
        editor.putString(KEY_ERROR_VENUE, errorJson);
        editor.commit();
    }
    public ErrorResponse getMessageErrorVenue() {
        Gson gson = new Gson();
        String json = sharedPreferences.getString(KEY_ERROR_VENUE, "");
        return gson.fromJson(json, ErrorResponse.class);
    }

    public void saveMessageErrorTransactions(ErrorResponse message) {
        Gson gson = new Gson();
        String errorJson = gson.toJson(message);
        editor.putString(KEY_ERROR_TRANSACTIONS, errorJson);
        editor.commit();
    }
    public ErrorResponse getMessageErrorTransactions() {
        Gson gson = new Gson();
        String json = sharedPreferences.getString(KEY_ERROR_TRANSACTIONS, "");
        return gson.fromJson(json, ErrorResponse.class);
    }

    public void saveMessageErrorReservedOffer(ErrorResponse message) {
        Gson gson = new Gson();
        String errorJson = gson.toJson(message);
        editor.putString(KEY_ERROR_RESERVED_OFFER, errorJson);
        editor.commit();
    }
    public ErrorResponse getMessageErrorReservedOffer() {
        Gson gson = new Gson();
        String json = sharedPreferences.getString(KEY_ERROR_RESERVED_OFFER, "");
        return gson.fromJson(json, ErrorResponse.class);
    }

    public void saveMessageErrorRedeemOffer(ErrorResponse message) {
        Gson gson = new Gson();
        String errorJson = gson.toJson(message);
        editor.putString(KEY_ERROR_REDEEM_TRANSACTION, errorJson);
        editor.commit();
    }
    public ErrorResponse getMessageErrorRedeemOffer() {
        Gson gson = new Gson();
        String json = sharedPreferences.getString(KEY_ERROR_REDEEM_TRANSACTION, "");
        return gson.fromJson(json, ErrorResponse.class);
    }

    public void saveMessageErrorOfferDetails(ErrorResponse message) {
        Gson gson = new Gson();
        String errorJson = gson.toJson(message);
        editor.putString(KEY_ERROR_OFFER_DETAILS, errorJson);
        editor.commit();
    }
    public ErrorResponse getMessageErrorOfferDetails() {
        Gson gson = new Gson();
        String json = sharedPreferences.getString(KEY_ERROR_OFFER_DETAILS, "");
        return gson.fromJson(json, ErrorResponse.class);
    }

    public void saveMessageErrorOffer(ErrorResponse message) {
        Gson gson = new Gson();
        String errorJson = gson.toJson(message);
        editor.putString(KEY_ERROR_OFFER, errorJson);
        editor.commit();
    }
    public ErrorResponse getMessageErrorOffer() {
        Gson gson = new Gson();
        String json = sharedPreferences.getString(KEY_ERROR_OFFER, "");
        return gson.fromJson(json, ErrorResponse.class);
    }

    public void saveMessageErrorVenueDetails(ErrorResponse message) {
        Gson gson = new Gson();
        String errorJson = gson.toJson(message);
        editor.putString(KEY_ERROR_VENUE_DETAILS, errorJson);
        editor.commit();
    }
    public ErrorResponse getMessageErrorVenueDetails() {
        Gson gson = new Gson();
        String json = sharedPreferences.getString(KEY_ERROR_VENUE_DETAILS, "");
        return gson.fromJson(json, ErrorResponse.class);
    }
}
