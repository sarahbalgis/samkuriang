package bodoamat.samkuriang.models;

import android.media.Image;

public class Garbage {
    private int id;
    private String name;
    private String age;
    private String type;
    private String background_images;
    private String description;
    private String description_type;

    public Garbage(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAge() {
        return age;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public String getDescription_type() {
        return description_type;
    }

    public String getBackground_images() {
        return background_images;
    }
}
