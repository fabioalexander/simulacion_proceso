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
	 * M�todo de tipo Proceso para enviar procesos a una cola
	 * @return
	 */
	public Proceso enviarProceso(){
		return colaProcesos.pollFirst();	
	}
	
	/**
	 * M�todo para recibir un nuevo proceso en la cola
	 * @param nuevoProceso
	 */
	public void recibirProceso(Proceso nuevoProceso){
		colaProcesos.addLast(nuevoProceso);
	}
	
	/**
	 * M�todo para ver el estado de la lista que se tiene
	 * @return
	 */
	public boolean estadoLista(){
		return colaProcesos.isEmpty();
	}

	/**
	 * M�todo get para la cola de procesos
	 * @return
	 */
	public LinkedList<Proceso> getColaProcesos() {
		return colaProcesos;
	}
	
	/**
	 * M�todo set para la cola de procesos
	 * @param colaProcesos
	 */
	public void setColaProcesos(LinkedList<Proceso> colaProcesos) {
		this.colaProcesos = colaProcesos;
	}
}
