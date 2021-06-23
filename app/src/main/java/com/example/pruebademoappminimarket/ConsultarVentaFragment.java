package com.example.pruebademoappminimarket;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.pruebademoappminimarket.Libreria.DatePickerFragment;
import com.example.pruebademoappminimarket.model.TipoDocumento;
import com.example.pruebademoappminimarket.model.VariableGlobal;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ConsultarVentaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ConsultarVentaFragment extends Fragment  {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    VariableGlobal mApp;

    View v;
    private EditText fechainicio, fechafinal;
    private TextView mensajeListar;

    private Button btnconsultar;

    ArrayList<TipoDocumento> ItemsDato;
    TipoDocumento ItemDato;


    public ConsultarVentaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ConsultarVentaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ConsultarVentaFragment newInstance(String param1, String param2) {
        ConsultarVentaFragment fragment = new ConsultarVentaFragment();
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
        v=inflater.inflate(R.layout.fragment_consultar_venta, container, false);
        fechainicio= v.findViewById(R.id.LisFechaInicio_id);
        fechafinal= v.findViewById(R.id.LisFechaFinal_id);
        mensajeListar= v.findViewById(R.id.mensajeLis);
        btnconsultar= v.findViewById(R.id.buttonConsultarlisDetalle);

        // consultar fecha

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date date = new Date();

        String fecha = dateFormat.format(date);

        fechainicio.setText(fecha);
        fechafinal.setText(fecha);

        //Fecha Inicio
        fechainicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(fechainicio);


            }
        });

        //Fecha final
        fechafinal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(fechafinal);
            }
        });


        // boton consultar
        btnconsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), ActivityListarVentas.class);
                i.putExtra("fecha_inicio",fechainicio.getText().toString());
                i.putExtra("fecha_final",fechafinal.getText().toString());

                startActivity(i);

            }
        });

        return v;
    }

    private void showDatePickerDialog(final EditText editText) {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 because january is zero
                final String selectedDate = year + "-" + twoDigits(month+1) + "-" + twoDigits(day);
                editText.setText(selectedDate);
            }
        });
        newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
    }

    private String twoDigits(int n) {
        return (n<=9) ? ("0"+n) : String.valueOf(n);
    }

}