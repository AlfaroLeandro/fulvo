package com.example.fulvo.presentadores;


import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;


import com.example.fulvo.FormularioActivity;

public class PresentadorSensor implements SensorEventListener {
    public final int VALOR_SENSOR_ACELEROMETRO_SHAKE = 550;
    public final float VALOR_SENSOR_LUZ_FONDO_BLANCO = 10F;
    private final FormularioActivity formularioActivity;
    private final SensorManager miSensor;

    @SuppressWarnings("AccessStaticViaInstance")
    public PresentadorSensor(FormularioActivity _formularioActivity)
    {
        formularioActivity = _formularioActivity;
        miSensor = (SensorManager) formularioActivity.getSystemService(formularioActivity.SENSOR_SERVICE);
    }

    public void registrarSensores() {
        miSensor.registerListener(this, miSensor.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
        miSensor.registerListener(this, miSensor.getDefaultSensor(Sensor.TYPE_LIGHT), SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void quitarSensores() {
        miSensor.unregisterListener(this, miSensor.getDefaultSensor(Sensor.TYPE_ACCELEROMETER));
        miSensor.unregisterListener(this, miSensor.getDefaultSensor(Sensor.TYPE_LIGHT));
    }

    @SuppressWarnings("PointlessBooleanExpression")
    @Override
    public void onSensorChanged(SensorEvent event) {

        synchronized (this) {
            Log.d("Acelerometro", String.valueOf(event.values[0]));

            switch (event.sensor.getType()) {

                case Sensor.TYPE_ACCELEROMETER:
                    float x = event.values[0];
                    float y = event.values[1];
                    float z = event.values[2];

                    double aceleracion = (x * x) + (y * y) + (z * z) - SensorManager.GRAVITY_EARTH;

                    if (aceleracion > VALOR_SENSOR_ACELEROMETRO_SHAKE) {
                        formularioActivity.leerDatos();
                    }
                    break;

                case Sensor.TYPE_LIGHT:
                    if(event.values[0]>VALOR_SENSOR_LUZ_FONDO_BLANCO && formularioActivity.isDarkTheme() == true)
                        formularioActivity.ponerFondoBlanco();
                    else if (event.values[0]<VALOR_SENSOR_LUZ_FONDO_BLANCO && formularioActivity.isDarkTheme() == false)
                        formularioActivity.ponerFondoNegro();
                    break;
            }
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

}
