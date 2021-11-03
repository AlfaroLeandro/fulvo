package com.example.fulvo;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VerificacionServicio {
    static final int TAMANIO_CONTRASENIA = 8;

    public static boolean esContraseniaValida(String pass) {
        if (pass.length() >= TAMANIO_CONTRASENIA)
            return true;

        return false;
    }

    public static Boolean esEmailValido(String email) {
        Pattern patron = Pattern.compile("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", Pattern.CASE_INSENSITIVE);
        Matcher matcher = patron.matcher(email);
        return matcher.matches();
    }

    public static boolean noHayConexionAInternet(ConnectivityManager connectivityManager) {
        boolean desconectado = true;
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            desconectado = false; //SI ESTA CONECTADO A INTERNET
        }

        return desconectado;
    }
}
