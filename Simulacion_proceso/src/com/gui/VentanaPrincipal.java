package com.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import com.logica.AdministradorProcesos;
import com.logica.EstadoProceso;
import com.logica.Proceso;

/**
 * Clase que contiene todos los componentes gráficos de la aplicación
 * 
 * @author Alfonso Calero, Fabio Rivera, Yeisson Rojas
 *
 */
public class VentanaPrincipal extends JFrame {
	
	/**
	 * Atributo del panel de añadir proceso
	 */
	private JPanel añadirProcesoPanel;
	
	/**
	 * Atributo del panel de listar los procesos que se van a ejecutar
	 */
	private JPanel listaProcesosPanel;
	
	/**
	 * Atributo del panel del log de transacciones de los procesos
	 */
	private JPanel logPanel;

	/**
	 * Atributo de tipo label del título del panel de añadir
	 */
	private JLabel lblAñadir;
	
	/**
	 * Atributo de tipo label del nombre del proceso
	 */
	private JLabel lblNombreProceso;
	
	/**
	 * Atributo de tipo label de la duración de ejecución del proceso
	 */
	private JLabel lblDuracionProceso;
	
	/**
	 * Atributo de tipo TextField del nombre del proceso
	 */
	private JTextField jtfNombreProceso;
	
	/**
	 * Atributo de tipo TextField de la duración de ejecución del proceso
	 */
	private JTextField jtfDuracionProceso;
	
	/**
	 * Atributo de tipo CheckBox para saber si tiene o no bloqueo el proceso
	 */
	private JCheckBox jcbBloqueado;
	
	/**
	 * Atributo de tipo label de la duración del bloqueo del proceso
	 */
	private JLabel lblDuracionBloq;
	
	/**
	 * Atributo de tipo label del tiempo que dura el proceso en ejecución antes de ser bloqueado
	 */
	private JLabel lblLanzamientoBloq;
	
	/**
	 * Atributo de tipo TextField de la duración del bloqueo del proceso
	 */
	private JTextField jtfDuracionBloq;
	
	/**
	 * Atributo de tipo TextField de la duración de ejecución del proceso antes de ser bloqueado
	 */
	private JTextField jtfLanzamientoBloq;
	
	/**
	 * Atributo de tipo Button para agregar procesos
	 */
	private JButton btnAgregarProceso;

	/**
	 * Atributo de tipo Button para ejecutar los procesos de la lista
	 */
	private JButton btnEjecutar;
	
	/**
	 * Atributo de tipo JList para los procesos en ejecución
	 */
	private JList<String> listProcesos;
	
	/**
	 * Atributo de tipo ScrollPane para los procesos en ejecución
	 */
	private JScrollPane scrollProcesos;

	/**
	 * Atributo de tipo ScrollPane para el log de transacciones
	 */
	private JScrollPane scrollLog;
	
	/**
	 * Atributo de tipo List para los log de transacciones
	 */
	private JList<String> listLog;

	/**
	 * Atributo de tipo AdministradorProcesos
	 */
	private AdministradorProcesos administradorP;

	/**
	 * Constructor de la clase donde se inicializan todos los atributos anteriores, se define cómo será la ventana
	 * y se añaden componentes a la misma
	 */
	public VentanaPrincipal(){
		this.setTitle("Procesos");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(null);
		//this.setLocationRelativeTo(null);
		this.setSize(950,700);
		
		administradorP = new AdministradorProcesos();
	
		this.añadirProcesoPanel = new JPanel();
		this.añadirProcesoPanel.setBounds(30, 5, 310, 242);
		this.añadirProcesoPanel.setLayout(null);
		this.añadirProcesoPanel.setBorder(new TitledBorder("Añadir Proceso"));
		
		this.lblAñadir = new JLabel("Añadir Procesos a la lista",SwingConstants.CENTER);
		this.lblAñadir.setBounds(0, 15, 310, 21);
		
		this.lblNombreProceso = new JLabel("Nombre Proceso");
		this.lblNombreProceso.setBounds(20, 52, 101, 21);
		
		this.lblDuracionProceso = new JLabel("Tiempo Ejecución");
		this.lblDuracionProceso.setBounds(20, 91, 106, 21);
		
		this.jtfNombreProceso = new JTextField();
		this.jtfNombreProceso.setBounds(151, 49, 135, 26);
		
		this.jtfDuracionProceso = new JTextField();
		this.jtfDuracionProceso.setBounds(151, 88, 135, 26);
		
		this.jcbBloqueado = new JCheckBox("Bloqueo");
		this.jcbBloqueado.setBounds(20, 127, 80, 22);
		
		this.lblDuracionBloq = new JLabel("Duración bloqueo: ");
		this.lblDuracionBloq.setBounds(110, 164, 130, 21);
		
		this.lblLanzamientoBloq = new JLabel("Bloquear después de: ");
		this.lblLanzamientoBloq.setBounds(110, 128, 130, 21);
		
		this.jtfDuracionBloq = new JTextField();
		this.jtfDuracionBloq.setBounds(241, 125, 45, 26);
		this.jtfDuracionBloq.setEnabled(false);
		
		this.jtfLanzamientoBloq = new JTextField();
		this.jtfLanzamientoBloq.setBounds(241, 161, 45, 26);
		this.jtfLanzamientoBloq.setEnabled(false);
		
		this.btnAgregarProceso = new JButton("Agregar Proceso");
		this.btnAgregarProceso.setBounds(80, 200, 150, 27);

		this.jcbBloqueado.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (jcbBloqueado.isSelected() == true) {
					jtfDuracionBloq.setEnabled(true);
					jtfLanzamientoBloq.setEnabled(true);
				} else {
					jtfDuracionBloq.setEnabled(false);
					jtfLanzamientoBloq.setEnabled(false);
				}
			}
		});
		
		this.btnAgregarProceso.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (jcbBloqueado.isSelected()) {
					if (validacionNumeros(jtfDuracionProceso.getText())
							&& validacionNumeros(jtfDuracionBloq.getText())
							&& validacionNumeros(jtfLanzamientoBloq.getText())) {

						if (validacionLanzamientoBloq() == true) {
							administradorP.getListaProcesos().recibirProceso(
									new Proceso("Proceso "
											+ jtfNombreProceso.getText(),
											EstadoProceso.NUEVO,
											Integer.parseInt(jtfDuracionProceso
													.getText()), jcbBloqueado
													.isSelected(), Integer
													.parseInt(jtfDuracionBloq
															.getText()),
											Integer.parseInt(jtfLanzamientoBloq
													.getText())));
							jtfNombreProceso.setText("");
							jtfDuracionProceso.setText("");
							jtfDuracionBloq.setText("");
							jtfLanzamientoBloq.setText("");
							actualizarLista();
						} else {
							JOptionPane
									.showMessageDialog(
											null,
											"El valor de lanzamiento del bloqueo no puede ser mayor o igual a la duracion del proceso");
						}
					} else {
						JOptionPane
								.showMessageDialog(null,
										"Ingrese como maximo 3 digitos en los campos requeridos");
					}

				} else {
					if (validacionNumeros(jtfDuracionProceso.getText())) {
						administradorP.getListaProcesos().recibirProceso(
								new Proceso("Proceso "
										+ jtfNombreProceso.getText(),
										EstadoProceso.NUEVO, Integer
												.parseInt(jtfDuracionProceso
														.getText()),
										jcbBloqueado.isSelected()));
						jtfNombreProceso.setText("");
						jtfDuracionProceso.setText("");
						jtfDuracionBloq.setText("");
						jtfLanzamientoBloq.setText("");
						actualizarLista();
					} else {
						JOptionPane
								.showMessageDialog(null,
										"Ingrese como maximo 3 digitos en el campo de duracion del proceso");
					}
				}

			}
		});

		this.añadirProcesoPanel.add(lblAñadir);
		this.añadirProcesoPanel.add(lblNombreProceso);
		this.añadirProcesoPanel.add(lblDuracionProceso);
		this.añadirProcesoPanel.add(jtfNombreProceso);
		this.añadirProcesoPanel.add(jtfDuracionProceso);
		this.añadirProcesoPanel.add(jcbBloqueado);
		this.añadirProcesoPanel.add(lblDuracionBloq);
		this.añadirProcesoPanel.add(lblLanzamientoBloq);
		this.añadirProcesoPanel.add(jtfDuracionBloq);
		this.añadirProcesoPanel.add(jtfLanzamientoBloq);
		this.añadirProcesoPanel.add(btnAgregarProceso);
		
		this.listaProcesosPanel = new JPanel();
		this.listaProcesosPanel.setBounds(30, 260, 310, 395);
		this.listaProcesosPanel.setLayout(null);
		this.listaProcesosPanel.setBorder(new TitledBorder("Lista de Procesos"));
		
		this.btnEjecutar = new JButton("Ejecutar");

		this.listProcesos = new JList<String>();
		this.listProcesos.setBorder(new LineBorder(Color.GRAY));

		this.scrollProcesos = new JScrollPane(listProcesos);
		this.scrollProcesos.setBounds(18, 20, 275, 325);
		this.scrollProcesos.getViewport().setView(listProcesos);
		
		this.btnEjecutar.setBounds(105, 360, 100, 27);
		
		this.btnEjecutar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				limpiarListas();
				administradorP.enEjecucion();
				actualizarListas();
			}
		});

		this.listaProcesosPanel.add(btnEjecutar);
		this.listaProcesosPanel.add(scrollProcesos);

		this.logPanel = new JPanel();
		this.logPanel.setBounds(360, 5, 555, 650);
		this.logPanel.setLayout(null);
		this.logPanel.setBorder(new TitledBorder("Log panel"));

		this.listLog = new JList<String>();
		this.scrollLog = new JScrollPane(listLog);
		this.scrollLog.getViewport().setView(listLog);
		this.scrollLog.setBounds(16, 20, 530, 620);
		this.listLog.setBorder(new LineBorder(Color.GRAY));

		this.logPanel.add(scrollLog);
		
		this.add(añadirProcesoPanel);
		this.add(listaProcesosPanel);
		this.add(logPanel);
	}

	/**
	 * Método que sirve para actualizar la lista de Procesos en ejecución
	 */
	public void actualizarLista() {
		DefaultListModel<String> modelo = new DefaultListModel<>();
		for (int i = 0; i < administradorP.getListaProcesos().getColaProcesos()
				.size(); i++) {
			modelo.add(i, administradorP.getListaProcesos().getColaProcesos()
					.get(i).getNombre()+" Tiempo: "+administradorP.getListaProcesos().getColaProcesos()
					.get(i).getTiempoEjecucion());
		}
		listProcesos.setModel(modelo);
		repaint();
	}

	/**
	 * Método que sirve para actualizar la lista del log de transacciones
	 */
	public void actualizarListas() {
		DefaultListModel<String> modelo = new DefaultListModel<>();
		for (int i = 0; i < administradorP.getLog().size(); i++) {
			modelo.add(i, administradorP.getLog().get(i));
		}
		listLog.setModel(modelo);
	}

	/**
	 * Método que sirve para la validar que el tiempo de lanzamiento de bloqueo no exceda la duración del proceso
	 * en ejecución 
	 * @return
	 */
	public boolean validacionLanzamientoBloq() {
		int a = Integer.parseInt(jtfLanzamientoBloq.getText());
		int b = Integer.parseInt(jtfDuracionProceso.getText());

		if (a < b) {
			return true;
		} else
			return false;
	}

	/**
	 * Método para la validación de los campos numéricos
	 * @param item
	 * @return
	 */
	public boolean validacionNumeros(String item) {
		String Cadena = String.valueOf(item);

		Pattern patron = Pattern.compile("^[0-9]{1,3}$");
		Matcher mat = patron.matcher(Cadena);
		if (mat.matches() != false) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Método que sirver para limpiar las listas
	 */
	public void limpiarListas(){
		DefaultListModel model = new DefaultListModel();
		listLog.setModel(model);
		repaint();
	}
	
	/**
	 * Método get de la instancia de la clase de Administrador de Procesos
	 * @return
	 */
	public AdministradorProcesos getAdministradorP() {
		return administradorP;
	}

	/**
	 * Método set de la instanciia de la clase de Administrador de Procesos
	 * @param administradorP
	 */
	public void setAdministradorP(AdministradorProcesos administradorP) {
		this.administradorP = administradorP;
	}
}	