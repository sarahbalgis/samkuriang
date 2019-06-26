package bodoamat.samkuriang.models;

public class BankSampah {
    private String namaBankSampah;
    private String alamatBankSampah;

    public BankSampah(String namaBankSampah, String alamatBankSampah) {
        this.namaBankSampah = namaBankSampah;
        this.alamatBankSampah = alamatBankSampah;
    }

    public String getNamaBankSampah() {
        return namaBankSampah;
    }

    public void setNamaBankSampah(String namaBankSampah) {
        this.namaBankSampah = namaBankSampah;
    }

    public String getAlamatBankSampah() {
        return alamatBankSampah;
    }

    public void setAlamatBankSampah(String alamatBankSampah) {
        this.alamatBankSampah = alamatBankSampah;
    }
}
