package com.talleres.proyectotalleresandroid.Login;

import com.talleres.proyectotalleresandroid.Entities.Usuario;

public interface LoginView {
    void responseLogin(Usuario data);
    void mensajeError(String mensaje);
}
