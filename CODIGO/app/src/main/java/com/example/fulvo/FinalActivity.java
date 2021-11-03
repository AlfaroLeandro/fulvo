package com.example.fulvo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

@SuppressWarnings("FieldCanBeLocal")
public class FinalActivity extends AppCompatActivity {

    private TextView titulo;
    private TextView temperatura;
    private TextView resultado;
    private TextView informacion;
    private TextView accesosH;
    private TextView accesosI;

    LinearLayout principal;

    private int accesosHabilitados, accesosInhabilitados;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        Log.i("onCreate", "se creo de nuevo la activity");

        titulo = findViewById(R.id.titulo);
        temperatura = findViewById(R.id.tempSalida);
        resultado = findViewById(R.id.resultado);
        informacion = findViewById(R.id.informacion);
        principal = findViewById(R.id.principal);

        accesosH = findViewById(R.id.accesosHabilitados);
        accesosI = findViewById(R.id.accesosInhabilitados);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String valorTemperatura = extras.getString("temperatura");
        boolean acceso = extras.getBoolean("accesoACancha");

        temperatura.setText("Su temperatura es: " + valorTemperatura + " °C");

        cargarAcceso();

        if (acceso) {
            //guardarAccesos(acceso);
            titulo.setText("HABILITADO");
            resultado.setText("Puede ingresar al estadio");
            informacion.setText("Disfrute del partido, por favor siga las " +
                    "siguientes recomendaciones:\n\n" +
                    "Utilice siempre el barbijo de manera correcta\n" +
                    "Póngase alcohol de mano frecuentemente\n" +
                    "Evite las aglomeraciones\n\n" +
                    "Gracias, Equipo Fulvo.");
            principal.setBackgroundColor(Color.rgb(76, 175, 80));

        } else {
            //guardarAccesos(acceso);
            titulo.setText("INHABILITADO");
            resultado.setText("No puede ingresar al estadio");
            informacion.setText("Por favor dirigase al centro de control más cercano");
            principal.setBackgroundColor(Color.rgb(211, 37, 59));
        }

        accesosH.setText("Total accesos habilitados: " + accesosHabilitados);
        accesosI.setText("Total accesos inhabilitados: " + accesosInhabilitados);
    }

    private void cargarAcceso() {
        SharedPreferences preferences = getSharedPreferences("Accesos", Context.MODE_PRIVATE);

        accesosHabilitados = preferences.getInt("Acceso Habilitado", 0);
        accesosInhabilitados = preferences.getInt("Acceso Inhabilitado", 0);
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}