package dad.miclienteftp.controller;

import org.apache.commons.net.ftp.FTPClient;

import dad.miclienteftp.resources.FTPColumns;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ConectarModel {

	private StringProperty servidor;

	private IntegerProperty puerto;

	private StringProperty usuario;

	private StringProperty contraseña;

	private ObjectProperty<FTPClient> clienteFTP;

	private ObjectProperty<FTPColumns[]> ficheros;

	private BooleanProperty conectado;

	public ConectarModel() {
		servidor = new SimpleStringProperty();
		puerto = new SimpleIntegerProperty();
		usuario = new SimpleStringProperty();
		contraseña = new SimpleStringProperty();
		clienteFTP = new SimpleObjectProperty<FTPClient>();
		ficheros = new SimpleObjectProperty<FTPColumns[]>();
		conectado = new SimpleBooleanProperty();
	}

	public final StringProperty servidorProperty() {
		return this.servidor;
	}

	public final String getServidor() {
		return this.servidorProperty().get();
	}

	public final void setServidor(final String servidor) {
		this.servidorProperty().set(servidor);
	}

	public final IntegerProperty puertoProperty() {
		return this.puerto;
	}

	public final int getPuerto() {
		return this.puertoProperty().get();
	}

	public final void setPuerto(final int puerto) {
		this.puertoProperty().set(puerto);
	}

	public final StringProperty usuarioProperty() {
		return this.usuario;
	}

	public final String getUsuario() {
		return this.usuarioProperty().get();
	}

	public final void setUsuario(final String usuario) {
		this.usuarioProperty().set(usuario);
	}

	public final StringProperty contraseñaProperty() {
		return this.contraseña;
	}

	public final String getContraseña() {
		return this.contraseñaProperty().get();
	}

	public final void setContraseña(final String contraseña) {
		this.contraseñaProperty().set(contraseña);
	}

	public final ObjectProperty<FTPClient> clienteFTPProperty() {
		return this.clienteFTP;
	}

	public final FTPClient getClienteFTP() {
		return this.clienteFTPProperty().get();
	}

	public final void setClienteFTP(final FTPClient clienteFTP) {
		this.clienteFTPProperty().set(clienteFTP);
	}

	public final ObjectProperty<FTPColumns[]> ficherosProperty() {
		return this.ficheros;
	}

	public final FTPColumns[] getFicheros() {
		return this.ficherosProperty().get();
	}

	public final void setFicheros(final FTPColumns[] ficheros) {
		this.ficherosProperty().set(ficheros);
	}

	public final BooleanProperty conectadoProperty() {
		return this.conectado;
	}
	
	public final boolean isConectado() {
		return this.conectadoProperty().get();
	}
	
	public final void setConectado(final boolean conectado) {
		this.conectadoProperty().set(conectado);
	}
}
