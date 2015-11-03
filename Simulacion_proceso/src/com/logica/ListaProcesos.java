package com.logica;

import java.util.LinkedList;

public class ListaProcesos {

	private LinkedList<Proceso> colaProcesos;
			
	public ListaProcesos() {
		colaProcesos = new LinkedList<Proceso>();
	}
	
	public Proceso enviarProceso(){
		return colaProcesos.pollFirst();
		
	}
	
	public void recibirProceso(Proceso nuevoProceso){
		colaProcesos.addLast(nuevoProceso);
	}
	
	public boolean estadoLista(){
		return colaProcesos.isEmpty();
	}

	public LinkedList<Proceso> getColaProcesos() {
		return colaProcesos;
	}

	public void setColaProcesos(LinkedList<Proceso> colaProcesos) {
		this.colaProcesos = colaProcesos;
	}
	
}
