package com.example.fulvo.presentadores;

import android.content.Intent;

import com.example.fulvo.FormularioActivity;
import com.example.fulvo.servicios.ServicioActualizarToken;
import com.example.fulvo.servicios.servicioRegistrarEvento;

public class PresentadorAPITokenEvento {
    FormularioActivity formularioActivity;
    Intent modeloActualizarToken;
    Intent modeloRegistrarEvento;


    public PresentadorAPITokenEvento(FormularioActivity _formularioActivity)
    {
        formularioActivity = _formularioActivity;
    }

    public void actualizarToken() {
        ServicioActualizarToken.iniciarContador();
        modeloActualizarToken = new Intent(formularioActivity, ServicioActualizarToken.class);
        formularioActivity.startService(modeloActualizarToken);
    }

    public void registrarEvento()
    {

        servicioRegistrarEvento.agregarEvento("Control de acceso a la cancha", "ACCESO");
        modeloRegistrarEvento = new Intent(formularioActivity, servicioRegistrarEvento.class);
        formularioActivity.startService(modeloRegistrarEvento);

    }
}
