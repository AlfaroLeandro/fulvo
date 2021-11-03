package com.example.fulvo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fulvo.comunicacionAPI.APISolicitudRegistrarUsuario;
import com.example.fulvo.presentadores.PresentadorAPI;

public class RegistroDeUsuarioActivity extends AppCompatActivity{
    private static final int NUMERO_DE_GRUPO = 8;
    private static final int NUMERO_DE_COMISION = 3900;
	private PresentadorAPI presentador;
	private APISolicitudRegistrarUsuario solicitud; 
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_de_usuario);
        
        presentador= new PresentadorAPI(this);
    }

    public void registrarUsuario(View view) {

        EditText passView = findViewById(R.id.passEditText);
        EditText emailView = findViewById(R.id.emailEditText);
        EditText nombreView = findViewById(R.id.nombreEditText);
        EditText apellidoView = findViewById(R.id.apellidoEditText);
        EditText dniView = findViewById(R.id.dniEditText);

        String pass = passView.getText().toString();
        String email = emailView.getText().toString();
        String nombre = nombreView.getText().toString();
        String apellido = apellidoView.getText().toString();
        String  dni = dniView.getText().toString();

        if (VerificacionServicio.noHayConexionAInternet((ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE))) {
            Toast.makeText(getBaseContext(), "No se encuentra conectado a internet", Toast.LENGTH_LONG).show();

        } else if (!VerificacionServicio.esEmailValido(email)) {
            Toast.makeText(getBaseContext(), "Email invalido", Toast.LENGTH_LONG).show();
        } else if (!VerificacionServicio.esContraseniaValida(pass)) {
            Toast.makeText(getBaseContext(), "Contraseña debe por lo menos de ocho carac.", Toast.LENGTH_LONG).show();

        } else {
        	
        	solicitud = new APISolicitudRegistrarUsuario();

        	 solicitud.setEnv("PROD");
	         solicitud.setLastname(apellido);
	         solicitud.setDni(Long.parseLong(dni));
	         solicitud.setEmail(email);
	         solicitud.setName(nombre);
	         solicitud.setPassword(pass);
	         solicitud.setCommission(NUMERO_DE_COMISION);
	         solicitud.setGroup(NUMERO_DE_GRUPO);
	         
        	presentador.comunicarseConAPI();
        }
    }

    public void PasarAInformeActivity() {
        Intent intent;
        intent = new Intent(getBaseContext(), FormularioActivity.class);
        startActivity(intent);
    }

	public APISolicitudRegistrarUsuario getSolicitud() {
		return solicitud;
	}
}
