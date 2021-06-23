package com.example.pruebademoappminimarket;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import android.widget.FrameLayout;
import android.widget.ListView;

import com.example.pruebademoappminimarket.model.VariableGlobal;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    BottomNavigationView btnavViewId;

    FrameLayout frameLayout;
    // Variable Global
    Bundle vGlobalFrament;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnavViewId=findViewById(R.id.bottomNavViewId);
        frameLayout=findViewById(R.id.frameLayoutId);

        // El framento que se activa por default es Categoria
        setFragment(new CategoriaFragment());

        // Definir para leer variable global

        VariableGlobal mApp = ((VariableGlobal)getApplicationContext());


        btnavViewId.setOnNavigationItemSelectedListener(menuItem ->
        {


            switch (menuItem.getItemId()) {
                // Categoria
                case R.id.homeId:
                    btnavViewId.setItemBackgroundResource(R.color.purple_200);

                    setFragment(new CategoriaFragment());
                    return true;
                // Usuario  ( Login - Mantenimiento )
                case R.id.clienteId:
                    btnavViewId.setItemBackgroundResource(R.color.purple_200);

                    if (mApp.getUsuario().equals(""))
                    {
                        // Login de cliente
                        Intent intent = new Intent(MainActivity.this.getBaseContext(),
                                ActivityLogin.class);
                        startActivity(intent);

                    }else
                    {
                        // Mantenimiento de cliente
                        setFragment(new UsuarioFragment());
                    }
                    return true;
                // Carrito de Compra
                case R.id.carritoId:
                    btnavViewId.setItemBackgroundResource(R.color.purple_200);

                    if (mApp.getUsuario().equals(""))
                    {
                        // Login de cliente
                        Intent intent = new Intent(MainActivity.this.getBaseContext(),
                                ActivityLogin.class);
                        startActivity(intent);


                    }else
                    {
                        // consulta de carrito de venta
                        setFragment(new CarritoFragment());
                    }

                    return true;

                // Carrito de Compra
                case R.id.reporteId:

                    btnavViewId.setItemBackgroundResource(R.color.purple_200);

                    if (mApp.getUsuario().equals(""))
                    {
                        // Login de cliente
                        Intent intent = new Intent(MainActivity.this.getBaseContext(),
                                ActivityLogin.class);
                        startActivity(intent);


                    }else
                    {
                        // consulta de venta del cliente
                        setFragment(new ConsultarVentaFragment());
                    }

                    return true;

                default:
                    return false;

            }
        });

    }

    private void setFragment(Fragment fragment) {

        fragment.setArguments(vGlobalFrament);
        FragmentTransaction fragmentTransaction =getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayoutId,fragment);
        fragmentTransaction.commit();
    }
}