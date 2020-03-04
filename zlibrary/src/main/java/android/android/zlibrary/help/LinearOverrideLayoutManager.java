package android.android.zlibrary.help;

import android.content.Context;
import android.util.AttributeSet;

import androidx.recyclerview.widget.LinearLayoutManager;

public class LinearOverrideLayoutManager extends LinearLayoutManager {

    private boolean isVerticallScrollEnabled;

    public LinearOverrideLayoutManager(Context context) {
        super(context);
        isVerticallScrollEnabled = true;
    }

    public LinearOverrideLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
        isVerticallScrollEnabled = true;
    }

    public LinearOverrideLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        isVerticallScrollEnabled = true;
    }

    @Override
    public boolean canScrollVertically() {
        return isVerticallScrollEnabled && super.canScrollVertically();
    }

    public void setVerticallScrollEnabled(boolean verticallScrollEnabled) {
        isVerticallScrollEnabled = verticallScrollEnabled;
    }
}
