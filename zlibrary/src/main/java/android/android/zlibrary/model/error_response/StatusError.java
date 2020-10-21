package android.android.zlibrary.model.error_response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StatusError
{
    @SerializedName("success")
    @Expose
    private String success;

    @SerializedName("status_code")
    @Expose
    private Integer statusCode;

    @SerializedName("error")
    @Expose
    private ErrorMessage errorMessage;


}
