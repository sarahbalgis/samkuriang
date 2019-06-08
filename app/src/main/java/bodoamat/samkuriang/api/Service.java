package bodoamat.samkuriang.api;

import bodoamat.samkuriang.models.Result;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface Service {
    //The register call
    @FormUrlEncoded
    @POST("customers/register")
    Call<Result> createCustomer(
            @Field("name") String name,
            @Field("email") String email,
            @Field("password") String password,
            @Field("address") String address,
            @Field("phone_number") String phone_number
    );

    // login
    @FormUrlEncoded
    @POST("customers/login")
    Call<Result> loginCustomer (
        @Field("email") String email,
        @Field("password") String password
    );

    @FormUrlEncoded
    @PUT("customers/update-profile/{id}")
    Call<Result> updateProfile(
            @Path("id") int id,
            @Field("name") String name,
            @Field("email") String email,
            @Field("address") String address,
            @Field("phone_number") String phone_number

    );

}
