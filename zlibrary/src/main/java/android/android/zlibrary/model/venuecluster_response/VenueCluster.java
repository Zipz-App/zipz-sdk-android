package android.android.zlibrary.model.venuecluster_response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VenueCluster implements Parcelable {
    @SerializedName("uuid")
    @Expose
    private String uuid;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("street_number")
    @Expose
    private String streetNumber;
    @SerializedName("city")
    @Expose
    private City city;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("neighborhood")
    @Expose
    private String neighborhood;
    @SerializedName("order")
    @Expose
    private Object order;

    protected VenueCluster(Parcel in) {
        uuid = in.readString();
        type = in.readString();
        name = in.readString();
        address = in.readString();
        streetNumber = in.readString();
        city = in.readParcelable(City.class.getClassLoader());
        image = in.readString();
        latitude = in.readString();
        longitude = in.readString();
        neighborhood = in.readString();
    }

    public static final Creator<VenueCluster> CREATOR = new Creator<VenueCluster>() {
        @Override
        public VenueCluster createFromParcel(Parcel in) {
            return new VenueCluster(in);
        }

        @Override
        public VenueCluster[] newArray(int size) {
            return new VenueCluster[size];
        }
    };

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public Object getOrder() {
        return order;
    }

    public void setOrder(Object order) {
        this.order = order;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(uuid);
        dest.writeString(type);
        dest.writeString(name);
        dest.writeString(address);
        dest.writeString(streetNumber);
        dest.writeParcelable(city, flags);
        dest.writeString(image);
        dest.writeString(latitude);
        dest.writeString(longitude);
        dest.writeString(neighborhood);
    }
}
