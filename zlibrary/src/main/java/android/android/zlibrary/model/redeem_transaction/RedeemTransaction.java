package android.android.zlibrary.model.redeem_transaction;

import android.android.zlibrary.model.offerdetails_response.Offer;
import android.android.zlibrary.model.transactions_response.QrCard;
import android.android.zlibrary.model.transactions_response.Staff;
import android.android.zlibrary.model.venueclusterdetails_response.Venue;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RedeemTransaction {
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
    private Staff staff;

    @SerializedName("qr_code")
    @Expose
    private QrCard qrCard;

    @SerializedName("expiration_time")
    @Expose
    private String expirationTime;

    @SerializedName("redeemed_at")
    @Expose
    private String redeemedAt;

    @SerializedName("status")
    @Expose
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public QrCard getQrCard() {
        return qrCard;
    }

    public void setQrCard(QrCard qrCard) {
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

    public void setRedeemedAt(String redeemedAt) {
        this.redeemedAt = redeemedAt;
    }
}
