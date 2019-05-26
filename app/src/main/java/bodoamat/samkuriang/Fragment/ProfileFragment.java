package bodoamat.samkuriang.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import bodoamat.samkuriang.Activity.LoginActivity;
import bodoamat.samkuriang.R;
import bodoamat.samkuriang.helper.SharedPrefManager;
import bodoamat.samkuriang.models.Customer;


public class ProfileFragment extends Fragment {

    Button btnSignOut;
    TextView profileNama, profileEmail;

    public ProfileFragment() {
        // Required empty public constructor
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

        btnSignOut = view.findViewById(R.id.btnSignOut);
        profileNama = view.findViewById(R.id.namaProfile);
        profileEmail = view.findViewById(R.id.emailProfile);

        //get Customers
        Customer customer = SharedPrefManager.getInstance(getActivity()).getCustomer();

        profileNama.setText(customer.getName());
        profileEmail.setText(customer.getEmail());


        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPrefManager.getInstance(getActivity()).logout();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                getActivity().startActivity(intent);
            }
        });

    }

}
