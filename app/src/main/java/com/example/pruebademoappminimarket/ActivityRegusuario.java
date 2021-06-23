package com.example.pruebademoappminimarket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.pruebademoappminimarket.model.ClienteRegistro;
import com.example.pruebademoappminimarket.model.TipoDocumento;
import com.example.pruebademoappminimarket.model.VariableGlobal;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ActivityRegusuario extends AppCompatActivity {

    private EditText nombreNew, apellidoNew, nroducumentoNew, direccionNew, emailNew, usuarioNew, passwordNew;
    private TextView mensajeLogin;
    private Button btnregistrarNew, btncancelarNew;
    private Spinner tipodocNew;

    //** declarar la cola de peticiones

    private RequestQueue colaPeticiones;

    ArrayList<TipoDocumento> ItemsDato;
    TipoDocumento ItemDato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regusuario);

        // direcciona variables trabajo del frame
        nombreNew = findViewById(R.id.nombreNew);
        apellidoNew = findViewById(R.id.apellidoNew);
        nroducumentoNew= findViewById(R.id.nroDocumentoNew);
        direccionNew= findViewById(R.id.direccionNew);
        emailNew = findViewById(R.id.emailNew);

        usuarioNew = findViewById(R.id.usuarioNew);
        passwordNew = findViewById(R.id.passwordNew);

        mensajeLogin = findViewById(R.id.mensajeNew);
        tipodocNew = findViewById(R.id.TipoDocumentoNew);

        btnregistrarNew = findViewById(R.id.buttonGuardarNew);
        btncancelarNew = findViewById(R.id.buttonCancelarNew);

        mensajeLogin.setText("");

        ItemsDato = new ArrayList<>();


        //** Creamos la cola de peticiones
        colaPeticiones = Volley.newRequestQueue(this);
        CargarListaTipoDocumentoVolley();

        btnregistrarNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // validar los datos de ingreso
                mensajeLogin.setText("");
                if (nombreNew.getText().toString().equals(""))
                {
                    mensajeLogin.setText("ingresar el nombre");
                }else {
                    if (apellidoNew.getText().toString().equals("")) {
                        mensajeLogin.setText("ingresar el apellido");
                    } else {
                        if (nroducumentoNew.getText().toString().equals("")) {
                            mensajeLogin.setText("ingresar numero documento");
                        }else {
                            if (direccionNew.getText().toString().equals("")) {
                                mensajeLogin.setText("ingresar dirección");
                            }else {
                                if (emailNew.getText().toString().equals("")) {
                                    mensajeLogin.setText("ingresar email");
                                } else {
                                    if (usuarioNew.getText().toString().equals("")) {
                                        mensajeLogin.setText("ingresar usuario");
                                    } else {

                                        if (passwordNew.getText().toString().equals("")) {
                                            mensajeLogin.setText("ingresar password");
                                        } else {

                                            RegistrarDatosClientesVolley();
                                            Intent i = new Intent(getApplicationContext(), MainActivity.class);
                                            startActivity(i);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        });

        btncancelarNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);

                startActivity(i);
                finish();
            }
        });

    }



    private void CargarListaTipoDocumentoVolley() {


        //Obtener el dato seleccionado
        //  String documentotipo = (String) tipodocNew.getSelectedItem();

        //** declarar variable de URL de Api
        // String url = "http://172.96.143.27/sistemacitas/validar_login.php";
        String url = "http://172.96.143.27:8090/project/rest/tipoDoc/listar";


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
                                JSONObject objDocumento = response.getJSONObject(i);


                                ItemDato = new TipoDocumento();

                                ItemDato.setId_tipo_doc(objDocumento.getInt("id_tipo_doc"));
                                ItemDato.setDescripcion(objDocumento.getString("descripcion"));

                                ItemsDato.add(ItemDato);

                                /*
                                ItemsDato.add(new TipoDocumento(
                                        objDocumento.getInt("id_tipo_doc"),
                                        objDocumento.getString("descripcion")
                                ));
                                */

                            }


                            tipodocNew.setAdapter(new ArrayAdapter<TipoDocumento>(ActivityRegusuario.this,
                                    android.R.layout.simple_spinner_dropdown_item, ItemsDato));

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

    private void RegistrarDatosClientesVolley() {


        String url = "http://172.96.143.27:8090/project/rest/cliente/agregar";

        ClienteRegistro clienteDb = new ClienteRegistro();
        TipoDocumento tipodocumentoDb = new TipoDocumento();

        tipodocumentoDb.setId_tipo_doc(1);

        clienteDb.setNombre(nombreNew.getText().toString());
        clienteDb.setApellidos(apellidoNew.getText().toString());
       // clienteDb.setTelefono("");

        clienteDb.setTipodoc(tipodocumentoDb);
        clienteDb.setNum_doc(nroducumentoNew.getText().toString());

        clienteDb.setDireccion(direccionNew.getText().toString());
        clienteDb.setEmail(emailNew.getText().toString());
        clienteDb.setUsuario(usuarioNew.getText().toString());
        clienteDb.setPassword(passwordNew.getText().toString());

        // Se convierte el objeto en JSON con libreria GSON
        Gson gson = new Gson();
        String JSON = gson.toJson(clienteDb);

        // mensajeLogin.setText(JSON);
        //** se conviert la variable MAP en un objecto JSON
        JSONObject parametroJson = null;
        try {
            parametroJson = new JSONObject(JSON);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // mensajeLogin.setText( parametroJson.getString("nombre"));



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
                            mensajeLogin.setText("registro correcto");
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
                        mensajeLogin.setText(error.toString());
                        Log.e("Error  al conectar",error.getMessage().toString());

                    }
                }

        );

        // Adicionar e- requerimiento a la cola
        colaPeticiones.add(requerimiento);





    }


    public void guardarNew(View view) throws JSONException {
        //Que debe realizar cuando presionen guardar
        String url = "http://172.96.143.27:8090/project/rest/cliente/agregar";

        ClienteRegistro clienteDb = new ClienteRegistro();
        TipoDocumento tipodocumentoDb = new TipoDocumento();

        tipodocumentoDb.setId_tipo_doc(1);


        // validar los datos de ingreso
        mensajeLogin.setText("");
        if (nombreNew.getText().toString().equals(""))
        {
            mensajeLogin.setText("ingresar el nombre");
        }else {
            if (apellidoNew.getText().toString().equals("")) {
                mensajeLogin.setText("ingresar el nombre");
            } else {
                if (emailNew.getText().toString().equals("")) {
                    mensajeLogin.setText("ingresar email");
                } else {
                    if (usuarioNew.getText().toString().equals("")) {
                        mensajeLogin.setText("ingresar usuario");
                    } else {

                        if (passwordNew.getText().toString().equals("")) {
                            mensajeLogin.setText("ingresar password");
                        }else
                        {

                            clienteDb.setNombre(nombreNew.getText().toString());
                            clienteDb.setApellidos(apellidoNew.getText().toString());
                            clienteDb.setTelefono("");
                            clienteDb.setDireccion("");
                            clienteDb.setNum_doc("");
                            clienteDb.setEmail(emailNew.getText().toString());
                            clienteDb.setUsuario(usuarioNew.getText().toString());
                            clienteDb.setPassword(passwordNew.getText().toString());
                            clienteDb.setTipodoc(tipodocumentoDb);
                            // Se convierte el objeto en JSON con libreria GSON
                            Gson gson = new Gson();
                            String JSON = gson.toJson(clienteDb);

                           // mensajeLogin.setText(JSON);
                            //** se conviert la variable MAP en un objecto JSON
                            JSONObject parametroJson = null;
                            try {
                                parametroJson = new JSONObject(JSON);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            // mensajeLogin.setText( parametroJson.getString("nombre"));



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
                                                mensajeLogin.setText("registro correcto");
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
                                            mensajeLogin.setText(error.toString());
                                            Log.e("Error  al conectar",error.getMessage().toString());

                                        }
                                    }

                            );

                            // Adicionar e- requerimiento a la cola
                            colaPeticiones.add(requerimiento);
                        }
                    }
                }
            }
        }
    }




}