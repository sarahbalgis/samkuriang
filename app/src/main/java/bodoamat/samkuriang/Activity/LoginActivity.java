package bodoamat.samkuriang.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import bodoamat.samkuriang.R;
import bodoamat.samkuriang.helper.SharedPrefManager;
import bodoamat.samkuriang.models.Result;
import bodoamat.samkuriang.utils.Service;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static bodoamat.samkuriang.utils.ConfigUtils.BASE_URL;


public class LoginActivity extends AppCompatActivity  implements View.OnClickListener{

    private EditText editTextEmail, editTextPassword;
    private boolean doubleBackToExitPressedOnce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        Intent intent = new Intent();
        editTextEmail.setText(getIntent().getStringExtra("message"));


        // sign in (biasa)
       findViewById(R.id.btnSignIn).setOnClickListener(this);

        //if user is already logged in openeing the profile activity
        if (SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }
    }

    private void userSignIn() {


        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();


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

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //Defining retrofit api service
        Service service = retrofit.create(Service.class);

        //defining the call
        Call<Result> call = service.loginCustomer(email, password);

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Signing In...");
        progressDialog.show();

        //calling the api
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                progressDialog.dismiss();
                    if (!response.body().getError()) {
                        finish();
                        SharedPrefManager.getInstance(getApplicationContext()).loginCustomer(response.body().getCustomer());
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        Toast.makeText(getApplicationContext(), "Login Successfully!", Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(getApplicationContext(), "Invalid Email or Password", Toast.LENGTH_LONG).show();
                    }
                }


            @Override
            public void onFailure(Call<Result> call,Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSignIn:
                userSignIn();
                break;
            case R.id.textSignUp:
                Intent intent = new Intent();
                intent.setClass(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
        }
    }



    //double click to exit
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            finishAffinity();
        }

        this.doubleBackToExitPressedOnce = true;
//        Toast.makeText(this, "Press again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 3000);
    }
    //end exit
}
