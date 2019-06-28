package bodoamat.samkuriang.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import bodoamat.samkuriang.activity.DaftarNasabahActivity;
import bodoamat.samkuriang.R;
import bodoamat.samkuriang.adapter.BannerAdapterPager;
import bodoamat.samkuriang.adapter.BeritaAdapterPager;
import bodoamat.samkuriang.api.ConfigUtils;
import bodoamat.samkuriang.api.Service;
import bodoamat.samkuriang.models.Customer;
import bodoamat.samkuriang.models.ModelBerita;
import bodoamat.samkuriang.models.Saving;
import bodoamat.samkuriang.models.StatusNasabah;
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

    // status nasabah
    ImageView icon_warning, icon_checklist;
    TextView status_nasabah_belum, status_nasabah_sudah, nama_bank_sampah;
    LinearLayout btn_daftar_nasabah;

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

        //status nasabah
        icon_warning = rootView.findViewById(R.id.icon_warning);
        icon_checklist = rootView.findViewById(R.id.icon_checklist);
        status_nasabah_belum = rootView.findViewById(R.id.status_nasabah_belum);
        status_nasabah_sudah = rootView.findViewById(R.id.status_nasabah_sudah);
        nama_bank_sampah = rootView.findViewById(R.id.tv_nama_bank_sampah);
        btn_daftar_nasabah = rootView.findViewById(R.id.btn_daftar_nasabah);

        Call<StatusNasabah> callStatus = service.getStatusCustomer(
                SharedPrefManager.getInstance(getActivity()).getCustomer().getId()
        );

        callStatus.enqueue(new Callback<StatusNasabah>() {
            @Override
            public void onResponse(Call<StatusNasabah> call, Response<StatusNasabah> response) {
                // jika status = 1 atau true, user sudah terdaftar di bank sampah
                if (response.body().getStatus().equals("1")){
                    btn_daftar_nasabah.setVisibility(View.INVISIBLE);
                    icon_warning.setVisibility(View.INVISIBLE);
                    status_nasabah_belum.setVisibility(View.INVISIBLE);
                    nama_bank_sampah.setText(response.body().getPlace_name());
                    nama_bank_sampah.setVisibility(View.VISIBLE);
                    icon_checklist.setVisibility(View.VISIBLE);
                    status_nasabah_sudah.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<StatusNasabah> call, Throwable t) {
                Toast.makeText(getActivity(), "gagal get status", Toast.LENGTH_LONG).show();
            }

        });

        btn_daftar_nasabah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // daftar jadi nasabah
                Intent intentDaftarNasabah = new Intent(getActivity(), DaftarNasabahActivity.class);
                getActivity().startActivity(intentDaftarNasabah);
            }
        });



        Customer customer = SharedPrefManager.getInstance(getActivity()).getCustomer();

        String[] namaPanjang = customer.getName().split(" ");
        haiNama.setText(namaPanjang[0]);





        // banner
        viewPagerBanner= rootView.findViewById(R.id.viewPagerBanner);
        dataImage.add(R.drawable.slider2);
        dataImage.add(R.drawable.slider3);
        dataImage.add(R.drawable.slider1);

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
        modelBeritas.add(new ModelBerita(R.drawable.berita1,"Indonesia di Peringkat Dua Dunia sebagai Negara Pembuang Makanan Terbanyak", "Ingatkah Anda ketika dimarahi oleh Ibu karena tidak menghabiskan makanan? Atau mungkin hal itu terjadi baru-baru ini saja?"));
        modelBeritas.add(new ModelBerita(R.drawable.berita2,"Pelopor Incinerator Wujudkan Aksi untuk Indonesia Bersih", "Wilayah metropolitan DKI Jakarta saja pada periode 2017 – 2018 ditemukan ada sekitar 11,679 ton/ hari sampah ditimbun di tempat pembuangan akhir (TPA)"));
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