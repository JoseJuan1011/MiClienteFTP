package dad.miclienteftp.resources;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.SocketException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class MainExample {
	
	@SuppressWarnings("serial")
	public static final Map<Integer, String> FILE_TYPE = new HashMap<Integer, String>() {{
		put(FTPFile.DIRECTORY_TYPE, "Directorio");
		put(FTPFile.FILE_TYPE, "Fichero");
		put(FTPFile.SYMBOLIC_LINK_TYPE, "Enlace");
	}};

	public static void main(String [] args) {
		try {
			
			// instanciar el cliente FTP
			FTPClient cliente = new FTPClient();
			
			// conectar con el servidor FTP
			cliente.connect("ftp.rediris.es", 21);
			
			// iniciar sesión anónima (login)
			cliente.login("", "");
			
			// cambiar el directorio actual en el servidor
			cliente.changeWorkingDirectory("/debian/dists");
			

			// recuperar el nombre del directorio actual
			String directorioActual = cliente.printWorkingDirectory();
			cliente.changeWorkingDirectory("/debian/dists/bookworm");
			directorioActual = cliente.printWorkingDirectory();
			System.out.println("Directorio actual: " + directorioActual);

			// recuperar un listado de los ficheros y directorios del directorio actual del servidor
			FTPFile [] ficheros = cliente.listFiles();
			
			// recorrer el listado de archivos recuperados
			System.out.format("+------------------------+%n");
			System.out.format("| Archivos del servidor: |%n");
			System.out.format("+------------------------+-----------------+----------------+-----------------+%n");
			System.out.format("| Nombre                                   | Tamaño (bytes) | Tipo            |%n");
			System.out.format("+------------------------------------------+----------------+-----------------+%n");
			Arrays.stream(ficheros)
				.forEach(fichero -> {
				    System.out.format("| %-40s | %-14d | %-15s |%n", fichero.getName(), fichero.getSize(), FILE_TYPE.get(fichero.getType()));
				});
			System.out.format("+------------------------------------------+----------------+-----------------+%n");
			
			// cambiar el directorio padre en el servidor
			cliente.changeWorkingDirectory("..");

			// descargar un fichero
			File descarga = new File("welcome.msg");
			FileOutputStream flujo = new FileOutputStream(descarga);
			cliente.retrieveFile("welcome.msg", flujo);
			flujo.flush();
			flujo.close();
			
			// desconectar del servidor
			cliente.disconnect();
			
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}