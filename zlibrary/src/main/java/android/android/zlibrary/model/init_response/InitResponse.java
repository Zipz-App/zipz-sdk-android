package android.android.zlibrary.model.init_response;

import android.android.zlibrary.model.registration_response.Status;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class InitResponse implements Parcelable {
    @SerializedName("status")
    @Expose
    private Status status;
    @SerializedName("response")
    @Expose
    private ResponseForInit response;

    protected InitResponse(Parcel in) {
    }

    public static final Creator<InitResponse> CREATOR = new Creator<InitResponse>() {
        @Override
        public InitResponse createFromParcel(Parcel in) {
            return new InitResponse(in);
        }

        @Override
        public InitResponse[] newArray(int size) {
            return new InitResponse[size];
        }
    };

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public ResponseForInit getResponse() {
        return response;
    }

    public void setResponse(ResponseForInit response) {
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
