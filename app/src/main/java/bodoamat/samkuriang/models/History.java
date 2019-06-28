package bodoamat.samkuriang.models;

public class History {
    private int id;
    private String price;
    private String size;
    private String name;
    private String created_at;

    public History(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getPrice() {
        return price;
    }


    public String getSize() {
        return size;
    }


    public String getName() {
        return name;
    }


    public String getCreated_at() {
        return created_at;
    }
}
