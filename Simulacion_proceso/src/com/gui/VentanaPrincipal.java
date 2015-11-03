package com.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.security.auth.callback.TextOutputCallback;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.logica.Proceso;

/**
 * Clase que cuenta con los componentes de la interfaz gráfica y que administra el hilo del proceso
 * 
 * @author Alfonso Calero, Fabio Rivera, Yeisson Rojas
 *
 */
public class VentanaPrincipal extends JFrame implements Runnable{
	
	private JLabel nombre;
	private JLabel tiempoEjecucion;
	private JLabel cronometro;
	private JLabel nombreProcesoEjecucion;
	 
	private JTextField nombreTexto;
	private JTextField tiempoTexto;
	 
	private JButton agregar;
	private JButton iniciar;
	private JButton detener;
	private JButton reanudar;
		
	private Proceso proceso;
		
	private Thread hilo;
	private boolean ejecucion;
	private boolean paused = false;
    
	private ArrayList<Proceso> listaProcesos;
	
	public VentanaPrincipal(){ 
		this.setTitle("Simulación de Procesos");
		this.setSize( 350, 300 );
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		
		this.proceso=new Proceso();
		
		this.listaProcesos=new ArrayList<Proceso>();
		
		this.nombre=new JLabel("Nombre de Proceso");
		this.nombre.setBounds(20, 20, 150, 30);
		
		this.tiempoEjecucion=new JLabel("Tiempo de Ejecución");
		this.tiempoEjecucion.setBounds(20, 60, 150, 30);
		
		this.nombreTexto=new JTextField();
		this.nombreTexto.setBounds(180, 20, 130, 30);
	
		this.tiempoTexto=new JTextField();
		this.tiempoTexto.setBounds(180, 60, 130, 30);
		
		this.agregar=new JButton("Agregar Proceso");
		this.agregar.setBounds(98, 100, 150, 30);
		this.agregar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					listaProcesos.add(new Proceso(nombreTexto.getText(),Integer.parseInt(tiempoTexto.getText())));
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Llene los campos con la información solicitada");
				}
				
			}
		});
		
		this.iniciar = new JButton("Iniciar");
		this.iniciar.setBounds(123, 135, 100, 30);
		this.iniciar.addActionListener(new ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent e) {
				iniciarCronometro();
			}
		});
		 
		this.nombreProcesoEjecucion = new JLabel("Proceso en ejecución:");
		this.nombreProcesoEjecucion.setHorizontalAlignment(JLabel.CENTER);
		this.nombreProcesoEjecucion.setBounds(0, 165, 330, 30);
        
		this.cronometro = new JLabel("Tiempo restante de ejecución: 0 segundos");
		this.cronometro.setHorizontalAlignment(JLabel.CENTER);
		this.cronometro.setBounds(0, 195, 330, 30);
		
		this.detener = new JButton("Detener");
		this.detener.setBounds(40, 225, 100, 30);
		this.detener.addActionListener(new ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent e) {
				pause();
			}
		});
		 
		this.reanudar = new JButton("Reanudar");
		this.reanudar.setBounds(195, 225, 100, 30);
		this.reanudar.addActionListener(new ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent e) {
				resume();
			}
		});
		 
		this.add(nombre);
		this.add(nombreTexto);
		this.add(tiempoEjecucion);
		this.add(tiempoTexto);
		this.add(nombreProcesoEjecucion);
		this.add(cronometro);
		this.add(iniciar);  
		this.add(detener);
		this.add(reanudar);
		this.add(agregar);
	}
  
	public void iniciarCronometro() {
		ejecucion = true;
		hilo = new Thread(this);
		hilo.start();
	}
	  
	public synchronized void pause() {
		paused = true;
	}

	public synchronized void resume() {
		paused = false;
		notify();
	}
	 
	public void run(){
		String textoCronometro="";
		String textoProceso="";
//		for (int i = 0; i < listaProcesos.size(); i++) {
//			proceso=listaProcesos.get(i);
//		}
		
		while (ejecucion) {
			for (int i = 0; i < listaProcesos.size(); i++) {
				proceso=listaProcesos.get(i);
				try {
					synchronized (this) {
						textoProceso = proceso.getNombre();
						nombreProcesoEjecucion.setText("Proceso en ejecución: "+textoProceso);
						textoCronometro = String.valueOf(proceso.getTiempoEjecucion());
						cronometro.setText("Tiempo restante de ejecución: "+textoCronometro+" segundos");	 
						if (paused) {
							wait();
						}
					}
					Thread.sleep(1000);
					proceso.setTiempoEjecucion(proceso.getTiempoEjecucion()-1);
					if(proceso.getTiempoEjecucion()<=0){
						paused=true;
						JOptionPane.showMessageDialog(null,"Proceso '"+proceso.getNombre()+"' finalizado");
					}
				} 
				catch(Exception e){}
					cronometro.setText("Tiempo restante de ejecución: 0 segundos" );
		 	}
		}
	}
}