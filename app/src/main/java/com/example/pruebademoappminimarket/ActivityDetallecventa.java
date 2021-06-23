package com.example.pruebademoappminimarket;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.pruebademoappminimarket.Adaptador.AdaptadorDetalleCVenta;
import com.example.pruebademoappminimarket.Adaptador.AdaptadorProducto;
import com.example.pruebademoappminimarket.model.CarritoDetalle;
import com.example.pruebademoappminimarket.model.CarritoVenta;
import com.example.pruebademoappminimarket.model.CarritoVentaDetalle;
import com.example.pruebademoappminimarket.model.CategoriaCartView;
import com.example.pruebademoappminimarket.model.ProductoCartView;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class ActivityDetallecventa extends AppCompatActivity {

    ArrayList<CarritoDetalle> ItemsDato;
    CarritoDetalle ItemDato;
    RecyclerView recycler;

    //** declarar la cola de peticiones
    private RequestQueue colaPeticiones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detallecventa);

        //Amarrar el reclicer del carteview
        recycler = findViewById(R.id.recyclerElementosCarritoVenta);
        recycler.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        // Crear array de items categoria
        ItemsDato = new ArrayList<CarritoDetalle>();

        //** Creamos la cola de peticiones
        colaPeticiones = Volley.newRequestQueue(this);
        // CargarListaCategoria();
        CargarListaCarritoVentaVolley();

    }

    private void CargarListaCarritoVentaVolley() {

//** declarar variable de URL de Api
        // String url = "http://172.96.143.27/sistemacitas/validar_login.php";
        String url = "http://172.96.143.27:8090/project/rest/carritodetalle/buscarCliente/1";


        // ItemsDato.clear();
        //** creo el objeto de requerimiento API
        // la solicitud debe ser un JsonArrayRequest y JSONArray ya que retorna un listado de registros
        JsonArrayRequest requerimiento = new JsonArrayRequest(
                Request.Method.POST,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        //** codificar el resultado de la respuesta.
                        try {

                            // Leer Registro por Registro

                            for (int i = 0; i < response.length();i++)
                            {
                                JSONObject objVenta = response.getJSONObject(i);
                                ItemDato = new CarritoDetalle();



                                ItemDato.setId_carritoven(objVenta.getInt("id_carritoven"));

                                //ItemDato.setFechaven(objVenta.getString("fechaven"));
                                ItemDato.setSubtotal(objVenta.getDouble("subtotal"));
                                ItemDato.setIgv(objVenta.getDouble("igv"));
                                ItemDato.setTotal(objVenta.getDouble("total"));
                              //  ItemDato.setTipoentrega(objVenta.getString("tipoentrega"));
                                ItemDato.setId_cliente(objVenta.getInt("id_cliente"));
                                ItemDato.setNombrecliente(objVenta.getString("nombrecliente"));

                                ItemDato.setId_carritovendeta(objVenta.getInt("id_carritovendeta"));
                                ItemDato.setCantidad_detalle(objVenta.getInt("cantidad_detalle"));
                                ItemDato.setPrecio_detalle(objVenta.getDouble("precio_detalle"));
                                ItemDato.setSubtotal_detalle(objVenta.getDouble("subtotal_detalle"));
                                ItemDato.setIgv_detalle(objVenta.getDouble("igv_detalle"));
                                ItemDato.setTotal_detalle(objVenta.getDouble("total_detalle"));
                                ItemDato.setId_producto(objVenta.getInt("id_producto"));
                                ItemDato.setNombreproducto(objVenta.getString("nombreproducto"));
                                ItemDato.setImagen(objVenta.getString("imagen"));



                                ItemsDato.add(ItemDato);
                                /*
                                ItemsDato.add(new CategoriaCartView(objProducto.getInt("id_producto"),
                                        objProducto.getString("descripcion"),
                                        objProducto.getDouble("precio"),
                                        "src\\main\\res\\drawable-v24\\carne.png")
                                );
                               */

                            }

                            AdaptadorDetalleCVenta adapter = new AdaptadorDetalleCVenta(ItemsDato);
                            recycler.setAdapter(adapter);

                        }catch (Exception ex)
                        {
                            Log.e("Error al ejecutar",ex.getMessage());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Información que caso que ecista error de conexión
                        Log.e("Error  al conectar",error.getMessage());

                    }
                }

        );


        colaPeticiones.add(requerimiento);



    }
}