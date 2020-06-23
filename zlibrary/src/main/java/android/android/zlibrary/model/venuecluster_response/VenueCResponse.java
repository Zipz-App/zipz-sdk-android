package android.android.zlibrary.model.venuecluster_response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VenueCResponse implements Parcelable {
    @SerializedName("venue_clusters")
    @Expose
    private List<VenueCluster> venueClusters = null;

    protected VenueCResponse(Parcel in) {
        venueClusters = in.createTypedArrayList(VenueCluster.CREATOR);
    }

    public static final Creator<VenueCResponse> CREATOR = new Creator<VenueCResponse>() {
        @Override
        public VenueCResponse createFromParcel(Parcel in) {
            return new VenueCResponse(in);
        }

        @Override
        public VenueCResponse[] newArray(int size) {
            return new VenueCResponse[size];
        }
    };

    public List<VenueCluster> getVenueClusters() {
        return venueClusters;
    }

    public void setVenueClusters(List<VenueCluster> venueClusters) {
        this.venueClusters = venueClusters;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(venueClusters);
    }
}
