package android.android.zlibrary.model.venuecluster_response;

import android.android.zlibrary.model.registration_response.Status;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VenueCLustersResponse implements Parcelable {
    @SerializedName("status")
    @Expose
    private Status status;
    @SerializedName("response")
    @Expose
    private VenueCResponse response;

    protected VenueCLustersResponse(Parcel in) {
        status = in.readParcelable(Status.class.getClassLoader());
        response = in.readParcelable(VenueCResponse.class.getClassLoader());
    }

    public static final Creator<VenueCLustersResponse> CREATOR = new Creator<VenueCLustersResponse>() {
        @Override
        public VenueCLustersResponse createFromParcel(Parcel in) {
            return new VenueCLustersResponse(in);
        }

        @Override
        public VenueCLustersResponse[] newArray(int size) {
            return new VenueCLustersResponse[size];
        }
    };

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public VenueCResponse getResponse() {
        return response;
    }

    public void setResponse(VenueCResponse response) {
        this.response = response;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(status, flags);
        dest.writeParcelable(response, flags);
    }
}
