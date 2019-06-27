package bodoamat.samkuriang.api;

import bodoamat.samkuriang.models.BankSampahs;
import bodoamat.samkuriang.models.DaftarNasabah;
import bodoamat.samkuriang.models.Result;

import bodoamat.samkuriang.models.Saving;
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

    // tabungan
    @GET("customers/tabungan/{id}")
    Call<Saving> getTabungan(
        @Path("id") int id
    );

    // data bank sampah
    @GET("customers/data-garbage-officer")
    Call<BankSampahs> getBankSampah();

    // daftar jadi nasabah
    @POST("customers/join-nasabah/{id}/{garbage_officer_id}")
    Call<DaftarNasabah> daftarNasabah(
        @Path("id") int id,
        @Path("garbage_officer_id") int garbage_officer_id

    );

    // update profile
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
