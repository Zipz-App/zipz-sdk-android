package android.android.zlibrary.model.registration_response;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegistrationResponse implements Parcelable {

    @SerializedName("status")
    @Expose
    private Status status;
    @SerializedName("response")
    @Expose
    private RResponse response;

    protected RegistrationResponse(Parcel in) {
    }

    public static final Creator<RegistrationResponse> CREATOR = new Creator<RegistrationResponse>() {
        @Override
        public RegistrationResponse createFromParcel(Parcel in) {
            return new RegistrationResponse(in);
        }

        @Override
        public RegistrationResponse[] newArray(int size) {
            return new RegistrationResponse[size];
        }
    };

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public RResponse getResponse() {
        return response;
    }

    public void setResponse(RResponse response) {
        this.response = response;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
