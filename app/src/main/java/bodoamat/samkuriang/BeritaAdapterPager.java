package bodoamat.samkuriang;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import bodoamat.samkuriang.Fragment.HomeFragment;

public class BeritaAdapterPager extends PagerAdapter {

    private List<ModelBerita> modelBeritas;
    private LayoutInflater layoutInflater;
    private HomeFragment context;

    public BeritaAdapterPager(List<ModelBerita> modelBeritas, HomeFragment context){
        this.modelBeritas = modelBeritas;
        this.context = context;
    }

    @Override
    public int getCount() {
        return modelBeritas.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view.equals(o);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        Context context = container.getContext();
        layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_berita, container, false);

        ImageView imgBerita;
        TextView judulBerita, deskripsiBerita;

        imgBerita = view.findViewById(R.id.img_berita);
        judulBerita = view.findViewById(R.id.judul_berita);
        deskripsiBerita = view.findViewById(R.id.deskripsi_berita);

        imgBerita.setImageResource(modelBeritas.get(position).getImgBerita());
        judulBerita.setText(modelBeritas.get(position).getJudulBerita());
        deskripsiBerita.setText(modelBeritas.get(position).getDeskripsiBerita());

        container.addView(view, 0);


        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        super.destroyItem(container, position, object);
    }


}
