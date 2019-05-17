package bodoamat.samkuriang;

public class ModelBerita {
    private int imgBerita;
    private String judulBerita;
    private String deskripsiBerita;

    public ModelBerita(int imgBerita, String judulBerita, String deskripsiBerita) {
        this.imgBerita = imgBerita;
        this.judulBerita = judulBerita;
        this.deskripsiBerita = deskripsiBerita;
    }

    public int getImgBerita() {
        return imgBerita;
    }

    public void setImgBerita(int imgBerita) {
        this.imgBerita = imgBerita;
    }

    public String getJudulBerita() {
        return judulBerita;
    }

    public void setJudulBerita(String judulBerita) {
        this.judulBerita = judulBerita;
    }

    public String getDeskripsiBerita() {
        return deskripsiBerita;
    }

    public void setDeskripsiBerita(String deskripsiBerita) {
        this.deskripsiBerita = deskripsiBerita;
    }
}
