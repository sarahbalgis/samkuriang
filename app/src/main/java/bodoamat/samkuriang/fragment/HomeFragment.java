package bodoamat.samkuriang.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import bodoamat.samkuriang.R;
import bodoamat.samkuriang.adapter.BannerAdapterPager;
import bodoamat.samkuriang.adapter.BeritaAdapterPager;
import bodoamat.samkuriang.api.ConfigUtils;
import bodoamat.samkuriang.api.Service;
import bodoamat.samkuriang.models.Customer;
import bodoamat.samkuriang.models.ModelBerita;
import bodoamat.samkuriang.models.Saving;
import bodoamat.samkuriang.storage.SharedPrefManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment implements View.OnClickListener {

    //text
    TextView haiNama, saldoTabungan, beratSampah;

    // banner
    ViewPager viewPagerBanner;
    List<Integer> dataImage = new ArrayList<>();
    ImageView indicator1, indicator2, indicator3;

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



        //text
        haiNama = rootView.findViewById(R.id.tv_hai_nama);
        saldoTabungan = rootView.findViewById(R.id.saldo_tabungan);
        beratSampah = rootView.findViewById(R.id.berat_sampah);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConfigUtils.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Service service = retrofit.create(Service.class);

        Call<Saving> callSaving = service.getTabungan(
                SharedPrefManager.getInstance(getActivity()).getCustomer().getId()
        );

        callSaving.enqueue(new Callback<Saving>() {
            @Override
            public void onResponse(Call<Saving> call, Response<Saving> response) {
                DecimalFormat decim = new DecimalFormat("#,###.##");
                Float tabungan = response.body().getTabungan();
                saldoTabungan.setText(decim. format(tabungan));
                beratSampah.setText(response.body().getBerat());

            }

            @Override
            public void onFailure(Call<Saving> call, Throwable t) {
                Toast.makeText(getActivity(), "gagal", Toast.LENGTH_LONG).show();
            }


        });



        Customer customer = SharedPrefManager.getInstance(getActivity()).getCustomer();

        String[] namaPanjang = customer.getName().split(" ");
        haiNama.setText(namaPanjang[0]);





        // banner
        viewPagerBanner= rootView.findViewById(R.id.viewPagerBanner);
        dataImage.add(R.drawable.slider1);
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
        modelBeritas.add(new ModelBerita(R.drawable.b,"Masyarakat Harus Menabung Sampah", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin dfaef dsgfssf sdaaafg gdytdhinm  dfgdfgggggfg agsauydgadab sgudygusya sgsa arcu sapien, porta nec orci ac, bibendum temsque l congue por et dignissim arcu, sed tempor dolor."));
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