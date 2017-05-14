package concesionarioGUI.concesionarioGUI;

import java.awt.event.ActionListener;
import java.util.ListIterator;

import concesionarioGUI.funcionalidad.Coche;

import java.awt.event.ActionEvent;

public class Mostrar extends VentanaPadre {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ListIterator<Coche> iterador;
	private Coche cocheCopia;

	/**
	 * Create the dialog.
	 */
	public Mostrar() {
		super();
		setTitle("Mostrar concesionario");
		iterador = Principal.concesionario.getConcesionario().listIterator();
		btnAccion.setVisible(false);

		textField.setEnabled(false);

		rdbtnPlata.setEnabled(false);
		rdbtnRojo.setEnabled(false);
		rdbtnAzul.setEnabled(false);

		comboBoxMarca.setEnabled(false);
		comboBoxModelos.setEnabled(false);

		anterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarAnterior();
			}
		});

		siguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarSiguiente();
			}
		});
		actualizar();

	}

	void actualizar() {
		if (Principal.concesionario.size() == 1) {
			siguiente.setEnabled(false);
			anterior.setEnabled(false);
		} else {
			siguiente.setEnabled(true);
			anterior.setEnabled(false);
		}
		cocheCopia = iterador.next();
		
		mostrarCoche(cocheCopia);
	}

	private void mostrarSiguiente() {
		if (iterador.hasNext()) {
			cocheCopia = iterador.next();

			mostrarCoche(cocheCopia);

			comprobarBotones();
			if (!iterador.hasNext()) {
				iterador.previous();
			}
			
		}
	}

	private void mostrarAnterior() {
		if (iterador.hasPrevious()) {
			cocheCopia = iterador.previous();

			mostrarCoche(cocheCopia);

			comprobarBotones();
		}
		
		if (!iterador.hasPrevious()) {
			iterador.next();
		}
	}

	private void mostrarCoche(Coche coche) {
		textField.setText(coche.getMatricula());
		switch (coche.getColor()) {
		case PLATA:
			rdbtnPlata.setSelected(true);
			break;
		case ROJO:
			rdbtnRojo.setSelected(true);
			break;
		case AZUL:
			rdbtnAzul.setSelected(true);
		}
		comboBoxMarca.addItem(coche.getModelo().getMarca());
		comboBoxMarca.setSelectedItem(coche.getModelo().getMarca());
		comboBoxModelos.addItem(coche.getModelo());
		comboBoxModelos.setSelectedItem(coche.getModelo());
	}

	void comprobarBotones() {
		if (!iterador.hasNext()) {
			siguiente.setEnabled(false);
		} else {
			siguiente.setEnabled(true);
		}
		if (!iterador.hasPrevious()) {
			anterior.setEnabled(false);
		} else {
			anterior.setEnabled(true);
		}
	}

}