package com.talleres.proyectotalleresandroid.Login;

import com.talleres.proyectotalleresandroid.Entities.Usuario;

public interface LoginObtainer {
    void responseLogin(Usuario data);
    void mensajeError(String mensaje);
}
