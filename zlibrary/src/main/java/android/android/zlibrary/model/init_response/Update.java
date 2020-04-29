package android.android.zlibrary.model.init_response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Update {
    @SerializedName("update")
    @Expose
    private Boolean update;
    @SerializedName("force_update")
    @Expose
    private Boolean forceUpdate;
    @SerializedName("active_version")
    @Expose
    private Integer activeVersion;
    @SerializedName("message")
    @Expose
    private String message;

    public Boolean getUpdate() {
        return update;
    }

    public void setUpdate(Boolean update) {
        this.update = update;
    }

    public Boolean getForceUpdate() {
        return forceUpdate;
    }

    public void setForceUpdate(Boolean forceUpdate) {
        this.forceUpdate = forceUpdate;
    }

    public Integer getActiveVersion() {
        return activeVersion;
    }

    public void setActiveVersion(Integer activeVersion) {
        this.activeVersion = activeVersion;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
