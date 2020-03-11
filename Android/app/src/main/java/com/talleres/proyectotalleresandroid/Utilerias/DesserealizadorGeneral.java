package com.talleres.proyectotalleresandroid.Utilerias;

import com.talleres.proyectotalleresandroid.Entities.Casas;
import com.talleres.proyectotalleresandroid.Entities.Meta;
import com.talleres.proyectotalleresandroid.Entities.Perfil;
import com.talleres.proyectotalleresandroid.Entities.Rol;
import com.talleres.proyectotalleresandroid.Entities.Usuario;
import com.talleres.proyectotalleresandroid.Services.StructureResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class DesserealizadorGeneral {
    public static StructureResponse<Usuario> jsonToUsuario(String response)
    {
        StructureResponse<Usuario> oRespuesta = new StructureResponse<Usuario>();
        try {
            Usuario oUsuario = new Usuario();
            Rol oRol = new Rol();
            Perfil oPerfil = new Perfil();
            ArrayList<Casas> listaCasas = new ArrayList<Casas>();
            JSONObject mJsonObject = new JSONObject(response);
            JSONObject meta = mJsonObject.getJSONObject("meta");
            boolean exito = mJsonObject.getBoolean("exito");
            Meta oMeta = jsonToMeta(meta);
            if(exito) {
                JSONObject data = mJsonObject.getJSONObject("data");
                if (data != null && data.length() > 0) {
                    JSONObject jrol = data.getJSONObject("rol");
                    JSONObject jperfil = data.getJSONObject("perfil");
                    JSONArray jcasas = data.getJSONArray("casas");

                    oUsuario.setId(data.getString("id"));
                    oUsuario.setNum_empleado(data.getInt("num_empleado"));
                    oUsuario.setNom_empleado(data.getString("nom_empleado"));
                    oUsuario.setDes_apellidoPaterno(data.getString("des_apellidoPaterno"));
                    oUsuario.setDes_apellidoMaterno(data.getString("des_apellidoMaterno"));
                    oUsuario.setNom_empresa(data.getString("nom_empresa"));
                    oUsuario.setNom_centro(data.getString("nom_centro"));
                    if(jrol != null)
                    {
                        oRol.setId(jrol.getString("id"));
                        oRol.setNom_rol(jrol.getString("nom_rol"));
                    }
                    if(jperfil != null)
                    {
                        oPerfil.setDes_correoPersonal(jperfil.getString("des_correoPersonal"));
                        oPerfil.setDes_telefonoPersonal(jperfil.getString("des_telefonoPersonal"));
                    }
                    for (int i = 0; i < jcasas.length(); i++) {
                        JSONObject mJsonObjectProperty = jcasas.getJSONObject(i);
                        Casas oCasa = new Casas();
                        oCasa.setDesc_nombre(mJsonObjectProperty.getString("desc_nombre"));
                        oCasa.setNum_casa(mJsonObjectProperty.getInt("num_casa"));
                        listaCasas.add(oCasa);
                    }
                    oUsuario.setPerfil(oPerfil);
                    oUsuario.setRol(oRol);
                    oUsuario.setCasas(listaCasas);
                }
            }
            oRespuesta.setData(oUsuario);
            oRespuesta.setMeta(oMeta);
            oRespuesta.setExito(exito);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return oRespuesta;

    }
    public static StructureResponse<ArrayList<Casas>> jsonToCasas(String response)
    {
        StructureResponse<ArrayList<Casas>> oRespuesta = new StructureResponse<>();
        try {
            ArrayList<Casas> listaCasas = new ArrayList<Casas>();
            JSONObject mJsonObject = new JSONObject(response);
            JSONObject meta = mJsonObject.getJSONObject("meta");
            boolean exito = mJsonObject.getBoolean("exito");
            Meta oMeta = jsonToMeta(meta);
            if(exito) {
                JSONArray data = mJsonObject.getJSONArray("data");
                if (data != null && data.length() > 0) {
                    for (int i = 0; i < data.length(); i++) {
                        JSONObject mJsonObjectProperty = data.getJSONObject(i);
                        Casas oCasa = new Casas();
                        oCasa.setDesc_nombre(mJsonObjectProperty.getString("desc_nombre"));
                        oCasa.setNum_casa(mJsonObjectProperty.getInt("num_casa"));
                        listaCasas.add(oCasa);
                    }
                }
            }
            oRespuesta.setData(listaCasas);
            oRespuesta.setMeta(oMeta);
            oRespuesta.setExito(exito);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return oRespuesta;

    }
    public static Meta jsonToMeta(JSONObject meta)
    {
        Meta ometa = new Meta();
        if(meta != null)
        {
            try {
                ometa.setCount(meta.getInt("count"));
                ometa.setStatus(meta.getString("status"));
                ometa.setUserMessage(meta.getString("mensaje"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return ometa;
    }
    public static StructureResponse<Object> jsonToMeta(String response)
    {
        StructureResponse<Object> oRespuesta = new StructureResponse<Object>();
        try {
            JSONObject mJsonObject = new JSONObject(response);
            JSONObject meta = mJsonObject.getJSONObject("meta");
            boolean exito = mJsonObject.getBoolean("exito");
            Meta oMeta = jsonToMeta(meta);
            oRespuesta.setExito(exito);
            oRespuesta.setMeta(oMeta);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return oRespuesta;
    }
    public static Meta jsonToMetaError(String response) {
        Meta ometa = new Meta();
        try {
            JSONObject mJsonObject = new JSONObject(response);
            JSONObject meta = mJsonObject.getJSONObject("meta");
            if (meta != null) {
                try {
                    ometa.setCount(meta.getInt("count"));
                    ometa.setStatus(meta.getString("status"));
                    JSONObject error = meta.getJSONObject("error");
                    if(error != null)
                    {
                        ometa.setUserMessage(error.getString("userMessage"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return ometa;
    }
}
