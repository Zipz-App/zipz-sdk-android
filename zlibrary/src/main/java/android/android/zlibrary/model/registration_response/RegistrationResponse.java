package android.android.zlibrary.model.registration_response;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegistrationResponse {

    @SerializedName("status")
    @Expose
    private Status status;
    @SerializedName("response")
    @Expose
    private RResponse response;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public RResponse getResponse() {
        return response;
    }

    public void setResponse(RResponse response) {
        this.response = response;
    }
}
