package android.android.zlibrary.model.venueclusterdetails_response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Hours {
    @SerializedName("mon")
    @Expose
    private Object mon;
    @SerializedName("tue")
    @Expose
    private Object tue;
    @SerializedName("wed")
    @Expose
    private Object wed;
    @SerializedName("thu")
    @Expose
    private Object thu;
    @SerializedName("fri")
    @Expose
    private Object fri;
    @SerializedName("sat")
    @Expose
    private Object sat;
    @SerializedName("sun")
    @Expose
    private Object sun;

    public Object getMon() {
        return mon;
    }

    public void setMon(Object mon) {
        this.mon = mon;
    }

    public Object getTue() {
        return tue;
    }

    public void setTue(Object tue) {
        this.tue = tue;
    }

    public Object getWed() {
        return wed;
    }

    public void setWed(Object wed) {
        this.wed = wed;
    }

    public Object getThu() {
        return thu;
    }

    public void setThu(Object thu) {
        this.thu = thu;
    }

    public Object getFri() {
        return fri;
    }

    public void setFri(Object fri) {
        this.fri = fri;
    }

    public Object getSat() {
        return sat;
    }

    public void setSat(Object sat) {
        this.sat = sat;
    }

    public Object getSun() {
        return sun;
    }

    public void setSun(Object sun) {
        this.sun = sun;
    }

}
