package android.android.zlibrary.model.init_response;

import android.android.zlibrary.model.registration_response.AppUser;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseForInit implements Parcelable {
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

    protected ResponseForInit(Parcel in) {
        token = in.readString();
        if (in.readByte() == 0) {
            sessionId = null;
        } else {
            sessionId = in.readInt();
        }
    }

    public static final Creator<ResponseForInit> CREATOR = new Creator<ResponseForInit>() {
        @Override
        public ResponseForInit createFromParcel(Parcel in) {
            return new ResponseForInit(in);
        }

        @Override
        public ResponseForInit[] newArray(int size) {
            return new ResponseForInit[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(token);
        if (sessionId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(sessionId);
        }
    }
}
