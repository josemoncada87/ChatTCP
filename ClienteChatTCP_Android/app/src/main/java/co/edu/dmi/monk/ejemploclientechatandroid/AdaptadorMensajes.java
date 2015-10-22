package co.edu.dmi.monk.ejemploclientechatandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by josemoncada87 on 21/10/2015.
 */
public class AdaptadorMensajes extends ArrayAdapter<Mensaje> {

    public AdaptadorMensajes(Context context, ArrayList<Mensaje> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Mensaje msn = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.modelo_lista_usuario_mensaje, parent, false);
        }
        // Lookup view for data population
        TextView tvNombreUsuario = (TextView) convertView.findViewById(R.id.tv_txt_user_model);
        TextView tvMensajeUsuario = (TextView) convertView.findViewById(R.id.tv_txt_msn_model);
        // Populate the data into the template view using the data object
        tvNombreUsuario.setText(msn.getUsuario());
        tvMensajeUsuario.setText(msn.getMensaje());
        // Return the completed view to render on screen
        return convertView;
    }
}
