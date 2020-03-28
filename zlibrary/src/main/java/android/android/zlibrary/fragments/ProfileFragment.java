package android.android.zlibrary.fragments;

import android.android.zlibrary.R;
import android.android.zlibrary.activities.MainZActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class ProfileFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final TextView tvAdvId = view.findViewById(R.id.tvAdvId);
        final TextView tvLatitude = view.findViewById(R.id.tvLatitude);
        final TextView tvLongitide = view.findViewById(R.id.tvLongitude);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                tvAdvId.setText(" " + MainZActivity.advId + "");
                tvLatitude.setText(" " + MainZActivity.latitudeValue + "");
                tvLongitide.setText(" " + MainZActivity.longitudeValue + "");
            }
        }, 200);
    }
}
