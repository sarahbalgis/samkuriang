package bodoamat.samkuriang.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import javax.security.auth.login.LoginException;

import bodoamat.samkuriang.Activity.LoginActivity;
import bodoamat.samkuriang.R;
import bodoamat.samkuriang.helper.SharedPrefManager;

public class ProfileFragment extends Fragment {

    Button btnSignOut;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        btnSignOut = rootView.findViewById(R.id.btnSignOut);

        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPrefManager.getInstance(getActivity()).logout();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                getActivity().startActivity(intent);
            }
        });

        return rootView;

    }

}
