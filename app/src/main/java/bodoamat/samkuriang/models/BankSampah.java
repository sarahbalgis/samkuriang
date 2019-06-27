package bodoamat.samkuriang.models;

public class BankSampah {
    private String place_name;
    private String address;
    private String lat;
    private String lng;
    private String phone_number;

    public String getPlace_name() {
        return place_name;
    }

    public String getAddress() {
        return address;
    }

    public String getLat() {
        return lat;
    }

    public String getLng() {
        return lng;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPlace_name(String place_name) {
        this.place_name = place_name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
}
