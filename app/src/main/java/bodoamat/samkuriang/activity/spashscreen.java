package bodoamat.samkuriang.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import bodoamat.samkuriang.R;

public class spashscreen extends AppCompatActivity {

    private ImageView imgLogo;
    private static int duration = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spashscreen);

        findViewById(R.id.logo);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent pindah = new Intent(spashscreen.this, welcome_slider.class);
                startActivity(pindah);
                finish();
            }
        }, duration);

        Animation myanim = AnimationUtils.loadAnimation(this, R.anim.animsplas);
        imgLogo.startAnimation(myanim);
    }
}
