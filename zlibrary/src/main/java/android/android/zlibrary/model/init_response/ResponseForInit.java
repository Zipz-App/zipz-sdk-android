package android.android.zlibrary.model.init_response;

import android.android.zlibrary.model.registration_response.AppUser;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseForInit {
    @SerializedName("app_user")
    @Expose
    private AppUser appUser;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("session_id")
    @Expose
    private Integer sessionId;
    @SerializedName("advertising_id")
    @Expose
    private List<Object> advertisingId = null;

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getSessionId() {
        return sessionId;
    }

    public void setSessionId(Integer sessionId) {
        this.sessionId = sessionId;
    }

    public List<Object> getAdvertisingId() {
        return advertisingId;
    }

    public void setAdvertisingId(List<Object> advertisingId) {
        this.advertisingId = advertisingId;
    }
}
