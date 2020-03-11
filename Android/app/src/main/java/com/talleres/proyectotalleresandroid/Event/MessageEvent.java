package com.talleres.proyectotalleresandroid.Event;

public class MessageEvent {

    public String mMessage;
    public int mFiltro;
    public int mId;

    public MessageEvent(String message) {
        mMessage = message;
    }
    public MessageEvent(String message,int filtro) {
        mMessage = message;
        mFiltro = filtro;
    }
    public MessageEvent(String message,int filtro,int id) {
        mMessage = message;
        mFiltro = filtro;
        mId = id;
    }

    public String getMessage() {
        return mMessage;
    }
    public int getFiltro() {
        return mFiltro;
    }
    public int getID() {
        return mId;
    }

    //filtro
    //1--eliminar casa
}
