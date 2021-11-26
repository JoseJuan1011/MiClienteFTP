package dad.miclienteftp.resources;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.net.ftp.FTPFile;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class FTPColumns {

	@SuppressWarnings("serial")
	public static final Map<Integer, String> FILE_TYPE = new HashMap<Integer, String>() {{
		put(FTPFile.DIRECTORY_TYPE, "Directorio");
		put(FTPFile.FILE_TYPE, "Fichero");
		put(FTPFile.SYMBOLIC_LINK_TYPE, "Enlace");
	}};
	
	private StringProperty nombre;

	private IntegerProperty tamaño;

	private StringProperty tipo;

	public FTPColumns() {
		nombre = new SimpleStringProperty();
		tamaño = new SimpleIntegerProperty();
		tipo = new SimpleStringProperty();
	}
	
	public FTPColumns(String nombre, int tamaño, String tipo) {
		this.nombre = new SimpleStringProperty(nombre);
		this.tamaño = new SimpleIntegerProperty(tamaño);
		this.tipo = new SimpleStringProperty(tipo);
	}
	
	public static FTPColumns[] toFTPColumnsArray(FTPFile[] ficheros) {
		FTPColumns[] filas = new FTPColumns[ficheros.length];
		for (int i=0;i<ficheros.length;i++) {
			filas[i] = new FTPColumns(ficheros[i].getName(),(int) ficheros[i].getSize(), FILE_TYPE.get(ficheros[i].getType()));
		}
		return filas;
	}

	public final StringProperty nombreProperty() {
		return this.nombre;
	}

	public final String getNombre() {
		return this.nombreProperty().get();
	}

	public final void setNombre(final String nombre) {
		this.nombreProperty().set(nombre);
	}

	public final IntegerProperty tamañoProperty() {
		return this.tamaño;
	}

	public final int getTamaño() {
		return this.tamañoProperty().get();
	}

	public final void setTamaño(final int tamaño) {
		this.tamañoProperty().set(tamaño);
	}

	public final StringProperty tipoProperty() {
		return this.tipo;
	}

	public final String getTipo() {
		return this.tipoProperty().get();
	}

	public final void setTipo(final String tipo) {
		this.tipoProperty().set(tipo);
	}

}
