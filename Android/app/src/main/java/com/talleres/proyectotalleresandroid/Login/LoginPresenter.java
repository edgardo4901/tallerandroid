package com.talleres.proyectotalleresandroid.Login;

import android.content.Context;

import com.talleres.proyectotalleresandroid.Entities.Usuario;
import com.talleres.proyectotalleresandroid.Utilerias.UrlServices;

import com.google.gson.JsonObject;

public class LoginPresenter implements LoginObtainer, LoginViewPresenter {
    LoginView vista;
    LoginProvider interactor;
    UrlServices resourceProvider;

    public LoginPresenter(LoginView vista, UrlServices resourceProvider, Context context){
        this.vista= vista;
        this.resourceProvider=resourceProvider;
        interactor=new LoginInteractor(this, this.resourceProvider,context);
    }
    public void login(String nombre, String contraseña){
        JsonObject jsonCentro = new JsonObject();
        jsonCentro.addProperty("usuario", nombre);
        jsonCentro.addProperty("contrasena", contraseña);
        //jsonCentro.addProperty("loginType", 10);
        interactor.login(jsonCentro);
    }

    @Override
    public void responseLogin(Usuario data) {
        vista.responseLogin(data);
    }
    @Override
    public void mensajeError(String mensaje) {
        vista.mensajeError(mensaje);
    }
}
