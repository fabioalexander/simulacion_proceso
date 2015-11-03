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
import com.logica.EstadoP;
import com.logica.Proceso;

public class VentanaPrincipal extends JFrame {
	
	private JPanel añadirProcesoPanel;
	private JPanel listaProcesosPanel;
	private JPanel logPanel;

	private JLabel lblAñadir;
	private JLabel lblNombreProceso;
	private JLabel lblDuracionProceso;
	private JTextField jtfNombreProceso;
	private JTextField jtfDuracionProceso;
	private JCheckBox jcbBloqueado;
	private JLabel lblDuracionBloq;
	private JLabel lblLanzamientoBloq;
	private JTextField jtfDuracionBloq;
	private JTextField jtfLanzamientoBloq;
	private JButton btnAgregarProceso;

	private JButton btnEjecutar;
	private JList<String> listProcesos;
	private JScrollPane scrollProcesos;

	private JScrollPane scrollLog;
	private JList<String> listLog;

	private AdministradorProcesos administradorP;

	public VentanaPrincipal() {
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
											EstadoP.NUEVO,
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
										EstadoP.NUEVO, Integer
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

	public void actualizarListas() {
		DefaultListModel<String> modelo = new DefaultListModel<>();
		for (int i = 0; i < administradorP.getLog().size(); i++) {
			modelo.add(i, administradorP.getLog().get(i));
		}
		listLog.setModel(modelo);
	}

	public boolean validacionLanzamientoBloq() {
		int a = Integer.parseInt(jtfLanzamientoBloq.getText());
		int b = Integer.parseInt(jtfDuracionProceso.getText());

		if (a < b) {
			return true;
		} else
			return false;
	}

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
	public void limpiarListas(){
		DefaultListModel model = new DefaultListModel();
		listLog.setModel(model);
		repaint();
	}
	public AdministradorProcesos getAdministradorP() {
		return administradorP;
	}

	public void setAdministradorP(AdministradorProcesos administradorP) {
		this.administradorP = administradorP;
	}

}	