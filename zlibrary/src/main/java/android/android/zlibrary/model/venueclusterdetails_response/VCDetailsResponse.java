package android.android.zlibrary.model.venueclusterdetails_response;

import android.android.zlibrary.model.venuecluster_response.VenueCluster;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VCDetailsResponse {

    @SerializedName("venue_cluster")
    @Expose
    private VenueCluster venueCluster;
    @SerializedName("venues")
    @Expose
    private List<Venue> venues = null;

    public VenueCluster getVenueCluster() {
        return venueCluster;
    }

    public void setVenueCluster(VenueCluster venueCluster) {
        this.venueCluster = venueCluster;
    }

    public List<Venue> getVenues() {
        return venues;
    }

    public void setVenues(List<Venue> venues) {
        this.venues = venues;
    }
}
