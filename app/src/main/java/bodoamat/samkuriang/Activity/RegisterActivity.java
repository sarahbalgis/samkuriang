package bodoamat.samkuriang.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import bodoamat.samkuriang.R;
import bodoamat.samkuriang.models.Customer;
import bodoamat.samkuriang.models.Result;
import bodoamat.samkuriang.utils.Service;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static bodoamat.samkuriang.utils.ConfigUtils.BASE_URL;


public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextNama, editTextAlamat, editTextNo, editTextEmail, editTextPassword, editTextCPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        //edit text
        editTextNama = findViewById(R.id.editTextNama);
        editTextAlamat = findViewById(R.id.editTextAlamat);
        editTextNo = findViewById(R.id.editTextNoHp);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextCPassword = findViewById(R.id.editTextConfirmPassword);

        //button sign up
        findViewById(R.id.btnSignUp).setOnClickListener(this);
    }


    private void userSignUp() {

        String name = editTextNama.getText().toString().trim();
        String address = editTextAlamat.getText().toString().trim();
        String phone_number = editTextNo.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String confirm_password = editTextCPassword.getText().toString().trim();


        if (name.isEmpty()) {
            editTextNama.setError("Name is required");
            editTextNama.requestFocus();
            return;
        }

        if (address.isEmpty()) {
            editTextAlamat.setError("Address is required");
            editTextAlamat.requestFocus();
            return;
        }

        if (phone_number.isEmpty()) {
            editTextNo.setError("Phone number is required");
            editTextNo.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Enter a valid email");
            editTextEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            editTextPassword.setError("Password required");
            editTextPassword.requestFocus();
            return;
        }

        if (password.length() < 6) {
            editTextPassword.setError("Password should be atleast 6 character");
            editTextPassword.requestFocus();
            return;
        }

        if (confirm_password.isEmpty()) {
            editTextCPassword.setError("Confirm Password required");
            editTextCPassword.requestFocus();
            return;
        }

        if (!confirm_password.equals(password)) {
            editTextCPassword.setError("Password do not match");
            editTextCPassword.requestFocus();
            return;
        }


        //defining a progress dialog to show while signing up

        /*Do register using the api call*/

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Signing Up...");
        progressDialog.show();

        // building retrofit object
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //Defining retrofit api service
        Service service = retrofit.create(Service.class);

        //Defining the user object as we need to pass it with the call
        Customer customer = new Customer(name, email, password, address, phone_number);

        //defining the call
        Call<Result> call = service.createCustomer(
                customer.getName(),
                customer.getEmail(),
                customer.getPassword(),
                customer.getAddress(),
                customer.getPhone_number()
        );

        //calling the api
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                //hiding progress dialog
                progressDialog.dismiss();

//                String s = null;

                //displaying the message from the response as toast


                //if there is no error
                if (!response.body().getError()) {
                    Toast.makeText(getApplicationContext(), "Register Succesfully!", Toast.LENGTH_LONG).show();
                    finish();
                    String message= editTextEmail.getText().toString();
                   // SharedPrefManager.getInstance(getApplicationContext()).loginCustomer(response.body().getCustomer());
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    intent.putExtra("message", message);
                    startActivity(intent);
                }

            }


            @Override
            public void onFailure( Call<Result> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSignUp:
                userSignUp();
                break;
            case R.id.textSignIn:
                Intent intent = new Intent();
                intent.setClass(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                break;
        }
    }


    //event back disable
//    @Override
//    public void onBackPressed(){
//    }
    //end - event back disable


}
