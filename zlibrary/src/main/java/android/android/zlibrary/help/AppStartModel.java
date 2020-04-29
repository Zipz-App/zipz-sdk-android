package android.android.zlibrary.help;

import android.app.Application;
import android.os.Build;

public class AppStartModel {

    private String DEVICE = "";
    private String OS = "";
    private String OS_VERSION = "";
    private String APP_VERSION = "";

    public AppStartModel(Application context) {

        try {
            DEVICE = Build.MANUFACTURER + " " + Build.MODEL;
        } catch (Exception e) {
        }
        OS = "android";
        try {
            OS_VERSION = Build.VERSION.RELEASE;
        } catch (Exception e) {
        }

    }

    public String getDEVICE() {
        return DEVICE;
    }

    public String getOS() {
        return OS;
    }

    public String getOS_VERSION() {
        return OS_VERSION;
    }

    public String getAPP_VERSION() {
        return APP_VERSION;
    }

}
