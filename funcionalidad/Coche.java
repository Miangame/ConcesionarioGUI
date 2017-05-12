package concesionario.funcionalidad;

import java.io.Serializable;
import java.util.regex.Pattern;

import concesionario.funcionalidad.excepciones.ColorNoValidoException;
import concesionario.funcionalidad.excepciones.MatriculaNoValidaException;
import concesionario.funcionalidad.excepciones.ModeloNoValidoException;

/**
 * Representa a un coche.
 * <p>
 * Un coche tendrá las siguientes características:
 * <ol>
 * <li>Color. Se limitarán los colores a tres: plata, rojo y azul.</li>
 * 
 * 
 * <li>Modelo. Se limitarán los modelos de coches a siete: Córdoba (marca
 * Seat),Toledo (marca Seat),Ibiza (marca Seat), Serie 1 (marca BMW), Serie 2
 * (marca BMW),Serie 3 (marca BMW) y Serie 5 (marca BMW). Para solicitar el
 * modelo al dar de alta al coche podrá implementarse un método pedirModelo que
 * mediante la gestión de un menú, devolverá el modelo indicado.</li>
 * 
 * 
 * <li>Matrícula (única por coche) El formato de las matrículas será el nuevo:
 * combinación de cuatro números (de 0000 a 9999) y tres letras, comenzando por
 * BBB y terminando por ZZZ, excluyendo vocales, la LL, la Ñ y la Q.</li>
 * 
 * 
 * <ol>
 * <li>Matrículas válidas: 0000-BBB, 0000 BBB, 0000BBB, 1234ZZZ.</li>
 * <li>Matrículas inválidas: 000_BBB, 9999-BBQ, 0000-BÑB, 0000-DEF, 0000 bbb,
 * 0000 AAA</li>
 * </ol>
 * </ol>
 * 
 * @author Miguel �?ngel Gavilán Merino
 * 
 */
public class Coche implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Matrícula del coche
	 */
	private String matricula;

	/**
	 * Modelo del coche
	 */
	private Modelo modelo;

	/**
	 * Color del coche
	 */
	private Color color;

	/**
	 * Expresión regular para que la matrícula del coche sea válida
	 */
	private static final Pattern patronMatricula = Pattern.compile("^\\d{4}[ -]?[a-zA-Z&&[^aeiouAEIOUÑQ]]{3}$");

	/**
	 * Constructor para crear un coche por la matrícula, modelo y color. Si
	 * alguno de estos no es válido, lanza una excepción
	 * 
	 * @param matricula
	 * @param modelo
	 * @param color
	 * @throws MatriculaNoValidaException
	 * @throws ModeloNoValidoException
	 * @throws ColorNoValidoException
	 */
	public Coche(String matricula, Modelo modelo, Color color)
			throws MatriculaNoValidaException, ModeloNoValidoException, ColorNoValidoException {
		setMatricula(matricula);
		setModelo(modelo);
		setColor(color);
	}

	/**
	 * Constructor que crea un coche solo por la matrícula. Si la matrícula es
	 * inválida, se lanza una excepción
	 * 
	 * @param matricula
	 * @throws MatriculaNoValidaException
	 */
	public Coche(String matricula) throws MatriculaNoValidaException {
		setMatricula(matricula);
	}

	public String getMatricula() {
		return matricula;
	}

	/**
	 * Asigna una matrícula al coche de nuestra clase, si es inválida se lanza
	 * una excepción
	 * 
	 * @param matricula
	 * @throws MatriculaNoValidaException
	 */
	private void setMatricula(String matricula) throws MatriculaNoValidaException {
		if (!esValida(matricula))
			throw new MatriculaNoValidaException("La matrícula no es válida");

		this.matricula = estandarizarMatricula(matricula);
	}

	/**
	 * Transforma el formato de una matrícula para que las podamos tratar a
	 * todas por igual
	 * 
	 * @param matricula
	 * @return matrícula válida
	 */
	private String estandarizarMatricula(String matricula) {
		matricula.replaceAll("[ -]", "");
		matricula.replaceAll("[a-z]", "[A-Z]");
		return matricula;
	}

	/**
	 * Comprueba si una matrícula es o no válida mediante la expresión regular
	 * 
	 * @param matricula
	 * @return true si la matrícula es válida, false si no lo es
	 */
	public static boolean esValida(String matricula) {
		return patronMatricula.matcher(matricula).matches();
	}

	public Modelo getModelo() {
		return modelo;
	}

	/**
	 * Asigna un modelo al coche de nuestra clase. Lanza una excepción si el
	 * modelo no es válido
	 * 
	 * @param modelo
	 * @throws ModeloNoValidoException
	 */
	private void setModelo(Modelo modelo) throws ModeloNoValidoException {
		if (modelo == null)
			throw new ModeloNoValidoException("El modelo no es válido");
		this.modelo = modelo;
	}

	public Color getColor() {
		return color;
	}

	/**
	 * Asigna un color al coche de nuestra clase. Lanza una excepción si el
	 * color no es válido
	 * 
	 * @param color
	 * @throws ColorNoValidoException
	 */
	private void setColor(Color color) throws ColorNoValidoException {
		if (modelo == null)
			throw new ColorNoValidoException("El color no es válido");
		this.color = color;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((matricula == null) ? 0 : matricula.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coche other = (Coche) obj;
		if (matricula == null) {
			if (other.matricula != null)
				return false;
		} else if (!matricula.equals(other.matricula))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Coche con matricula " + getMatricula() + ", color " + getColor() + " y modelo " + getModelo() + "\n";
	}

}
