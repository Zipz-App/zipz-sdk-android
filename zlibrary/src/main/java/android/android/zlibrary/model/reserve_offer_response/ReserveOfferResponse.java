package android.android.zlibrary.model.reserve_offer_response;

import android.android.zlibrary.model.registration_response.Status;
import android.android.zlibrary.model.venueclusterdetails_response.VCDetailsResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReserveOfferResponse
{
    @SerializedName("status")
    @Expose
    private Status status;
    @SerializedName("response")
    @Expose
    private ROResponse response;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public ROResponse getResponse() {
        return response;
    }

    public void setResponse(ROResponse response) {
        this.response = response;
    }
}
