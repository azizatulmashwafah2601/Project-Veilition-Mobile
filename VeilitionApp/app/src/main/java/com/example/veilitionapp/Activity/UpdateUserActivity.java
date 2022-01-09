package com.example.veilitionapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.veilitionapp.API.APIClient;
import com.example.veilitionapp.API.APIInterface;
import com.example.veilitionapp.R;
import com.example.veilitionapp.SessionManager;
import com.example.veilitionapp.model.login.Login;
import com.example.veilitionapp.model.login.LoginData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateUserActivity extends AppCompatActivity implements View.OnClickListener{

    EditText etNama, etEmail, etPassword, etTelepon, etAlamat;
    Button buttonUpdate;
    ImageView buttonBack;
    SessionManager sessionManager;
    String email, password, nama, telepon, alamat;
    //String id;
    APIInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);

        sessionManager = new SessionManager(UpdateUserActivity.this);
        if (!sessionManager.isLoggedIn()){
            moveToLogin();
        }

        etNama = findViewById(R.id.etNama);
        etTelepon = findViewById(R.id.etNoTelp);
        etEmail = findViewById(R.id.etEmail);
        etAlamat = findViewById(R.id.etAlamat);
        etPassword = findViewById(R.id.etPassword);

        email = sessionManager.getUserDetail().get(SessionManager.EMAIL);
        password = sessionManager.getUserDetail().get(SessionManager.PASSWORD);
        nama = sessionManager.getUserDetail().get(SessionManager.NAME);
        telepon = sessionManager.getUserDetail().get(SessionManager.TELEPON);
        alamat = sessionManager.getUserDetail().get(SessionManager.ALAMAT);

        etEmail.setText(email);
        etPassword.setText(password);
        etNama.setText(nama);
        etTelepon.setText(telepon);
        etAlamat.setText(alamat);



        buttonUpdate = findViewById(R.id.btnUpdate);
        buttonUpdate.setOnClickListener(this);

        buttonBack = findViewById(R.id.btn_back);
        buttonBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btnUpdate:
                updateUserAccount();
                break;
            case R.id.btn_back:
                finish();
        }

    }

    private void updateUserAccount() {

        String id = sessionManager.getUserDetail().get(SessionManager.USER_ID);

        nama = etNama.getText().toString();
        telepon = etTelepon.getText().toString();
        email = etEmail.getText().toString();
        alamat = etAlamat.getText().toString();
        password = etPassword.getText().toString();

        if (email.trim().equals("")) {
            etEmail.setError("Wajib diisi!");
            etEmail.requestFocus();
            return;
        }
        if (password.trim().equals("")) {
            etPassword.setError("Wajib diisi!");
            etPassword.requestFocus();
            return;
        }
        if (nama.trim().equals("")) {
            etNama.setError("Wajib diisi!");
            etNama.requestFocus();
            return;
        }
        if (telepon.trim().equals("")) {
            etTelepon.setError("Wajib diisi!");
            etTelepon.requestFocus();
            return;
        }
        if (alamat.trim().equals("")) {
            etAlamat.setError("Wajib diisi!");
            etAlamat.requestFocus();
            return;
        }

        apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<Login> call = apiInterface.UpdateResponse( id, email, password, nama, telepon, alamat);
        call.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                Login updateResponse = response.body();
                if (response.isSuccessful()){
                    if (updateResponse.isStatus()){
                        SessionManager sessionManager = new SessionManager(getApplicationContext());
                        sessionManager.logoutSession();
                        Toast.makeText(UpdateUserActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(UpdateUserActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }else{
                        Toast.makeText(UpdateUserActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(UpdateUserActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                }

                /**int kode = response.body().getKode();
                String pesan = response.body().getMessage();

                if (kode == 1) {
                    SessionManager sessionManager = new SessionManager(getApplicationContext());
                    sessionManager.logoutSession();
                    LoginData loginData = response.body().getData();
                    sessionManager.createLoginSession(loginData);

                    Toast.makeText(UpdateUserActivity.this, "Kode: " + kode + " | Pesan: " + pesan, Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(UpdateUserActivity.this, "Kode: " + kode + " | Pesan: " + pesan, Toast.LENGTH_SHORT).show();
                }**/

            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {

                Toast.makeText(UpdateUserActivity.this, "Data gagal diperbarui: " +t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void moveToLogin() {
        Intent intent = new Intent(UpdateUserActivity.this, SplashActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
        finish();
    }
}