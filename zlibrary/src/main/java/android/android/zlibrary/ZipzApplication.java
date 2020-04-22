package android.android.zlibrary;

import android.android.zlibrary.database.LocalStore;
import android.app.Application;

public class ZipzApplication extends Application {

    private LocalStore mSessionManager;
    private static ZipzApplication sInstance = new ZipzApplication();

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        mSessionManager = new LocalStore(getApplicationContext());
    }

    public static ZipzApplication getInstance() {
        return sInstance;
    }

    public LocalStore getmSessionManager() {
        return mSessionManager;
    }


}