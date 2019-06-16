package bodoamat.samkuriang.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class EditProfileFragment extends Fragment implements View.OnClickListener {


    EditText editNama, editEmail, editAlamat, editNomor;
    Button btnEditSave;


    public EditProfileFragment() {

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_edit_profile, container, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity();

        btnEditSave = view.findViewById(R.id.btnSave);

        editNama = view.findViewById(R.id.name1);
        editEmail = view.findViewById(R.id.email1);
        editAlamat = view.findViewById(R.id.address1);
        editNomor = view.findViewById(R.id.phone1);


        btnEditSave.setOnClickListener(this);

        Customer customer = SharedPrefManager.getInstance(getActivity()).getCustomer();

        editNama.setText(customer.getName());
        editEmail.setText(customer.getEmail());
        editAlamat.setText(customer.getAddress());
        editNomor.setText(customer.getPhone_number());

    }

    private void updateProfile() {
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
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

        Customer customer = new Customer(SharedPrefManager.getInstance(getActivity()).getCustomer().getId(), name, email, address, phone_number);

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
                Toast.makeText(getActivity(), "berhasil", Toast.LENGTH_LONG).show();
                if (!response.body().getError()) {
                    SharedPrefManager.getInstance(getActivity()).loginCustomer(response.body().getCustomer());
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                progressDialog.dismiss();
//                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                Toast.makeText(getActivity(), "gagal", Toast.LENGTH_LONG).show();
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