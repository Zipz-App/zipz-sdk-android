package android.android.zlibrary.fragments;

import android.android.zlibrary.R;
import android.android.zlibrary.adapter.OfferAdapter;
import android.android.zlibrary.app.ZipzApplication;
import android.android.zlibrary.model.error_response.ErrorMessage;
import android.android.zlibrary.model.error_response.ErrorResponse;
import android.android.zlibrary.model.venueclusterdetails_response.Venue;
import android.android.zlibrary.model.venuedetails_response.PrivateOffer;
import android.android.zlibrary.model.venuedetails_response.PublicOffer;
import android.android.zlibrary.model.venuedetails_response.VenueDResponse;
import android.android.zlibrary.model.venuedetails_response.VenuesDetailsResponse;
import android.android.zlibrary.retrofit.RestClient;
import android.os.Bundle;
import android.os.Handler;
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

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VenueDetailsFragment extends Fragment {
    private static final String ARGUMENT_UUID = "uuid";
    private static final String ARGUMENT_VENUE_TYPE = "type";
    private static final String ARGUMENT_VENUE_NAME = "name";
    private static final String ARGUMENT_VENUE_ADDRESS = "address";
    private static final String ARGUMENT_VENUE_IMAGE = "image";
    private static String errorMessage;
    private RecyclerView rvOffers;
    private ImageView ivBack, ivVenue, ivVenueDetails;
    private TextView tvToolbarTitle;
    private TextView tvVenueName, tvVenueAddress;

    private List<PublicOffer> publicOffers;

    public static VenueDetailsFragment newInstance(String uuid, String venueType,
                                                   String venueName, String venueAddress, String venueImage) {
        Bundle args = new Bundle();
        args.putString(ARGUMENT_UUID, uuid);
        args.putString(ARGUMENT_VENUE_TYPE, venueType);
        args.putString(ARGUMENT_VENUE_NAME, venueName);
        args.putString(ARGUMENT_VENUE_ADDRESS, venueAddress);
        args.putString(ARGUMENT_VENUE_IMAGE, venueImage);
        VenueDetailsFragment fragment = new VenueDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_venue_details, container, false);
        String uuid = getArguments().getString(ARGUMENT_UUID);
        String title = getArguments().getString(ARGUMENT_VENUE_TYPE);
        String name = getArguments().getString(ARGUMENT_VENUE_NAME);
        String address = getArguments().getString(ARGUMENT_VENUE_ADDRESS);
        String imageUrl = getArguments().getString(ARGUMENT_VENUE_IMAGE);
        publicOffers = new ArrayList<>();
        tvToolbarTitle = root.findViewById(R.id.tvToolbarTitle);
        rvOffers = root.findViewById(R.id.rvOffers);
        ivBack = root.findViewById(R.id.ivBack);
        tvVenueName = root.findViewById(R.id.tvVenueClusterName);
        tvVenueAddress = root.findViewById(R.id.tvVenueClusterAddress);
        ivVenue = root.findViewById(R.id.ivVenueCluster);

        tvToolbarTitle.setText(title);
        tvVenueName.setText(name);
        tvVenueAddress.setText(address);
        Picasso.get().load(imageUrl).into(ivVenue);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assert getFragmentManager() != null;
                getFragmentManager().popBackStackImmediate();
            }
        });

        getVenueDetails(uuid);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                populateLists();
                String message = checkMessage();
                int code = checkRequestCode();
            }
        }, 1000);


        return root;

    }


    public static void getVenueDetails(String uuid) {
        final JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("uuid", uuid);
        Call<VenuesDetailsResponse> venueClusterDetailsCall = RestClient.getInstance().service.
                venueDetails(jsonObject);
        venueClusterDetailsCall.enqueue(new Callback<VenuesDetailsResponse>() {
            @Override
            public void onResponse(Call<VenuesDetailsResponse> call, Response<VenuesDetailsResponse> response) {
                if (response.isSuccessful() && response.code() == HttpURLConnection.HTTP_OK) {
                    Log.d("venue details", "onResponse() called with: call = [" + call + "], response = [" + response + "]");
                    VenuesDetailsResponse venueDetailsResponse = response.body();

                    if (venueDetailsResponse.getStatus().getSuccess()) {
                        VenueDResponse venueDResponse = response.body().getResponse();
                        Gson gson = new Gson();
                        String str = gson.toJson(response.body().getResponse().getOffers().getPublicOffers());
                          Log.e("asdzxc", "ima" + str);
                        if (venueDResponse != null) {
                            Venue venue = venueDResponse.getVenue();

                            ZipzApplication.getInstance().getmSessionManager().insertVenue(venue);
                            getVenue();

                            List<PrivateOffer> listOfPrivateOffers = venueDResponse.getOffers().getPrivateOffers();
                            List<PublicOffer> listOfPublicOffers = venueDResponse.getOffers().getPublicOffers();

                            int privateOfferSize = listOfPrivateOffers.size();
                            int publicOfferSize = listOfPublicOffers.size();
                            if (publicOfferSize != 0) {
                                errorMessage = "Success.";
                                ZipzApplication.getInstance().getmSessionManager().saveMessageVenueDetails(200, errorMessage);
                                checkRequestCode();
                                checkMessage();
                                ZipzApplication.getInstance().getmSessionManager().insertPublicOfferList(listOfPublicOffers);
                                getListOfPublicOffer();
                            } else {
                                errorMessage = "The list of public offer is null.";
                                ZipzApplication.getInstance().getmSessionManager().saveMessageVenueDetails(200, errorMessage);
                                checkRequestCode();
                                checkMessage();
                            }

                            if (privateOfferSize != 0) {
                                errorMessage = "Success.";
                                ZipzApplication.getInstance().getmSessionManager().saveMessageVenueDetails(200, errorMessage);
                                checkRequestCode();
                                checkMessage();
                                ZipzApplication.getInstance().getmSessionManager().insertPrivateOfferList(listOfPrivateOffers);
                                getListOfPrivateOffer();
                            } else {
                                errorMessage = "The list of private offer is null.";
                                ZipzApplication.getInstance().getmSessionManager().saveMessageVenueDetails(200, errorMessage);
                                checkRequestCode();
                                checkMessage();
                            }

                        }
                    }

                } else {
                    if (!response.isSuccessful() && response.code() != HttpURLConnection.HTTP_OK)
                    {
                        Gson gson = new Gson();
                        try {
                            ErrorResponse errorResponse = gson.fromJson(response.errorBody().string(), ErrorResponse.class);
                            ZipzApplication.getInstance().getmSessionManager().saveMessageErrorVenueDetails(errorResponse);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<VenuesDetailsResponse> call, Throwable t) {
                Log.d("venue Response", "onFailure() called with: call = [" + call + "], t = [" + t + "]");
            }
        });
    }

    public static ErrorResponse getMessageErrorVenueDetails() {
        return ZipzApplication.getInstance().getmSessionManager().getMessageErrorVenueDetails();
    }
    public static Venue getVenue() {
        return ZipzApplication.getInstance().getmSessionManager().getVenue();
    }

    public static List<PrivateOffer> getListOfPrivateOffer() {
        return ZipzApplication.getInstance().getmSessionManager().getPrivateOfferList();
    }

    public static List<PublicOffer> getListOfPublicOffer() {
        return ZipzApplication.getInstance().getmSessionManager().getPublicOfferList();
    }

    private void populateLists() {
        publicOffers = getListOfPublicOffer();
        if (publicOffers != null && publicOffers.size() != 0) {
            OfferAdapter adapter = new OfferAdapter(publicOffers);
            rvOffers.setHasFixedSize(true);
            LinearLayoutManager layoutManager
                    = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
            rvOffers.setLayoutManager(layoutManager);
            rvOffers.setAdapter(adapter);
        }
    }

    public static int checkRequestCode() {
        return ZipzApplication.getInstance().getmSessionManager().getRequestCodeVenueDetails();
    }

    public static String checkMessage() {
        return ZipzApplication.getInstance().getmSessionManager().getMessageVenueDetails();
    }
}
