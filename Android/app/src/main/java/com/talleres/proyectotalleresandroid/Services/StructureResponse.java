package com.talleres.proyectotalleresandroid.Services;

import com.talleres.proyectotalleresandroid.Entities.Meta;


public class StructureResponse<T> {

    private boolean exito;
    private Meta meta;
     private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public boolean isExito() {
        return exito;
    }

    public void setExito(boolean exito) {
        this.exito = exito;
    }
}
