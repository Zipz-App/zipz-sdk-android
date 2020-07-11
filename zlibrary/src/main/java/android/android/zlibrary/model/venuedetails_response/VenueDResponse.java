package android.android.zlibrary.model.venuedetails_response;

import android.android.zlibrary.model.venueclusterdetails_response.Venue;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VenueDResponse {

    @SerializedName("venue")
    @Expose
    private Venue venue;
    @SerializedName("offers")
    @Expose
    private Offers offers;

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public Offers getOffers() {
        return offers;
    }

    public void setOffers(Offers offers) {
        this.offers = offers;
    }
}
