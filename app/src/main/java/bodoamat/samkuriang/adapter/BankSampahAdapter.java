package bodoamat.samkuriang.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import bodoamat.samkuriang.R;
import bodoamat.samkuriang.activity.DetailBankSampahActivity;
import bodoamat.samkuriang.fragment.MapsFragment;
import bodoamat.samkuriang.models.BankSampah;

public class BankSampahAdapter extends RecyclerView.Adapter<BankSampahAdapter.ViewHolder> {
    private List<BankSampah> data;
    private Context mCtx;

    public BankSampahAdapter(List<BankSampah> data, Context mCtx) {
        this.data = data;
        this.mCtx = mCtx;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_bank_sampah, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BankSampahAdapter.ViewHolder holder, int position) {

        final BankSampah bankSampah = data.get(position);
        holder.name.setText(bankSampah.getPlace_name());
        holder.address.setText(bankSampah.getAddress());
        holder.phone_number.setText(bankSampah.getPhone_number());

        holder.btnDaftarNasabah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                //Yes button clicked
//                                MapsFragment.userJadiNasabah();
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                //No button clicked
                                dialog.cancel();
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setMessage("Anda yakin ingin menjadi Nasabah Bank " + bankSampah.getPlace_name() +"?").setPositiveButton("Yakin", dialogClickListener)
                        .setNegativeButton("Belum", dialogClickListener).show();
            }
        });
    }

    private void userJadiNasabah(){


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView name,address,phone_number;
        private Button btnDaftarNasabah;

        public ViewHolder(View view) {
            super(view);

            name = (TextView)view.findViewById(R.id.tvNamaBankSampah);
            address = (TextView)view.findViewById(R.id.tvAlamatBankSampah);
            phone_number = (TextView)view.findViewById(R.id.tvTeleponBankSampah);
            btnDaftarNasabah = view.findViewById(R.id.btn_daftar_nasabah);

        }
    }


}
