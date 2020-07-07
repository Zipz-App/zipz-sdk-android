package android.android.zlibrary.fragments;

import android.android.zlibrary.R;
import android.android.zlibrary.adapter.VenueAdapter;
import android.android.zlibrary.app.ZipzApplication;
import android.android.zlibrary.model.venueclusterdetails_response.Venue;
import android.android.zlibrary.model.venueclusterdetails_response.VenueClusterDetailsResponse;
import android.android.zlibrary.retrofit.RestClient;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VenueClusterDetailsFragment extends Fragment {
    private static final String ARGUMENT_UUID = "uuid";
    private static final String ARGUMENT_VENUE_CLUSTER_TYPE = "type";
    private static final String ARGUMENT_VENUE_CLUSTER_NAME = "name";
    private static final String ARGUMENT_VENUE_CLUSTER_ADDRESS = "address";
    private static final String ARGUMENT_VENUE_CLUSTER_IMAGE = "image";
    private RecyclerView rvVenue;
    private ImageView ivBack, ivVenueCluster, ivVenueClusterDetails;
    private TextView tvToolbarTitle;
    private TextView tvVenueClusterName, tvVenueClusterAddress;


    public static VenueClusterDetailsFragment newInstance(String uuid, String venueClusterType,
                                                          String venueClusterName, String venueClusterAddress, String venueClusterImage) {
        Bundle args = new Bundle();
        args.putString(ARGUMENT_UUID, uuid);
        args.putString(ARGUMENT_VENUE_CLUSTER_TYPE, venueClusterType);
        args.putString(ARGUMENT_VENUE_CLUSTER_NAME, venueClusterName);
        args.putString(ARGUMENT_VENUE_CLUSTER_ADDRESS, venueClusterAddress);
        args.putString(ARGUMENT_VENUE_CLUSTER_IMAGE, venueClusterImage);
        VenueClusterDetailsFragment fragment = new VenueClusterDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_venue_cluster_details, container, false);
        String uuid = getArguments().getString(ARGUMENT_UUID);
        String title = getArguments().getString(ARGUMENT_VENUE_CLUSTER_TYPE);
        String name = getArguments().getString(ARGUMENT_VENUE_CLUSTER_NAME);
        String address = getArguments().getString(ARGUMENT_VENUE_CLUSTER_ADDRESS);
        String imageUrl = getArguments().getString(ARGUMENT_VENUE_CLUSTER_IMAGE);

        tvToolbarTitle = root.findViewById(R.id.tvToolbarTitle);
        rvVenue = root.findViewById(R.id.rvVenue);
        ivBack = root.findViewById(R.id.ivBack);
        tvVenueClusterName = root.findViewById(R.id.tvVenueClusterName);
        tvVenueClusterAddress = root.findViewById(R.id.tvVenueClusterAddress);
        ivVenueCluster = root.findViewById(R.id.ivVenueCluster);

        tvToolbarTitle.setText(title);
        tvVenueClusterName.setText(name);
        tvVenueClusterAddress.setText(address);
        Picasso.get().load(imageUrl).into(ivVenueCluster);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assert getFragmentManager() != null;
                getFragmentManager().popBackStackImmediate();
            }
        });

        getVenueClustersDetails(uuid);
        getVCDetails(uuid);

        List<Venue> aa = getVenueClustersListDetails(uuid);
        Log.d("aaa !!", "onCreateView() called with: inflater = [" + aa.size() + "]");

        return root;

    }

    private void getVCDetails(String uuid) {
        final JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("uuid", uuid);
        Call<VenueClusterDetailsResponse> venueClusterDetailsCall = RestClient.getInstance().service.
                venueClustersDetails(jsonObject);
        venueClusterDetailsCall.enqueue(new Callback<VenueClusterDetailsResponse>() {
            @Override
            public void onResponse(Call<VenueClusterDetailsResponse> call, Response<VenueClusterDetailsResponse> response) {
                if (response.isSuccessful() && response.code() == HttpURLConnection.HTTP_OK) {
                    Log.d("venue Cluster details", "onResponse() called with: call = [" + call + "], response = [" + response + "]");
                    VenueClusterDetailsResponse venueClusterDetailsResponse = response.body();
                    if (venueClusterDetailsResponse.getStatus().getSuccess()) {
                        VenueClusterDetailsResponse clusterDetailsResponse = response.body();
                        if (clusterDetailsResponse != null) {
                            List<Venue> venueList = clusterDetailsResponse.getResponse().getVenues();

                            populateLists(venueList);

                            int size = venueList.size();
                            Log.d("vc frfrfr", "onResponse() called with: call = [" + size + "]");
                        }
                    } else if (venueClusterDetailsResponse.getStatus().getStatusCode() == 422) {

                    }

                }
            }

            @Override
            public void onFailure(Call<VenueClusterDetailsResponse> call, Throwable t) {
                Log.d("venue Cluster Response", "onFailure() called with: call = [" + call + "], t = [" + t + "]");
            }
        });
    }

    public static void getVenueClustersDetails(String uuid) {
        final JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("uuid", uuid);
        Call<VenueClusterDetailsResponse> venueClusterDetailsCall = RestClient.getInstance().service.
                venueClustersDetails(jsonObject);
        venueClusterDetailsCall.enqueue(new Callback<VenueClusterDetailsResponse>() {
            @Override
            public void onResponse(Call<VenueClusterDetailsResponse> call, Response<VenueClusterDetailsResponse> response) {
                if (response.isSuccessful() && response.code() == HttpURLConnection.HTTP_OK) {
                    Log.d("venue Cluster details", "onResponse() called with: call = [" + call + "], response = [" + response + "]");
                    VenueClusterDetailsResponse venueClusterDetailsResponse = response.body();
                    if (venueClusterDetailsResponse.getStatus().getSuccess()) {
                        VenueClusterDetailsResponse clusterDetailsResponse = response.body();
                        if (clusterDetailsResponse != null) {
                            List<Venue> venueList = clusterDetailsResponse.getResponse().getVenues();

                            ZipzApplication.getInstance().getmSessionManager().insertVenueClusterDetails(venueList);
                            getVenueClusterDetailsList();

                            int size = venueList.size();
                            Log.d("vc frfrfr", "onResponse() called with: call = [" + size + "]");
                        }
                    } else if (venueClusterDetailsResponse.getStatus().getStatusCode() == 422) {

                    }

                }
            }

            @Override
            public void onFailure(Call<VenueClusterDetailsResponse> call, Throwable t) {
                Log.d("venue Cluster Response", "onFailure() called with: call = [" + call + "], t = [" + t + "]");
            }
        });
    }

    public static List<Venue> getVenueClustersListDetails(String uuid) {
        final List<Venue>[] venueList = new List[0];
        final JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("uuid", uuid);
        Call<VenueClusterDetailsResponse> venueClusterDetailsCall = RestClient.getInstance().service.
                venueClustersDetails(jsonObject);
        venueClusterDetailsCall.enqueue(new Callback<VenueClusterDetailsResponse>() {
            @Override
            public void onResponse(Call<VenueClusterDetailsResponse> call, Response<VenueClusterDetailsResponse> response) {
                if (response.isSuccessful() && response.code() == HttpURLConnection.HTTP_OK) {
                    Log.d("venue Cluster details", "onResponse() called with: call = [" + call + "], response = [" + response + "]");
                    VenueClusterDetailsResponse venueClusterDetailsResponse = response.body();
                    if (venueClusterDetailsResponse.getStatus().getSuccess()) {
                        VenueClusterDetailsResponse clusterDetailsResponse = response.body();
                        if (clusterDetailsResponse != null) {
                             venueList[0] = clusterDetailsResponse.getResponse().getVenues();

                            ZipzApplication.getInstance().getmSessionManager().insertVenueClusterDetails(venueList[0]);
                            getVenueClusterDetailsList();

                            int size = venueList[0].size();
                            Log.d("vc frfrfr", "onResponse() called with: call = [" + size + "]");
                        }
                    } else if (venueClusterDetailsResponse.getStatus().getStatusCode() == 422) {

                    }

                }
            }

            @Override
            public void onFailure(Call<VenueClusterDetailsResponse> call, Throwable t) {
                Log.d("venue Cluster Response", "onFailure() called with: call = [" + call + "], t = [" + t + "]");
            }
        });
        return venueList[0];
    }

    public static List<Venue> getVenueClusterDetailsList() {
        return ZipzApplication.getInstance().getmSessionManager().getVenueClusterDetailsList();
    }

    private void populateLists(List<Venue> venueList) {
        if (venueList != null && venueList.size() != 0) {
            VenueAdapter adapter = new VenueAdapter(venueList);
            rvVenue.setHasFixedSize(true);
            LinearLayoutManager layoutManager
                    = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
            rvVenue.setLayoutManager(layoutManager);
            rvVenue.setAdapter(adapter);
        }
    }

}
