package com.example.projectcuti.izin.data;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectcuti.R;
import com.example.projectcuti.izin.data.model.Izin;

import java.util.List;

public class AdapterRekap extends RecyclerView.Adapter<AdapterRekap.ViewModel> {
    List<Izin> list;

    public AdapterRekap(List<Izin> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public AdapterRekap.ViewModel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View ListItem = layoutInflater.inflate(R.layout.rekapdatakaryawan, parent, false);
        ViewModel viewHolder = new ViewModel(ListItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRekap.ViewModel holder, int position) {
        Izin izin = list.get(position);

        String number = "Nomor Izin: " + izin.getId();

        if (izin.getStatus().equals("Pending")) {
            holder.txt_status.setTextColor(holder.txt_status.getResources().getColor(R.color.yellow));
        }
        if (izin.getStatus().equals("Ditolak")) {
            holder.txt_status.setTextColor(holder.txt_status.getResources().getColor(R.color.black));
        }
        holder.txt_status.setText(izin.getStatus());
        holder.txt_nik.setText(number);
        holder.txt_tgl.setText(izin.getSince());
        holder.txt_selesai.setText(izin.getUntil());
        holder.txt_jenis.setText(izin.getType());
        holder.txt_keterangan.setText(izin.getKeterangan());
    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }

    public static class ViewModel extends RecyclerView.ViewHolder {
        TextView txt_status, txt_nik, txt_tgl, txt_selesai, txt_jenis, txt_keterangan;

        public ViewModel(@NonNull View itemView) {
            super(itemView);
            txt_keterangan = itemView.findViewById(R.id.txt_keterangan);
            txt_status = itemView.findViewById(R.id.txt_status);
            txt_nik = itemView.findViewById(R.id.txt_nik);
            txt_tgl = itemView.findViewById(R.id.txt_tgl);
            txt_selesai = itemView.findViewById(R.id.txt_selesai);
            txt_jenis = itemView.findViewById(R.id.txt_jenis);
        }
    }
}
