package android.android.zlibrary.model.registration_response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ErrorMessage {

//    @SerializedName("password")
////    @Expose
////    private String password = null;
////    @SerializedName("email_login")
////    @Expose
////    private String emailLogin = null;
////
////    public String getPassword() {
////        return password;
////    }
////
////    public void setPassword(String password) {
////        this.password = password;
////    }
////
////    public String getEmailLogin() {
////        return emailLogin;
////    }
////
////    public void setEmailLogin(String emailLogin) {
////        this.emailLogin = emailLogin;
////    }

    @SerializedName("email")
    @Expose
    private List<String> email = null;

    public List<String> getEmail() {
        return email;
    }

    public void setEmail(List<String> email) {
        this.email = email;
    }
}
