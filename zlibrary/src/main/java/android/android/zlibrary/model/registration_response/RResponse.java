package android.android.zlibrary.model.registration_response;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RResponse {
    @SerializedName("app_user")
    @Expose
    private AppUser appUser;

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }
}
