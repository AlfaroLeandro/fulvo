package com.example.fulvo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.BatteryManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class InicioActivity extends AppCompatActivity {

    private final int ENVIAR_SMS_PEDIDO_CODIGO_PERMISO = 1;
    private final int PIN_MINIMO = 10000;
    private final int PIN_MAXIMO = 99999;
    private final int PORCENTAJE_MAX_BATERIA = 100;
    private EditText number;
    private EditText pinRecibido;
    private Button enviar;
    private Button validarPin;
    private int pinEnviado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicial);

        TextView txtBateria = findViewById(R.id.textViewBateria);
        txtBateria.setText(getCargaBateria());

        number = findViewById(R.id.editTextNumeroCelular);
        enviar = findViewById(R.id.buttonEnviarSMS);
        pinRecibido = findViewById(R.id.editTextPin);
        validarPin = findViewById(R.id.buttonValidarPin);

        enviar.setEnabled(false);
        pinRecibido.setEnabled(false);
        validarPin.setEnabled(false);

        if(chequearPermisoDeSMS(Manifest.permission.SEND_SMS)) {
            enviar.setEnabled(true);
        }else {
            ActivityCompat.requestPermissions(this,
                    new String[] {Manifest.permission.SEND_SMS}, ENVIAR_SMS_PEDIDO_CODIGO_PERMISO);
        }

    }

    public void onSend(View v) {
        String nroTelefono = number.getText().toString();
        int pinMinimo = PIN_MINIMO;
        int pinMaximo = PIN_MAXIMO;

        int pinGenerado = (int) ((Math.random() * ((pinMaximo - pinMinimo) + 1)) + pinMinimo);

        Log.i("pin", String.valueOf(pinGenerado) );

        if(nroTelefono == null || nroTelefono.length() == 0) {
            return;
        }

        if(chequearPermisoDeSMS(Manifest.permission.SEND_SMS)) {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(nroTelefono, null,"Este es tu código para entrar a la app: " + pinGenerado, null, null);
            Toast.makeText(this, "Mensaje Enviado!", Toast.LENGTH_SHORT).show();
            pinRecibido.setEnabled(true);
            validarPin.setEnabled(true);
            pinEnviado =pinGenerado;
        }else {
            Toast.makeText(this, "Permiso Denegado", Toast.LENGTH_SHORT).show();
        }
    }

    public void validarPinIngresado(View v){
        String pinEscrito = pinRecibido.getText().toString();

        Log.i("pin escrito", pinEscrito );

        Log.i("pin enviado", String.valueOf(pinEnviado));

        if(String.valueOf(pinEnviado).equals(pinEscrito)) {

            Toast.makeText(this, "Pin Correcto!", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(getBaseContext(), IniciarSesionActivity.class);

            startActivity(intent);

        } else {
            Toast.makeText(this, "Pin Invalido!", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean chequearPermisoDeSMS(String permiso) {
        int chequeo = ContextCompat.checkSelfPermission(this, permiso);
        return (chequeo == PackageManager.PERMISSION_GRANTED);
    }

    public String getCargaBateria() {

        IntentFilter iFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent estadoBateria = getApplicationContext().registerReceiver(null, iFilter);

        int nivelBateria = estadoBateria.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int escalaBateria = estadoBateria.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
        float porcentajeBateria = nivelBateria * PORCENTAJE_MAX_BATERIA / (float) escalaBateria;

        return "Carga de la bateria: " + porcentajeBateria + "%";
    }
}
