package concesionario.concesionarioGUI;

import concesionario.funcionalidad.excepciones.CocheNoExisteException;
import concesionario.funcionalidad.excepciones.ListaVaciaException;
import concesionario.funcionalidad.excepciones.MatriculaNoValidaException;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;

public class Baja extends VentanaPadre {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the dialog.
	 */
	public Baja() {
		super();
		rdbtnAzul.setEnabled(false);
		rdbtnRojo.setEnabled(false);
		rdbtnPlata.setEnabled(false);
		comboBoxModelos.setEnabled(false);
		comboBoxMarca.setEnabled(false);
		setModal(true);

		contentPanel.setBorder(
				new TitledBorder(null, "Baja de coches", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		contentPanel.setLayout(null);

		anterior.setVisible(false);
		siguiente.setVisible(false);

		btnAccion.setText("Eliminar");
		btnAccion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					Principal.concesionario.eliminar(textField.getText());
					JOptionPane.showMessageDialog(contentPanel, "Coche eliminado correctamente", "Info",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (CocheNoExisteException | MatriculaNoValidaException | ListaVaciaException e1) {
					JOptionPane.showMessageDialog(contentPanel, e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				}
				dispose();
			}
		});

	}
}
