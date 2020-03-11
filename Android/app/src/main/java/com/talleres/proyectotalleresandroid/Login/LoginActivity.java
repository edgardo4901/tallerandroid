package com.talleres.proyectotalleresandroid.Login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.talleres.proyectotalleresandroid.Casas.CasasActivity;
import com.talleres.proyectotalleresandroid.Entities.Usuario;
import com.talleres.proyectotalleresandroid.R;
import com.talleres.proyectotalleresandroid.Utilerias.Preference;
import com.talleres.proyectotalleresandroid.Utilerias.UrlServices;

public class LoginActivity  extends AppCompatActivity implements  LoginView {

    LoginViewPresenter loginViewPresenter;

    Preference oPreference;

    Button btnIniciar;
    EditText edtContraseña,edtusuario;
    RelativeLayout lytCargando;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        oPreference = new Preference(getApplicationContext());

        edtContraseña = findViewById(R.id.edt_Contraseña);
        edtusuario = findViewById(R.id.edt_Usuario);
        btnIniciar = findViewById(R.id.btn_Inicio);
        lytCargando = findViewById(R.id.lytCargando);

        loginViewPresenter=new LoginPresenter(this, new UrlServices(),getApplicationContext());

        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtusuario.getText().toString().equals("") || edtContraseña.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), getString(R.string.str_camposloginvacios), Toast.LENGTH_LONG).show();
                } else {
                    lytCargando.setVisibility(View.VISIBLE);
                    loginViewPresenter.login(edtusuario.getText().toString(), edtContraseña.getText().toString());
                }
            }
        });
    }
    @Override
    public void responseLogin(Usuario data){
        oPreference.setNombre(data.getNom_empleado() + " " + data.getDes_apellidoMaterno());
        oPreference.setId(data.getId());
        lytCargando.setVisibility(View.GONE);
        llamarMain(data);
    }
    @Override
    public void mensajeError(String mensaje){
        lytCargando.setVisibility(View.GONE);
        Toast.makeText(getApplicationContext(),mensaje,Toast.LENGTH_LONG).show();
    }
    private void llamarMain(Usuario oUsuario)
    {
        Intent intent = new Intent(getApplication(), CasasActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("ListaCasas",  oUsuario.getCasas());
        startActivity(intent);
        finish();
    }
}
