package android.android.zlibrary.model;

public class VenueModel {
    private long place_id;
    private String place_name;
    private String place_category_name;
    private String place_logo;
    private String address;
    private double longitude;
    private double latitude;
    private boolean favorite;
    private boolean opened;
    private double distance;
    private String formatedDistance;
    private String cityName;
    private String neighborhood;
    private String placeCategoryName;
    private String state;
    private String place_name_2;
    private String shoppingName;
    private String order;

    public VenueModel(long place_id, String place_category_name, String place_name, String place_logo, String address, double longitude,
                      double latitude, boolean favorite, boolean opened, double distance, String formatedDistance, String cityName,
                      String neighborhood, String placeCategoryName, String state, String placeName2, String shoppingName, String order) {
        this.place_id = place_id;
        this.place_category_name = place_category_name.trim();
        this.place_name = place_name.trim();
        this.place_logo = place_logo;
        this.address = address.trim();
        this.longitude = longitude;
        this.latitude = latitude;
        this.favorite = favorite;
        this.opened = opened;
        this.distance = distance;
        this.formatedDistance = formatedDistance;
        this.cityName = cityName;
        this.neighborhood = neighborhood;
        this.placeCategoryName = placeCategoryName;
        this.state = state;
        this.place_name_2 = placeName2.trim();
        this.shoppingName = shoppingName;
        this.order = order;
    }

    public String getPlace_name_2() {
        return place_name_2;
    }

    public void setPlace_name_2(String place_name_2) {
        this.place_name_2 = place_name_2;
    }

    public void setPlace_id(long place_id) {
        this.place_id = place_id;
    }

    public void setPlace_name(String place_name) {
        this.place_name = place_name;
    }

    public void setPlace_category_name(String place_category_name) {
        this.place_category_name = place_category_name;
    }

    public void setPlace_logo(String place_logo) {
        this.place_logo = place_logo;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setOpened(boolean opened) {
        this.opened = opened;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getPlaceCategoryName() {
        return placeCategoryName;
    }

    public void setPlaceCategoryName(String placeCategoryName) {
        this.placeCategoryName = placeCategoryName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public long getPlace_id() {
        return place_id;
    }

    public String getPlace_category_name() {
        return place_category_name;
    }

    public String getPlace_name() {
        return place_name;
    }

    public String getPlace_logo() {
        return place_logo;
    }

    public String getAddress() {
        return address;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public boolean isOpened() {
        return opened;
    }

    public String getFormatedDistance() {
        return formatedDistance;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public void setFormatedDistance(String formatedDistance) {
        this.formatedDistance = formatedDistance;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getShoppingName() {
        return shoppingName;
    }

    public void setShoppingName(String shoppingName) {
        this.shoppingName = shoppingName;
    }

}
