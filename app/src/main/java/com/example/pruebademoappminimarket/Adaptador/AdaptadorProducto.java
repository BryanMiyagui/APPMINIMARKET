package com.example.pruebademoappminimarket.Adaptador;


import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pruebademoappminimarket.ActivityLogin;
import com.example.pruebademoappminimarket.ActivityProducto;
import com.example.pruebademoappminimarket.ActivityProductoDetalle;
import com.example.pruebademoappminimarket.MainActivity;
import com.example.pruebademoappminimarket.R;
import com.example.pruebademoappminimarket.model.CategoriaCartView;
import com.example.pruebademoappminimarket.model.ProductoCartView;
import com.example.pruebademoappminimarket.model.VariableGlobal;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdaptadorProducto
        extends RecyclerView.Adapter <AdaptadorProducto.ViewHolderDatos> {

    ArrayList <ProductoCartView> ItemsDato;

    public AdaptadorProducto(ArrayList<ProductoCartView> itemsDato) {

        ItemsDato = itemsDato;
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_file_producto,null,false);

        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {

        holder.asignarDatos(ItemsDato.get(position));
        holder.setOnClickListener();
    }

    @Override
    public int getItemCount() {
        return ItemsDato.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder implements View.OnClickListener {

        // Variable del Contexto
        Context context;

        TextView dato_codigocategoria;
        TextView dato_codigoproducto;
        TextView dato_desripcion;
        TextView dato_precio;

        ImageButton dato_imagen;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);

            context = itemView.getContext();

            dato_imagen = itemView.findViewById(R.id.imagenproductoLis);
            dato_codigocategoria = itemView.findViewById(R.id.codigocategoriaProductoLis);
            dato_codigoproducto = itemView.findViewById(R.id.codigoproductoLis);
            dato_desripcion = itemView.findViewById(R.id.descripcionproductoLis);
            dato_precio = itemView.findViewById(R.id.precioproductoLis);


        }
        void setOnClickListener(){
            dato_imagen.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            // Definir para leer variable global
            VariableGlobal mApp = ((VariableGlobal)v.getContext().getApplicationContext());

           // VariableGlobal mApp = ((VariableGlobal)getActivity().getApplicationContext());


            //VariableGlobal  mApp = ((VariableGlobal) Application());

            // Verificar el estado del Usuario

            if (mApp.getUsuario().equals("")) {   //solicitar login , activity login
                Intent intent = new Intent(context,ActivityLogin.class);
                context.startActivity(intent);
            }else{
                // Llamar al producto detalle
                Intent intent = new Intent(context, ActivityProductoDetalle.class);

                intent.putExtra("codigo_categoria", dato_codigocategoria.getText());
                intent.putExtra("codigo_producto", dato_codigoproducto.getText());

                context.startActivity(intent);
            }

        }

        public void asignarDatos(ProductoCartView s) {

            dato_codigocategoria.setText(String.valueOf(s.getId_categoria()));
            dato_codigoproducto.setText(String.valueOf(s.getId_producto()));
            dato_desripcion.setText(s.getDescripcion());
            dato_precio.setText(Double.toString(s.getPrecio()));

            Picasso.get()
                    .load(s.getImagenproducto())
                    .resize(100, 100)
                    .centerCrop()
                    .into(dato_imagen);


        }
    }
}


