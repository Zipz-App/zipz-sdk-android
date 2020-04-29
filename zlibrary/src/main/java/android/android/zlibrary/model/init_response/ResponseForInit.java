package android.android.zlibrary.model.init_response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseForInit {
    @SerializedName("app_user")
    @Expose
    private AppUser appUser;
    @SerializedName("session_id")
    @Expose
    private Integer sessionId;
    @SerializedName("advertising_id")
    @Expose
    private List<Object> advertisingId = null;
    @SerializedName("notification_token")
    @Expose
    private Object notificationToken;
    @SerializedName("update")
    @Expose
    private Update update;
    @SerializedName("missing_fields")
    @Expose
    private Object missingFields;

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
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

    public Object getNotificationToken() {
        return notificationToken;
    }

    public void setNotificationToken(Object notificationToken) {
        this.notificationToken = notificationToken;
    }

    public Update getUpdate() {
        return update;
    }

    public void setUpdate(Update update) {
        this.update = update;
    }

    public Object getMissingFields() {
        return missingFields;
    }

    public void setMissingFields(Object missingFields) {
        this.missingFields = missingFields;
    }
}
