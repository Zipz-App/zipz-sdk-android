package android.android.zlibrary.model;

import java.util.List;

public class VenueListModel extends VenueModel {

    private long cityID;
    private long shoppingID;
    private long establishmentID;
    private String cityName;
    private String neighborhood;
    private String placeCategoryName;
    private String state;
    private String placeNameSecond;
    private String shoppingName;


    private List<String> listOfAvailableSearches;

    public VenueListModel(long place_id, String place_category_name, String place_name, String place_logo, String address, double longitude, double latitude,
                          boolean favorite, boolean opened, double distance, String formatedDistance, long cityID, long shoppingID, long establishmentID, String cityName,
                          String neighborhood, String placeCategoryName, String state, String placeNameSecond, String shoppingName, String order) {
        super(place_id, place_category_name, place_name, place_logo, address, longitude, latitude, favorite, opened, distance, formatedDistance, cityName,
                neighborhood, placeCategoryName, state, placeNameSecond, shoppingName, order);
        this.cityID = cityID;
        this.shoppingID = shoppingID;
        this.establishmentID = establishmentID;
        this.cityName = cityName;
        this.neighborhood = neighborhood;
        this.placeCategoryName = placeCategoryName;
        this.state = state;
        this.placeNameSecond = placeNameSecond;
        this.shoppingName = shoppingName;

    }

    public VenueListModel(long place_id, String place_category_name, String place_name, String place_logo, String address, double longitude, double latitude,
                          boolean favorite, boolean opened, double distance, String formatedDistance, long cityID, long shoppingID, long establishmentID, String cityName,
                          String neighborhood, String placeCategoryName, String state, String placeNameSecond, String shoppingName, String order, List<String> listOfAvailableSearches) {
        super(place_id, place_category_name, place_name, place_logo, address, longitude, latitude, favorite, opened, distance, formatedDistance, cityName,
                neighborhood, placeCategoryName, state, placeNameSecond, shoppingName, order);
        this.cityID = cityID;
        this.shoppingID = shoppingID;
        this.establishmentID = establishmentID;
        this.cityName = cityName;
        this.neighborhood = neighborhood;
        this.placeCategoryName = placeCategoryName;
        this.state = state;
        this.placeNameSecond = placeNameSecond;
        this.shoppingName = shoppingName;
        this.listOfAvailableSearches = listOfAvailableSearches;

    }

    public long getCityID() {
        return cityID;
    }

    public long getShoppingID() {
        return shoppingID;
    }

    public long getEstablishmentID() {
        return establishmentID;
    }

    public void setCityID(long cityID) {
        this.cityID = cityID;
    }

    public void setShoppingID(long shoppingID) {
        this.shoppingID = shoppingID;
    }

    public void setEstablishmentID(long establishmentID) {
        this.establishmentID = establishmentID;
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

    public String getPlaceNameSecond() {
        return placeNameSecond;
    }

    public void setPlaceNameSecond(String placeNameSecond) {
        this.placeNameSecond = placeNameSecond;
    }

    @Override
    public String getShoppingName() {
        return shoppingName;
    }

    @Override
    public void setShoppingName(String shoppingName) {
        this.shoppingName = shoppingName;
    }

    public List<String> getListOfAvailableSearches() {
        return listOfAvailableSearches;
    }

    public void setListOfAvailableSearches(List<String> listOfAvailableSearches) {
        this.listOfAvailableSearches = listOfAvailableSearches;
    }
}
