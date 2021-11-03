package com.example.fulvo.servicios;

import android.app.IntentService;
import android.content.Intent;
import static java.lang.Thread.sleep;

import androidx.annotation.Nullable;

import com.example.fulvo.modelos.ModeloRetrofitTokenEvento;

public class servicioRegistrarEvento extends IntentService {
    private final static int TIEMPO_DORMIR = 1000;
    private static String descriptions, type_events;
    private static boolean hayEventoParaRegistrar = false;

    public servicioRegistrarEvento() {
        super("ServicioRegistroEvento");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

           if (hayEventoParaRegistrar){
               ModeloRetrofitTokenEvento resgistroEvent = new ModeloRetrofitTokenEvento();
               resgistroEvent.registrarEvento(descriptions, type_events);

               try{
                   sleep(TIEMPO_DORMIR);
                   hayEventoParaRegistrar = false;
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
    }

    public static void agregarEvento(String descripcion, String type_event) {
        descriptions = descripcion;
        type_events = type_event;
        hayEventoParaRegistrar = true;
    }

}
