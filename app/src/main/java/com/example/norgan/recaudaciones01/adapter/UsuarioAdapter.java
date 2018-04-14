package com.example.norgan.recaudaciones01.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.norgan.recaudaciones01.R;
import com.example.norgan.recaudaciones01.entidades.Usuario;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by norgan on 14/04/2018.
 */

public class UsuarioAdapter extends RecyclerView.Adapter<UsuarioAdapter.UsuarioHolder> {


    List<Usuario> listaUsuarios;

    public UsuarioAdapter(ArrayList<Usuario> listaUsuarios) {

        this.listaUsuarios=listaUsuarios;
    }


    @Override
    public UsuarioHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.recyusuario,parent,false);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        vista.setLayoutParams(layoutParams);

        return new UsuarioHolder(vista);
    }

    @Override
    public void onBindViewHolder(UsuarioHolder holder, int position) {
        holder.tvId.setText(listaUsuarios.get(position).getDocumento().toString());
        holder.tvMonto.setText(listaUsuarios.get(position).getMonto().toString());
        holder.tvOrigen.setText(listaUsuarios.get(position).getOrigen().toString());
        holder.tvLugar.setText(listaUsuarios.get(position).getLugar().toString());

    }

    @Override
    public int getItemCount() {

        return listaUsuarios.size();

    }

    public class UsuarioHolder extends RecyclerView.ViewHolder {

        TextView tvId,tvMonto,tvOrigen,tvLugar;




        public UsuarioHolder(View itemView) {
            super(itemView);

            tvId = (TextView)itemView.findViewById(R.id.tvId);
            tvMonto = (TextView)itemView.findViewById(R.id.tvMonto);
            tvOrigen = (TextView)itemView.findViewById(R.id.tvOrigen);
            tvLugar = (TextView)itemView.findViewById(R.id.tvLugar);





        }
    }
}
