package android.android.zlibrary.model.venueclusterdetails_response;

import android.android.zlibrary.model.registration_response.Status;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VenueClusterDetailsResponse {
    @SerializedName("status")
    @Expose
    private Status status;
    @SerializedName("response")
    @Expose
    private VCDetailsResponse response;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public VCDetailsResponse getResponse() {
        return response;
    }

    public void setResponse(VCDetailsResponse response) {
        this.response = response;
    }
}
