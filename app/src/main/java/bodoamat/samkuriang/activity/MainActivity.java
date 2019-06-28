package bodoamat.samkuriang.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import bodoamat.samkuriang.R;
import bodoamat.samkuriang.fragment.HistoryFragment;
import bodoamat.samkuriang.fragment.HomeFragment;
import bodoamat.samkuriang.fragment.MapsFragment;
import bodoamat.samkuriang.fragment.ProfileFragment;
import bodoamat.samkuriang.storage.SharedPrefManager;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton btnCamera;

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
                    Dexter.withActivity(MainActivity.this)
                            .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                            .withListener(new PermissionListener() {
                                @Override
                                public void onPermissionGranted(PermissionGrantedResponse response) {

                                    MapsFragment mapsFragment = new MapsFragment();
                                    FragmentTransaction MapsFragmentTransaction = getSupportFragmentManager().beginTransaction();
                                    MapsFragmentTransaction.replace(R.id.fragment_container, mapsFragment);
                                    MapsFragmentTransaction.commit();


                                }


                                @Override
                                public void onPermissionDenied(PermissionDeniedResponse response) {
                                    if (response.isPermanentlyDenied()) {
                                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                                        builder.setTitle("Permission Denied")
                                                .setMessage("Permission to access device location is permanently denied. You need to go to Setting to allow the permission.")
                                                .setNegativeButton("Cancel", null)
                                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        Intent intent = new Intent();
                                                        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                                        intent.setData(Uri.fromParts("package", getPackageName(), null));

                                                    }
                                                }).show();
                                    } else {
                                        Toast.makeText(MainActivity.this, "Permission Denied", Toast.LENGTH_SHORT).show();
                                    }

                                }

                                @Override
                                public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                                    token.continuePermissionRequest();
                                }
                            }).check();
                    return true;


//                case R.id.camera:
//                    Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
//                    startActivity(intent);
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

        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            HomeFragment homeFragment = new HomeFragment();
            FragmentTransaction HomeFragmentTransaction = getSupportFragmentManager().beginTransaction();
            HomeFragmentTransaction.replace(R.id.fragment_container, homeFragment);
            HomeFragmentTransaction.commit();
        }

        btnCamera = findViewById(R.id.floating_action_button);

        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DetectorActivity.class);
                startActivity(intent);
            }

        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
//        BottomNavigationViewHelper.removeShiftMode(bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


    }

    @Override
    protected void onStart() {
        super.onStart();

        if (!SharedPrefManager.getInstance(getApplicationContext()).isLoggedIn()) {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

}

