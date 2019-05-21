package bodoamat.samkuriang;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import bodoamat.samkuriang.Fragment.HistoryFragment;
import bodoamat.samkuriang.Fragment.HomeFragment;
import bodoamat.samkuriang.Fragment.MapsFragment;
import bodoamat.samkuriang.Fragment.ProfileFragment;
import bodoamat.samkuriang.helper.SharedPrefManager;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.home:
                    HomeFragment homeFragment = new HomeFragment();
                    FragmentTransaction HomeFragmentTransaction = getSupportFragmentManager().beginTransaction();
                    HomeFragmentTransaction.replace(R.id.fragment_container, homeFragment);
                    HomeFragmentTransaction.commit();

                    return true;

                case R.id.maps:
                    MapsFragment mapsFragment = new MapsFragment();
                    FragmentTransaction MapsFragmentTransaction = getSupportFragmentManager().beginTransaction();
                    MapsFragmentTransaction.replace(R.id.fragment_container, mapsFragment);
                    MapsFragmentTransaction.commit();

                    return true;

//                case R.id.action_image:
//                    CameraFragment cameraFragment = new CameraFragment();
//                    FragmentTransaction CameraFragmentTransaction = getSupportFragmentManager().beginTransaction();
//                    CameraFragmentTransaction.replace(R.id.content, cameraFragment);
//                    CameraFragmentTransaction.commit();
//
//                    return true;

                case R.id.history:
                    HistoryFragment historyFragment = new HistoryFragment();
                    FragmentTransaction HistoryFragmentTransaction = getSupportFragmentManager().beginTransaction();
                    HistoryFragmentTransaction.replace(R.id.fragment_container, historyFragment);
                    HistoryFragmentTransaction.commit();

                    return true;

                case R.id.profile:
                    ProfileFragment profileFragment = new ProfileFragment();
                    FragmentTransaction ProfileFragmentTransaction = getSupportFragmentManager().beginTransaction();
                    ProfileFragmentTransaction.replace(R.id.fragment_container, profileFragment);
                    ProfileFragmentTransaction.commit();

                    return true;

            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (!SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
//        BottomNavigationViewHelper.removeShiftMode(bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        HomeFragment homeFragment = new HomeFragment();
        FragmentTransaction HomeFragmentTransaction = getSupportFragmentManager().beginTransaction();
        HomeFragmentTransaction.replace(R.id.fragment_container, homeFragment);
        HomeFragmentTransaction.commit();

    }
}

