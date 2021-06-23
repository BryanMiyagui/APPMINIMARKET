package com.example.pruebademoappminimarket;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pruebademoappminimarket.model.VariableGlobal;

public class SplashActivity extends AppCompatActivity {

    private ImageView logo;
    private static int splashTimeOut=2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        logo=(ImageView)findViewById(R.id.logo);


        // Inicializar los atributos de la clase global
        // mApp = ((VariableGlobal)getApplication());

        VariableGlobal mApp = ((VariableGlobal)getApplicationContext());

        mApp.setId_cliente(0);
        mApp.setNombre("");
        mApp.setApellidos("");
        mApp.setEmail("");
        mApp.setUsuario("");


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);

                startActivity(i);
                finish();
            }
        },splashTimeOut);

        Animation myanim = AnimationUtils.loadAnimation(this,R.anim.mysplashanimation);

        logo.startAnimation(myanim);
    }
}