package com.talleres.proyectotalleresandroid.Entities;

import java.io.Serializable;
import java.util.ArrayList;

public class Usuario implements Serializable {

    private String id;
    private int num_empleado;
    private String nom_empleado;
    private String des_apellidoPaterno;
    private String des_apellidoMaterno;
    private String nom_empresa;
    private String nom_centro;
    private Perfil perfil;
    private Rol rol;
    private ArrayList<Casas> casas;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getNum_empleado() {
        return num_empleado;
    }

    public void setNum_empleado(int num_empleado) {
        this.num_empleado = num_empleado;
    }

    public String getNom_empleado() {
        return nom_empleado;
    }

    public void setNom_empleado(String nom_empleado) {
        this.nom_empleado = nom_empleado;
    }

    public String getDes_apellidoPaterno() {
        return des_apellidoPaterno;
    }

    public void setDes_apellidoPaterno(String des_apellidoPaterno) {
        this.des_apellidoPaterno = des_apellidoPaterno;
    }

    public String getDes_apellidoMaterno() {
        return des_apellidoMaterno;
    }

    public void setDes_apellidoMaterno(String des_apellidoMaterno) {
        this.des_apellidoMaterno = des_apellidoMaterno;
    }

    public String getNom_empresa() {
        return nom_empresa;
    }

    public void setNom_empresa(String nom_empresa) {
        this.nom_empresa = nom_empresa;
    }

    public String getNom_centro() {
        return nom_centro;
    }

    public void setNom_centro(String nom_centro) {
        this.nom_centro = nom_centro;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public ArrayList<Casas> getCasas() {
        return casas;
    }

    public void setCasas(ArrayList<Casas> casas) {
        this.casas = casas;
    }
}
