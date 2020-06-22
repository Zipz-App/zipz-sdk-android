package android.android.zlibrary.model.registration_response;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RResponse implements Parcelable {
    @SerializedName("app_user")
    @Expose
    private AppUser appUser;

    protected RResponse(Parcel in) {
        appUser = in.readParcelable(AppUser.class.getClassLoader());
    }

    public static final Creator<RResponse> CREATOR = new Creator<RResponse>() {
        @Override
        public RResponse createFromParcel(Parcel in) {
            return new RResponse(in);
        }

        @Override
        public RResponse[] newArray(int size) {
            return new RResponse[size];
        }
    };

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(appUser, flags);
    }
}
