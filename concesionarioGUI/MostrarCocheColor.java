package concesionarioGUI.concesionarioGUI;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.ListIterator;

import concesionarioGUI.funcionalidad.Coche;

public class MostrarCocheColor extends VentanaPadre {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ArrayList<Coche> concesionario;
	private ListIterator<Coche> iterador;

	/**
	 * Create the dialog.
	 */
	public MostrarCocheColor(ArrayList<Coche> concesionario) {
		super();
		setTitle("Mostrar por color");
		this.concesionario = concesionario;

		iterador = concesionario.listIterator();

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
		if (concesionario.size() == 1) {
			siguiente.setEnabled(false);
			anterior.setEnabled(false);
		} else {
			siguiente.setEnabled(true);
			anterior.setEnabled(false);
		}
		mostrarCoche(concesionario.get(0));
	}

	private void mostrarSiguiente() {
		if (iterador.hasNext()) {
			Coche cocheCopia = iterador.next();
			if (concesionario.indexOf(cocheCopia) < concesionario.size()-1) {
				cocheCopia = iterador.next();
				mostrarCoche(cocheCopia);
			} else {
				mostrarCoche(cocheCopia);
			}

			comprobarBotones();
		}
	}

	private void mostrarAnterior() {
		if (iterador.hasPrevious()) {
			Coche cocheCopia = iterador.previous();
			if (concesionario.indexOf(cocheCopia) > 1) {
				cocheCopia = iterador.previous();
				mostrarCoche(cocheCopia);
			} else {
				mostrarCoche(cocheCopia);
			}

			comprobarBotones();
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
		comboBoxMarca.removeAllItems();
		comboBoxMarca.addItem(coche.getModelo().getMarca());
		comboBoxModelos.removeAllItems();
		comboBoxModelos.addItem(coche.getModelo());
	}

	private void comprobarBotones() {
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