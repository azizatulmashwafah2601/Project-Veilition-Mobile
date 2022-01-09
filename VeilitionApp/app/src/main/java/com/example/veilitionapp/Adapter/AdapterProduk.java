package com.example.veilitionapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.veilitionapp.API.APIClient;
import com.example.veilitionapp.API.APIInterface;
import com.example.veilitionapp.Activity.DetailProdukActivity;
import com.example.veilitionapp.Activity.UpdateUserActivity;
import com.example.veilitionapp.R;
import com.example.veilitionapp.model.dataproduk.DataItem;
import com.example.veilitionapp.model.dataproduk.Produk;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterProduk extends RecyclerView.Adapter<AdapterProduk.HolderData>{
    private Context context;
    private List<DataItem> listProduk;
    private List<DataItem> listDetail;
    private String idProduk;
    public static String BASE_IMG="https://ws-tif.com/veilition/Retrofit/foto_produk/";

    public AdapterProduk(Context context, List<DataItem> listProduk) {
        this.context = context;
        this.listProduk = listProduk;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.produk_item, parent, false);
        HolderData holder = new HolderData(layout);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {

        DataItem dataItem = listProduk.get(position);

        /**String urlGambar = dataItem.getFotoProduk();
        Glide.with(context)
                .load(urlGambar)// load gambar dari database
                .placeholder(R.drawable.ic_account)// tampil awal sebelum load gambar dari data
                .error(R.drawable.ic_warning) // jika load error
                .into(holder.ivProduk);**/
        holder.tvId.setText(String.valueOf(dataItem.getIdProduk()));
        holder.tvNama.setText(dataItem.getNamaProduk());
        holder.tvHarga.setText(dataItem.getHargaProduk());
        holder.tvDesk.setText(dataItem.getDeskripsiProduk());
        //holder.ivProduk.setImageResource(Integer.parseInt(dataItem.getFotoProduk()));
        Glide.with(context).asBitmap().load(BASE_IMG +dataItem.getFotoProduk()).into(holder.ivProduk);


    }

    @Override
    public int getItemCount() {
        return listProduk.size();
    }

    public class HolderData extends RecyclerView.ViewHolder{
        TextView tvId, tvNama, tvHarga, tvDesk;
        ImageView ivProduk;

        public HolderData(@NonNull View itemView) {
            super(itemView);

            tvId = itemView.findViewById(R.id.tv_id);
            tvNama = itemView.findViewById(R.id.tv_productTitle);
            tvHarga = itemView.findViewById(R.id.tv_productPrice);
            tvDesk = itemView.findViewById(R.id.tv_desk);
            ivProduk = itemView.findViewById(R.id.iv_productImage);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    idProduk = tvId.getText().toString();
                    getDetail();
                }
            });
        }

        private void getDetail() {
            APIInterface ardDetail = APIClient.getClient().create(APIInterface.class);
            Call<Produk> ambilData = ardDetail.detailResponse(idProduk);

            ambilData.enqueue(new Callback<Produk>() {
                @Override
                public void onResponse(Call<Produk> call, Response<Produk> response) {
                    int kode = response.body().getKode();
                    String message = response.body().getPesan();
                    listDetail = response.body().getData();

                    String varIdProduk = listDetail.get(0).getIdProduk();
                    String varNamaProduk = listDetail.get(0).getNamaProduk();
                    String varHargaProduk = listDetail.get(0).getHargaProduk();
                    String varFotoProduk = listDetail.get(0).getFotoProduk();
                    String varDeskripsiProduk = listDetail.get(0).getDeskripsiProduk();

                    Intent kirim = new Intent(context, DetailProdukActivity.class);
                    kirim.putExtra("xId", varIdProduk);
                    kirim.putExtra("xNama", varNamaProduk);
                    kirim.putExtra("xHarga", varHargaProduk);
                    kirim.putExtra("xDesk", varDeskripsiProduk);
                    kirim.putExtra("xFoto", varFotoProduk);

                    context.startActivity(kirim);
                    Toast.makeText(context, "Kode : " + kode + " | Pesan : " + message, Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onFailure(Call<Produk> call, Throwable t) {
                    Toast.makeText(context, "Gagal Menghubungi Server" + t.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });

        }
    }
}
