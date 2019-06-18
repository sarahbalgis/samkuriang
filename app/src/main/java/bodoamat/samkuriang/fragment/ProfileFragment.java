package bodoamat.samkuriang.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import bodoamat.samkuriang.R;
import bodoamat.samkuriang.activity.EditProfileActivity;
import bodoamat.samkuriang.activity.LoginActivity;
import bodoamat.samkuriang.activity.PasswordActivity;
import bodoamat.samkuriang.models.Customer;
import bodoamat.samkuriang.storage.SharedPrefManager;


public class ProfileFragment extends Fragment {

    Button btnOut, btnEdit, btnPassword;
    TextView profileNama, profileAddress;


    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);


        profileNama = rootView.findViewById(R.id.namaProfile);
        profileAddress = rootView.findViewById(R.id.addressProfile);
        btnOut = rootView.findViewById(R.id.btnOut);
        btnEdit = rootView.findViewById(R.id.editProfile);
        btnPassword = rootView.findViewById(R.id.changePassword);

        return rootView;
    }

    @Override
    public void onResume() {


        Customer customer =  SharedPrefManager.getInstance(getActivity()).getCustomer();

        profileNama.setText(customer.getName());
        profileAddress.setText(customer.getAddress());

        Log.e("DEBUG", "onResume of HomeFragment");
        super.onResume();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity();


        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(getActivity(), EditProfileActivity.class);
               getActivity().startActivity(intent);
            }
        });

        btnPassword.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PasswordActivity.class);
                getActivity().startActivity(intent);
            }
        });


        btnOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPrefManager.getInstance(getActivity()).logout();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                getActivity().startActivity(intent);
            }
        });



    }




}
