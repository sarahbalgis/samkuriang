package bodoamat.samkuriang.models;

public class StatusNasabah {
    private int id;
    private String status ;
    private String place_name;

    public StatusNasabah(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public String getPlace_name() {
        return place_name;
    }
}
