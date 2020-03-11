package com.talleres.proyectotalleresandroid.Casas;

import android.content.Context;

import com.google.gson.JsonObject;
import com.talleres.proyectotalleresandroid.Entities.Casas;
import com.talleres.proyectotalleresandroid.Entities.Meta;
import com.talleres.proyectotalleresandroid.R;
import com.talleres.proyectotalleresandroid.Services.RetrofitClient;
import com.talleres.proyectotalleresandroid.Services.StructureResponse;
import com.talleres.proyectotalleresandroid.Utilerias.DesserealizadorGeneral;
import com.talleres.proyectotalleresandroid.Utilerias.Preference;
import com.talleres.proyectotalleresandroid.Utilerias.UrlServices;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CasasInteractor implements CasasInterface.CasasProvider {

    private com.talleres.proyectotalleresandroid.Services.APIGeneral APIGeneral;
    CasasInterface.CasasObtainer listadoCasasObtainer;
    UrlServices resourceProvider;
    Context context;

    public CasasInteractor(CasasPresenter listadoCasasPresenter,
                           UrlServices resourceProvider, Context context) {
        this.listadoCasasObtainer = listadoCasasPresenter;
        this.resourceProvider=resourceProvider;
        this.context = context;
    }

    @Override
    public void consultarCasas() {
        try {
            String url=resourceProvider.urlApiTalleres;
            final Preference oPreference = new Preference(context);
            APIGeneral = RetrofitClient.getAPIService(url);
            APIGeneral.metodoGet("usuarios","consultarCasasUsuario",oPreference.getId()).enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                    if(response.isSuccessful()) {
                        try {
                            String respuesta = response.body().string();
                            StructureResponse<ArrayList<Casas>> datos = DesserealizadorGeneral.jsonToCasas(respuesta);
                            if(datos.isExito())
                            {
                                listadoCasasObtainer.respuestaLista(datos.getData());
                            }
                            else
                            {
                                listadoCasasObtainer.mensajeRespuesta(datos.getMeta().getUserMessage(),false);
                            }
                        } catch (IOException e) {
                            listadoCasasObtainer.mensajeRespuesta(context.getString(R.string.str_errorgeneral),false);
                            e.printStackTrace();
                        }
                    }
                    else
                    {
                        try {
                            String respuesta = response.errorBody().string();
                            Meta oMeta = DesserealizadorGeneral.jsonToMetaError(respuesta);
                            listadoCasasObtainer.mensajeRespuesta(oMeta.getUserMessage(),false);

                        } catch (IOException e) {
                            listadoCasasObtainer.mensajeRespuesta(context.getString(R.string.str_errorgeneral),false);
                            e.printStackTrace();
                        }
                    }
                }
                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    listadoCasasObtainer.mensajeRespuesta(context.getString(R.string.str_errorgeneral),false);
                }
            });
        }
        catch (Exception ex){
            listadoCasasObtainer.mensajeRespuesta(context.getString(R.string.str_errorgeneral),false);
        }
    }
    @Override
    public void guardarCasa(JsonObject data) {
        try {
            String url=resourceProvider.urlApiTalleres;
            APIGeneral = RetrofitClient.getAPIService(url);
            APIGeneral.metodoPost("usuarios","guardarCasa",data).enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                    if(response.isSuccessful()) {
                        try {
                            String respuesta = response.body().string();
                            StructureResponse<ArrayList<Casas>> datos = DesserealizadorGeneral.jsonToCasas(respuesta);
                            if(datos.isExito())
                            {
                                listadoCasasObtainer.respuestaLista(datos.getData());
                            }
                            else
                            {
                                listadoCasasObtainer.mensajeRespuesta(datos.getMeta().getUserMessage(),false);
                            }
                        } catch (IOException e) {
                            listadoCasasObtainer.mensajeRespuesta(context.getString(R.string.str_errorgeneral),false);
                            e.printStackTrace();
                        }
                    }
                    else
                    {
                        try {
                            String respuesta = response.errorBody().string();
                            Meta oMeta = DesserealizadorGeneral.jsonToMetaError(respuesta);
                            listadoCasasObtainer.mensajeRespuesta(oMeta.getUserMessage(),false);

                        } catch (IOException e) {
                            listadoCasasObtainer.mensajeRespuesta(context.getString(R.string.str_errorgeneral),false);
                            e.printStackTrace();
                        }
                    }
                }
                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    listadoCasasObtainer.mensajeRespuesta(context.getString(R.string.str_errorgeneral),false);
                }
            });
        }
        catch (Exception ex){
            listadoCasasObtainer.mensajeRespuesta(context.getString(R.string.str_errorgeneral),false);
        }
    }
    @Override
    public void eliminarCasa(JsonObject data) {
        try {
            String url=resourceProvider.urlApiTalleres;
            APIGeneral = RetrofitClient.getAPIService(url);
            APIGeneral.metodoPost("usuarios","eliminarCasa",data).enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                    if(response.isSuccessful()) {
                        try {
                            String respuesta = response.body().string();
                            StructureResponse<ArrayList<Casas>> datos = DesserealizadorGeneral.jsonToCasas(respuesta);
                            if(datos.isExito())
                            {
                                listadoCasasObtainer.respuestaLista(datos.getData());
                            }
                            else
                            {
                                listadoCasasObtainer.mensajeRespuesta(datos.getMeta().getUserMessage(),false);
                            }
                        } catch (IOException e) {
                            listadoCasasObtainer.mensajeRespuesta(context.getString(R.string.str_errorgeneral),false);
                            e.printStackTrace();
                        }
                    }
                    else
                    {
                        try {
                            String respuesta = response.errorBody().string();
                            Meta oMeta = DesserealizadorGeneral.jsonToMetaError(respuesta);
                            listadoCasasObtainer.mensajeRespuesta(oMeta.getUserMessage(),false);

                        } catch (IOException e) {
                            listadoCasasObtainer.mensajeRespuesta(context.getString(R.string.str_errorgeneral),false);
                            e.printStackTrace();
                        }
                    }
                }
                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    listadoCasasObtainer.mensajeRespuesta(context.getString(R.string.str_errorgeneral),false);
                }
            });
        }
        catch (Exception ex){
            listadoCasasObtainer.mensajeRespuesta(context.getString(R.string.str_errorgeneral),false);
        }
    }
}
