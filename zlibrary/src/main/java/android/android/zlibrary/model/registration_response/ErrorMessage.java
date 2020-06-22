package android.android.zlibrary.model.registration_response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ErrorMessage implements Parcelable {

    @SerializedName("email")
    @Expose
    private List<String> email = null;

    protected ErrorMessage(Parcel in) {
        email = in.createStringArrayList();
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

    public List<String> getEmail() {
        return email;
    }

    public void setEmail(List<String> email) {
        this.email = email;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringList(email);
    }
}
