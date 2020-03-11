package com.talleres.proyectotalleresandroid.Casas;

import android.content.Context;

import com.google.gson.JsonObject;
import com.talleres.proyectotalleresandroid.Entities.Casas;
import com.talleres.proyectotalleresandroid.Utilerias.UrlServices;

import java.util.ArrayList;


public class CasasPresenter implements CasasInterface.CasasObtainer, CasasInterface.CasasViewPresenter {

    CasasInterface.CasasView vista;
    CasasInterface.CasasProvider interactor;
    UrlServices resourceProvider;

    public CasasPresenter(CasasInterface.CasasView vista, UrlServices resourceProvider, Context context){
        this.vista= vista;
        this.resourceProvider=resourceProvider;
        interactor=new CasasInteractor(this, this.resourceProvider,context);
    }

    @Override
    public void guardarCasa(String idusuario, String numero, String nombre){
        JsonObject jsonData = new JsonObject();
        jsonData.addProperty("idUsuario",idusuario);
        jsonData.addProperty("numero",numero);
        jsonData.addProperty("nombre",nombre);
        interactor.guardarCasa(jsonData);
    }
    @Override
    public void eliminarCasa(String idusuario, int numero){
        JsonObject jsonData = new JsonObject();
        jsonData.addProperty("idUsuario",idusuario);
        jsonData.addProperty("numero",numero);
        interactor.eliminarCasa(jsonData);
    }
    @Override
    public void consultarCasas(){
        interactor.consultarCasas();
    }

    @Override
    public void respuestaLista(ArrayList<Casas> respuesta) {
        vista.respuestaLista(respuesta);
    }
    @Override
    public void mensajeRespuesta(String respuesta,boolean actualizar) {
        vista.mostrarMensajeRespuesta(respuesta,actualizar);
    }

}
