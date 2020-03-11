package com.talleres.proyectotalleresandroid.Splash;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.talleres.proyectotalleresandroid.Casas.CasasActivity;
import com.talleres.proyectotalleresandroid.Entities.Casas;
import com.talleres.proyectotalleresandroid.Login.LoginActivity;
import com.talleres.proyectotalleresandroid.R;
import com.talleres.proyectotalleresandroid.Utilerias.Preference;

public class SplashActivity extends AppCompatActivity {
    Preference oPreference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        oPreference = new Preference(getApplicationContext());

        Thread background = new Thread() {
            public void run() {
                try {
                    sleep(1 * 1000);
                    validarSesion();

                } catch (Exception e) {

                }
            }
        };
        background.start();

    }
    public void validarSesion() {
        if(oPreference.getId() != null && !oPreference.getId().equals(""))
        {
            Intent intent = new Intent(getApplicationContext(), CasasActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
            finish();
        }
        else {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
            finish();
        }
    }
}
