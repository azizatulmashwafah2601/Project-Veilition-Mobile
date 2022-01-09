package com.example.veilitionapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.veilitionapp.Fragment.AkunFragment;
import com.example.veilitionapp.model.login.LoginData;

import java.util.HashMap;

public class SessionManager {

    private Context _context;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public static final String IS_LOGGED_IN = "isLoggedIn";
    public static final String USER_ID = "user_id";
    public static final String EMAIL ="email";
    public static final String PASSWORD ="password";
    public static final String NAME ="nama";
    public static final String TELEPON ="telepon";
    public static final String ALAMAT ="alamat";

    public SessionManager (Context context){
        this._context = context;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        editor = sharedPreferences.edit();
    }


    public void createLoginSession(LoginData user){
        editor.putBoolean(IS_LOGGED_IN, true);
        editor.putString(USER_ID, user.getUserId());
        editor.putString(EMAIL, user.getEmail());
        editor.putString(PASSWORD, user.getPassword());
        editor.putString(NAME, user.getName());
        editor.putString(TELEPON, user.getTelepon());
        editor.putString(ALAMAT, user.getAlamat());
        editor.commit();
    }

    public void createSession(String email){
        editor.putBoolean(IS_LOGGED_IN, true);
        editor.putString(EMAIL, email);
        editor.commit();
    }

    public HashMap<String,String> getUserDetail(){
        HashMap<String,String> user = new HashMap<>();
        user.put(USER_ID, sharedPreferences.getString(USER_ID, null));
        user.put(EMAIL, sharedPreferences.getString(EMAIL, null));
        user.put(PASSWORD, sharedPreferences.getString(PASSWORD, null));
        user.put(NAME, sharedPreferences.getString(NAME, null));
        user.put(TELEPON, sharedPreferences.getString(TELEPON, null));
        user.put(ALAMAT, sharedPreferences.getString(ALAMAT, null));
        return user;
    }


    public void logoutSession(){
        editor.clear();
        editor.commit();
    }

    public boolean isLoggedIn(){
        return sharedPreferences.getBoolean(IS_LOGGED_IN,false);
    }

    public String getSessionID() {
        String id_user = sharedPreferences.getString("user_id", "");
        return id_user;
    }

}
