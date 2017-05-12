package concesionarioGUI.concesionarioGUI;

import javax.swing.JOptionPane;

import concesionarioGUI.funcionalidad.Coche;
import concesionarioGUI.funcionalidad.excepciones.CocheNoExisteException;
import concesionarioGUI.funcionalidad.excepciones.ListaVaciaException;
import concesionarioGUI.funcionalidad.excepciones.MatriculaNoValidaException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MostrarCocheMatricula extends VentanaPadre {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the dialog.
	 */
	public MostrarCocheMatricula() {
		super();
		setTitle("Mostrar por matricula");

		btnAccion.setText("Buscar");
		anterior.setVisible(false);
		siguiente.setVisible(false);

		rdbtnPlata.setEnabled(false);
		rdbtnRojo.setEnabled(false);
		rdbtnAzul.setEnabled(false);

		comboBoxMarca.setEnabled(false);
		comboBoxModelos.setEnabled(false);

		btnAccion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Coche coche;
				try {
					coche = Principal.concesionario.getCoche(textField.getText());
					mostrarCoche(coche);
				} catch (MatriculaNoValidaException | CocheNoExisteException | ListaVaciaException e1) {
					JOptionPane.showMessageDialog(contentPanel, "No existe ning�n coche con esa matricula.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	private void mostrarCoche(Coche coche) {
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

}