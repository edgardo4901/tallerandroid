package com.talleres.proyectotalleresandroid.Adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.talleres.proyectotalleresandroid.Entities.Casas;
import com.talleres.proyectotalleresandroid.Event.MessageEvent;
import com.talleres.proyectotalleresandroid.R;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class CasasAdapter extends ArrayAdapter<Casas> {
    Context context;
    List<Casas> data = new ArrayList<>();

    public CasasAdapter(Context context, List<Casas> data) {
        super(context, R.layout.item_layaut_casas, data);
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View v = inflater.inflate(R.layout.item_layaut_casas, parent, false);

        TextView txtNombreCasa = v.findViewById(R.id.txtNombreCasa);
        TextView txtNumeroCasa = v.findViewById(R.id.txtNumeroCasa);
        ImageView imgEliminar = v.findViewById(R.id.imgEliminar);

        txtNombreCasa.setText(data.get(position).getDesc_nombre());
        txtNumeroCasa.setText("Numero: " + data.get(position).getNum_casa());
        imgEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new MessageEvent("", 1, position));
            }
        });

        return v;
    }
}
