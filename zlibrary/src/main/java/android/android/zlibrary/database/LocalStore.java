package android.android.zlibrary.database;

import android.android.zlibrary.app.ZipzApplication;
import android.android.zlibrary.model.User;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;

import static android.provider.Contacts.SettingsColumns.KEY;

public class LocalStore {
    private static final String PREF_NAME = "zipzsdk";

    private SharedPreferences sharedPreferences;
    private final SharedPreferences.Editor editor;
    private static LocalStore instance;

    private static final String KEY_TOKEN = "token";

    private static final String KEY_USER = "user";
    private static final String KEY_UUID = "uuid" ;
    private static final String IS_LOGIN = "isLoggedIn";
    private static final String KEY_USERNAME = "username";

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


    public void insertUser(User user) {
        Gson gson = new Gson();
        String userJson = gson.toJson(user);
        editor.putString(KEY_USER, userJson);
        editor.apply();
    }

    public User getUser() {
        Gson gson = new Gson();
        String json = sharedPreferences.getString(KEY_USER, "");
        return gson.fromJson(json, User.class);

    }

    public boolean isLoggedIn() {
        return sharedPreferences.getBoolean(IS_LOGIN, false);
    }

    public void setIsLogin(boolean isLogin) {
        Log.d("token", "setIsLogin() called with: isLogin = [" + isLogin + "]");
        editor.putBoolean(IS_LOGIN, isLogin);
        editor.commit();
    }

    public void saveUser(User user) {
        Gson gson = new Gson();
        String jsonFavorites = gson.toJson(user);

        editor.putString("USER", jsonFavorites);

        editor.apply();
    }

    public User getSavedUser() {
        User user;
        sharedPreferences = ZipzApplication.getInstance().getApplicationContext().
                getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        String jsonUser = sharedPreferences.getString("USER", null);
        Gson gson = new Gson();
        user = gson.fromJson(jsonUser, User.class);
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

}
