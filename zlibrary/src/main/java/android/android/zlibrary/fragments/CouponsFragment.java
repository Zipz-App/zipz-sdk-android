package android.android.zlibrary.fragments;

import android.android.zlibrary.R;
import android.android.zlibrary.help.LinearOverrideLayoutManager;
import android.android.zlibrary.model.VenueListModel;
import android.android.zlibrary.venue.VenueAdapter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CouponsFragment extends Fragment {
    private RecyclerView recyclerView;
    private VenueAdapter adapter;
    private ArrayList<VenueListModel> dataSet;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_coupons, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        VenueListModel venueListModel = new VenueListModel(12, "Fast-food", "McDonald's", "https://www.zipz.app/images/places/McDonalds-Top-Center-Shop-1575302165.png", "Loja 60/61 - Piso Bela Vista",
                20.456, 44.45666, true, false, 200, "distance", 1, 1, 1,
                "Sao Paolo", "Top center shopping", "place name", "state", "-Shop.Itaaguera", "fast food", "order");
        VenueListModel venueListModel2 = new VenueListModel(13, "Fast-food", "Taco Bell", "https://www.zipz.app/images/places/Taco-Bell-Top-Center-Shop-1575302084.png", "Loja P34 - Piso paulista",
                20.456, 44.45666, true, false, 200, "distance", 1, 1, 1,
                "Sao Paolo", "Top center shopping", "place name", "state", "-Shop.Itaaguera", "fast food", "order");
        VenueListModel venueListModel3 = new VenueListModel(14, "hamburgeria", "Guffo Burger", "https://www.zipz.app/images/places/Guffo-Burger-Zipz-App-1575301714.png", "1358 - Pargue Planatlo",
                20.456, 44.45666, true, false, 200, "distance", 1, 1, 1,
                "Sao Paolo", "Top center shopping", "place name", "state", "-Shop.Itaaguera", "fast food", "order");
        dataSet = new ArrayList<>();

        dataSet.add(venueListModel);
        dataSet.add(venueListModel2);
        dataSet.add(venueListModel3);
        adapter = new VenueAdapter(getContext(), dataSet);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearOverrideLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutAnimation(AnimationUtils.loadLayoutAnimation(getContext(), R.anim.recycler_layout_animation_fall_down));
    }
}