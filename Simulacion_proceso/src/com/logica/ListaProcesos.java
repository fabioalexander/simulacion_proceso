package com.logica;

import java.util.LinkedList;

/**
 * Clase que contiene la lista de procesos y las diferentes acciones sobre esta
 * 
 * @author Alfonso Calero, Fabio Rivera, Yeisson Rojas
 *
 */
public class ListaProcesos {

	/**
	 * Lista de procesos que contiene la cola de procesos
	 */
	private LinkedList<Proceso> colaProcesos;
			
	/**
	 * Constructor por defecto de la clase ListaProcesos
	 */
	public ListaProcesos() {
		colaProcesos = new LinkedList<Proceso>();
	}
	
	/**
	 * Método de tipo Proceso para enviar procesos a una cola
	 * @return
	 */
	public Proceso enviarProceso(){
		return colaProcesos.pollFirst();	
	}
	
	/**
	 * Método para recibir un nuevo proceso en la cola
	 * @param nuevoProceso
	 */
	public void recibirProceso(Proceso nuevoProceso){
		colaProcesos.addLast(nuevoProceso);
	}
	
	/**
	 * Método para ver el estado de la lista que se tiene
	 * @return
	 */
	public boolean estadoLista(){
		return colaProcesos.isEmpty();
	}

	/**
	 * Método get para la cola de procesos
	 * @return
	 */
	public LinkedList<Proceso> getColaProcesos() {
		return colaProcesos;
	}
	
	/**
	 * Método set para la cola de procesos
	 * @param colaProcesos
	 */
	public void setColaProcesos(LinkedList<Proceso> colaProcesos) {
		this.colaProcesos = colaProcesos;
	}
}
