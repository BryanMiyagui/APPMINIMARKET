package com.example.pruebademoappminimarket;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.pruebademoappminimarket.Adaptador.AdaptadorCategoria;
import com.example.pruebademoappminimarket.model.CategoriaCartView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;


public class CategoriaFragment extends Fragment {



    ArrayList<CategoriaCartView> ItemsDato;
    CategoriaCartView ItemDato;
    RecyclerView recycler;
    //** declarar la cola de peticiones
    private RequestQueue colaPeticiones;


    View v;
    List<CategoriaCartView> mList;
    AdaptadorCategoria adaptadorCategoria;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;


    public CategoriaFragment() {

    }



    public static CategoriaFragment newInstance(String param1, String param2) {
        CategoriaFragment fragment = new CategoriaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v=inflater.inflate(R.layout.fragment_categoria, container, false);

        // ocultar titulo y activar recycler
        recycler = v.findViewById(R.id.recyclerElementosCategoria);
        recycler.setLayoutManager(
                new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        // Crear array de items categoria
        ItemsDato = new ArrayList<CategoriaCartView>();

        //** Creamos la cola de peticiones
        colaPeticiones = Volley.newRequestQueue(getActivity());

        //CargarListaCategoria();
        CargarListaCategoriaVolley();

       // CargarListaCategoria();
        return v;
    }

    private void CargarListaCategoriaVolley() {


        //** declarar variable de URL de Api
        // String url = "http://172.96.143.27/sistemacitas/validar_login.php";
        String url = "http://172.96.143.27:8090/project/rest/categoria/listar";


        // ItemsDato.clear();
        //** creo el objeto de requerimiento API
        // la solicitud debe ser un JsonArrayRequest y JSONArray ya que retorna un listado de registros
        JsonArrayRequest requerimiento = new JsonArrayRequest(
                Request.Method.GET  ,
                url  ,
                null  ,

                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        //** codificar el resultado de la respuesta.
                        try {


                            // Leer Registro por Registro

                            for (int i = 0; i < response.length();i++)
                            {
                                JSONObject objCategoria = response.getJSONObject(i);
                                ItemDato = new CategoriaCartView();

                                ItemDato.setDescripcion(objCategoria.getString("descripcion"));
                                ItemDato.setId_categoria(objCategoria.getInt("id_categoria"));

                                ItemDato.setImagenproducto(objCategoria.getString("imagen"));

                                ItemsDato.add(ItemDato);



                            }

                            AdaptadorCategoria adapter = new AdaptadorCategoria(ItemsDato);
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



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);



        }
    }



    private void CargarListaCategoria() {

      //  ItemsDato = new ArrayList<CategoriaCartView>();

        ItemDato = new CategoriaCartView();
        ItemDato.setDescripcion("Carnes");
        ItemDato.setId_categoria(100);

      //  ItemDato.setImagenproducto(R.drawable.carne);

        ItemsDato.add(ItemDato);

        ItemDato = new CategoriaCartView();
        ItemDato.setDescripcion("Licores");
        ItemDato.setId_categoria(100);

   //     ItemDato.setImagenproducto(R.drawable.licores_editado);

        ItemsDato.add(ItemDato);
        ItemDato = new CategoriaCartView();
        ItemDato.setDescripcion("Frutas");
        ItemDato.setId_categoria(100);

      //  ItemDato.setImagenproducto(R.drawable.frutas_verduras_edicion);

        ItemsDato.add(ItemDato);

        ItemDato = new CategoriaCartView();
        ItemDato.setDescripcion("Verdudas");
        ItemDato.setId_categoria(100);

       // ItemDato.setImagenproducto(R.drawable.frutas_verduras_edicion);

        ItemsDato.add(ItemDato);

        ItemDato = new CategoriaCartView();
        ItemDato.setDescripcion("Menestras");
        ItemDato.setId_categoria(100);

     //   ItemDato.setImagenproducto(R.drawable.menestras_editado);

        ItemsDato.add(ItemDato);

        ItemDato = new CategoriaCartView();
        ItemDato.setDescripcion("Abarrotes");
        ItemDato.setId_categoria(100);

     //   ItemDato.setImagenproducto(R.drawable.abarrotes_edicion);

        ItemsDato.add(ItemDato);
        ItemDato = new CategoriaCartView();
        ItemDato.setDescripcion("Cuidado Personal");
        ItemDato.setId_categoria(100);

   //     ItemDato.setImagenproducto(R.drawable.cuidados_personales_edicion);

        ItemsDato.add(ItemDato);


        AdaptadorCategoria adapter = new AdaptadorCategoria(ItemsDato);

        recycler.setAdapter(adapter);
    }

}