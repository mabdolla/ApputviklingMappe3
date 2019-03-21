package com.example.mohammadabdolla.s309856mappe3.ListAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.mohammadabdolla.s309856mappe3.R;
import com.example.mohammadabdolla.s309856mappe3.model.Reservasjon;

import java.util.List;


public class ReservasjonListAdapter extends ArrayAdapter<Reservasjon> {

    private Context context;
    private int resource;
    private List<Reservasjon> reservasjonList;

    public ReservasjonListAdapter(Context context, int resource, List<Reservasjon> reservasjonList) {

        super(context, resource, reservasjonList);
        this.reservasjonList = reservasjonList;
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {


        String romnr = getItem(position).getRomnr();
        String dato = getItem(position).getDato();
        String tid = getItem(position).getTid();
        final Reservasjon reservasjon = new Reservasjon(romnr, dato, tid);

        ViewHolder holder;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(resource, parent, false);
            holder = new ViewHolder();
            holder.Romnr = convertView.findViewById(R.id.reservasjon_romnr);
            holder.Dato = convertView.findViewById(R.id.reservasjon_dato);
            holder.Tid = convertView.findViewById(R.id.reservasjon_tid);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.Romnr.setText("Rom nummer: " + romnr);
        holder.Dato.setText("Dato : " + dato);
        holder.Tid.setText("Tid : " + tid);
        return convertView;
    }

    private static class ViewHolder {
        TextView Romnr;
        TextView Dato;
        TextView Tid;
    }
}

