package android.android.zlibrary.activities;

import android.android.zlibrary.R;
import android.android.zlibrary.help.LinearOverrideLayoutManager;
import android.android.zlibrary.model.VenueListModel;
import android.android.zlibrary.venue.VenueAdapter;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CouponActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private VenueAdapter adapter;
    private ArrayList<VenueListModel> dataSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zipz_venue_list);
        dataSet = new ArrayList<>();
        VenueListModel venueListModel = new VenueListModel(12, "Fast-food", "McDonald's", "https://www.zipz.app/images/places/McDonalds-Top-Center-Shop-1575302165.png", "Loja 60/61 - Piso Bela Vista",
                20.456, 44.45666, true, false, 200, "distance", 1, 1, 1,
                "Sao Paolo", "Top center shopping", "place name", "state", "-Shop.Itaaguera", "fast food", "order");
        VenueListModel venueListModel2 = new VenueListModel(13, "Fast-food", "Taco Bell", "https://www.zipz.app/images/places/Taco-Bell-Top-Center-Shop-1575302084.png", "Loja P34 - Piso paulista",
                20.456, 44.45666, true, false, 200, "distance", 1, 1, 1,
                "Sao Paolo", "Top center shopping", "place name", "state", "-Shop.Itaaguera", "fast food", "order");
        VenueListModel venueListModel3 = new VenueListModel(14, "hamburgeria", "Guffo Burger", "https://www.zipz.app/images/places/Guffo-Burger-Zipz-App-1575301714.png", "1358 - Pargue Planatlo",
                20.456, 44.45666, true, false, 200, "distance", 1, 1, 1,
                "Sao Paolo", "Top center shopping", "place name", "state", "-Shop.Itaaguera", "fast food", "order");

        dataSet.add(venueListModel);
        dataSet.add(venueListModel2);
        dataSet.add(venueListModel3);
        adapter = new VenueAdapter(this, dataSet);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearOverrideLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutAnimation(AnimationUtils.loadLayoutAnimation(this, R.anim.recycler_layout_animation_fall_down));


    }
}
