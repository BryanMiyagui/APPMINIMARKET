package com.example.pruebademoappminimarket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.pruebademoappminimarket.model.CarritoDetalle;
import com.example.pruebademoappminimarket.model.VariableGlobal;
import com.example.pruebademoappminimarket.model.Venta;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class ActivityReClienteEnvio extends AppCompatActivity {


    private EditText direccionRventa, telefonoRventa;
    private TextView totalpagarRventa, mensajeRventa;
    private Spinner formapago;
    private RadioButton  opciontiendaRventa, opcioncasaRventa;
    private Button buttonGuardarRventa, buttonCancelarRventa;
    private String formapagoSeleccion = "";

    //** declarar la cola de peticiones
    private RequestQueue colaPeticiones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_re_cliente_envio);

        opciontiendaRventa = findViewById(R.id.opciontiendaRventa);
        opcioncasaRventa = findViewById(R.id.opcioncasaRventa);
        direccionRventa = findViewById(R.id.direccionRventa);
        telefonoRventa = findViewById(R.id.telefonoRventa);
        mensajeRventa = findViewById(R.id.mensajeRventa);
        totalpagarRventa = findViewById(R.id.totalpagarRventa);

        buttonGuardarRventa = findViewById(R.id.buttonGuardarRventa);
        buttonCancelarRventa = findViewById(R.id.buttonCancelarRventa);


        formapago = findViewById(R.id.tipopagoRventa);
        String [] opcionestarjeta={"Visa","Mastercard","Dinners","American Express"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, opcionestarjeta);
        formapago.setAdapter(adapter);


        totalpagarRventa.setText(getIntent().getStringExtra("total_pagar"));
        opcioncasaRventa.setChecked(true);

        //** Creamos la cola de peticiones
        colaPeticiones = Volley.newRequestQueue(this);

        ConsultarDatosClienteVolley();

        // Check recoger en tienda mostrar Google Map
        opciontiendaRventa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent mapIntent = new Intent(Intent.ACTION_VIEW); // utilizar un recurso del dispositivo

                mapIntent.setData(Uri.parse("geo:0,0?q=-12.067172, -77.035887(IDAT-LIMA CENTRO)")); //(sobrenombre)
                mapIntent.setPackage("com.google.android.apps.maps"); // nombre de la app

                // validar que esté instalada la appp
                if(mapIntent.resolveActivity(getPackageManager())!=null){
                    startActivity(mapIntent);
                }else
                {
                    mensajeRventa.setText("Movil no cuenta con Google Map");
                }
            }
        });

        // Boton guardar informacion
        buttonGuardarRventa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String infoCorrecta = "";

                 // para tienda
                 if (opciontiendaRventa.isChecked())
                 {
                     if (telefonoRventa.getText().toString().equals(""))
                     {
                         mensajeRventa.setText("Registrar telefono");
                         infoCorrecta = "falta";
                     }
                 }else {
                     // para casa
                     if (direccionRventa.getText().toString().equals("")) {
                         mensajeRventa.setText("Registrar direccion");
                         infoCorrecta = "falta";
                     } else {
                         if (telefonoRventa.getText().toString().equals("")) {
                             mensajeRventa.setText("Registrar telefono");
                             infoCorrecta = "falta";
                         }
                     }
                 }

                if (infoCorrecta.equals(""))
                {
                        RegistrarVentaCarritoVolley();
                        // Cerrar Ventana
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        finish();
                        startActivity(i);
                }


            }
        });

        // Boton cancelar

        buttonCancelarRventa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), MainActivity.class);

                startActivity(i);
                finish();

            }
        });

        // Boton seleccionar forma de pago


    }

    private void ConsultarDatosClienteVolley() {

        VariableGlobal LmApp = ((VariableGlobal)getApplication().getApplicationContext());
        //** declarar variable de URL de Api
        // String url = "http://172.96.143.27/sistemacitas/validar_login.php";
        //String url = "http://172.96.143.27:8090/project/rest/cliente/buscar/"+mApp.getId_cliente();

        String url = "http://172.96.143.27:8090/project/rest/cliente/buscar/"+String.valueOf(LmApp.getId_cliente());

        // ItemsDato.clear();
        //** creo el objeto de requerimiento API
        // la solicitud debe ser un JsonRequest y JSONObject ya que retorna un solo registro
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

                            //Actualizo dato de la pantalla

                            telefonoRventa.setText(response.getString("telefono"));
                            direccionRventa.setText(response.getString("direccion"));


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

    private void RegistrarVentaCarritoVolley() {

        VariableGlobal LmApp = ((VariableGlobal)getApplication().getApplicationContext());
        String url = "http://172.96.143.27:8090/project/rest/venta/agregarVentacarrito/"+String.valueOf(LmApp.getId_cliente());
       // String url = "http://192.168.0.6:8090/project/rest/venta/agregarVentacarrito/"+String.valueOf(LmApp.getId_cliente());

        System.out.println(url);

        Venta itemVenta = new Venta();

        if (opciontiendaRventa.isChecked())
        {
            itemVenta.setTipoentrega("Entrega tienda");
        }else
        {
            itemVenta.setTipoentrega("Entrega delivery");
        }

        itemVenta.setDireccionentrega(direccionRventa.getText().toString());
        itemVenta.setTipopago(formapago.getSelectedItem().toString());
        itemVenta.setTelefonoentrega(telefonoRventa.getText().toString());

        //System.out.println(ItemVenta.getTipoentrega()+ " - " + ItemVenta.getDireccionentrega() + " - " + ItemVenta.getTipopago());


        // Se convierte el objeto en JSON con libreria GSON
        Gson gson = new Gson();
        String JSON = gson.toJson(itemVenta);

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
                          //  mensajeRventa.setText("registro correcto");
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
                       // mensajeRventa.setText(error.toString());
                        Log.e("Error  al conectar",error.getMessage().toString());

                    }
                }

        );

        // Adicionar e- requerimiento a la cola
        colaPeticiones.add(requerimiento);

    }
}