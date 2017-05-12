package concesionario.funcionalidad;

public enum Modelo {
	CORDOBA(Marca.SEAT), TOLEDO(Marca.SEAT), IBIZA(Marca.SEAT), SERIE1(Marca.BMW), SERIE2(Marca.BMW), SERIE3(
			Marca.BMW), SERIE5(Marca.BMW);

	private Marca marca;
	private static final Modelo[] VALUES = values();

	private Modelo(Marca marca) {
		this.marca = marca;
	}

	public Marca getMarca() {
		return marca;
	}

	public String toString() {
		return getMarca() + " " + name();
	}

	public String[] generarOpcionesMenu() {
		String[] opciones = new String[VALUES.length];

		int i = 0;
		for (Modelo modelo : VALUES) {
			opciones[i] = modelo.toString();
			i++;
		}

		return opciones;
	}

}
