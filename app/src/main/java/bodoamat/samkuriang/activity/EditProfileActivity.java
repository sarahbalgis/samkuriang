package bodoamat.samkuriang.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import bodoamat.samkuriang.R;
import bodoamat.samkuriang.fragment.ProfileFragment;
import bodoamat.samkuriang.storage.SharedPrefManager;
import bodoamat.samkuriang.models.Customer;
import bodoamat.samkuriang.models.Result;
import bodoamat.samkuriang.api.ConfigUtils;
import bodoamat.samkuriang.api.Service;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EditProfileActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editNama, editEmail, editAlamat, editNomor;
    Button btnEditSave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        btnEditSave = findViewById(R.id.btnSave);

        editNama = findViewById(R.id.name1);
        editEmail = findViewById(R.id.email1);
        editAlamat = findViewById(R.id.address1);
        editNomor = findViewById(R.id.phone1);


        btnEditSave.setOnClickListener(this);

        Customer customer = SharedPrefManager.getInstance(this).getCustomer();

        editNama.setText(customer.getName());
        editEmail.setText(customer.getEmail());
        editAlamat.setText(customer.getAddress());
        editNomor.setText(customer.getPhone_number());

    }


    private void updateProfile() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Updating...");
        progressDialog.show();

        String name = editNama.getText().toString().trim();
        String email = editEmail.getText().toString().trim();
        String address = editAlamat.getText().toString().trim();
        String phone_number = editNomor.getText().toString().trim();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConfigUtils.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Service service = retrofit.create(Service.class);

        Customer customer = new Customer(SharedPrefManager.getInstance(this).getCustomer().getId(), name, email, address, phone_number);

        Call<Result> call = service.updateProfile(
                customer.getId(),
                customer.getName(),
                customer.getEmail(),
                customer.getAddress(),
                customer.getPhone_number()
        );

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                progressDialog.dismiss();
//                Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(), "berhasil", Toast.LENGTH_LONG).show();
                if (!response.body().getError()) {
                    SharedPrefManager.getInstance(getApplicationContext()).loginCustomer(response.body().getCustomer());
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                progressDialog.dismiss();
//                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(), "gagal", Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    public void onClick(View v) {
        if (v == btnEditSave) {
            updateProfile();
            finish();
        }
    }
}
