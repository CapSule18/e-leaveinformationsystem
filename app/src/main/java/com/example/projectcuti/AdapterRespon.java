package com.example.projectcuti;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectcuti.model.Izin;

import java.util.ArrayList;

public class AdapterRespon extends RecyclerView.Adapter<AdapterRespon.ViewModel> {
    ArrayList<Izin> list;

    public AdapterRespon(ArrayList<Izin> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public AdapterRespon.ViewModel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View ListItem = layoutInflater.inflate(R.layout.rekapdatakaryawan, parent, false);
        ViewModel viewHolder = new ViewModel(ListItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRespon.ViewModel holder, int position) {
        Izin izin = list.get(position);

        holder.txt_status.setText(izin.getStatus());
        if (izin.getStatus().equals("pending")) {
            holder.txt_status.setTextColor(holder.txt_status.getResources().getColor(R.color.yellow));
        }
        holder.txt_nik.setText(izin.getNik());
        holder.txt_nama.setText(izin.getNama());
        holder.txt_tgl.setText(izin.getTgl_mulai());
        holder.txt_selesai.setText(izin.getTgl_akhir());
        holder.txt_jenis.setText(izin.getJenis());
        holder.card.setOnClickListener(view -> {
            System.out.println("Click card!");
            view.getContext().startActivity(new Intent(view.getContext(), ResponIzin.class).putExtra("id", izin.getId()));
        });
    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }

    public static class ViewModel extends RecyclerView.ViewHolder {
        TextView txt_status, txt_nik, txt_nama, txt_tgl, txt_selesai, txt_jenis;
        CardView card;

        public ViewModel(@NonNull View itemView) {
            super(itemView);
            txt_status = itemView.findViewById(R.id.txt_status);
            txt_nik = itemView.findViewById(R.id.txt_nik);
            txt_nama = itemView.findViewById(R.id.txt_nama);
            txt_tgl = itemView.findViewById(R.id.txt_tgl);
            txt_selesai = itemView.findViewById(R.id.txt_selesai);
            txt_jenis = itemView.findViewById(R.id.txt_jenis);
            card = itemView.findViewById(R.id.Card);
        }
    }
}
