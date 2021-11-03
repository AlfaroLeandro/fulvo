package com.example.fulvo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fulvo.presentadores.PresentadorAPITokenEvento;
import com.example.fulvo.presentadores.PresentadorSensor;

import java.util.HashMap;
import java.util.Map;

public class FormularioActivity extends AppCompatActivity {

    private PresentadorAPITokenEvento presentadorAPITokenEvento;
    private PresentadorSensor presentadorSensor;

    private Spinner spEdades;
    private Spinner spDosis;
    private Spinner spVacunas;
    private Spinner spSintomas;
    private Spinner spContacto;

    private EditText temperatura;
    private TextView titulo;
    private TextView rangoEdad;
    private TextView dosisRecibidas;
    private TextView vacunasRecibidas;
    private TextView sintomasActuales;
    private TextView contactoEstrecho;
    private TextView temperaturaActual;

    private TextView indicacion;

    boolean darkTheme = false;

    LinearLayout segundaLayout;
    LinearLayout primerLayout;
    ScrollView scroll;

    // Constantes staticas de la clase
    private static final int TEM_MAX = 37;

    // Para manejar los spinners
    Map<String, String> datosAGuardar = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("pasada", "entre en el onCreate de informe");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        primerLayout = findViewById(R.id.rootLayout);
        segundaLayout = findViewById(R.id.secondlayout);

        scroll = findViewById(R.id.viewScroll);

        spEdades = findViewById(R.id.spEdad);
        ArrayAdapter<CharSequence> adapterEdades = ArrayAdapter.createFromResource(this, R.array.edades, R.layout.elemento_spinner_personalizado);
        adapterEdades.setDropDownViewResource(R.layout.elemento_lista_spinner_personalizado);
        spEdades.setAdapter(adapterEdades);

        spDosis = findViewById(R.id.spDosis);
        ArrayAdapter<CharSequence> adapterDosis = ArrayAdapter.createFromResource(this, R.array.dosis, R.layout.elemento_spinner_personalizado);
        adapterDosis.setDropDownViewResource(R.layout.elemento_lista_spinner_personalizado);
        spDosis.setAdapter(adapterDosis);

        spVacunas = findViewById(R.id.spVacunas);
        ArrayAdapter<CharSequence> adapterVacunas = ArrayAdapter.createFromResource(this, R.array.vacunas, R.layout.elemento_spinner_personalizado);
        adapterVacunas.setDropDownViewResource(R.layout.elemento_lista_spinner_personalizado);
        spVacunas.setAdapter(adapterVacunas);

        spSintomas = findViewById(R.id.spSintomas);
        ArrayAdapter<CharSequence> adapterSintomas = ArrayAdapter.createFromResource(this, R.array.sintomas, R.layout.elemento_spinner_personalizado);
        adapterSintomas.setDropDownViewResource(R.layout.elemento_lista_spinner_personalizado);
        spSintomas.setAdapter(adapterSintomas);

        spContacto = findViewById(R.id.spContacto);
        ArrayAdapter<CharSequence> adapterContacto = ArrayAdapter.createFromResource(this, R.array.contacto, R.layout.elemento_spinner_personalizado);
        adapterContacto.setDropDownViewResource(R.layout.elemento_lista_spinner_personalizado);
        spContacto.setAdapter(adapterContacto);

        temperatura = findViewById(R.id.temperatura);

        titulo = findViewById(R.id.titulo);
        rangoEdad = findViewById(R.id.textView6);
        dosisRecibidas = findViewById(R.id.textView7);
        vacunasRecibidas = findViewById(R.id.textView8);
        sintomasActuales = findViewById(R.id.textView9);
        contactoEstrecho = findViewById(R.id.textView10);
        temperaturaActual = findViewById(R.id.etiqueta_temperatura);

        indicacion = findViewById(R.id.textIndicacion);

        //Actualizar token cada 6 minutos. El token dura 7 min en el server.
        presentadorAPITokenEvento = new PresentadorAPITokenEvento(this);
        presentadorAPITokenEvento.actualizarToken();

        presentadorSensor = new PresentadorSensor(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        presentadorSensor.registrarSensores();
    }


    @Override
    protected void onDestroy() {
        presentadorSensor.quitarSensores();
        super.onDestroy();
    }

    public void ponerFondoBlanco()
    {
        primerLayout.setBackgroundColor(Color.WHITE);
        segundaLayout.setBackgroundColor(Color.WHITE);
        scroll.setBackgroundColor(Color.WHITE);

        titulo.setBackgroundColor(Color.WHITE);
        rangoEdad.setBackgroundColor(Color.WHITE);
        dosisRecibidas.setBackgroundColor(Color.WHITE);
        vacunasRecibidas.setBackgroundColor(Color.WHITE);
        sintomasActuales.setBackgroundColor(Color.WHITE);
        contactoEstrecho.setBackgroundColor(Color.WHITE);
        temperaturaActual.setBackgroundColor(Color.WHITE);

        titulo.setTextColor(Color.BLACK);
        rangoEdad.setTextColor(Color.BLACK);
        dosisRecibidas.setTextColor(Color.BLACK);
        vacunasRecibidas.setTextColor(Color.BLACK);
        sintomasActuales.setTextColor(Color.BLACK);
        contactoEstrecho.setTextColor(Color.BLACK);
        temperaturaActual.setTextColor(Color.BLACK);

        indicacion.setBackgroundColor(Color.WHITE);
        indicacion.setTextColor(Color.BLACK);

        spEdades.setBackgroundColor(Color.WHITE);
        spDosis.setBackgroundColor(Color.WHITE);
        spVacunas.setBackgroundColor(Color.WHITE);
        spSintomas.setBackgroundColor(Color.WHITE);
        spContacto.setBackgroundColor(Color.WHITE);

        temperatura.setBackgroundColor(Color.WHITE);
        temperatura.setTextColor(Color.BLACK);
        temperatura.setHintTextColor(Color.GRAY);

        darkTheme = false;
        Toast.makeText(getApplicationContext(), "Tema Oscuro Desactivado", Toast.LENGTH_SHORT).show();
        Log.i("Theme", "oscuro Desactivado.");
    }

    public void ponerFondoNegro()
    {
        primerLayout.setBackgroundColor(Color.BLACK);
        segundaLayout.setBackgroundColor(Color.BLACK);
        scroll.setBackgroundColor(Color.BLACK);

        titulo.setBackgroundColor(Color.BLACK);
        rangoEdad.setBackgroundColor(Color.BLACK);
        dosisRecibidas.setBackgroundColor(Color.BLACK);
        vacunasRecibidas.setBackgroundColor(Color.BLACK);
        sintomasActuales.setBackgroundColor(Color.BLACK);
        contactoEstrecho.setBackgroundColor(Color.BLACK);
        temperaturaActual.setBackgroundColor(Color.BLACK);

        titulo.setTextColor(Color.WHITE);
        rangoEdad.setTextColor(Color.WHITE);
        dosisRecibidas.setTextColor(Color.WHITE);
        vacunasRecibidas.setTextColor(Color.WHITE);
        sintomasActuales.setTextColor(Color.WHITE);
        contactoEstrecho.setTextColor(Color.WHITE);
        temperaturaActual.setTextColor(Color.WHITE);

        indicacion.setBackgroundColor(Color.BLACK);
        indicacion.setTextColor(Color.WHITE);

        spEdades.setBackgroundColor(Color.BLACK);
        spDosis.setBackgroundColor(Color.BLACK);
        spVacunas.setBackgroundColor(Color.BLACK);
        spSintomas.setBackgroundColor(Color.BLACK);
        spContacto.setBackgroundColor(Color.BLACK);

        temperatura.setBackgroundColor(Color.BLACK);
        temperatura.setTextColor(Color.WHITE);
        temperatura.setHintTextColor(Color.GRAY);

        darkTheme = true;
        Toast.makeText(getApplicationContext(), "Tema Oscuro Activado", Toast.LENGTH_SHORT).show();
        Log.i("Theme", "oscuro activado.");
    }

    public void leerDatos() {

        datosAGuardar.put("Edad", spEdades.getSelectedItem().toString());
        datosAGuardar.put("Dosis", spDosis.getSelectedItem().toString());
        datosAGuardar.put("Vacunas", spVacunas.getSelectedItem().toString());
        datosAGuardar.put("Sintomas", spSintomas.getSelectedItem().toString());
        datosAGuardar.put("Contacto", spContacto.getSelectedItem().toString());

        String temperaturaCorregida = this.temperatura.getText().toString().replace(",", ".");
        boolean accesoACancha = false; //Indica si tiene puede ingresar o no

        Intent intent = new Intent(FormularioActivity.this, FinalActivity.class);

        if (!temperaturaCorregida.isEmpty()
                && !datosAGuardar.get("Dosis").equals("Seleccione opcion desplegable ↓")
                && !datosAGuardar.get("Sintomas").equals("Seleccione opcion desplegable ↓")
                && !datosAGuardar.get("Contacto").equals("Seleccione opcion desplegable ↓")) {

            double valorTemperatura = Double.parseDouble(temperaturaCorregida);

            if (valorTemperatura <= TEM_MAX && !datosAGuardar.get("Dosis").equals("0") //Entra con 1 o más dosis
                    && datosAGuardar.get("Sintomas").equals("No he tenido sintomas")     //Entra sin sintomas
                    && datosAGuardar.get("Contacto").equals("No"))                       //Entra sin contacto estrecho
            {
                accesoACancha = true;
            }

            guardarAccesos(accesoACancha);

        } else {
            Toast.makeText(getApplicationContext(), "Complete los campos obligatorios", Toast.LENGTH_SHORT).show();
            return;
        }
        intent.putExtra("accesoACancha", accesoACancha);
        intent.putExtra("temperatura", temperaturaCorregida);

        presentadorAPITokenEvento.registrarEvento();

        Toast.makeText(getApplicationContext(), "En breve tendrá el resultado", Toast.LENGTH_SHORT).show();
        presentadorSensor.quitarSensores();
        startActivity(intent);
    }


    private void guardarAccesos(boolean acceso) {

        SharedPreferences preferences = getSharedPreferences("Accesos", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = preferences.edit();

        if(acceso){
            int accesosHabilitados = preferences.getInt("Acceso Habilitado", 0);
            accesosHabilitados++;
            editor.putInt("Acceso Habilitado", accesosHabilitados);
            Log.i("aumentado H", String.valueOf(accesosHabilitados));
        } else {
            int accesosInhabilitados = preferences.getInt("Acceso Inhabilitado", 0);
            accesosInhabilitados++;
            editor.putInt("Acceso Inhabilitado", accesosInhabilitados);
            Log.i("aumentado I", String.valueOf(accesosInhabilitados));
        }

        editor.apply();
    }

    public boolean isDarkTheme() {
        return darkTheme;
    }
}
