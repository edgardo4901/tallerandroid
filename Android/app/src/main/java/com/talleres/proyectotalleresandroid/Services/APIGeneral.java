package com.talleres.proyectotalleresandroid.Services;
import com.google.gson.JsonObject;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;

public interface APIGeneral {

    @Headers("Content-Type: application/json")
    @GET("{nombreServicio}/{nombreControlador}/{parametro}")
    Call<ResponseBody> metodoGet(@Path("nombreServicio") String nombreServicio, @Path("nombreControlador") String nombreControlador, @Path("parametro") String parametro);

    @GET("{nombreServicio}/{nombreControlador}")
    Call<ResponseBody> metodoGetSinParametros(@Path("nombreServicio") String nombreServicio, @Path("nombreControlador") String nombreControlador);

    @Headers("Content-Type: application/json")
    @DELETE("{nombreServicio}/{nombreControlador}/{parametro}")
    Call<ResponseBody> metodoDelete(@Path("nombreServicio") String nombreServicio, @Path("nombreControlador") String nombreControlador, @Path("parametro") String parametro);

    @Headers("Content-Type: application/json")
    @PUT("{nombreServicio}/{nombreControlador}/{parametro}")
    Call<ResponseBody> metodoPut(@Path("nombreServicio") String nombreServicio, @Path("nombreControlador") String nombreControlador, @Path("parametro") String parametro, @Body JsonObject json, @Header("Authorization") String autorizacion);

    @Headers("Content-Type: application/json")
    @POST("{nombreServicio}/{nombreMetodo}")
    Call<ResponseBody> metodoPost(@Path("nombreServicio") String nombreServicio, @Path("nombreMetodo") String nombreMetodo, @Body JsonObject json);

    @Headers("Content-Type: application/json")
    @POST("{nombreServicio}/{nombreControlador}/{nombreMetodo}")
    Call<ResponseBody> metodoPostQueryParams(@Path("nombreServicio") String nombreServicio, @Path("nombreControlador") String nombreControlador, @Path("nombreMetodo") String nombreMetodo, @Body JsonObject json, @Header("Authorization") String autorizacion);

    @Multipart
    @POST("{nombreServicio}/{nombreControlador}/{nombreMetodo}")
    Call<ResponseBody> motodoFiles(@Path("nombreServicio") String nombreServicio, @Path("nombreControlador") String nombreControlador, @Path("nombreMetodo") String nombreMetodo, @PartMap Map<String, RequestBody> params, @Header("Authorization") String autorizacion, @Part MultipartBody.Part file);

    @Multipart
    @POST("{nombreServicio}/{nombreControlador}/{nombreMetodo}")
    Call<ResponseBody> motodoFiles(@Path("nombreServicio") String nombreServicio, @Path("nombreControlador") String nombreControlador, @Path("nombreMetodo") String nombreMetodo, @Header("Authorization") String autorizacion, @PartMap Map<String, RequestBody> params);
}
