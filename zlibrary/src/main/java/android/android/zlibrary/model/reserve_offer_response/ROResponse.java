package android.android.zlibrary.model.reserve_offer_response;

import android.android.zlibrary.model.offerdetails_response.Offer;
import android.android.zlibrary.model.venuecluster_response.VenueCluster;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ROResponse
{
    @SerializedName("reserve_offer")
    @Expose
    private ReserveOffer reserveOffer;

//    @SerializedName("offer")
//    @Expose
//    private Offer offer;


    public ReserveOffer getReserveOffer() {
        return reserveOffer;
    }

    public void setReserveOffer(ReserveOffer reserveOffer) {
        this.reserveOffer = reserveOffer;
    }


}
