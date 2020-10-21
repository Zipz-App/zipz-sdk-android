package android.android.zlibrary.model.error_response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ErrorMessage implements Parcelable {

    @SerializedName("message")
    @Expose
    private List<String> errorField = null;

    protected ErrorMessage(Parcel in) {
        errorField = in.createStringArrayList();
    }

    public static final Creator<ErrorMessage> CREATOR = new Creator<ErrorMessage>() {
        @Override
        public ErrorMessage createFromParcel(Parcel in) {
            return new ErrorMessage(in);
        }

        @Override
        public ErrorMessage[] newArray(int size) {
            return new ErrorMessage[size];
        }
    };

    public List<String> getErrorField() {
        return errorField;
    }

    public void setErrorField(List<String> errorField) {
        this.errorField = errorField;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringList(errorField);
    }
}
