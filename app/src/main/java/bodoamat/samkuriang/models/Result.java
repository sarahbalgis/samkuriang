package bodoamat.samkuriang.models;

import com.google.gson.annotations.SerializedName;

public class Result {
    @SerializedName("error")
    private Boolean error;

    @SerializedName("message")
    private String message;

    @SerializedName("customer")
    private Customer customer;

    public Result(Boolean error, String message, Customer customer) {
        this.error = error;
        this.message = message;
        this.customer = customer;
    }

    public Boolean getError() {
        return error;
    }

    public String getMessage() {

        return message;
    }

    public Customer getCustomer() {

        return customer;
    }

}
