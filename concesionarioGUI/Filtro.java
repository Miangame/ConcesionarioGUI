package concesionarioGUI.concesionarioGUI;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author Miguel �?ngel Gavilán Merino
 */
public class Filtro extends FileFilter {
	/**
	 * Extensión del archivo
	 */
	private String extension;

	/**
	 * Descripción de la extensión
	 */
	private String description;

	public Filtro(String extension, String description) {
		this.extension = extension;
		this.description = description;
	}

	/**
	 * Comprueba si los archivos son válidos
	 * 
	 * @param file
	 *            Archivo
	 * @return devuelve true si el archivo contiene la extensión correcta, en
	 *         caso de que no la lleve se le añadira la extensión
	 */
	@Override
	public boolean accept(File file) {
		if (file.isDirectory()) {
			return true;
		}
		return file.getName().endsWith(extension);
	}

	public String getDescription() {
		return description + String.format(" (*%s)", extension);
	}

	public String getExtension() {
		return extension;
	}

}
