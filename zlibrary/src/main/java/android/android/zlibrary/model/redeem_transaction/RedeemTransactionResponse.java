package android.android.zlibrary.model.redeem_transaction;

import android.android.zlibrary.model.registration_response.Status;
import android.android.zlibrary.model.transactions_response.TResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RedeemTransactionResponse {

    @SerializedName("status")
    @Expose
    private Status status;
    @SerializedName("response")
    @Expose
    private RTResponse response;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public RTResponse getResponse() {
        return response;
    }

    public void setResponse(RTResponse response) {
        this.response = response;
    }
}
