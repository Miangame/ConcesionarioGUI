package concesionarioGUI.concesionarioGUI;

import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import concesionarioGUI.funcionalidad.*;
import concesionarioGUI.funcionalidad.excepciones.CocheYaExisteException;
import concesionarioGUI.funcionalidad.excepciones.ColorNoValidoException;
import concesionarioGUI.funcionalidad.excepciones.MatriculaNoValidaException;
import concesionarioGUI.funcionalidad.excepciones.ModeloNoValidoException;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Principal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5773022840919578526L;
	static Concesionario concesionario = new Concesionario();
	JFileChooser jFileChooser = new JFileChooser();
	private Filtro filtro = new Filtro(".obj", "Objeto");

	private JPanel contentPane;
	private Ayuda ayuda = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Principal() {
		jFileChooser.setAcceptAllFileFilterUsed(false);
		jFileChooser.addChoosableFileFilter(filtro);
		
		crearCoches();
		
		setTitle(Fichero.getArchivo().getName());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFicheros = new JMenu("Ficheros");
		mnFicheros.setMnemonic('f');
		menuBar.add(mnFicheros);

		JMenuItem mntmNuevo = new JMenuItem("Nuevo");
		mntmNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nuevoArchivo();
			}
		});
		mntmNuevo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
		mnFicheros.add(mntmNuevo);

		JMenuItem mntmAbrir = new JMenuItem("Abrir");
		mntmAbrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirArchivo();
			}
		});
		mntmAbrir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		mnFicheros.add(mntmAbrir);

		JMenuItem mntmGuardar = new JMenuItem("Guardar");
		mntmGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardar();
			}
		});
		mntmGuardar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		mnFicheros.add(mntmGuardar);

		mnFicheros.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (concesionario.isModificado()) {
					mntmGuardar.setEnabled(true);
				} else {
					mntmGuardar.setEnabled(false);
				}
			}
		});

		JMenuItem mntmGuardarComo = new JMenuItem("Guardar como...");
		mntmGuardarComo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardarComo();
				concesionario.setModificado(false);
			}
		});
		mnFicheros.add(mntmGuardarComo);

		JSeparator separator_1 = new JSeparator();
		mnFicheros.add(separator_1);

		JMenuItem mntmSalir = new JMenuItem("Salir");
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (concesionario.isModificado()) {
					int respuesta = JOptionPane.showConfirmDialog(null, "No has guardado, �Desea Guardar?",
							"NO HAS GUARDADO", JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_CANCEL_OPTION);
					if (respuesta == JOptionPane.YES_OPTION) {
						guardarComo();
						JOptionPane.showMessageDialog(null, "Se ha guardado correctamente.");
						concesionario.setModificado(false);
						System.exit(0);
					} else if (respuesta == JOptionPane.CANCEL_OPTION) {
						dispose();
					} else {
						System.exit(0);
					}
				} else {
					System.exit(0);
				}

			}
		});
		mnFicheros.add(mntmSalir);

		JMenu mnCoches = new JMenu("Coches");
		mnCoches.setMnemonic('c');
		menuBar.add(mnCoches);

		JMenuItem mntmAadir = new JMenuItem("Alta");
		mntmAadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Alta alta = new Alta();
				alta.setVisible(true);
			}
		});
		mntmAadir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
		mnCoches.add(mntmAadir);

		JMenuItem mntmBorrar = new JMenuItem("Baja");
		mntmBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Baja baja = new Baja();
				baja.setVisible(true);
			}
		});
		mntmBorrar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, InputEvent.CTRL_MASK));
		mnCoches.add(mntmBorrar);

		JMenuItem mntmMostrarConcesionario = new JMenuItem("Mostrar concesionario");
		mntmMostrarConcesionario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (concesionario.isEmpty()) {
					JOptionPane.showMessageDialog(getParent(), "El concesionario está vacío", "ERROR",
							JOptionPane.ERROR_MESSAGE);
				} else {
					Mostrar mostrar = new Mostrar();
					mostrar.setVisible(true);
				}

			}
		});
		mntmMostrarConcesionario.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
		mnCoches.add(mntmMostrarConcesionario);

		JMenuItem mntmNmeroDeCoches = new JMenuItem("Número de coches");
		mntmNmeroDeCoches.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(contentPane, "Hay " + concesionario.size() + " coches",
						"Número de coches", JOptionPane.PLAIN_MESSAGE);
			}
		});
		mnCoches.add(mntmNmeroDeCoches);

		JSeparator separator = new JSeparator();
		mnCoches.add(separator);

		JMenu mnBuscarPor = new JMenu("Buscar por");
		mnCoches.add(mnBuscarPor);

		JMenuItem mntmMatrcula = new JMenuItem("Matr\u00EDcula");
		mntmMatrcula.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_MASK));
		mntmMatrcula.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MostrarCocheMatricula mostrarCocheMatricula = new MostrarCocheMatricula();
				mostrarCocheMatricula.setVisible(true);
			}
		});
		mnBuscarPor.add(mntmMatrcula);

		JMenuItem mntmColor = new JMenuItem("Color");
		mntmColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ElegirColor elegirColor = new ElegirColor();
				elegirColor.setVisible(true);
			}
		});
		mnBuscarPor.add(mntmColor);

		JMenu mnNewMenu = new JMenu("Ayuda");
		mnNewMenu.setMnemonic('a');
		menuBar.add(mnNewMenu);

		JMenuItem mntmVerAyuda = new JMenuItem("Ver ayuda");
		mntmVerAyuda.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
		mntmVerAyuda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (ayuda == null) {
					ayuda = new Ayuda();
					ayuda.setVisible(true);
				} else {
					ayuda.setVisible(true);
				}
			}
		});
		mnNewMenu.add(mntmVerAyuda);

		JMenuItem mntmAcercaDe = new JMenuItem("Acerca de...");
		mntmAcercaDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AcercaDe acercaDe = new AcercaDe();
				acercaDe.setVisible(true);
			}
		});
		mnNewMenu.add(mntmAcercaDe);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

	}// Principal

	private void guardar() {
		if (getTitle() == "Sin t�tulo") {
			guardarComo();
		} else {
			try {
				Fichero.guardar(concesionario, new File(getTitle() + ".obj"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void nuevoArchivo() {
		if (concesionario.isModificado()) {
			if (JOptionPane.showConfirmDialog(null, "Has hecho cambios... �seguro que quieres abrir un nuevo fichero?",
					"Concesionario modificado", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				concesionario = new Concesionario();
				setTitle("Sin titulo");
			}
		} else {
			concesionario = new Concesionario();
			setTitle("Sin titulo");
		}
	}

	private void abrirArchivo() {
		if (jFileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			try {
				concesionario = Fichero.abrir(jFileChooser.getSelectedFile());
				setTitle(Fichero.getArchivo().getName());
			} catch (ClassNotFoundException e) {
				JOptionPane.showMessageDialog(null, "El fichero no es un concesionario", "Error",
						JOptionPane.INFORMATION_MESSAGE);
			} catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(null, "Error al abrir el fichero", "Error",
						JOptionPane.INFORMATION_MESSAGE);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Error", "Error de entrada/salida",
						JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}

	private void guardarComo() {

		if (jFileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
			File file = new File(jFileChooser.getSelectedFile().getAbsolutePath());
			if (!file.exists() || comprobarSiExiste(file)) {
				try {
					Fichero.guardar(concesionario, jFileChooser.getSelectedFile());
					setTitle(Fichero.getArchivo().getName() + ".obj");
				} catch (FileNotFoundException e) {
					JOptionPane.showMessageDialog(null, "Error al guardar", "Error", JOptionPane.INFORMATION_MESSAGE);
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "Error al guardar", "Error", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}
	}

	private boolean comprobarSiExiste(File file) {
		if (file.exists()) {
			int sobreescribir = JOptionPane.showConfirmDialog(null, "Ya existe ese archivo. �Desea sobreescribir?",
					"Sobreescribir", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (sobreescribir == JOptionPane.YES_OPTION) {
				return true;
			}
		}
		return false;
	}
	
	private void crearCoches() {
		try {
			concesionario.annadir("1234qws", Modelo.CORDOBA, Color.PLATA);
			concesionario.annadir("1224qws", Modelo.IBIZA, Color.PLATA);
			concesionario.annadir("1214qws", Modelo.SERIE1, Color.PLATA);
			concesionario.annadir("1111qws", Modelo.SERIE5, Color.PLATA);
			concesionario.annadir("1234qqq", Modelo.CORDOBA, Color.AZUL);
			concesionario.annadir("1114qqq", Modelo.TOLEDO, Color.AZUL);
			concesionario.annadir("1444www", Modelo.CORDOBA, Color.ROJO);
		} catch (CocheYaExisteException | MatriculaNoValidaException | ModeloNoValidoException
				| ColorNoValidoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
