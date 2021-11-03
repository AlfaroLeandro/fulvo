package com.example.fulvo.modelos;


import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.fulvo.comunicacionAPI.APIRespuesta;
import com.example.fulvo.comunicacionAPI.ConstToken;
import com.example.fulvo.comunicacionAPI.RespuestaError;
import com.example.fulvo.presentadores.PresentadorAPI;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@SuppressWarnings("NullableProblems")
public class ModeloRetrofit {
	Retrofit retrofit;
    PresentadorAPI presentadorAPI;

	public ModeloRetrofit(PresentadorAPI _presentadorAPI)
	{
        retrofit = new Retrofit.Builder()
                .baseUrl("http://so-unlam.net.ar/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        presentadorAPI = _presentadorAPI;
	}

    public APIServicio obtenerNuevoServicio() {
        return retrofit.create(APIServicio.class);
    }

    public void interactuarConAPI(Call<APIRespuesta> call, Context contexto, APIPedido tipoDePedido)
    {

        call.enqueue(new Callback<APIRespuesta>() {
            @Override
            public void onResponse(Call<APIRespuesta> call, Response<APIRespuesta> respuesta) {
                if (respuesta.isSuccessful()) { //VALIDA SI EL CODIGO ESTA ENTRE 200 Y 300

                    ConstToken.setToken(respuesta.body().getToken());
                    ConstToken.setToken_refresh(respuesta.body().getToken_refresh());

                    if(tipoDePedido== APIPedido.REGISTRAR_USUARIO)
                        Toast.makeText(contexto, "Se registro el usuario satisfactoriamente", Toast.LENGTH_LONG).show();
                    else if(tipoDePedido== APIPedido.INICIAR_SESION)
                        Toast.makeText(contexto, "Se inicio sesion satisfactoriamente", Toast.LENGTH_LONG).show();

                        presentadorAPI.PasarAInformeActivity();
                } else if (respuesta.body() == null) {

                    Gson gson = new Gson();
                    Type type = new TypeToken<RespuestaError>() { }.getType();

                    RespuestaError respuestaError = gson.fromJson(respuesta.errorBody().charStream(), type);

                    Toast.makeText(contexto, respuestaError.getMsg(), Toast.LENGTH_LONG).show();
                    Log.i("mensajeError", respuestaError.getMsg());
                } else {
                    Log.i("mensajeError", "fallo");
                }
            }

            @Override
            public void onFailure(Call<APIRespuesta> call, Throwable t) {
                Log.e("fallo mensaje", t.getMessage());
            }
        });

    }
}
