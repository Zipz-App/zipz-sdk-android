package android.android.zlibrary.model.registration_response;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ErrorRegistrationResponse {
    @SerializedName("status")
    @Expose
    private Status status;
    @SerializedName("response")
    @Expose
    private Object response;

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
