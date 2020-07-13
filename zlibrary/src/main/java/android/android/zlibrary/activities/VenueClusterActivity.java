package android.android.zlibrary.activities;

import android.android.zlibrary.R;
import android.android.zlibrary.adapter.VenuesAdapter;
import android.android.zlibrary.help.Helper;
import android.android.zlibrary.help.LinearOverrideLayoutManager;
import android.android.zlibrary.model.VenueListModel;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class VenueClusterActivity extends Activity {

    TextView txtVenueClusterName, txtVenueType, txtVenueAdrress;
    ImageView imgVenue;
    private VenuesAdapter venuesAdapter;
    private ArrayList<VenueListModel> venueListModels;
    RecyclerView rvVenues;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.venue_cluster_details);
        imgVenue = findViewById(R.id.imgVenue);
        imgVenue.getLayoutParams().width = Helper.getImgDimen(this);
        imgVenue.getLayoutParams().height = Helper.getImgDimen(this);
        Intent intent = getIntent();
        if (intent.hasExtra("name")) {
            txtVenueClusterName = findViewById(R.id.txtVenueClusterName);
            txtVenueClusterName.setText(intent.getStringExtra("name"));
        }
        if (intent.hasExtra("type")) {
            txtVenueType = findViewById(R.id.txtVenueType);
            txtVenueType.setText(intent.getStringExtra("type"));
        }
        if (intent.hasExtra("address")) {
            txtVenueAdrress = findViewById(R.id.txtVenueAdrress);
            txtVenueAdrress.setText(intent.getStringExtra("address"));
        }

        rvVenues = findViewById(R.id.rcVenues);
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
        venuesAdapter = new VenuesAdapter(this, venueListModels);
        rvVenues.setLayoutManager(new LinearOverrideLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvVenues.setAdapter(venuesAdapter);
        rvVenues.setLayoutAnimation(AnimationUtils.loadLayoutAnimation(this, R.anim.recycler_layout_animation_fall_down));
    }
}
