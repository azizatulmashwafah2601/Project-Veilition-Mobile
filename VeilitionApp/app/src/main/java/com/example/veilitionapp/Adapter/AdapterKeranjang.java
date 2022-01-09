package com.example.veilitionapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.veilitionapp.R;
import com.example.veilitionapp.model.keranjang.DataKeranjang;

import java.util.List;

public class AdapterKeranjang extends RecyclerView.Adapter<AdapterKeranjang.HolderData>{
    private Context context;
    private List<DataKeranjang> listKeranjang;
    public static String BASE_IMG="https://ws-tif.com/veilition/Retrofit/foto_produk/";


    public AdapterKeranjang(Context context, List<DataKeranjang> listKeranjang) {
        this.context = context;
        this.listKeranjang = listKeranjang;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false);
        HolderData holder = new HolderData(layout);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        DataKeranjang dk = listKeranjang.get(position);

        holder.tvId.setText(dk.getIdKeranjang());
        holder.tvNama.setText(dk.getNamaProduk());
        holder.tvHarga.setText(dk.getHargaProduk());
        holder.tvJumlah.setText(dk.getJumlah());
        Glide.with(context).asBitmap().load(BASE_IMG +dk.getFotoProduk()).into(holder.ivProduk);

    }

    @Override
    public int getItemCount() {
        return listKeranjang.size();
    }

    public class HolderData extends RecyclerView.ViewHolder{
        TextView tvId, tvNama, tvHarga, tvJumlah;
        ImageView ivProduk;

        public HolderData(@NonNull View itemView) {
            super(itemView);

            tvId = itemView.findViewById(R.id.tv_idk);
            tvNama = itemView.findViewById(R.id.tv_nama);
            tvHarga = itemView.findViewById(R.id.tv_harga);
            tvJumlah = itemView.findViewById(R.id.tv_jumlah);
            ivProduk = itemView.findViewById(R.id.img_produk);
        }
    }
}
