package android.android.zlibrary.model.venue_response;

import android.android.zlibrary.model.registration_response.Status;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VenuesResponse {

    @SerializedName("status")
    @Expose
    private Status status;
    @SerializedName("response")
    @Expose
    private VenueResponse response;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public VenueResponse getResponse() {
        return response;
    }

    public void setResponse(VenueResponse response) {
        this.response = response;
    }
}
