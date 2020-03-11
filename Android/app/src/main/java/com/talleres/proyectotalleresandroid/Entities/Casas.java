package com.talleres.proyectotalleresandroid.Entities;

import java.io.Serializable;

public class Casas implements Serializable {
    private String desc_nombre;
    private int num_casa;

    public String getDesc_nombre() {
        return desc_nombre;
    }

    public void setDesc_nombre(String desc_nombre) {
        this.desc_nombre = desc_nombre;
    }

    public int getNum_casa() {
        return num_casa;
    }

    public void setNum_casa(int num_casa) {
        this.num_casa = num_casa;
    }
}
