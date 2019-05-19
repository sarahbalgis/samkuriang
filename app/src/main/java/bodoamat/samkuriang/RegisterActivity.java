package bodoamat.samkuriang;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class RegisterActivity extends AppCompatActivity {

    TextView txtSignIn;
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

        //sign in
        txtSignIn = findViewById(R.id.textSignIn);

        txtSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        //end - sign in
    }


    private void userSignUp() {
        String nama = editTextNama.getText().toString().trim();
        String alamat = editTextAlamat.getText().toString().trim();
        String no_hp = editTextNo.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String confirm_password = editTextCPassword.getText().toString().trim();


        if (nama.isEmpty()){
            editTextNama.setError("Name is required");
            editTextNama.requestFocus();
            return;
        }

        if (alamat.isEmpty()){
            editTextAlamat.setError("Address is required");
            editTextAlamat.requestFocus();
            return;
        }

        if (no_hp.isEmpty()){
            editTextNo.setError("Phone number is required");
            editTextNo.requestFocus();
            return;
        }

        if (email.isEmpty()){
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Enter a valid email");
            editTextEmail.requestFocus();
            return;
        }

        if (password.isEmpty()){
            editTextPassword.setError("Password required");
            editTextPassword.requestFocus();
            return;
        }

        if(password.length() < 6){
            editTextPassword.setError("Password should be atleast 6 character");
            editTextPassword.requestFocus();
            return;
        }

        if (confirm_password.isEmpty()){
            editTextCPassword.setError("Confirm Password required");
            editTextCPassword.requestFocus();
            return;
        }

        if(!confirm_password.equals(password)) {
            editTextCPassword.setError("Password do not match");
            editTextCPassword.requestFocus();
            return;
        }

    }


    //event back disable
    @Override
    public void onBackPressed(){
    }
    //end - event back disable


}
