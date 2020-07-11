package android.android.zlibrary.fragments;

import android.android.zlibrary.R;
import android.android.zlibrary.adapter.OfferAdapter;
import android.android.zlibrary.app.ZipzApplication;
import android.android.zlibrary.model.venueclusterdetails_response.Venue;
import android.android.zlibrary.model.venuedetails_response.PrivateOffer;
import android.android.zlibrary.model.venuedetails_response.PublicOffer;
import android.android.zlibrary.model.venuedetails_response.VenueDResponse;
import android.android.zlibrary.model.venuedetails_response.VenuesDetailsResponse;
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
        String message = errorMessage;
        Log.d("test", "onCreateView() " + message);
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
                        if (venueDResponse != null) {
                            Venue venue = venueDResponse.getVenue();
                            List<PrivateOffer> listOfPrivateOffers = venueDResponse.getOffers().getPrivateOffers();
                            List<PublicOffer> listOfPublicOffers = venueDResponse.getOffers().getPublicOffers();

                            int privateOffer = listOfPrivateOffers.size();
                            int publicOffer = listOfPublicOffers.size();
                            if (publicOffer != 0) {
                                venueDetailsCallback("Success.");
                                errorMessage = "Success.";
                                ZipzApplication.getInstance().getmSessionManager().insertPublicOfferList(listOfPublicOffers);
                                getListOfPublicOffer();
                            } else {
                                errorMessage = "The list of public offer is null.";
                                venueDetailsCallback("The list of public offer is null.");
                            }
                            if (privateOffer != 0) {
                                errorMessage = "Success.";
                                venueDetailsCallback("Success.");
                                getVenueDetailsCallback();

                                ZipzApplication.getInstance().getmSessionManager().insertPrivateOfferList(listOfPrivateOffers);
                                getListOfPrivateOffer();
                            } else {
                                errorMessage= "The list of private offer is null.";
                                venueDetailsCallback("The list of private offer is null.");
                            }
                            Log.d("offer private", "onResponse() called with: call = [" + privateOffer + "]");
                            Log.d("offer public", "onResponse() called with: call = [" + publicOffer + "]");
                        }
                    } else if (response.code() == 422) {
                        errorMessage= "The uuid field is required.";
                        venueDetailsCallback("The uuid field is required.");
                    } else if (response.code() == 500) {
                        errorMessage = "Something went wrong.";
                        venueDetailsCallback("Something went wrong.");
                    }

                }
            }

            @Override
            public void onFailure(Call<VenuesDetailsResponse> call, Throwable t) {
                Log.d("venue Response", "onFailure() called with: call = [" + call + "], t = [" + t + "]");
            }
        });
    }

    public static List<PrivateOffer> getListOfPrivateOffer() {
        return ZipzApplication.getInstance().getmSessionManager().getPrivateOfferList();
    }

    public static List<PublicOffer> getListOfPublicOffer() {
        int size = ZipzApplication.getInstance().getmSessionManager().getPublicOfferList().size();
        return ZipzApplication.getInstance().getmSessionManager().getPublicOfferList();
    }

    public static String venueDetailsCallback(String message) {
        errorMessage = message;
        return message;
    }

    public static String getVenueDetailsCallback() {
        return errorMessage;
    }


    private void populateLists(List<PublicOffer> privateOffers) {
        if (privateOffers != null && privateOffers.size() != 0) {
            OfferAdapter adapter = new OfferAdapter(privateOffers);
            rvOffers.setHasFixedSize(true);
            LinearLayoutManager layoutManager
                    = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
            rvOffers.setLayoutManager(layoutManager);
            rvOffers.setAdapter(adapter);
        }
    }
}
