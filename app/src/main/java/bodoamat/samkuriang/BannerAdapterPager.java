package bodoamat.samkuriang;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jcminarro.roundkornerlayout.RoundKornerRelativeLayout;

import java.util.ArrayList;
import java.util.List;

import bodoamat.samkuriang.Fragment.HomeFragment;


public class BannerAdapterPager extends PagerAdapter {

    HomeFragment context;
    List<Integer> dataImage = new ArrayList<>();


    public BannerAdapterPager(HomeFragment context, List<Integer> dataImage) {
        this.context = context;
        this.dataImage = dataImage;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        Context context = container.getContext();

        //  cara membuat view. layout ingin ditambahkan di context yang mana sih
        View view = LayoutInflater.from(context).inflate(R.layout.item_banner, container, false);

        ImageView imageView = view.findViewById(R.id.img_banner);
        imageView.setImageResource(dataImage.get(position));
        container.addView(view);

        return view;
    }

    @Override
    public int getCount() { // buat nampilin berapa banyak nanti yg ditampilin di viewPager
        return dataImage.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {

        return view == o;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RoundKornerRelativeLayout) object);
    }
}
