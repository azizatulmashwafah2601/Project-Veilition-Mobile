package com.example.veilitionapp.Fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.veilitionapp.Activity.LoginActivity;
import com.example.veilitionapp.Activity.MainActivity;
import com.example.veilitionapp.Activity.RegisterActivity;
import com.example.veilitionapp.Activity.UpdateUserActivity;
import com.example.veilitionapp.R;
import com.example.veilitionapp.SessionManager;

public class AkunFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AkunFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AkunFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AkunFragment newInstance(String param1, String param2) {
        AkunFragment fragment = new AkunFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private Context context;
    private TextView tvNama, tvEmail, tvPhone, logout;
    private String nama, email, phone;
    SessionManager sessionManager;
    private RelativeLayout edit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_akun, container, false);

        sessionManager = new SessionManager(getActivity());
        if (!sessionManager.isLoggedIn()){
            moveToLogin();
        }

        tvNama = view.findViewById(R.id.tv_nama);
        tvEmail = view.findViewById(R.id.tv_email);
        tvPhone = view.findViewById(R.id.tv_phone);


        nama = sessionManager.getUserDetail().get(SessionManager.NAME);
        email = sessionManager.getUserDetail().get(SessionManager.EMAIL);
        phone = sessionManager.getUserDetail().get(SessionManager.TELEPON);

        tvNama.setText(nama);
        tvEmail.setText(email);
        tvPhone.setText(phone);

        //LOGOUT
        logout = view.findViewById(R.id.tv_logout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialogPesan = new AlertDialog.Builder(getActivity());
                dialogPesan.setTitle("Perhatian!");
                dialogPesan.setIcon(R.drawable.ic_warning);
                dialogPesan.setMessage("Apakah anda yakin ingin keluar?");
                dialogPesan.setCancelable(true);

                dialogPesan.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        sessionManager.logoutSession();
                        Intent intent = new Intent(getActivity(), LoginActivity.class);
                        startActivity(intent);
                        getActivity().finish();
                        Toast.makeText(getActivity(), "Anda telah keluar!", Toast.LENGTH_SHORT).show();
                    }
                });
                dialogPesan.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                dialogPesan.show();

            }
        });

        //EDITAKUN
        edit = view.findViewById(R.id.btn_editProfile);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialogPesan = new AlertDialog.Builder(getActivity());
                dialogPesan.setTitle("Perhatian!");
                dialogPesan.setIcon(R.drawable.ic_warning);
                dialogPesan.setMessage("Apakah anda yakin ingin mengedit akun?");
                dialogPesan.setCancelable(true);

                dialogPesan.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        Intent intent = new Intent(getActivity(), UpdateUserActivity.class);
                        startActivity(intent);
                    }
                });
                dialogPesan.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                dialogPesan.show();

            }
        });

        return view;
    }

    private void moveToLogin() {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
    }
}