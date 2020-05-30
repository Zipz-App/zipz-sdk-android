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

public class HomeScreen extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final TextView tvName = view.findViewById(R.id.tvName);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (MainZActivity.name != null) {
                    tvName.setText("Name " + MainZActivity.name + "");
                }

            }
        }, 100);
    }
}
