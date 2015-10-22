package co.edu.dmi.monk.ejemploclientechatandroid.actividades;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import co.edu.dmi.monk.ejemploclientechatandroid.AdaptadorMensajes;
import co.edu.dmi.monk.ejemploclientechatandroid.Comunicacion;
import co.edu.dmi.monk.ejemploclientechatandroid.Mensaje;
import co.edu.dmi.monk.ejemploclientechatandroid.R;

public class ActividadChat extends AppCompatActivity implements Observer {

    private ListView lista;
    private AdaptadorMensajes adaptadorElementos;
    private static final String TAG = "ActividadChat";
    private String nuevoMensaje;
    private String usuario;
    private String nuevoUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_chat);

        Intent creador = getIntent();
        usuario = creador.getStringExtra("user");

        nuevoMensaje = "";
        nuevoUsuario = "";

        // Asignacion del adaptador
        lista = (ListView) findViewById(R.id.lv_chat);
        ArrayList<Mensaje> mensajes = new ArrayList<>();
        adaptadorElementos = new AdaptadorMensajes(this, mensajes);
        lista.setAdapter(adaptadorElementos);

        Comunicacion.getInstance().addObserver(this);



        Button btnEnviarMensaje = (Button) findViewById(R.id.btn_enviar_mensaje_chat);
        btnEnviarMensaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText ed = (EditText) findViewById(R.id.ed_txt_mensaje); // obtengo el view del editText
                String texto = ed.getText().toString(); // obtengo el texto dentro del editable
                String mensaje = "mensaje_send:" + texto + ":" + usuario; // armo el mensaje
                Comunicacion.getInstance().enviar(mensaje); // envio el mensaje
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_actividad_chat, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void update(Observable observable, Object data) {
        String mensaje = (String) data;
        Log.d(TAG, "chat: " + observable.getClass() + " // " + mensaje);
        if (mensaje.contains("mensaje_send:")) {
            String[] partes = mensaje.split(":");
            nuevoMensaje = partes[1];
            nuevoUsuario = partes[2];
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Mensaje n = new Mensaje(nuevoUsuario, nuevoMensaje);
                    adaptadorElementos.add(n);
                    adaptadorElementos.notifyDataSetChanged();
                }
            });
        }
    }
}
