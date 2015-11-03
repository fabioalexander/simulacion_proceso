package com.test;

import com.gui.VentanaPrincipal;

/**
 * Clase que contiene el m�todo main con el que se ejecuta la aplicaci�n
 * 
 * @author Alfonso Calero, Fabio Rivera, Yeisson Rojas
 *
 */
public class Test {

	/**
	 * M�todo main que permite la ejecuci�n de la aplicaci�n
	 * @param args
	 */
	public static void main(String[] args) {
		/**
		 * Instanciamiento de la clase de la VentanaPrincipal
		 */
		VentanaPrincipal ventana=new VentanaPrincipal();
		
		/**
		 * M�todo que permite visualizar la aplicaci�n
		 */
		ventana.setVisible(true);
	}
}
