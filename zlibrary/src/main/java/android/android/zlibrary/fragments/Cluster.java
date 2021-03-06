package android.android.zlibrary.fragments;

import android.android.zlibrary.R;
import android.android.zlibrary.adapter.ShoppingAdapter;
import android.android.zlibrary.adapter.VenueAdapter;
import android.android.zlibrary.app.ZipzApplication;
import android.android.zlibrary.help.SpaceItemDecoration;
import android.android.zlibrary.model.error_response.ErrorMessage;
import android.android.zlibrary.model.error_response.ErrorResponse;
import android.android.zlibrary.model.venue_response.VenuesResponse;
import android.android.zlibrary.model.venuecluster_response.VenueCLustersResponse;
import android.android.zlibrary.model.venuecluster_response.VenueCluster;
import android.android.zlibrary.model.venueclusterdetails_response.Venue;
import android.android.zlibrary.model.venueclusterdetails_response.VenueClusterDetailsResponse;
import android.android.zlibrary.retrofit.RestClient;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Cluster extends Fragment {

    private static List<Venue> venues;
    RecyclerView recyclerView;

    List<VenueCluster> shoppingList = new ArrayList<>();
    List<VenueCluster> categoriesList = new ArrayList<>();
    List<VenueCluster> brandsList = new ArrayList<>();
    List<VenueCluster> lohasList = new ArrayList<>();

    private List<Venue> venueList;
    RecyclerView rvVenues;
    private VenueAdapter venueAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView tvName = view.findViewById(R.id.tvName);

        if (ZipzApplication.getInstance() != null) {
            if (ZipzApplication.getInstance().getmSessionManager() != null) {
                if (ZipzApplication.getInstance().getmSessionManager().getUserName() != null) {
                    if (tvName != null) {
                        tvName.setText(String.format("Name %s", ZipzApplication.getInstance().getmSessionManager().getUserName()));
                    }
                }
            }
        }

        recyclerView = view.findViewById(R.id.rcVenuesClusters);

        getVenueList();

        venueClustersRequest();
        venueClustersDetails();

        rvVenues = view.findViewById(R.id.rcVenues);
        getVenue();

    }

    private  void populateLists() {
        if (shoppingList != null && shoppingList.size() != 0) {
            ShoppingAdapter adapter = new ShoppingAdapter(shoppingList);
            recyclerView.setHasFixedSize(true);
            LinearLayoutManager layoutManager
                    = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);
        }
    }

    private void venueClustersDetails() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("uuid", ZipzApplication.getInstance().getmSessionManager().getUUID());

        Call<VenueClusterDetailsResponse> initCall = RestClient.getInstance().service.venueClustersDetails(jsonObject);
        initCall.enqueue(new Callback<VenueClusterDetailsResponse>() {
            @Override
            public void onResponse(Call<VenueClusterDetailsResponse> call, Response<VenueClusterDetailsResponse> response) {
                Log.d("Venue clusters code", "response code" + response.code() + "");
                Log.d("Venue clusters error", "error body" + response.errorBody() + "");
                if (response.isSuccessful() && response.code() == HttpURLConnection.HTTP_OK) {
                    VenueClusterDetailsResponse venueClusterDetailsResponse = response.body();
                    if (venueClusterDetailsResponse != null) {
                        List<Venue> venueList = venueClusterDetailsResponse.getResponse().getVenues();
                        int size = venueList.size();
                    }

                }

            }

            @Override
            public void onFailure(Call<VenueClusterDetailsResponse> call, Throwable t) {
                Log.d("Venue clusters", "onFailure() called with: call = [" + call + "], t = [" + t + "]");
            }
        });
    }

    private void getVenue() {
        JsonObject jsonObject = new JsonObject();
        final Call<VenuesResponse> venuesCall = RestClient.getInstance().service.
                venues(jsonObject);
        venuesCall.enqueue(new Callback<VenuesResponse>() {
            @Override
            public void onResponse(Call<VenuesResponse> call, Response<VenuesResponse> response) {
                if (response.isSuccessful() && response.code() == HttpURLConnection.HTTP_OK) {
                    VenuesResponse venuesResponse = response.body();
                    venueList = venuesResponse.getResponse().getVenues();
                    populateVenueLists();
                }
            }

            @Override
            public void onFailure(Call<VenuesResponse> call, Throwable t) {
                Log.d("venue call", "onFailure() called with: call = [" + call + "], t = [" + t + "]");
            }
        });


    }

    public static void getVenueList() {
        JsonObject jsonObject = new JsonObject();
        final Call<VenuesResponse> venuesCall = RestClient.getInstance().service.
                venues(jsonObject);
        venuesCall.enqueue(new Callback<VenuesResponse>() {
            @Override
            public void onResponse(Call<VenuesResponse> call, Response<VenuesResponse> response) {
                if (response.isSuccessful() && response.code() == HttpURLConnection.HTTP_OK) {
                    Log.e("venuecall", "onResponse() called with: call = [" + call + "], response = [" + response + "]");
                    VenuesResponse venuesResponse = response.body();
                    if(venuesResponse.getResponse().getVenues().size() != 0)
                    {
                        venues = venuesResponse.getResponse().getVenues();
                        ZipzApplication.getInstance().getmSessionManager().insertVenues(venues);
                        getVenuesList();
                    }

                }
                else {
                    if (!response.isSuccessful() && response.code() != HttpURLConnection.HTTP_OK)
                    {
                        Gson gson = new Gson();
                        try {
                            ErrorResponse errorResponse = gson.fromJson(response.errorBody().string(), ErrorResponse.class);
                            ZipzApplication.getInstance().getmSessionManager().saveMessageErrorVenue(errorResponse);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }


            @Override
            public void onFailure(Call<VenuesResponse> call, Throwable t) {
                Log.e("venuecall", "onFailure() called with: call = [" + call + "], t = [" + t + "]");
            }
        });


    }
    public static ErrorResponse getMessageErrorVenue() {
        return ZipzApplication.getInstance().getmSessionManager().getMessageErrorVenue();
    }
    public static List<Venue> getVenuesList() {
        return ZipzApplication.getInstance().getmSessionManager().getVenuesList();
    }

    private void populateVenueLists() {
        if (venueList != null && venueList.size() != 0) {
            Log.d("venues !!!", "populateLists() called");
            venueAdapter = new VenueAdapter(venueList);
            rvVenues.setHasFixedSize(true);
            LinearLayoutManager layoutManager
                    = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
            rvVenues.setLayoutManager(layoutManager);
            rvVenues.addItemDecoration(new SpaceItemDecoration(18));
            rvVenues.setAdapter(venueAdapter);
        }

    }

    private void venueClustersRequest() {
        JsonObject jsonObject = new JsonObject();
        final Call<VenueCLustersResponse> venueClusterCall = RestClient.getInstance().service.
                venueClusters(jsonObject);
        venueClusterCall.enqueue(new Callback<VenueCLustersResponse>() {
            @Override
            public void onResponse(Call<VenueCLustersResponse> call, Response<VenueCLustersResponse> response) {
                if (response.isSuccessful() && response.code() == HttpURLConnection.HTTP_OK) {
                    Log.d("venue Cluster Response", "onResponse() called with: call = [" + call + "], response = [" + response + "]");
                    VenueCLustersResponse venueClusterResponse = response.body();
                    if (venueClusterResponse.getStatus().getSuccess()) {
                        VenueCLustersResponse venueCLustersResponse = response.body();
                        if (venueCLustersResponse != null) {
                            List<VenueCluster> venueClusterList = venueCLustersResponse.getResponse().getVenueClusters();
                            for (int i = 0; i < venueClusterList.size(); i++) {
                                String type = venueClusterList.get(i).getType();
                                if (type.equals("shopping")) {
                                    shoppingList.add(venueClusterList.get(i));
                                } else if (type.equals("category")) {
                                    categoriesList.add(venueClusterList.get(i));
                                } else if (type.equals("brand")) {
                                    brandsList.add(venueClusterList.get(i));
                                } else if (type.equals("venue")) {
                                    lohasList.add(venueClusterList.get(i));
                                }
                                ZipzApplication.getInstance().getmSessionManager().insertVenueCluster(venueClusterList);
                                getVenueClusterList();
                                populateLists();
                            }
                            int size = venueClusterList.size();
                            Log.d("vc list size", "onResponse() called with: call = [" + size + "]");
                        }
                    } else {
                        ErrorMessage errorMessage = response.body().getStatus().getError();
                        ZipzApplication.getInstance().getmSessionManager().saveMesssage(response.code(),errorMessage);
                    }

                }
            }

            @Override
            public void onFailure(Call<VenueCLustersResponse> call, Throwable t) {
                Log.d("venue Cluster Response", "onFailure() called with: call = [" + call + "], t = [" + t + "]");
            }
        });
    }

    public static void venueClusterList() {
        JsonObject jsonObject = new JsonObject();
        Call<VenueCLustersResponse> initCall = RestClient.getInstance().service.venueClusters(jsonObject);
        initCall.enqueue(new Callback<VenueCLustersResponse>() {
            @Override
            public void onResponse(Call<VenueCLustersResponse> call, Response<VenueCLustersResponse> response) {
                Log.d("Venue clusters code", "response code" + response.code() + "");
                Log.d("Venue clusters error", "error body" + response.errorBody() + "");
                if (response.isSuccessful() && response.code() == HttpURLConnection.HTTP_OK) {
                    VenueCLustersResponse venueCLustersResponse = response.body();
                    if (venueCLustersResponse != null) {
                        List<VenueCluster> venueClusterList = venueCLustersResponse.getResponse().getVenueClusters();
                        ZipzApplication.getInstance().getmSessionManager().insertVenueCluster(venueClusterList);
                        getVenueClusterList();
                        int size = venueClusterList.size();
                        Log.d("vc list size", "onResponse() called with: call = [" + size + "]");
                    }


                } else {
                    if (!response.isSuccessful() && response.code() != HttpURLConnection.HTTP_OK)
                    {
                        Gson gson = new Gson();
                        try {
                            ErrorResponse errorResponse = gson.fromJson(response.errorBody().string(), ErrorResponse.class);
                            ZipzApplication.getInstance().getmSessionManager().saveMessageErrorVenueCluster(errorResponse);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }

            @Override
            public void onFailure(Call<VenueCLustersResponse> call, Throwable t) {
                Log.d("Venue clusters", "onFailure() called with: call = [" + call + "], t = [" + t + "]");
            }
        });
    }
    public static ErrorResponse getMessageErrorVenueCluster() {
        return ZipzApplication.getInstance().getmSessionManager().getMessageErrorVenueCluster();
    }
    public static List<VenueCluster> getVenueClusterList() {
        return ZipzApplication.getInstance().getmSessionManager().getVenueClusterList();
    }
}
