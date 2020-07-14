package android.android.zlibrary.database;

import android.android.zlibrary.app.ZipzApplication;
import android.android.zlibrary.model.offerdetails_response.Offer;
import android.android.zlibrary.model.registration_response.AppUser;
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

    public void saveMesssage(int code, String message) {
        editor.putInt(KEY_REQUEST_CODE, code);
        editor.putString(KEY_MESSAGE, message);
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

    public String getMessage() {
        return sharedPreferences.getString(KEY_MESSAGE, "");
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
}
