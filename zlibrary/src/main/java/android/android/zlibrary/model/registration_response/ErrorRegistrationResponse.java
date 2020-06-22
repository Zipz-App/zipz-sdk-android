package android.android.zlibrary.model.registration_response;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ErrorRegistrationResponse implements Parcelable {
    @SerializedName("status")
    @Expose
    private Status status;
    @SerializedName("response")
    @Expose
    private Object response;

    protected ErrorRegistrationResponse(Parcel in) {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ErrorRegistrationResponse> CREATOR = new Creator<ErrorRegistrationResponse>() {
        @Override
        public ErrorRegistrationResponse createFromParcel(Parcel in) {
            return new ErrorRegistrationResponse(in);
        }

        @Override
        public ErrorRegistrationResponse[] newArray(int size) {
            return new ErrorRegistrationResponse[size];
        }
    };

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }

}
