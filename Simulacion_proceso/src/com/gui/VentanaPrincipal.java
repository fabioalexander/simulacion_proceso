package com.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.logica.Proceso;

public class VentanaPrincipal extends JFrame implements Runnable{
	
	 private JLabel nombre;
	 private JLabel tiempoEjecucion;
	 private JLabel cronometro;
	 
	 private JTextField nombreTexto;
	 private JTextField tiempoTexto;
	 
	 private JButton iniciar;
	 private JButton detener;
	 private JButton reanudar;
		
	 private Proceso proceso;
		
	 private Thread hilo;
	 private boolean ejecucion;
	 private boolean paused = false;
	 private boolean stopped = false;
    
	 public VentanaPrincipal(){ 
		 this.setTitle("Simulación de Procesos");
		 this.setSize( 350, 270 );
		 this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		 this.setLayout(null);
		 this.setLocationRelativeTo(null);
		
		 this.proceso=new Proceso();

		 this.nombre=new JLabel("Nombre de Proceso");
		 this.nombre.setBounds(20, 20, 150, 30);
		
		 this.tiempoEjecucion=new JLabel("Tiempo de Ejecución");
		 this.tiempoEjecucion.setBounds(20, 60, 150, 30);
		
		 this.nombreTexto=new JTextField();
		 this.nombreTexto.setBounds(180, 20, 130, 30);
	
		 this.tiempoTexto=new JTextField();
		 this.tiempoTexto.setBounds(180, 60, 130, 30);
		
		 this.iniciar = new JButton("Iniciar");
		 this.iniciar.setBounds(123, 100, 100, 30);
		 this.iniciar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				iniciarCronometro();
			}
		});
		 
		 this.cronometro = new JLabel("Tiempo restante de ejecución: 0 segundos");
		 this.cronometro.setHorizontalAlignment(JLabel.CENTER);
		 this.cronometro.setBounds(0, 130, 330, 50);
        
		 this.detener = new JButton("Detener");
		 this.detener.setBounds(40, 180, 100, 30);
		 this.detener.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		 });
		 
		 this.reanudar = new JButton("Reanudar");
		 this.reanudar.setBounds(195, 180, 100, 30);
		 this.reanudar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		 });
		 
		 this.add(nombre);
		 this.add(nombreTexto);
		 this.add(tiempoEjecucion);
		 this.add(tiempoTexto);
		 this.add(cronometro);
		 this.add(iniciar);  
		 this.add(detener);
		 this.add(reanudar);
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

	 public synchronized void stop() {
		 stopped = true;
		 notify();
	 }
	 
	 public void run(){
		 proceso=new Proceso(nombreTexto.getText(),Integer.parseInt(tiempoTexto.getText()));
		 String textoCronometro="";
		 
		 try{
			 while(ejecucion){
				 Thread.sleep(1000);
				 proceso.setTiempoEjecucion(proceso.getTiempoEjecucion()-1);
                 
				 if(proceso.getTiempoEjecucion() == 0){
					 JOptionPane.showMessageDialog(null, "Proceso '"+proceso.getNombre()+"' finalizado");
				 }
				 else textoCronometro = String.valueOf(proceso.getTiempoEjecucion());
                 
				 cronometro.setText("Tiempo restante de ejecución: "+textoCronometro+" segundos");                
            }
		
        }catch(Exception e){}
        cronometro.setText("Tiempo restante de ejecución: 0 segundos" );
    }
}