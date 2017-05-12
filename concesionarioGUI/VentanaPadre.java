package concesionarioGUI.concesionarioGUI;

import java.awt.BorderLayout;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import concesionarioGUI.funcionalidad.*;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.ButtonGroup;

public class VentanaPadre extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected final JPanel contentPanel = new JPanel();
	protected JTextField textField;
	protected JLabel lblMatrcula;
	protected JLabel lblCoche;
	protected JLabel lblModelo;
	protected JButton btnAccion;
	protected JButton cancelButton;
	protected JButton anterior;
	protected JButton siguiente;
	protected JComboBox<Modelo> comboBoxModelos;
	protected JComboBox<Marca> comboBoxMarca;
	protected JRadioButton rdbtnPlata;
	protected JRadioButton rdbtnRojo;
	protected JRadioButton rdbtnAzul;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Create the dialog.
	 */
	public VentanaPadre() {
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panelBotones = new JPanel();
		panelBotones.setBorder(new TitledBorder(null, "Color", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelBotones.setBounds(10, 137, 422, 37);
		contentPanel.add(panelBotones);
		panelBotones.setLayout(null);
		
		rdbtnPlata = new JRadioButton("Plata");
		buttonGroup.add(rdbtnPlata);
		rdbtnPlata.setBounds(113, 7, 62, 23);
		panelBotones.add(rdbtnPlata);
		
		rdbtnRojo = new JRadioButton("Rojo");
		buttonGroup.add(rdbtnRojo);
		rdbtnRojo.setBounds(177, 7, 62, 23);
		panelBotones.add(rdbtnRojo);
		
		rdbtnAzul = new JRadioButton("Azul");
		buttonGroup.add(rdbtnAzul);
		rdbtnAzul.setBounds(241, 7, 62, 23);
		panelBotones.add(rdbtnAzul);

		lblMatrcula = new JLabel("Matrícula");
		lblMatrcula.setBounds(89, 33, 70, 15);
		contentPanel.add(lblMatrcula);

		lblCoche = new JLabel("Marca");
		lblCoche.setBounds(89, 74, 70, 15);
		contentPanel.add(lblCoche);

		lblModelo = new JLabel("Modelo");
		lblModelo.setBounds(89, 108, 46, 14);
		contentPanel.add(lblModelo);



		btnAccion = new JButton("");
		btnAccion.setHorizontalAlignment(SwingConstants.RIGHT);
		btnAccion.setSize(92, 23);
		btnAccion.setLocation(203, 235);
		contentPanel.add(btnAccion);

		cancelButton = new JButton("Cancelar");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		cancelButton.setSize(100, 23);
		cancelButton.setLocation(307, 235);
		cancelButton.setActionCommand("Cancel");
		contentPanel.add(cancelButton);

		anterior = new JButton("<-");
		anterior.setBounds(195, 235, 50, 23);
		contentPanel.add(anterior);

		siguiente = new JButton("->");
		siguiente.setBounds(245, 235, 50, 23);
		contentPanel.add(siguiente);

		comboBoxModelos = new JComboBox<Modelo>();
		comboBoxModelos.setBounds(192, 104, 151, 22);
		contentPanel.add(comboBoxModelos);

		comboBoxMarca = new JComboBox<Marca>();
		comboBoxMarca.setBounds(192, 69, 151, 24);
		contentPanel.add(comboBoxMarca);

		textField = new JTextField();
		textField.setBounds(192, 31, 151, 19);
		textField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				if (!Coche.esValida(textField.getText())) {
					textField.setText(textField.getText());
					textField.setForeground(java.awt.Color.RED);
				}

			}

			@Override
			public void focusGained(FocusEvent arg0) {
				textField.setForeground(java.awt.Color.BLACK);
			}
		});
		contentPanel.add(textField);
		textField.setColumns(10);
	}
}
