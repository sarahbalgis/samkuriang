package bodoamat.samkuriang.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import bodoamat.samkuriang.R;
import bodoamat.samkuriang.models.History;

public class ItemListHistoryAdapter extends RecyclerView.Adapter<ItemListHistoryAdapter.ViewHolder> {
    private List<History> histories;
    private Context mCtx;

    public ItemListHistoryAdapter(List<History> histories, Context mCtx) {
        this.histories = histories;
        this.mCtx = mCtx;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_history, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ItemListHistoryAdapter.ViewHolder holder, int position) {
        History history = histories.get(position);
        holder.nominalHarga.setText(history.getPrice());
        holder.jumlahSampah.setText(history.getSize());
        holder.namaSampah.setText(history.getName());
        holder.tanggalRiwayat.setText(history.getCreated_at());

    }

    @Override
    public int getItemCount() {
        return histories.size();
    }


//    @Override
//    public int getItemCount() {
//      return histories.size();
//    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nominalHarga, jumlahSampah, namaSampah, tanggalRiwayat;

        public ViewHolder(View itemView) {
            super(itemView);

            nominalHarga = itemView.findViewById(R.id.nominal_harga);
            jumlahSampah = itemView.findViewById(R.id.jumlah_kg);
            namaSampah = itemView.findViewById(R.id.text_jenis);
            tanggalRiwayat = itemView.findViewById(R.id.tanggal_history);
        }
    }
}
