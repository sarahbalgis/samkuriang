package bodoamat.samkuriang.Fragment;

import android.content.Intent;
import android.os.Bundle;
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

    Button btnOut;
    TextView profileNama, profileAddress;

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



        profileNama = view.findViewById(R.id.namaProfile);
        profileAddress = view.findViewById(R.id.addressProfile);
        btnOut = view.findViewById(R.id.btnOut);

        //get Customers
        Customer customer = SharedPrefManager.getInstance(getActivity()).getCustomer();

        profileNama.setText(customer.getName());
        profileAddress.setText(customer.getAddress());


        btnOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPrefManager.getInstance(getActivity()).logout();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                getActivity().startActivity(intent);
            }
        });

    }

}
