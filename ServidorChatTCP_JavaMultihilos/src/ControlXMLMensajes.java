import processing.core.PApplet;
import processing.data.XML;

public class ControlXMLMensajes {
	private XML usuarios;
	private PApplet app;

	public ControlXMLMensajes(PApplet app) {
		this.app = app;

		try {
			usuarios = app.loadXML("../data/BD_mensajes.xml");
		} catch (Exception e) {
			usuarios = app.parseXML("<mensajes></mensajes>");
		}

	}

	public void agregarMensaje(String usuario, String mensaje) {
		XML hijo = app.parseXML("<mensaje usuario=\"" + usuario + "\">"
				+ mensaje + "</mensaje>");
		usuarios.addChild(hijo);
		app.saveXML(usuarios, "../data/BD_mensajes.xml");
	}

}
