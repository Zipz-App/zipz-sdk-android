package android.android.zlibrary.model.offerdetails_response;

import android.android.zlibrary.model.registration_response.Status;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OfferDetailsResponse {
    @SerializedName("status")
    @Expose
    private Status status;
    @SerializedName("response")
    @Expose
    private OfferDResponse response;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public OfferDResponse getResponse() {
        return response;
    }

    public void setResponse(OfferDResponse response) {
        this.response = response;
    }
}
