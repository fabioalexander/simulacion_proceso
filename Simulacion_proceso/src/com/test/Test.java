package com.test;

import com.gui.VentanaPrincipal;

/**
 * Clase que contiene el método main con el que se ejecuta la aplicación
 * 
 * @author Alfonso Calero, Fabio Rivera, Yeisson Rojas
 *
 */
public class Test {

	/**
	 * Método main que permite la ejecución de la aplicación
	 * @param args
	 */
	public static void main(String[] args) {
		/**
		 * Instanciamiento de la clase de la VentanaPrincipal
		 */
		VentanaPrincipal ventana=new VentanaPrincipal();
		
		/**
		 * Método que permite visualizar la aplicación
		 */
		ventana.setVisible(true);
	}
}
