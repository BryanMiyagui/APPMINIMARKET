package com.example.pruebademoappminimarket;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.pruebademoappminimarket.model.VariableGlobal;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ActivityLogin extends AppCompatActivity {


    private EditText usuarioLogin, claveLogin;
    private TextView mensajeLogin;
    private Button buttonLogin, buttonRegistrar;

    //** declarar la cola de peticiones
    private RequestQueue colaPeticiones;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        // Variable Global


      //  mApp = ((MainActivity)getApplicationContext());
       // String nombre = mApp.mApp.getNombre();
     //   MainActivity  mApp = ((MainActivity)getApplicationContext());

        // direcciona variables trabajo del frame
        usuarioLogin = findViewById(R.id.usuarioLogin);
        claveLogin = findViewById(R.id.claveLogin);
        mensajeLogin = findViewById(R.id.mensajeLogin);


        mensajeLogin.setText("");
        buttonLogin = findViewById(R.id.buttonLogin);
        buttonRegistrar = findViewById(R.id.buttonRegistrarLogin);

        //** Creamos la cola de peticiones
        colaPeticiones = Volley.newRequestQueue(this);

        buttonRegistrar.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), ActivityRegusuario.class);


                startActivity(intent);
            }
        }));

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //** declarar variable de URL de Api
                // String url = "http://172.96.143.27/sistemacitas/validar_login.php";
                String url = "http://172.96.143.27:8090/project/rest/cliente/validarLogin";

                //** creamos el variable para guarda datos tipo JSON
                Map<String, String> objjson = new HashMap<>();
                objjson.put("usuario", usuarioLogin.getText().toString());
                objjson.put("password", claveLogin.getText().toString());


                //** se conviert la variable MAP en un objecto JSON
                JSONObject parametroJson = new JSONObject(objjson);

                //** creo el objeto de requerimiento API
                JsonObjectRequest requerimiento = new JsonObjectRequest(
                        Request.Method.POST   ,
                        url   ,
                        parametroJson  ,

                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {

                                //** codificar el resultado de la respuesta.
                                try {

                                    //Verificar respuesta de logeo
                                    if (response.getString("rpta").equals("true")) {
                                        // Toast.makeText(getApplicationContext(), response.getString("mensaje"), Toast.LENGTH_SHORT).show();

                                        // Definir variable global para Actualizar
                                        // mApp = ((VariableGlobal)getApplication());
                                        VariableGlobal  mApp = ((VariableGlobal)getApplicationContext());

                                        // Acualizo la variable global con los datos del cliente logeado
                                        mApp.setUsuario(response.getString("usuario"));
                                        mApp.setId_cliente(Integer.parseInt(response.getString("id_cliente")));
                                        mApp.setNombre(response.getString("nombre"));
                                        mApp.setApellidos(response.getString("apellidos"));

                                        Intent i = new Intent(getApplicationContext(), MainActivity.class);

                                        startActivity(i);
                                        finish();




                                        //mApp
                                        mensajeLogin.setText("Ingreso correcto");
                                    } else {
                                        // Toast.makeText(getApplicationContext(), response.getString("mensaje"), Toast.LENGTH_SHORT).show();
                                        mensajeLogin.setText(response.getString("mensaje"));
                                    }

                                } catch (Exception ex) {
                                    Log.e("Error al ejecutar", ex.getMessage());
                                }
                            }
                        }   ,

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
        });


    }

}