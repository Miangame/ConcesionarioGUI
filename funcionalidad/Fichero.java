/**
 * 
 */
package concesionario.funcionalidad;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.regex.Pattern;

/**
 * Clase que opera con ficheros
 * 
 * @author Miguel Ã�ngel GavilÃ¡n Merino
 *
 */
public class Fichero implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Control de la extensiÃ³n del archivo
	 */
	private static Pattern patron = Pattern.compile("^((\\w)+(\\.obj))$");

	/**
	 * Nombre del fichero
	 */
	public static File archivo = new File("Sin_titulo");

	/**
	 * Crea un nuevo archivo segÃºn el nombre pasado por argumentos
	 * 
	 * @param archivo
	 */
	public static void setArchivo(String fichero) {
		Fichero.archivo = new File(fichero);
	}

	/**
	 * Devuelve el archivo
	 * 
	 * @return archivo
	 */
	public static File getArchivo() {
		return archivo;
	}

	/**
	 * Crea un nuevo archivo
	 */
	public static void nuevo() {
		setArchivo("Sin_titulo.obj");
	}

	/**
	 * Abre un fichero
	 * @return 
	 * 
	 * @return Contenido del fichero
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static Concesionario abrir(File archivo) throws FileNotFoundException, IOException, ClassNotFoundException {
		Concesionario concesionario = null;
		Object obj = null;
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(archivo))) {
			obj = in.readObject();
		}
		System.out.println(obj);
		if (obj instanceof Concesionario)
		return concesionario = (Concesionario) obj;
		
		return concesionario;
	}

	/**
	 * Guarda un objeto en un archivo
	 * @param concesionario 
	 * 
	 * @param objeto
	 *            objeto que vamos a guardar
	 * @throws IOException
	 */
	public static void guardar(Concesionario concesionario, File objeto) throws IOException {
		try (ObjectOutputStream output = new ObjectOutputStream(
				new BufferedOutputStream(new FileOutputStream(comprobarArchivo(objeto))))) {
			output.writeObject(concesionario);
		}
	}

	/**
	 * Comprueba el nombre del archivo
	 * 
	 * @param fichero2
	 *            nombre del archivo
	 * @return fichero
	 */
	public static File comprobarArchivo(File fichero) {
		if (patron.matcher(fichero.getName()).matches())
			return fichero;
		else
			setArchivo(fichero.getAbsolutePath() + ".obj");
		return fichero;
	}

}