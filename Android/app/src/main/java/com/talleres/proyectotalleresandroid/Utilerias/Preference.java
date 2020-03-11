package com.talleres.proyectotalleresandroid.Utilerias;

import android.content.Context;
import android.content.SharedPreferences;

public class Preference {

	private final String SHARED_PREFS_FILE = "HMPrefs";

    private final String Id = "idusuariotalleres";
    private final String Nombre = "nombreusuariotalleres";
	
	private Context mContext;

	public Preference(Context context){
		mContext = context;
	}

	private SharedPreferences getSettings(){
		return mContext.getSharedPreferences(SHARED_PREFS_FILE, 0);
	}
    public String getId() {
        return getSettings().getString(Id, null);
    }
    public void setId(String prId){
        SharedPreferences.Editor editor = getSettings().edit();
        editor.putString(Id, prId);
        editor.commit();
    }
    public String getNombre() {
        return getSettings().getString(Nombre, null);
    }
    public void setNombre(String prNombre){
        SharedPreferences.Editor editor = getSettings().edit();
        editor.putString(Nombre, prNombre);
        editor.commit();
    }
}
