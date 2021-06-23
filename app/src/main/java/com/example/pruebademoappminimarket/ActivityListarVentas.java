package com.example.pruebademoappminimarket;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;


import com.example.pruebademoappminimarket.Adaptador.AdaptadorListarVentas;

import com.example.pruebademoappminimarket.model.VariableGlobal;
import com.example.pruebademoappminimarket.model.Venta;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ActivityListarVentas extends AppCompatActivity {

    //** declarar la cola de peticiones
    private RequestQueue colaPeticiones;
    ArrayList<Venta> ItemsDato;
    Venta ItemDato;
    RecyclerView recycler;

    String fechaInicio, fechaFinal;
    Button btncancelar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_ventas);

        btncancelar = findViewById(R.id.CancelarListarVentas);

        fechaInicio = getIntent().getStringExtra("fecha_inicio");
        fechaFinal = getIntent().getStringExtra("fecha_final");



        //Amarrar el reclicer del carteview
        recycler = findViewById(R.id.recyclerElementosListarVentas);
        recycler.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        // Crear array de items categoria
        ItemsDato = new ArrayList<Venta>();

        //** Creamos la cola de peticiones
        colaPeticiones = Volley.newRequestQueue(this);
        // CargarListaCategoria();
        CargarListaVentasVolley();




        // Boton Cancelar
        btncancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
                finish();

            }
        });
    }

    private void CargarListaVentasVolley() {

        //** declarar variable de URL de Api
        // String url = "http://172.96.143.27/sistemacitas/validar_login.php";
    //    String url = "http://172.96.143.27:8090/project/rest/venta/buscarClienteFecha";


        String clienteIdConectado;

        // Variable Global

        VariableGlobal mApp = ((VariableGlobal)getApplication().getApplicationContext());
        if (mApp.getUsuario().isEmpty())
        {
            clienteIdConectado = "0";
        }else
        {
            clienteIdConectado = String.valueOf(mApp.getId_cliente());
        }

        String url = "http://172.96.143.27:8090/project/rest/venta/buscarClienteFecha/"+clienteIdConectado+"/"+fechaInicio+"/"+fechaFinal;

        //** creamos el variable para guarda datos tipo JSON
        Map<String, String> objjson = new HashMap<>();
        objjson.put("id_cliente", clienteIdConectado);
        objjson.put("fechaven_inicio", fechaInicio);
        objjson.put("fechaven_final", fechaFinal);


        //** se conviert la variable MAP en un objecto JSON
        JSONObject parametroJson = new JSONObject(objjson);

        //JSONArray parametroJson = new JSONArray();
        System.out.println(String.valueOf(parametroJson));
        /*
        JSONObject body = new JSONObject();
        // Your code, e.g. body.put(key, value);
        CustomJsonArrayRequest req = new CustomJsonArrayRequest(Request.Method.POST, url, body, success, error);

         */

        // ItemsDato.clear();
        //** creo el objeto de requerimiento API
        // la solicitud debe ser un JsonArrayRequest y JSONArray ya que retorna un listado de registros
        JsonArrayRequest requerimiento = new JsonArrayRequest(
                Request.Method.GET,
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
                                JSONObject objProducto = response.getJSONObject(i);
                                ItemDato = new Venta();

                                ItemDato.setId_venta(objProducto.getInt("id_venta"));

                                ItemDato.setFechaven(objProducto.getString("fechaven"));
                                ItemDato.setTotal(objProducto.getDouble("total"));



                                ItemsDato.add(ItemDato);


                            }

                            AdaptadorListarVentas adapter = new AdaptadorListarVentas(ItemsDato);
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