package com.example.pruebademoappminimarket.Adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pruebademoappminimarket.R;
import com.example.pruebademoappminimarket.model.ProductoCartView;
import com.example.pruebademoappminimarket.model.Venta;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdaptadorListarVentas
        extends RecyclerView.Adapter <AdaptadorListarVentas.ViewHolderDatos> {


    ArrayList<Venta> ItemsDato;

    public AdaptadorListarVentas(ArrayList<Venta> itemsDato) {

        ItemsDato = itemsDato;
    }

    @NonNull
    @Override
    public AdaptadorListarVentas.ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_file_consultaventa,null,false);

        return new AdaptadorListarVentas.ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorListarVentas.ViewHolderDatos holder, int position) {
        holder.asignarDatos(ItemsDato.get(position));

    }

    @Override
    public int getItemCount() {
        return ItemsDato.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {

        // Variable del Contexto
        Context context;

        TextView tvcodigoventaLisC;
        TextView tvfechaventaLisC;
        TextView tvmontoventaLisC;


        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);

            context = itemView.getContext();

            tvcodigoventaLisC = itemView.findViewById(R.id.codigoventaLisC);
            tvfechaventaLisC = itemView.findViewById(R.id.fechaventaLisC);
            tvmontoventaLisC = itemView.findViewById(R.id.montoventaLisC);
        }

        public void asignarDatos(Venta s) {

            tvcodigoventaLisC.setText("Documento : "+String.valueOf(s.getId_venta()));
            tvfechaventaLisC.setText("Fecha : "+String.valueOf(s.getFechaven()));
            tvmontoventaLisC.setText("Monto : "+Double.toString(s.getTotal()));

        }
    }
}
