package bodoamat.samkuriang.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import bodoamat.samkuriang.R;
import bodoamat.samkuriang.fragment.MapsFragment;
import bodoamat.samkuriang.models.BankSampah;

public class BankSampahAdapter extends PagerAdapter {

    private List<BankSampah> bankSampahs;
    private LayoutInflater layoutInflater;
    private MapsFragment context;

    public BankSampahAdapter(List<BankSampah> bankSampahs, MapsFragment context) {
        this.bankSampahs = bankSampahs;
        this.context = context;
    }

    @Override
    public int getCount() {
        return bankSampahs.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        Context context = container.getContext();
        layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_bank_sampah, container, false);

        TextView namaBankSampah, alamatBankSampah;

        namaBankSampah = view.findViewById(R.id.tvNamaBankSampah);
        alamatBankSampah = view.findViewById(R.id.tvAlamatBankSampah);

        namaBankSampah.setText(bankSampahs.get(position).getNamaBankSampah());
        alamatBankSampah.setText(bankSampahs.get(position).getAlamatBankSampah());

        container.addView(view, 0);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
