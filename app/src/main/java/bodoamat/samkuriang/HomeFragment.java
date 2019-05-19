package bodoamat.samkuriang;

import android.animation.ArgbEvaluator;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomeFragment extends Fragment implements View.OnClickListener {

    // banner
    ViewPager viewPagerBanner;
    List<Integer> dataImage = new ArrayList<>();
    ImageView indicator1, indicator2, indicator3, indicator4;

    // timer pada banner
    private Timer timer;
    private int current_position = 0;

    // berita
    ViewPager viewPagerBerita;
    BeritaAdapterPager beritaAdapterPager;
    List<ModelBerita> modelBeritas;

    public HomeFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_home, container, false);

        // banner
        viewPagerBanner= rootView.findViewById(R.id.viewPagerBanner);
        dataImage.add(R.drawable.a);
        dataImage.add(R.drawable.b);
        dataImage.add(R.drawable.c);

        indicator1 = rootView.findViewById(R.id.indicator1);
        indicator2 = rootView.findViewById(R.id.indicator2);
        indicator3 = rootView.findViewById(R.id.indicator3);

        BannerAdapterPager bannerAdapterPager = new BannerAdapterPager(this, dataImage);
        viewPagerBanner.setAdapter(bannerAdapterPager);

        // ini buat bkin bulet2nya beda fokus
        viewPagerBanner.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                if (i==0){
                    indicator1.setImageResource(R.drawable.circle_hijau);
                    indicator2.setImageResource(R.drawable.circle_putih);
                    indicator3.setImageResource(R.drawable.circle_putih);
                }
                else if (i==1){
                    indicator1.setImageResource(R.drawable.circle_putih);
                    indicator2.setImageResource(R.drawable.circle_hijau);
                    indicator3.setImageResource(R.drawable.circle_putih);
                }
                else if (i==2){
                    indicator1.setImageResource(R.drawable.circle_putih);
                    indicator2.setImageResource(R.drawable.circle_putih);
                    indicator3.setImageResource(R.drawable.circle_hijau);
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        // berita
        modelBeritas = new ArrayList<>();
        modelBeritas.add(new ModelBerita(R.drawable.a,"Masyarakat Diajak Berpikir Ekonomis tentang Sampah", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin dfaef dsgfssf sdaaafg gdytdhinm  dfgdfgggggfg agsauydgadab sgudygusya sgsa arcu sapien, porta nec orci ac, bibendum temsque l congue por et dignissim arcu, sed tempor dolor."));
        modelBeritas.add(new ModelBerita(R.drawable.b,"Masyarakat Diajak Berpikir Ekonomis tentang Sampah", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin dfaef dsgfssf sdaaafg gdytdhinm  dfgdfgggggfg agsauydgadab sgudygusya sgsa arcu sapien, porta nec orci ac, bibendum temsque l congue por et dignissim arcu, sed tempor dolor."));
        modelBeritas.add(new ModelBerita(R.drawable.c,"Masyarakat Diajak Berpikir Ekonomis tentang Sampah", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin dfaef dsgfssf sdaaafg gdytdhinm  dfgdfgggggfg agsauydgadab sgudygusya sgsa arcu sapien, porta nec orci ac, bibendum temsque l congue por et dignissim arcu, sed tempor dolor."));

        beritaAdapterPager = new BeritaAdapterPager(modelBeritas, this);

        viewPagerBerita = rootView.findViewById(R.id.viewPagerBerita);
        viewPagerBerita.setAdapter(beritaAdapterPager);
        viewPagerBerita.setPadding(60,0,350,0);


        viewPagerBerita.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });



        // slide show pada banner
        createSlideShow();
        return rootView;
    }

    @Override
    public void onClick(View v) {

    }

    private void createSlideShow(){
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (current_position == dataImage.size()){
                    current_position = 0;
                }

                viewPagerBanner.setCurrentItem(current_position++,true);
            }
        };

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(runnable);
            }
        }, 500, 3000);

    }
}
