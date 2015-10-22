package co.edu.dmi.monk.ejemploclientechatandroid;

/**
 * Created by 1130613425 on 21/10/2015.
 */
public class Mensaje {

    private String usuario;
    private String mensaje;

    public Mensaje(String usuario, String mensaje) {
        this.usuario = usuario;
        this.mensaje = mensaje;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getMensaje() {
        return mensaje;
    }
}
