package bodoamat.samkuriang.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class PasswordActivity extends AppCompatActivity implements View.OnClickListener {

    EditText oldPassword, newPassword, confirmPassword;
    Button btnSavePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        Toolbar toolbar = findViewById(R.id.setToolbar);
        toolbar.setTitle("Ubah Password");
        setSupportActionBar(toolbar);

        btnSavePassword = findViewById(R.id.btn_save_password);


        oldPassword = findViewById(R.id.old_password);
        newPassword = findViewById(R.id.new_password);
        confirmPassword = findViewById(R.id.confirm_password);

        btnSavePassword.setOnClickListener(this);

    }

    private void updatePassword() {


        String old_password = oldPassword.getText().toString().trim();
        String password = newPassword.getText().toString().trim();
        String confirm_password = confirmPassword.getText().toString().trim();


        if (password.length() < 6) {
            newPassword.setError("Password should be atleast 6 character");
            newPassword.requestFocus();
            return;
        }

        if (confirm_password.isEmpty()) {
            confirmPassword.setError("Confirm Password required");
            confirmPassword.requestFocus();
            return;
        }

        if (!confirm_password.equals(password)) {
            confirmPassword.setError("Password do not match");
            confirmPassword.requestFocus();
            return;
        }


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConfigUtils.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Service service = retrofit.create(Service.class);

        Customer customer = new Customer(SharedPrefManager.getInstance(this).getCustomer().getId(), old_password, password);

        Call<Result> call = service.updatePassword(
                customer.getId(),
                customer.getOld_password(),
                customer.getPassword()
        );

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Updating...");
        progressDialog.show();

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                progressDialog.dismiss();
//                Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                if (!response.body().getError()) {
                    finish();
                    SharedPrefManager.getInstance(getApplicationContext()).loginCustomer(response.body().getCustomer());
                }

            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                progressDialog.dismiss();
//                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(), "update gagal", Toast.LENGTH_LONG).show();

            }
        });

    }

    @Override
    public void onClick(View v) {
        if (v == btnSavePassword) {
            updatePassword();
        }

    }

}