package bodoamat.samkuriang.models;

public class Saving {
    private int id;
    private Float tabungan;
    private String berat;

    public Saving(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public Float getTabungan(){
        return tabungan;
    }

    public String getBerat(){
        return berat;
    }
}
