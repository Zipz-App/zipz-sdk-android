package android.android.zlibrary.fragments;

import android.android.zlibrary.R;
import android.android.zlibrary.activities.MainZActivity;
import android.android.zlibrary.adapter.VenueClustersAdapter;
import android.android.zlibrary.adapter.VenuesAdapter;
import android.android.zlibrary.help.LinearOverrideLayoutManager;
import android.android.zlibrary.model.VenueListModel;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HomeScreen extends Fragment {

    private VenueClustersAdapter adapter;
    private ArrayList<VenueListModel> dataSet;
    RecyclerView recyclerView;

    private VenuesAdapter venuesAdapter;
    private ArrayList<VenueListModel> venueListModels;
    RecyclerView rvVenues;

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
        }, 10);
        recyclerView = view.findViewById(R.id.rcVenuesClusters);
        VenueListModel venueCluster = new VenueListModel(12, "Type 1", "Venue Cluster 1", "https://www.zipz.app/images/places/McDonalds-Top-Center-Shop-1575302165.png", "Address 1",
                20.456, 44.45666, true, false, 200, "distance", 1, 1, 1,
                "Sao Paolo", "", "place name", "state", "-Shop.Itaaguera", "fast food", "order");
        VenueListModel venueCluster1 = new VenueListModel(12, "Type 2", "Venue Cluster 2", "https://www.zipz.app/images/places/McDonalds-Top-Center-Shop-1575302165.png", "Address 2",
                20.456, 44.45666, true, false, 200, "distance", 1, 1, 1,
                "Sao Paolo", "", "place name", "state", "-Shop.Itaaguera", "fast food", "order");
        VenueListModel venueCluster2 = new VenueListModel(12, "Type 3", "Venue Cluster 3", "https://www.zipz.app/images/places/McDonalds-Top-Center-Shop-1575302165.png", "Address 3",
                20.456, 44.45666, true, false, 200, "distance", 1, 1, 1,
                "Sao Paolo", "", "place name", "state", "-Shop.Itaaguera", "fast food", "order");

        dataSet = new ArrayList<>();

        dataSet.add(venueCluster);
        dataSet.add(venueCluster1);
        dataSet.add(venueCluster2);
        adapter = new VenueClustersAdapter(getContext(), dataSet);
        recyclerView.setLayoutManager(new LinearOverrideLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutAnimation(AnimationUtils.loadLayoutAnimation(getContext(), R.anim.recycler_layout_animation_fall_down));

        rvVenues = view.findViewById(R.id.rcVenues);
        VenueListModel venue = new VenueListModel(12, "Type 1", "Venue  1", "https://www.zipz.app/images/places/McDonalds-Top-Center-Shop-1575302165.png", "Address 1",
                20.456, 44.45666, true, false, 200, "distance", 1, 1, 1,
                "Sao Paolo", "", "place name", "state", "-Shop.Itaaguera", "fast food", "order");
        VenueListModel venue1 = new VenueListModel(12, "Type 2", "Venue  2", "https://www.zipz.app/images/places/McDonalds-Top-Center-Shop-1575302165.png", "Address 2",
                20.456, 44.45666, true, false, 200, "distance", 1, 1, 1,
                "Sao Paolo", "", "place name", "state", "-Shop.Itaaguera", "fast food", "order");
        VenueListModel venue2 = new VenueListModel(12, "Type 3", "Venue  3", "https://www.zipz.app/images/places/McDonalds-Top-Center-Shop-1575302165.png", "Address 3",
                20.456, 44.45666, true, false, 200, "distance", 1, 1, 1,
                "Sao Paolo", "", "place name", "state", "-Shop.Itaaguera", "fast food", "order");

        venueListModels = new ArrayList<>();

        venueListModels.add(venue);
        venueListModels.add(venue1);
        venueListModels.add(venue2);
        venuesAdapter = new VenuesAdapter(getContext(), venueListModels);
        rvVenues.setLayoutManager(new LinearOverrideLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rvVenues.setAdapter(venuesAdapter);
        rvVenues.setLayoutAnimation(AnimationUtils.loadLayoutAnimation(getContext(), R.anim.recycler_layout_animation_fall_down));
    }
}
