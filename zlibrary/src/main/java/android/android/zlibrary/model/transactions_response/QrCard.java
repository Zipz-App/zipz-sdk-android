package android.android.zlibrary.model.transactions_response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QrCard {

    @SerializedName("name")
    @Expose
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
