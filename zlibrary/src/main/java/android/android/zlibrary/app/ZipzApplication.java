package android.android.zlibrary.app;

import android.android.zlibrary.database.LocalStore;
import android.android.zlibrary.help.AppStartModel;
import android.app.Application;
import android.content.Context;

public class ZipzApplication extends Application {

    private LocalStore mSessionManager;
    private static ZipzApplication sInstance = new ZipzApplication();
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

    }
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
    public AppStartModel getAppStartModel() {
        return new AppStartModel(this);
    }

}
