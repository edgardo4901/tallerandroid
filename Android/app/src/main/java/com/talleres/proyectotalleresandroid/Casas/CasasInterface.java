package com.talleres.proyectotalleresandroid.Casas;

import com.google.gson.JsonObject;
import com.talleres.proyectotalleresandroid.Entities.Casas;

import java.util.ArrayList;

public interface CasasInterface {
    interface CasasObtainer {
        void respuestaLista(ArrayList<Casas> respuesta);
        void mensajeRespuesta(String respuesta, boolean actualizar);
    }


    interface CasasProvider {
        void consultarCasas();
        void guardarCasa(JsonObject data);
        void eliminarCasa(JsonObject data);
    }

    interface CasasView {
        void respuestaLista(ArrayList<Casas> respuesta);
        void mostrarMensajeRespuesta(String respuesta, boolean actualizar);
    }
    interface CasasViewPresenter {
        void guardarCasa(String idusuario, String numero, String nombre);
        void eliminarCasa(String idUsuario, int numero);
        void consultarCasas();
    }
}
