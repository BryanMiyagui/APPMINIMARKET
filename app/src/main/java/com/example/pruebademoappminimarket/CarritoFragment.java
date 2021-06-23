package com.example.pruebademoappminimarket;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.pruebademoappminimarket.Adaptador.AdaptadorDetalleCVenta;
import com.example.pruebademoappminimarket.model.CarritoDetalle;
import com.example.pruebademoappminimarket.model.VariableGlobal;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CarritoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CarritoFragment extends Fragment {

    // Varialbles globales
    ArrayList<CarritoDetalle> ItemsDato;
    CarritoDetalle ItemDato;
    RecyclerView recycler;
    View v;

    //** declarar la cola de peticiones
    private RequestQueue colaPeticiones;

    Button botonRealizarCompra;

    TextView totalPagarCarritoMsg, mensajeCarrito;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CarritoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CarritoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CarritoFragment newInstance(String param1, String param2) {
        CarritoFragment fragment = new CarritoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_carrito, container, false);
        //Amarrar el reclicer del carteview

        recycler = v.findViewById(R.id.recyclerElementosCarritoVenta);
        recycler.setLayoutManager(
                new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        botonRealizarCompra = v.findViewById(R.id.realizarcompraCarritoVenta);
        totalPagarCarritoMsg = v.findViewById(R.id.totalpagarCarritoVenta);
        mensajeCarrito = v.findViewById(R.id.mensajeCarritoVenta);

        // Crear array de items categoria
        ItemsDato = new ArrayList<CarritoDetalle>();

        //** Creamos la cola de peticiones
        colaPeticiones = Volley.newRequestQueue(getActivity());


        // Cargar la lista del carrito de venta
        CargarListaCarritoVentaVolley();

        // Boton para activar activity de compra
        botonRealizarCompra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Verificar si tenemos al menos un articulo en carrito para realizar la compra
                if (ItemsDato.size() > 0) {


                    Intent intent = new Intent(getContext(), ActivityReClienteEnvio.class);

                    intent.putExtra("total_pagar",totalPagarCarritoMsg.getText());

                    startActivity(intent);

                }else
                {
                    mensajeCarrito.setText("El carrito no cuenta con producto");
                }
            }
        });

        return v;
    }

    private void CargarListaCarritoVentaVolley()
    {
        String clienteIdConectado;

        // Variable Global
        VariableGlobal mApp = ((VariableGlobal)getActivity().getApplicationContext());

        if (mApp.getUsuario().isEmpty())
        {
            clienteIdConectado = "0";
        }else
        {
            clienteIdConectado = String.valueOf(mApp.getId_cliente());
        }

//** declarar variable de URL de Api
        // String url = "http://172.96.143.27/sistemacitas/validar_login.php";
        String url = "http://172.96.143.27:8090/project/rest/carritodetalle/buscarCliente/"+clienteIdConectado;


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
                            Double totalPagarCarrito;
                            totalPagarCarrito = 0.0;
                            // Leer Registro por Registro

                            for (int i = 0; i < response.length();i++)
                            {
                                JSONObject objVenta = response.getJSONObject(i);
                                ItemDato = new CarritoDetalle();

                                // Cabezera de compra
                                ItemDato.setId_carritoven(objVenta.getInt("id_carritoven"));
                                //ItemDato.setFechaven(objVenta.getString("fechaven"));
                                ItemDato.setSubtotal(objVenta.getDouble("subtotal"));
                                ItemDato.setIgv(objVenta.getDouble("igv"));
                                ItemDato.setTotal(objVenta.getDouble("total"));
                                //  ItemDato.setTipoentrega(objVenta.getString("tipoentrega"));
                                ItemDato.setId_cliente(objVenta.getInt("id_cliente"));
                                ItemDato.setNombrecliente(objVenta.getString("nombrecliente"));

                                // Detalle de compra
                                ItemDato.setId_carritovendeta(objVenta.getInt("id_carritovendeta"));
                                ItemDato.setCantidad_detalle(objVenta.getInt("cantidad_detalle"));
                                ItemDato.setPrecio_detalle(objVenta.getDouble("precio_detalle"));
                                ItemDato.setSubtotal_detalle(objVenta.getDouble("subtotal_detalle"));
                                ItemDato.setIgv_detalle(objVenta.getDouble("igv_detalle"));
                                ItemDato.setTotal_detalle(objVenta.getDouble("total_detalle"));
                                ItemDato.setId_producto(objVenta.getInt("id_producto"));
                                ItemDato.setNombreproducto(objVenta.getString("nombreproducto"));
                                ItemDato.setImagen(objVenta.getString("imagen"));

                                totalPagarCarrito = totalPagarCarrito + objVenta.getDouble("total_detalle");

                                ItemsDato.add(ItemDato);
                                /*
                                ItemsDato.add(new CategoriaCartView(objProducto.getInt("id_producto"),
                                        objProducto.getString("descripcion"),
                                        objProducto.getDouble("precio"),
                                        "src\\main\\res\\drawable-v24\\carne.png")
                                );
                               */

                            }

                            // se pasan los datos al adaptador
                            AdaptadorDetalleCVenta adapter = new AdaptadorDetalleCVenta(ItemsDato);
                            recycler.setAdapter(adapter);
                            totalPagarCarritoMsg.setText("Total a pagar : S/. "+String.valueOf(totalPagarCarrito));

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