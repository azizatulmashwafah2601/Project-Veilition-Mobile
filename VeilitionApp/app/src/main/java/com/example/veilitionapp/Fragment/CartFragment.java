package com.example.veilitionapp.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.veilitionapp.API.APIClient;
import com.example.veilitionapp.API.APIInterface;
import com.example.veilitionapp.Activity.LoginActivity;
import com.example.veilitionapp.Adapter.AdapterKeranjang;
import com.example.veilitionapp.Adapter.AdapterProduk;
import com.example.veilitionapp.R;
import com.example.veilitionapp.SessionManager;
import com.example.veilitionapp.model.dataproduk.DataItem;
import com.example.veilitionapp.model.dataproduk.Produk;
import com.example.veilitionapp.model.keranjang.DataKeranjang;
import com.example.veilitionapp.model.keranjang.KeranjangResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CartFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CartFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CartFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CartFragment newInstance(String param1, String param2) {
        CartFragment fragment = new CartFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    SessionManager sessionManager;
    private Context context;

    private RecyclerView rvKeranjang;
    private RecyclerView.Adapter adKeranjang;
    private RecyclerView.LayoutManager lmCart;
    private List<DataKeranjang> listKeranjang = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        sessionManager = new SessionManager(getActivity());
        if (!sessionManager.isLoggedIn()){
            moveToLogin();
        }

        rvKeranjang = view.findViewById(R.id.rv_cart);
        //lmProduk = new GridLayoutManager(getActivity(), 2);
        lmCart = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        rvKeranjang.setLayoutManager(lmCart);
        RetrieveDataKeranjang();

        return view;
    }

    public void RetrieveDataKeranjang(){

        String id = sessionManager.getUserDetail().get(SessionManager.USER_ID);

        APIInterface ardCart = APIClient.getClient().create(APIInterface.class);
        Call<KeranjangResponse> tampilData = ardCart.keranjangResponse(id);

        tampilData.enqueue(new Callback<KeranjangResponse>() {
            @Override
            public void onResponse(Call<KeranjangResponse> call, Response<KeranjangResponse> response) {
                int kode = response.body().getKode();
                String pesan = response.body().getPesan();

                listKeranjang = response.body().getData();

                adKeranjang = new AdapterKeranjang(getActivity(), listKeranjang);
                rvKeranjang.setAdapter(adKeranjang);
                adKeranjang.notifyDataSetChanged();
                Toast.makeText(getActivity(), "Kode : "+kode+" | Pesan : "+pesan, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<KeranjangResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "Gagal Menghubungi Server : "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void moveToLogin() {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
    }
}