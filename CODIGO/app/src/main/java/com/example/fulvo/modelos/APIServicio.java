package com.example.fulvo.modelos;

import com.example.fulvo.comunicacionAPI.APISolicitudRegistrarUsuario;
import com.example.fulvo.comunicacionAPI.APISolicitudEvento;
import com.example.fulvo.comunicacionAPI.APISolicitudIniciarSesion;
import com.example.fulvo.comunicacionAPI.APIRespuesta;
import com.example.fulvo.comunicacionAPI.APIRespuestaEvento;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface APIServicio {

    @POST("api/event")
    Call<APIRespuestaEvento> registrarEvento (@Header ("Authorization") String token, @Body APISolicitudEvento solicitudEvento);

    @PUT("api/refresh")
    Call<APIRespuesta> actualizarToken (@Header("Authorization") String token_refresh);

    @POST("api/register")
    Call<APIRespuesta> register (@Body APISolicitudRegistrarUsuario solicitud);

    @POST("api/login")
    Call<APIRespuesta> ingresar (@Body APISolicitudIniciarSesion solicitudIniciarSesion);

}
