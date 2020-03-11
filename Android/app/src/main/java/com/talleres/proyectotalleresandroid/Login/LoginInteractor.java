package com.talleres.proyectotalleresandroid.Login;

import android.content.Context;

import com.google.gson.JsonObject;
import com.talleres.proyectotalleresandroid.Entities.Meta;
import com.talleres.proyectotalleresandroid.Entities.Usuario;
import com.talleres.proyectotalleresandroid.R;
import com.talleres.proyectotalleresandroid.Services.APIGeneral;
import com.talleres.proyectotalleresandroid.Services.RetrofitClient;
import com.talleres.proyectotalleresandroid.Services.StructureResponse;
import com.talleres.proyectotalleresandroid.Utilerias.DesserealizadorGeneral;
import com.talleres.proyectotalleresandroid.Utilerias.UrlServices;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginInteractor implements LoginProvider {

    private APIGeneral APIService;
    LoginObtainer loginObtainer;
    UrlServices resourceProvider;
    Context context;


    public LoginInteractor(LoginObtainer LoginObtainer,
                                  UrlServices resourceProvider,Context context) {
        this.loginObtainer = LoginObtainer;
        this.resourceProvider=resourceProvider;
        this.context = context;
    }
    @Override
    public void login(JsonObject data) {
        try {
            String url=resourceProvider.urlApiTalleres;

            APIService = RetrofitClient.getAPIService(url);
            APIService.metodoPost("usuarios","login",data).enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                    if(response.isSuccessful()) {
                        try {
                            String respuesta = response.body().string();
                            StructureResponse<Usuario> datos = DesserealizadorGeneral.jsonToUsuario(respuesta);
                            if(datos.isExito()) {
                                loginObtainer.responseLogin(datos.getData());
                            }
                            else
                            {
                                loginObtainer.mensajeError(datos.getMeta().getUserMessage());
                            }
                        } catch (IOException e) {
                            loginObtainer.mensajeError(context.getString(R.string.str_errorgeneral));
                            e.printStackTrace();
                        }
                    }
                    else
                    {
                        try {
                            String respuesta = response.errorBody().string();
                            Meta oMeta = DesserealizadorGeneral.jsonToMetaError(respuesta);
                            loginObtainer.mensajeError(oMeta.getUserMessage());

                        } catch (IOException e) {
                            loginObtainer.mensajeError(context.getString(R.string.str_errorgeneral));
                            e.printStackTrace();
                        }
                    }
                }
                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    loginObtainer.mensajeError(context.getString(R.string.str_errorgeneral));
                }
            });
        }
        catch (Exception ex){
            loginObtainer.mensajeError(context.getString(R.string.str_errorgeneral));
        }
    }
}
