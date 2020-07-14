package android.android.zlibrary.model.offerdetails_response;

import android.android.zlibrary.model.venueclusterdetails_response.Venue;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OfferDResponse {
    @SerializedName("venue")
    @Expose
    private Venue venue;
    @SerializedName("offer")
    @Expose
    private Offer offer;

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }
}
