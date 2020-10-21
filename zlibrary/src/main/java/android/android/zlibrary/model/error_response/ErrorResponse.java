package android.android.zlibrary.model.error_response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ErrorResponse
{
    @SerializedName("status")
    @Expose
    private StatusError statusError;

    public ErrorResponse(StatusError statusError) {
        this.statusError = statusError;
    }

    public StatusError getStatusError() {
        return statusError;
    }

    public void setStatusError(StatusError statusError) {
        this.statusError = statusError;
    }
}
