package concesionario.concesionarioGUI;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;

public class Ayuda extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the dialog.
	 */
	public Ayuda() {
		setResizable(false);
		setBounds(100, 100, 609, 384);
		getContentPane().setLayout(null);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 307, 607, 35);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
			{
				JButton okButton = new JButton("Aceptar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}

		JPanel panel1 = new JPanel();
		panel1.setBounds(12, 12, 583, 91);
		panel1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "A\u00F1adir", TitledBorder.LEADING, TitledBorder.TOP, null, Color.RED));
		getContentPane().add(panel1);
		panel1.setLayout(null);

		JLabel label1 = new JLabel();
		label1.setBounds(12, 12, 516, 70);

		label1.setText(
				"<html> <body> <p>Para añadir un coche hay que seguir una serie de reglas:</p><br/> <p>1.La matrícula debe componerse de 4 digitos,3 letras (sin vocales,Q o Ñ).</p> <p>2.El color, modelo y marca debe de asignarse uno de las diferentes opciones.</p> </body> </html>");
		panel1.add(label1);
		{
			JPanel panel2 = new JPanel();
			panel2.setBounds(12, 115, 583, 42);
			panel2.setBorder(new TitledBorder(null, "Eliminar", TitledBorder.LEADING, TitledBorder.TOP, null, Color.RED));
			getContentPane().add(panel2);
			panel2.setLayout(null);
			{
				JLabel label2 = new JLabel();
				label2.setBounds(12, 12, 559, 45);
				label2.setText(
						"<html>\n<body>\n<p>Para eliminar un coche hay que especificar la matrícula del coche.</p><br/>\n</body>\n</html>");
				panel2.add(label2);
			}
			{
				JPanel panel3 = new JPanel();
				panel3.setBounds(12, 170, 583, 91);
				panel3.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Buscar", TitledBorder.LEADING, TitledBorder.TOP, null, Color.RED));
				getContentPane().add(panel3);
				panel3.setLayout(null);
				{
					JLabel label3 = new JLabel();
					label3.setBounds(12, 12, 559, 67);
					label3.setText(
							"<html>\n<body>\n<p>Para buscar un coche hay dos formas posibles:</p><br/>\n<p>1.Buscar un coche por la matrícula.</p>\n<p>2.Buscar un grupo de coches por el color.</p>\n</body>\n</html>");
					panel3.add(label3);
				}
			}
			{
				JPanel panel4 = new JPanel();
				panel4.setBounds(12, 273, 583, 35);
				panel4.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Archivos", TitledBorder.LEADING, TitledBorder.TOP, null, Color.RED));
				getContentPane().add(panel4);
				panel4.setLayout(null);
				{
					JLabel label4 = new JLabel();
					label4.setBounds(12, 12, 559, 15);
					label4.setText(
							"<html>\n<body>\n<p>Este programa utiliza la extension de archivos .obj</p>\n</body>\n</html>");
					panel4.add(label4);
				}
			}
			{
				JLabel label2 = new JLabel();
				label2.setBounds(135, 0, 70, 15);

			}
		}
	}
}
