package android.android.zlibrary.model.init_response;

import android.android.zlibrary.model.login_response.Status;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class InitResponse {
    @SerializedName("status")
    @Expose
    private Status status;
    @SerializedName("response")
    @Expose
    private ResponseForInit response;

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
}
