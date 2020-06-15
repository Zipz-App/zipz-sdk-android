package android.android.zlibrary.activities;

import android.android.zlibrary.R;
import android.android.zlibrary.adapter.OffersAdapter;
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

public class OfferActivity extends Activity {

    TextView txtVenueName,txtVenueType,txtVenueAdrress;
    ImageView imgVenue;
    private OffersAdapter offersAdapter;
    private ArrayList<VenueListModel> venueListModels;
    RecyclerView rvOffers;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.offer_activity);

        imgVenue = findViewById(R.id.imgVenue);
        imgVenue.getLayoutParams().width = Helper.getImgDimen(this);
        imgVenue.getLayoutParams().height = Helper.getImgDimen(this);
        Intent intent = getIntent();
        if (intent.hasExtra("name")) {
            txtVenueName = findViewById(R.id.txtVenueName);
            txtVenueName.setText(intent.getStringExtra("name"));
        }
        if (intent.hasExtra("type")) {
            txtVenueType = findViewById(R.id.txtVenueType);
            txtVenueType.setText(intent.getStringExtra("type"));
        }
        if (intent.hasExtra("address")) {
            txtVenueAdrress = findViewById(R.id.txtVenueAdrress);
            txtVenueAdrress.setText(intent.getStringExtra("address"));
        }



    }
}
