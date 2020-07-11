package android.android.zlibrary.model.venuedetails_response;

import android.android.zlibrary.model.registration_response.Status;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VenuesDetailsResponse {

    @SerializedName("status")
    @Expose
    private Status status;
    @SerializedName("response")
    @Expose
    private VenueDResponse response;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public VenueDResponse getResponse() {
        return response;
    }

    public void setResponse(VenueDResponse response) {
        this.response = response;
    }
}
