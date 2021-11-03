package com.example.fulvo.modelos;

import android.util.Log;

import com.example.fulvo.comunicacionAPI.APIRespuesta;
import com.example.fulvo.comunicacionAPI.ConstToken;
import com.example.fulvo.comunicacionAPI.RespuestaError;
import com.example.fulvo.comunicacionAPI.APISolicitudEvento;
import com.example.fulvo.comunicacionAPI.APIRespuestaEvento;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@SuppressWarnings("NullableProblems")
public class ModeloRetrofitTokenEvento {

    public void registrarEvento(String descripcion, String type_events){
        APISolicitudEvento requestEvent = new APISolicitudEvento();
        requestEvent.setEnv("PROD");
        requestEvent.setDescription(descripcion);
        requestEvent.setType_events(type_events);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://so-unlam.net.ar/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIServicio apiServicio = retrofit.create(APIServicio.class);
        String tokenAct = ConstToken.getToken();
        Call<APIRespuestaEvento> respuestaEventoCall = apiServicio.registrarEvento("Bearer "+ tokenAct,requestEvent);
        respuestaEventoCall.enqueue(new Callback<APIRespuestaEvento>() {
            @Override
            public void onResponse(Call<APIRespuestaEvento> call, Response<APIRespuestaEvento> respuesta) {
                if (respuesta.isSuccessful()){
                    Log.i("REGISTRAR_EVENTO",type_events + "registrado");
                }else if(respuesta.body() == null){
                    Gson gson = new Gson();
                    Type type =  new TypeToken<RespuestaError>(){}.getType();
                    RespuestaError errorRespuesta = gson.fromJson(respuesta.errorBody().charStream(), type);
                    Log.i("REGISTRAR_EVENTO",errorRespuesta.getMsg());
                }else{
                    Log.i("REGISTRAR_EVENTO","fallo "+descripcion);
                }
            }

            @Override
            public void onFailure(Call<APIRespuestaEvento> call, Throwable t) {
            }
        });
    }

    public void actualizarToken(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://so-unlam.net.ar/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIServicio apiServicio = retrofit.create(APIServicio.class);
        Call<APIRespuesta> respuestaActualizarToken = apiServicio.actualizarToken("Bearer " + ConstToken.getToken_refresh());
        respuestaActualizarToken.enqueue(new Callback<APIRespuesta>() {
            @Override
            public void onResponse(Call<APIRespuesta> call, Response<APIRespuesta> respuesta) {
                if (respuesta.isSuccessful()){
                    Log.i("TOKEN", "SALIO BIEN TOKEN");
                    ConstToken.setToken(respuesta.body().getToken());
                    ConstToken.setToken_refresh(respuesta.body().getToken_refresh());

                }else if(respuesta.body() == null){
                    Log.i("TOKEN", "ERROR TOKEN");
                    Gson gson = new Gson();
                    Type type =  new TypeToken<RespuestaError>(){}.getType();
                    RespuestaError errorRespuesta = gson.fromJson(respuesta.errorBody().charStream(), type);
                    Log.i("ERROR", errorRespuesta.getMsg());
                }else{
                    Log.i("TOKEN", "ERROR TOKEN2");
                }
            }

            @Override
            public void onFailure(Call<APIRespuesta> call, Throwable t) {
                Log.i("TOKEN", "ERROR TOKEN3");
            }
        });
    }
}
