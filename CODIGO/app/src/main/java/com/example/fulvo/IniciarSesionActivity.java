package com.example.fulvo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fulvo.comunicacionAPI.APISolicitudIniciarSesion;
import com.example.fulvo.servicios.ServicioActualizarToken;
import com.example.fulvo.presentadores.PresentadorAPI;

public class IniciarSesionActivity extends AppCompatActivity {

    private PresentadorAPI presentador;
    private APISolicitudIniciarSesion solicitudIniciarSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_sesion);
        
        presentador = new PresentadorAPI(this);
        Log.i("IniciarSesion", "OnCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("IniciarSesion", "OnStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        ServicioActualizarToken.detenerContador();
        Log.i("IniciarSesion", "OnResume");
    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.i("IniciarSesion", "OnStop");
    }

    //TODO: poner como metodo estatico en clase a parte


    public void iniciarSesion(View view) {

        //obtener datos de campos email y contraseña
        EditText emailView = findViewById(R.id.emailEditText);
        EditText passView = findViewById(R.id.passwordEditText);

        String email = emailView.getText().toString();
        String contrasenia = passView.getText().toString();

        //verificar conexion
        if (VerificacionServicio.noHayConexionAInternet((ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE))) {
            Toast.makeText(getBaseContext(), "Error, Sin conexion a internet", Toast.LENGTH_LONG).show();

            //validar email
        }else if(esValidoUsuarioYContrasenia(emailView, passView))
            iniciarSesion( email, contrasenia);
    }

    private void iniciarSesion(String email, String contrasenia) {

    	solicitudIniciarSesion = new APISolicitudIniciarSesion();

        solicitudIniciarSesion.setEmail(email);
        solicitudIniciarSesion.setPassword(contrasenia);

        presentador.comunicarseConAPI();
    }

    public void registrarse (View view){
        Intent intent;
        intent = new Intent(getBaseContext(), RegistroDeUsuarioActivity.class);
        startActivity(intent);
    }

    public boolean esValidoUsuarioYContrasenia(EditText email, EditText contrasenia){
        String campEmail =  email.getText().toString();
        String campPass = contrasenia.getText().toString();
        boolean esValido = true;

        if(campEmail.isEmpty()){
            email.setError("por favor, ingrese su E-mail para iniciar Sesion");
            esValido = false;
        }else if(!VerificacionServicio.esEmailValido(campEmail)) {
            email.setError("Error, email no valido");
        }
        if(campPass.isEmpty() || campPass.length()<8){
            contrasenia.setError("contrasenia incorrecta, debe ingresar mas de ocho caracteres");
            esValido = false;
        }
        return esValido;
    }

    public void PasarAInformeActivity() {
        Intent intent;
        intent = new Intent(getBaseContext(), FormularioActivity.class);
        startActivity(intent);
    }

	public APISolicitudIniciarSesion getSolicitudIniciarSesion() {
		return solicitudIniciarSesion;
	}

}
