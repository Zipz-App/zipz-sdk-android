package android.android.zlibrary.database;

import android.android.zlibrary.app.ZipzApplication;
import android.android.zlibrary.model.registration_response.AppUser;
import android.android.zlibrary.model.venuecluster_response.VenueCluster;
import android.android.zlibrary.model.venueclusterdetails_response.Venue;
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
    private static final String KEY_REQUEST_CODE = "requestCode";
    private static final String KEY_MESSAGE = "message";

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
        Type type = new TypeToken<List<Venue>>(){}.getType();
        List<Venue> venueClusterList = gson.fromJson(json, type);
        return venueClusterList;
    }

    public void insertVenueClusterDetails(List<Venue> venue) {
        Gson gson = new Gson();
        String venueClusterJson = gson.toJson(venue);
        editor.putString(KEY_VENUE_CLUSTER_DETAILS, venueClusterJson);
        editor.apply();
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

    public String getMessage() {
        return sharedPreferences.getString(KEY_MESSAGE, "");
    }

    public int getRequestCode() {
        return sharedPreferences.getInt(KEY_REQUEST_CODE, 0);
    }

}
