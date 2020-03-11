package com.talleres.proyectotalleresandroid.Casas;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.talleres.proyectotalleresandroid.Adapter.CasasAdapter;
import com.talleres.proyectotalleresandroid.Entities.Casas;
import com.talleres.proyectotalleresandroid.Event.MessageEvent;
import com.talleres.proyectotalleresandroid.Login.LoginActivity;
import com.talleres.proyectotalleresandroid.R;
import com.talleres.proyectotalleresandroid.Utilerias.Preference;
import com.talleres.proyectotalleresandroid.Utilerias.UrlServices;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

public class CasasActivity extends AppCompatActivity implements CasasInterface.CasasView {

    CasasInterface.CasasViewPresenter casasViewPresenter;
    CasasAdapter adapter;

    Preference oPreference;

    int PosicionSeleccionada = 0;
    int PosicionEliminada = 0;

    boolean nuevo = false;

    int numCasa = 0;

    TextView toolbar_title;
    RelativeLayout lytCargando,ltyConfirmacion,ltyGuardarCasa;
    ListView lvCasas;
    TextView txtTituloConfirmar;
    Button btnAceptarConfirmar,btnCancelarConfirmar,btnAceptarGuardar,btnCancelarGuardar;
    EditText edt_Numero,edt_Nombre;
    FloatingActionButton fabAgregarCasa;

    ArrayList<Casas> listaCasas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_casas);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        oPreference = new Preference(getApplicationContext());

        toolbar_title = findViewById(R.id.toolbar_title);
        lytCargando = findViewById(R.id.lytCargando);
        lvCasas = findViewById(R.id.lvCasas);
        ltyConfirmacion = findViewById(R.id.ltyConfirmacion);
        txtTituloConfirmar = findViewById(R.id.txtTituloConfirmar);
        btnAceptarConfirmar = findViewById(R.id.btnAceptarConfirmar);
        btnCancelarConfirmar = findViewById(R.id.btnCancelarConfirmar);
        ltyGuardarCasa = findViewById(R.id.ltyGuardarCasa);
        btnAceptarGuardar = findViewById(R.id.btnAceptarGuardar);
        btnCancelarGuardar = findViewById(R.id.btnCancelarGuardar);
        edt_Numero = findViewById(R.id.edt_Numero);
        edt_Nombre = findViewById(R.id.edt_Nombre);
        fabAgregarCasa = findViewById(R.id.fabAgregarCasa);

        toolbar_title.setText(oPreference.getNombre());

        listaCasas = new ArrayList<>();
        Intent iin= getIntent();
        Bundle b = iin.getExtras();

        casasViewPresenter =new CasasPresenter(this, new UrlServices(),getApplicationContext());

        if(b!=null)
        {
            listaCasas = (ArrayList<Casas>) b.get("ListaCasas");
            cargarCasas();
        }
        else{
            lytCargando.setVisibility(View.VISIBLE);
            casasViewPresenter.consultarCasas();
        }

        lytCargando.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        ltyConfirmacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        ltyGuardarCasa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btnCancelarConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ltyConfirmacion.setVisibility(View.GONE);
            }
        });
        btnCancelarGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ltyGuardarCasa.setVisibility(View.GONE);
            }
        });
        btnAceptarConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lytCargando.setVisibility(View.VISIBLE);
                casasViewPresenter.eliminarCasa(oPreference.getId(),listaCasas.get(PosicionSeleccionada).getNum_casa());
            }
        });
        btnAceptarGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validarGuardado()) {
                    lytCargando.setVisibility(View.VISIBLE);
                    casasViewPresenter.guardarCasa(oPreference.getId(), edt_Numero.getText().toString(),edt_Nombre.getText().toString());
                }
            }
        });
        fabAgregarCasa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt_Nombre.setText("");
                edt_Numero.setText("");
                ltyGuardarCasa.setVisibility(View.VISIBLE);
            }
        });
        txtTituloConfirmar.setText(getString(R.string.str_tituloeliminar));

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_cerrarsesion:
                CerrarSesion();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private boolean validarGuardado(){
        if(edt_Nombre.getText().toString().trim().equals("") || edt_Numero.getText().toString().trim().equals("")){
            Toast.makeText(getApplicationContext(), getString(R.string.msg_completarcamposguardado), Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    private void CerrarSesion(){
        oPreference.setId(null);
        oPreference.setNombre(null);
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
        finish();
    }

    @Override
    public void respuestaLista(ArrayList<Casas> respuesta) {
            lytCargando.setVisibility(View.GONE);
            ltyConfirmacion.setVisibility(View.GONE);
            ltyGuardarCasa.setVisibility(View.GONE);
            listaCasas = respuesta;
            cargarCasas();
    }
    @Override
    public void mostrarMensajeRespuesta(String respuesta, boolean actualizar) {
        lytCargando.setVisibility(View.GONE);
        Toast.makeText(getApplicationContext(), respuesta, Toast.LENGTH_LONG).show();
    }
    @Override
    public void onStart() {
        try {
            if (!EventBus.getDefault().isRegistered(this)) {
                EventBus.getDefault().register(this);
            }
        }
        catch (Exception e)
        {

        }
        super.onStart();
    }
    @Override
    public void onStop() {
        try {
            if (EventBus.getDefault().isRegistered(this)) {
                EventBus.getDefault().unregister(this);
            }
        }
        catch (Exception e)
        {

        }
        super.onStop();
    }
    @Subscribe
    public void onEvent(MessageEvent event) {
        if (event.getFiltro() == 1) {
            PosicionSeleccionada = event.getID();
            ltyConfirmacion.setVisibility(View.VISIBLE);
            numCasa = listaCasas.get(PosicionSeleccionada).getNum_casa();
            PosicionEliminada = event.getID();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void cargarCasas()
    {
        adapter = new CasasAdapter(getApplicationContext(), listaCasas);
        lvCasas.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
