package com.example.pruebademoappminimarket.Adaptador;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.pruebademoappminimarket.ActivityEliminacventa;
import com.example.pruebademoappminimarket.ActivityProducto;
import com.example.pruebademoappminimarket.R;
import com.example.pruebademoappminimarket.model.CarritoDetalle;
import com.example.pruebademoappminimarket.model.CarritoVenta;
import com.example.pruebademoappminimarket.model.CategoriaCartView;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class AdaptadorDetalleCVenta extends RecyclerView.Adapter <AdaptadorDetalleCVenta.ViewHolderDatos> {

    ArrayList<CarritoDetalle> ItemsDato;

    public AdaptadorDetalleCVenta(ArrayList<CarritoDetalle> itemsDato) {

        ItemsDato = itemsDato;
    }

    @NonNull
    @Override
    public AdaptadorDetalleCVenta.ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_file_detallecventa,null,false);



        return new AdaptadorDetalleCVenta.ViewHolderDatos(view);

    }


    @Override
    public void onBindViewHolder(@NonNull AdaptadorDetalleCVenta.ViewHolderDatos holder, int position) {


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
        // Variable del CartView
        TextView dato_idClienteCarrito;
        TextView dato_idVentaCarrito;
        TextView dato_idVentaDetalleCarrito;
        TextView dato_codigoproducto;
        TextView dato_descripcionproducto;
        TextView dato_cantidadproducto;
        TextView dato_precioproducto;
        TextView dato_totalproducto;

        ImageButton dato_imagen;
        Button dato_eliminarcarrito;


        public ViewHolderDatos(@NonNull View itemView) {

            super(itemView);

            context = itemView.getContext();

            dato_idVentaCarrito= itemView.findViewById(R.id.idVentaCarrito);
            dato_idVentaDetalleCarrito = itemView.findViewById(R.id.idVentaDetalleCarrito);
            dato_idClienteCarrito = itemView.findViewById(R.id.idClienteCarrito);
            dato_imagen = itemView.findViewById(R.id.imagenproductoCarrito);
            dato_codigoproducto = itemView.findViewById(R.id.codigoproductoCarrito);
            dato_descripcionproducto = itemView.findViewById(R.id.descripcionproductoCarrito);

            dato_cantidadproducto = itemView.findViewById(R.id.cantidadproductoCarrito);
            dato_precioproducto = itemView.findViewById(R.id.precioproductoCarrito);
            dato_totalproducto = itemView.findViewById(R.id.totalproductoCarrito);

            dato_eliminarcarrito = itemView.findViewById(R.id.eliminardetalleCarrito);



        }

        void setOnClickListener(){

            dato_eliminarcarrito.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            // Variable del CartView

           //---------   eliminarDetalleCompraVolley(ItemDato);

            Intent intent = new Intent(context, ActivityEliminacventa.class);

            intent.putExtra("codigo_carritoventa",dato_idVentaCarrito.getText().toString());
            intent.putExtra("codigo_carritoventadetalle",dato_idVentaDetalleCarrito.getText().toString());

            context.startActivity(intent);


        //    Toast toast = Toast.makeText(context, "Eliminacion de carrito", Toast.LENGTH_SHORT);
        //    toast.show();

            /*
            Intent intent = new Intent(context, ActivityProducto.class);

            intent.putExtra("codigo_categoria",dato_codigo.getText());
            intent.putExtra("dato_titulo",dato_descripcion.getText());

            context.startActivity(intent);

             */
        }



        public void asignarDatos(CarritoDetalle carritoVenta) {

            //  Bitmap obtener_imagen = get_imagen(categoriaCartView.getImagenproducto());

            // BitmapDrawable obtener_imagen = new BitmapDrawable(context.getResources(),
            //         BitmapFactory.decodeFile(categoriaCartView.getImagenproducto()));

            dato_idVentaCarrito.setText(String.valueOf(carritoVenta.getId_carritoven()));
            dato_idVentaDetalleCarrito.setText(String.valueOf(carritoVenta.getId_carritovendeta()));
            dato_codigoproducto.setText(String.valueOf(carritoVenta.getId_producto()));
            dato_descripcionproducto.setText(carritoVenta.getNombreproducto());

            dato_cantidadproducto.setText("Cantidad : " + String.valueOf(carritoVenta.getCantidad_detalle()));
            dato_precioproducto.setText("Precio : S/. " + String.valueOf(carritoVenta.getPrecio_detalle()));
            dato_totalproducto.setText("Total : S/. " + String.valueOf(carritoVenta.getTotal_detalle()));

            Picasso.get()
                    .load(carritoVenta.getImagen())
                    .resize(200, 200)
                    .centerCrop()
                    .into(dato_imagen);

        }


    }
}

