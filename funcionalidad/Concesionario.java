package concesionarioGUI.funcionalidad;

import java.io.Serializable;
import java.util.ArrayList;

import concesionarioGUI.funcionalidad.excepciones.CocheNoExisteException;
import concesionarioGUI.funcionalidad.excepciones.CocheYaExisteException;
import concesionarioGUI.funcionalidad.excepciones.ColorNoValidoException;
import concesionarioGUI.funcionalidad.excepciones.ListaVaciaException;
import concesionarioGUI.funcionalidad.excepciones.MatriculaNoValidaException;
import concesionarioGUI.funcionalidad.excepciones.ModeloNoValidoException;

/*
 * No pueden existir dos coches con la misma matrícula en el almacén del concesinario
 * no puede añadirse un coche al concecionario con alguno de sus atributos inválidos. Han de conocerse todas sus características 
 * Ninguno de los valores puede ser por defecto
 */
/**
 * Representa un concesionario de coches.
 * 
 * Lógicamente, no podrá añadirse un coche inválido almacén del concesinario)
 * 
 * Han de conocerse todas sus características Ninguno de los valores puede ser
 * por defecto
 * 
 * @author Miguel �?ngel Gavilán Merino
 * 
 */
public class Concesionario implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Lista con coches
	 */
	private ArrayList<Coche> lista = new ArrayList<Coche>();
	private final String LISTA_VACIA = "El concesionario está vacío";
	private boolean modificado = false;

	/**
	 * Añade un coche al concesionario
	 * 
	 * @param matricula
	 * @param modelo
	 * @param color
	 * @throws CocheYaExisteException
	 * @throws MatriculaNoValidaException
	 * @throws ModeloNoValidoException
	 * @throws ColorNoValidoException
	 */
	public void annadir(String matricula, Modelo modelo, Color color)
			throws CocheYaExisteException, MatriculaNoValidaException, ModeloNoValidoException, ColorNoValidoException {

		Coche coche = new Coche(matricula, modelo, color);

		if (lista.contains(coche))
			throw new CocheYaExisteException("El coche ya existe");

		lista.add(coche);
		setModificado(true);
	}

	/**
	 * Elimina un coche del concesionario
	 * 
	 * @param matricula
	 * @throws CocheNoExisteException
	 * @throws MatriculaNoValidaException
	 * @throws ListaVaciaException
	 */
	public void eliminar(String matricula)
			throws CocheNoExisteException, MatriculaNoValidaException, ListaVaciaException {
		if (isEmpty())
			throw new ListaVaciaException(LISTA_VACIA);

		if (!lista.remove(new Coche(matricula)))
			throw new CocheNoExisteException("El coche no existe");

		setModificado(true);
	}

	public boolean isEmpty() {
		return lista.isEmpty();
	}

	/**
	 * Obtiene un coche del concesionario a través de la matrícula
	 * 
	 * @param matricula
	 * @return Coche del concesionario
	 * @throws MatriculaNoValidaException
	 * @throws CocheNoExisteException
	 * @throws ListaVaciaException
	 */
	public Coche getCoche(String matricula)
			throws MatriculaNoValidaException, CocheNoExisteException, ListaVaciaException {
		if (isEmpty())
			throw new ListaVaciaException(LISTA_VACIA);
		try {
			return lista.get(lista.indexOf(new Coche(matricula)));
		} catch (IndexOutOfBoundsException e) {
			throw new CocheNoExisteException("El coche no existe");
		}
	}

	public ArrayList<Coche> getConcesionario() {
		return lista;
	}

	/**
	 * Devuelve una cadena con los coches del color deseado
	 * 
	 * @param color
	 * @return candena con los coches de un color
	 * @throws ListaVaciaException
	 * @throws CocheNoExisteException
	 */
	public ArrayList<Coche> getCocheColor(Color color) throws ListaVaciaException, CocheNoExisteException {
		if (isEmpty())
			throw new ListaVaciaException(LISTA_VACIA);

		ArrayList<Coche> cadena = new ArrayList<Coche>();
		for (Coche coche : lista) {
			if (coche.getColor() == color) {
				cadena.add(coche);
			}
		}
		if (cadena.size() == 0) {
			throw new CocheNoExisteException("No se ha encontrado ningún coche");
		}
		return cadena;
	}

	/**
	 * Número de coches del concesionario
	 * 
	 * @return tamaño de la lista
	 */
	public int size() {
		return lista.size();
	}

	public void setModificado(boolean modificado) {
		this.modificado = modificado;
	}

	public boolean isModificado() {
		return modificado;
	}

	@Override
	public String toString() {
		StringBuilder cadena = new StringBuilder();
		int i = 0;
		if (isEmpty()) {
			cadena.append("\nEl concesionario está vacio\n");
		}
		for (Coche coche : lista) {
			cadena.append("\n" + (i + 1) + ". " + coche);
			i++;
		}

		return cadena.toString();
	}

	public Coche get(int index) {
		if (lista.isEmpty())
			return null;
		if (index < 0 | index > lista.size() - 1)
			return null;
		return lista.get(index);
	}

	public int indexOf(Coche cocheCopia) {
		return lista.indexOf(cocheCopia);
	}

}
