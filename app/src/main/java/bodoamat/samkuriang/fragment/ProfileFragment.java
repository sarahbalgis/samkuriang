package bodoamat.samkuriang.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import bodoamat.samkuriang.activity.LoginActivity;
import bodoamat.samkuriang.activity.EditProfileActivity;
import bodoamat.samkuriang.R;
import bodoamat.samkuriang.storage.SharedPrefManager;
import bodoamat.samkuriang.models.Customer;


public class ProfileFragment extends Fragment {

    Button btnOut, btnEdit;
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
        return inflater.inflate(R.layout.fragment_profile, container, false);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity();


        profileNama = view.findViewById(R.id.namaProfile);
        profileAddress = view.findViewById(R.id.addressProfile);
        btnOut = view.findViewById(R.id.btnOut);
        btnEdit = view.findViewById(R.id.editProfile);


        Customer customer =  SharedPrefManager.getInstance(getActivity()).getCustomer();

        profileNama.setText(customer.getName());
        profileAddress.setText(customer.getAddress());



        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditProfileFragment editProfileFragment = new EditProfileFragment();
                FragmentTransaction EditProfileFragmentTransaction = getFragmentManager().beginTransaction();
                EditProfileFragmentTransaction.replace(R.id.profileFragment, editProfileFragment);
                EditProfileFragmentTransaction.commit();
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
