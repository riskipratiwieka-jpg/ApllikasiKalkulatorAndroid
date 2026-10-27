package com.swu.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class RiwayatAdapter extends RecyclerView.Adapter<RiwayatAdapter.RiwayatViewHolder> {
    private ArrayList<Riwayat> listRiwayat;

    public RiwayatAdapter (ArrayList<Riwayat> listRiwayat){
        this.listRiwayat = listRiwayat;
    }

    @NonNull
    @Override
    public RiwayatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_riwayat, parent, false);
        return new RiwayatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RiwayatViewHolder holder, int position) {
        Riwayat riwayat = listRiwayat.get(position);
        holder.tvPerhitungan.setText(riwayat.getPerhitungan());
        holder.tvHasil.setText("=" + riwayat.getHasil());
    }

    @Override
    public int getItemCount() {
        return listRiwayat.size();
    }

    //KOMPONEN VIEWHOLDER
    public static class RiwayatViewHolder extends RecyclerView.ViewHolder {
        TextView tvPerhitungan, tvHasil;
        public RiwayatViewHolder(@NonNull View itemView) {
            super(itemView);
            tvPerhitungan = itemView.findViewById(R.id.tvItemPerhitungan);
            tvHasil = itemView.findViewById(R.id.tvItemhasil);
        }


    }
}
