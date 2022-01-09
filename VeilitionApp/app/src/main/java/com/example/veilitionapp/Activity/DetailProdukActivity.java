package com.example.veilitionapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.veilitionapp.Fragment.CartFragment;
import com.example.veilitionapp.R;

import org.w3c.dom.Text;

public class DetailProdukActivity extends AppCompatActivity {

    ImageView ivProduk;
    TextView tvIdpro, tvNamaProduk, tvHargaProduk, tvdeskripsi;
    String idProduk, namaProduk, hargaProduk, deskripsi, foto;
    Button btPesan;
    ImageButton btKeranjang;
    public static String BASE_IMG="https://ws-tif.com/veilition/Retrofit/foto_produk/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_produk);

        tvIdpro = findViewById(R.id.tv_idPro);
        ivProduk = findViewById(R.id.iv_productImage);
        tvNamaProduk = findViewById(R.id.tv_namaProduk);
        tvHargaProduk = findViewById(R.id.tv_hargaProduk);
        tvdeskripsi = findViewById(R.id.tv_descDetail);
        tvdeskripsi.setMovementMethod(new ScrollingMovementMethod());

        Intent terima = getIntent();
        idProduk = terima.getStringExtra("xId");
        namaProduk = terima.getStringExtra("xNama");
        hargaProduk = terima.getStringExtra("xHarga");
        deskripsi = terima.getStringExtra("xDesk");
        foto = terima.getStringExtra("xFoto");

        tvIdpro.setText(idProduk);
        tvNamaProduk.setText(namaProduk);
        tvHargaProduk.setText(hargaProduk);
        tvdeskripsi.setText(deskripsi);
        Glide.with(DetailProdukActivity.this).load(BASE_IMG+foto).into(ivProduk);

        btKeranjang = findViewById(R.id.bt_keranjang);
        btKeranjang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DetailProdukActivity.this, CartFragment.class));
            }
        });
    }
}