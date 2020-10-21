package android.android.zlibrary.model.reserve_offer_response;

import android.android.zlibrary.model.offerdetails_response.Offer;
import android.android.zlibrary.model.venueclusterdetails_response.Venue;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReserveOffer implements Parcelable {

    @SerializedName("uuid")
    @Expose
    private String uuid;

    @SerializedName("venue")
    @Expose
    private Venue venue;

    @SerializedName("offer")
    @Expose
    private Offer offer;

    @SerializedName("staff")
    @Expose
    private String staff;

    @SerializedName("qr_card")
    @Expose
    private String qrCard;

    @SerializedName("expiration_time")
    @Expose
    private String expirationTime;

    @SerializedName("redeemed_at")
    @Expose
    private String redeemedAt;

    @SerializedName("status")
    @Expose
    private String status;

    protected ReserveOffer(Parcel in) {
        uuid = in.readString();
        staff = in.readString();
        qrCard = in.readString();
        expirationTime = in.readString();
        redeemedAt = in.readString();
        status = in.readString();
        venue = in.readParcelable(Venue.class.getClassLoader());
        offer = in.readParcelable(Offer.class.getClassLoader());

    }

    public static final Creator<ReserveOffer> CREATOR = new Creator<ReserveOffer>() {
        @Override
        public ReserveOffer createFromParcel(Parcel in) {
            return new ReserveOffer(in);
        }

        @Override
        public ReserveOffer[] newArray(int size) {
            return new ReserveOffer[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(uuid);
        dest.writeString(staff);
        dest.writeString(qrCard);
        dest.writeString(expirationTime);
        dest.writeString(redeemedAt);
        dest.writeString(status);
        dest.writeParcelable((Parcelable) venue, flags);
        dest.writeParcelable((Parcelable) offer, flags);

    }

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public String getStaff() {
        return staff;
    }

    public void setStaff(String staff) {
        this.staff = staff;
    }

    public String getQrCard() {
        return qrCard;
    }

    public void setQrCard(String qrCard) {
        this.qrCard = qrCard;
    }

    public String getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(String expirationTime) {
        this.expirationTime = expirationTime;
    }

    public String getRedeemedAt() {
        return redeemedAt;
    }

    public void setRedeemedAt(String redeemed_at) {
        this.redeemedAt = redeemed_at;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
