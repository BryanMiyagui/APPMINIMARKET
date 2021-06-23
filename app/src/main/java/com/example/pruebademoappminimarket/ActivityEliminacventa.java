package com.example.pruebademoappminimarket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.pruebademoappminimarket.model.CarritoDetalle;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class ActivityEliminacventa extends AppCompatActivity {
    // Definir variables que se envia por parametro
    String codigo_carritoventa;
    String codigo_carritoventadetalle;
    //** declarar la cola de peticiones
    private RequestQueue colaPeticiones;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminacventa);

        codigo_carritoventa = getIntent().getStringExtra("codigo_carritoventa");
        codigo_carritoventadetalle = getIntent().getStringExtra("codigo_carritoventadetalle");
        colaPeticiones = Volley.newRequestQueue(this);

        // Guardar Información
        CarritoDetalle ItemDato = new CarritoDetalle();

        // Cabezera venta

        ItemDato.setId_carritoven(Integer.parseInt(codigo_carritoventa));

        ItemDato.setId_carritovendeta(Integer.parseInt (codigo_carritoventadetalle));
        //ItemDato.setId_cliente(Integer.parseInt(dato_idClienteCarrito.getText().toString()));



        eliminarDetalleCompraVolley(ItemDato);

        // Cerrar Ventana
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);

    }

    private void eliminarDetalleCompraVolley(CarritoDetalle itemDato) {


        String url = "http://172.96.143.27:8090/project/rest/carritodetalle/borrar/"+itemDato.getId_carritoven()+"/"+itemDato.getId_carritovendeta();
        // String url = "http://localhost:8090/project/rest/carritodetalle/borrar";




        //** creo el objeto de requerimiento API
        JsonObjectRequest requerimiento = new JsonObjectRequest(
                Request.Method.DELETE,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {


                        //** codificar el resultado de la respuesta.
                        try {

                            //Toast toast = Toast.makeText(getApplicationContext(), "Eliminación correcta", Toast.LENGTH_SHORT);
                            //toast.show();

                            //System.out.println("Eliminación correcta");
                            //mensajeError.setText("registro correcto");
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

        // Adicionar e- requerimiento a la cola
        colaPeticiones.add(requerimiento);



    }
}