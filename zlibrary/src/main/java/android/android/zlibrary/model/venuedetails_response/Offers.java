package android.android.zlibrary.model.venuedetails_response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Offers {
    @SerializedName("public_offers")
    @Expose
    private List<PublicOffer> publicOffers = null;
    @SerializedName("private_offers")
    @Expose
    private List<PrivateOffer> privateOffers = null;

    public List<PublicOffer> getPublicOffers() {
        return publicOffers;
    }

    public void setPublicOffers(List<PublicOffer> publicOffers) {
        this.publicOffers = publicOffers;
    }

    public List<PrivateOffer> getPrivateOffers() {
        return privateOffers;
    }

    public void setPrivateOffers(List<PrivateOffer> privateOffers) {
        this.privateOffers = privateOffers;
    }

}
