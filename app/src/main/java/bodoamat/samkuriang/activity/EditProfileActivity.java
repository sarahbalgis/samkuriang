package bodoamat.samkuriang.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import bodoamat.samkuriang.R;
import bodoamat.samkuriang.api.ConfigUtils;
import bodoamat.samkuriang.api.Service;
import bodoamat.samkuriang.models.Customer;
import bodoamat.samkuriang.models.Result;
import bodoamat.samkuriang.storage.SharedPrefManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EditProfileActivity extends AppCompatActivity implements View.OnClickListener {

    Toolbar toolbar;
    EditText editNama, editEmail, editAlamat, editNomor;
    TextView textProfileImage;
    Button btnEditSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        toolbar = findViewById(R.id.setToolbar);
        toolbar.setTitle("Ubah Profil ");
        setSupportActionBar(toolbar);

        btnEditSave = findViewById(R.id.btnSave);

        editNama = findViewById(R.id.name1);
        editEmail = findViewById(R.id.email1);
        editAlamat = findViewById(R.id.address1);
        editNomor = findViewById(R.id.phone1);
        textProfileImage = findViewById(R.id.textProfile);


        btnEditSave.setOnClickListener(this);

        Customer customer = SharedPrefManager.getInstance(this).getCustomer();

        editNama.setText(customer.getName());
        editEmail.setText(customer.getEmail());
        editAlamat.setText(customer.getAddress());
        editNomor.setText(customer.getPhone_number());
        String namaPanjang = customer.getName();
        namaPanjang = namaPanjang.substring(0,1);
        textProfileImage.setText(namaPanjang);

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
                    finish();
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
        }
    }
}