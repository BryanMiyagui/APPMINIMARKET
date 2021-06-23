package com.example.pruebademoappminimarket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.pruebademoappminimarket.Adaptador.AdaptadorProducto;
import com.example.pruebademoappminimarket.model.CarritoDetalle;
import com.example.pruebademoappminimarket.model.CarritoVenta;
import com.example.pruebademoappminimarket.model.ClienteRegistro;
import com.example.pruebademoappminimarket.model.ProductoCartView;
import com.example.pruebademoappminimarket.model.TipoDocumento;
import com.example.pruebademoappminimarket.model.VariableGlobal;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ActivityProductoDetalle extends AppCompatActivity {

    // Definir variables que se envia por parametro
    String codigo_categoria;
    String codigo_producto;

    TextView titulo_activity;
    //** declarar la cola de peticiones
    private RequestQueue colaPeticiones;

    // declarar variable de ventana
    TextView codigoCategoria, codigoProducto, nombreProducto, descripcionProducto, precioProducto, precioMsgProducto, totalPagarDetalle, mensajeError;
    EditText cantidadCompraProducto;
    ImageButton botonIncrementar, botonDecrecer;
    Button botonRegistrar, botonCancelar;
    ImageButton fotoProducto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto_detalle);

        // Datos por parametro del Activity listado producto
        codigo_categoria = getIntent().getStringExtra("codigo_categoria");
        codigo_producto = getIntent().getStringExtra("codigo_producto");

        // Definicion de variables de trabajo
        codigoCategoria = findViewById(R.id.codigocategoriaDetCompra);
        codigoProducto = findViewById(R.id.codigoproductoDetCompra);

        nombreProducto = findViewById(R.id.nombreproductoDetCompra);
        fotoProducto = findViewById(R.id.imagenproductoDetCompra);
        descripcionProducto = findViewById(R.id.descripcionproductoDetCompra);

        precioMsgProducto = findViewById(R.id.preciomensajeDetCompra);
        precioProducto = findViewById(R.id.precioproductoDetCompra);

        botonDecrecer = findViewById(R.id.buttonmenosDetCompra);
        botonIncrementar = findViewById(R.id.buttonmasDetCompra);

        cantidadCompraProducto = findViewById(R.id.cantidadproductoDetCompra);

        totalPagarDetalle = findViewById(R.id.totalpagoDetCompra);

        botonRegistrar  = findViewById(R.id.agregarcompraDetCompra);
        botonCancelar  = findViewById(R.id.cancelarcompraDetCompra);

        mensajeError = findViewById(R.id.mensajeLogin);
        //** Creamos la cola de peticiones
        colaPeticiones = Volley.newRequestQueue(this);

        buscarProductoVolley(codigo_categoria, codigo_producto);

        botonIncrementar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               incrementarCompra(); 
            }

            
        });

        botonDecrecer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               decremetarCompra();
                
            }
        });

        botonRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                registarDetalleCompra();
            }
        });

        botonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CancelarCompra();
            }
        });
        
    }



    private void decremetarCompra() {
        Integer cantidadCompra;
        Double precioProd;
        Double TotalPag;

        mensajeError.setText("");

        precioProd =Double.parseDouble(precioProducto.getText().toString());
        cantidadCompra = Integer.parseInt(cantidadCompraProducto.getText().toString());
        if (cantidadCompra > 1)
        {
            cantidadCompra = cantidadCompra - 1;
            TotalPag = precioProd * cantidadCompra;
            totalPagarDetalle.setText("Total S/. "+String.format( "%.2f", TotalPag));
        }

        cantidadCompraProducto.setText(String.valueOf(cantidadCompra));
    }
    private void incrementarCompra() {

        Integer cantidadCompra;
        Double precioProd;
        Double TotalPag;

        precioProd =Double.parseDouble(precioProducto.getText().toString());

        cantidadCompra = Integer.parseInt(cantidadCompraProducto.getText().toString());

        if (cantidadCompra < 10)
        {
            cantidadCompra = cantidadCompra +1;
            TotalPag = precioProd * cantidadCompra;



            totalPagarDetalle.setText("Total S/. "+String.format( "%.2f", TotalPag));

        }else
        {
            mensajeError.setText("maximo 10 producto a comprar");
        }


        cantidadCompraProducto.setText(String.valueOf(cantidadCompra));

    }

    private void registarDetalleCompra() {

        VariableGlobal mApp = ((VariableGlobal)getApplicationContext());

        // Guardar Información
        CarritoDetalle ItemDato = new CarritoDetalle();


        // Cabezera venta

        ItemDato.setId_carritoven(0);
        ItemDato.setSubtotal(0.0);
        ItemDato.setIgv(0.0);
        ItemDato.setTotal(0.0);
        ItemDato.setId_cliente(mApp.getId_cliente());

        // Detalle de venta

        ItemDato.setId_producto(Integer.parseInt(codigo_producto.toString()));
        ItemDato.setPrecio_detalle(Double.parseDouble(precioProducto.getText().toString()));
        ItemDato.setCantidad_detalle(Integer.parseInt(cantidadCompraProducto.getText().toString()));


/*
    {
        "id_carritoven": 1,

        "id_cliente": 1,

        "id_producto": 1,
        "precio_detalle": 19.5,
        "cantidad_detalle": 4

    }
* */

       registrarDetalleCompraVolley(ItemDato);

        // Cerrar Ventana
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);

    }


    private void CancelarCompra() {
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
    }

    private void buscarProductoVolley(String codigo_categoria, String codigo_producto) {
        //** declarar variable de URL de Api
        // String url = "http://172.96.143.27/sistemacitas/validar_login.php";
        String url = "http://172.96.143.27:8090/project/rest/producto/buscar/"+codigo_producto;


        // ItemsDato.clear();
        //** creo el objeto de requerimiento API
        // la solicitud debe ser un JsonArrayRequest y JSONArray ya que retorna un listado de registros
        //** se conviert la variable MAP en un objecto JSON


        //** creo el objeto de requerimiento API
        JsonObjectRequest requerimiento = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        //** codificar el resultado de la respuesta.
                        try {
                            //Asignacion de respuesta.


                            codigoCategoria.setText(String.valueOf(response.getInt("id_categoria")));
                            codigoProducto.setText(String.valueOf(response.getInt("id_producto")));

                            nombreProducto.setText(response.getString("nombre"));
                            descripcionProducto.setText(response.getString("descripcion"));



                            precioMsgProducto.setText("Precio S/. "+response.getString("precio"));
                            precioProducto.setText(String.valueOf(response.getDouble("precio")));


                            totalPagarDetalle.setText("Total S/. "+response.getString("precio"));

                            Picasso.get()
                                    .load(response.getString("imagen"))
                                    .resize(100, 100)
                                    .centerCrop()
                                    .into(fotoProducto);


                        } catch (Exception ex) {
                            Log.e("Error al ejecutar", ex.getMessage());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Información que caso que ecista error de conexión
                        Log.e("Error  al conectar", error.getMessage());

                    }
                }

        );

        colaPeticiones.add(requerimiento);

    }


    private void registrarDetalleCompraVolley(CarritoDetalle itemDato) {

        String url = "http://172.96.143.27:8090/project/rest/carritodetalle/agregar";


        // Se convierte el objeto en JSON con libreria GSON
        Gson gson = new Gson();
        String JSON = gson.toJson(itemDato);

        // mensajeLogin.setText(JSON);
        //** se conviert la variable MAP en un objecto JSON
        JSONObject parametroJson = null;
        try {
            parametroJson = new JSONObject(JSON);
        } catch (JSONException e) {
            e.printStackTrace();
        }



        System.out.println(String.valueOf(parametroJson));



        //** creo el objeto de requerimiento API
        JsonObjectRequest requerimiento = new JsonObjectRequest(
                Request.Method.POST,
                url,
                parametroJson,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {


                        //** codificar el resultado de la respuesta.
                        try {
                            mensajeError.setText("registro correcto");
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
                        mensajeError.setText(error.toString());
                        Log.e("Error  al conectar",error.getMessage().toString());

                    }
                }

        );

        // Adicionar e- requerimiento a la cola
        colaPeticiones.add(requerimiento);


    }


}