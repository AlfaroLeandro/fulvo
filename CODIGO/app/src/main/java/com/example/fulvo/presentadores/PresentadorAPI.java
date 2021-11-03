package com.example.fulvo.presentadores;

import com.example.fulvo.comunicacionAPI.APIRespuesta;
import com.example.fulvo.modelos.APIPedido;
import com.example.fulvo.modelos.ModeloRetrofit;
import com.example.fulvo.modelos.APIServicio;
import com.example.fulvo.RegistroDeUsuarioActivity;
import com.example.fulvo.IniciarSesionActivity;

import retrofit2.Call;


public class PresentadorAPI{

	private RegistroDeUsuarioActivity registroDeUsuarioActivity = null;
	private IniciarSesionActivity iniciarSesionActivity = null;
	private final ModeloRetrofit modeloRetrofit = new ModeloRetrofit(this);
	
	public PresentadorAPI(RegistroDeUsuarioActivity activity)
	{
		registroDeUsuarioActivity = activity;
	}
	
	public PresentadorAPI(IniciarSesionActivity activity)
	{
		iniciarSesionActivity = activity;
	}


	public void comunicarseConAPI()
	{
		APIServicio apiServicio = modeloRetrofit.obtenerNuevoServicio();

		if(registroDeUsuarioActivity !=null)
		{
			Call<APIRespuesta> call = apiServicio.register(registroDeUsuarioActivity.getSolicitud());
			modeloRetrofit.interactuarConAPI(call, registroDeUsuarioActivity.getBaseContext(), APIPedido.REGISTRAR_USUARIO);
		}
		else
		{
		    Call<APIRespuesta> call = apiServicio.ingresar(iniciarSesionActivity.getSolicitudIniciarSesion());
		    modeloRetrofit.interactuarConAPI(call, iniciarSesionActivity.getBaseContext(), APIPedido.INICIAR_SESION);
		}
	}

	public void PasarAInformeActivity()
	{
		if(registroDeUsuarioActivity !=null)
			registroDeUsuarioActivity.PasarAInformeActivity();
		else
			iniciarSesionActivity.PasarAInformeActivity();
	}
}
