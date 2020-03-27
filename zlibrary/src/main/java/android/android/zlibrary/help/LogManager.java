package android.android.zlibrary.help;

public class LogManager {

    private static final String TAG = "ZIPZ_APP";
    private static final boolean IS_LOG_ALLOWED = true;

    public static void logError(Exception e) {
        if (IS_LOG_ALLOWED) {
            e.printStackTrace();
        }
    }
}
