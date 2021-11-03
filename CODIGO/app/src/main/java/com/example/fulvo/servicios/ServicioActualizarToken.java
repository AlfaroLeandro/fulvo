package com.example.fulvo.servicios;

import android.app.IntentService;
import android.content.Intent;

import static java.lang.Thread.sleep;

import com.example.fulvo.modelos.ModeloRetrofitTokenEvento;

@SuppressWarnings("FieldCanBeLocal")
public class ServicioActualizarToken extends IntentService {
    private final static int ESPERA_ACTUALIZACION_TOKEN = 300000;
    private final static int TIEMPO_DORMIR = 1000;
    private static boolean ejecutando;
    private int tiempoInicial;

    public ServicioActualizarToken() {
        super("ModeloActualizarToken");
    }

    public static void iniciarContador() {
        ejecutando = true;
    }

    public static void detenerContador() {
        ejecutando = false;
    }

    @SuppressWarnings("BusyWait")
    @Override
    protected void onHandleIntent(Intent intent) {
        tiempoInicial = (int) System.currentTimeMillis();
        while (ejecutando) {

            if (((int) System.currentTimeMillis() - tiempoInicial) > ESPERA_ACTUALIZACION_TOKEN) {
                ModeloRetrofitTokenEvento actualizacionToken = new ModeloRetrofitTokenEvento();
                actualizacionToken.actualizarToken();
                tiempoInicial = (int) System.currentTimeMillis();
            }
            try {
                sleep(TIEMPO_DORMIR);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
