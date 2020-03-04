package android.android.zlibrary.help;

import android.android.zlibrary.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

public class Helper {
    private static int imgDimen = 0;
    public static int getImgDimen(Context context) {
        if (imgDimen == 0) {
            View v = LayoutInflater.from(context).inflate(R.layout.item_venue_list, null);
            RelativeLayout linearContent = v.findViewById(R.id.linearContent);
            linearContent.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
            imgDimen = linearContent.getMeasuredHeight();
        }
        return imgDimen;
    }
}
