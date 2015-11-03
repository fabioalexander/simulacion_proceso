package com.logica;

import java.util.LinkedList;

/**
 * Clase que contiene todas las acciones sobre la ejecuci�n de los procesos
 * 
 * @author Alfonso Calero, Fabio Rivera, Yeisson Rojas
 *
 */
public class AdministradorProcesos {

	/**
	 * Atributo de tipo Integer para controlar cu�nto tiempo m�ximo se puede ejecutar un proceso
	 */
	private int quantum;
	
	/**
	 * Atributo de tipo Proceso para controlar los procesos que se ejecutan
	 */
	private Proceso procesoEnEjecucion;
	
	/**
	 * Atributo de tipo ListaProcesos para controlar la lista de los procesos que se tiene
	 */
	private ListaProcesos listaProcesos;
	
	/**
	 * Atributo de tipo LinkedList para guardar los procesos que se finalizan
	 */
	private LinkedList<String> listaFinalizados;
	
	/**
	 * Atributo de tipo LinkedList para guardar los procesos que se ejecutan
	 */
	private LinkedList<String> listaEnProceso;
	
	/**
	 * Atributo de tipo LinkedList para guardar los procesos que se bloquean
	 */
	private LinkedList<String> listaBloqueo;	
	
	/**
	 * Atributo de tipo LinkedList para guardar el log de transacciones de los procesos
	 */
	private LinkedList<String> log;
	
	/**
	 * Constructor de la clase AdministradorProcesos
	 */
	public AdministradorProcesos() {
		this.quantum=10000;
		this.listaProcesos=new ListaProcesos();
		this.procesoEnEjecucion=null;	
		log = new LinkedList<String>();
		listaFinalizados = new LinkedList<String>();
		listaEnProceso = new LinkedList<String>();
		listaBloqueo = new LinkedList<String>();
	}
		
	/**
	 * M�todo que controla las acciones sobre los procesos, as� como los recorridos que hacen por cada una de las
	 * listas que se tienen
	 */
	public void enEjecucion(){
		while (!listaProcesos.estadoLista()) {
			procesoEnEjecucion=listaProcesos.enviarProceso();
			listaEnProceso.add(procesoEnEjecucion.getNombre());
			if (procesoEnEjecucion.isBloqueo()) {
				for (int i = 0; i < quantum; i++) {
					if (procesoEnEjecucion.getLanzamientoBloq()!= 0) {
						procesoEnEjecucion.setTiempoEjecucion(procesoEnEjecucion.getTiempoEjecucion()-1);
						log.add("Segundos en ejecuci�n del proceso "+procesoEnEjecucion.getNombre()+": "+(i+1));
						if (procesoEnEjecucion.getTiempoEjecucion()<=0) {
							i=quantum;
						}
					}else {
						i= quantum;						
					}
					procesoEnEjecucion.setLanzamientoBloq(procesoEnEjecucion.getLanzamientoBloq()-1);
				}
			if (procesoEnEjecucion.getDuracionBloq() > 0 && procesoEnEjecucion.getLanzamientoBloq()<=0) {
					int contador = procesoEnEjecucion.getDuracionBloq();
					listaBloqueo.add(procesoEnEjecucion.getNombre());
					for (int j = 0; j < contador ; j++) {
						procesoEnEjecucion.setDuracionBloq(procesoEnEjecucion.getDuracionBloq()-1);
						log.add("Segundos en bloqueo del proceso "+procesoEnEjecucion.getNombre()+": "+(j+1));
					}
					procesoEnEjecucion.setTiempoEjecucion(procesoEnEjecucion.getTiempoEjecucion()-procesoEnEjecucion.getDuracionBloq());
					for (int i = 0; i < procesoEnEjecucion.getTiempoEjecucion(); i++) {
						log.add("Segundos en ejecuci�n del proceso "+procesoEnEjecucion.getNombre()+": "+(i+1));
					}
					procesoEnEjecucion.setLanzamientoBloq(procesoEnEjecucion.getTiempoEjecucion());
				}
				if (procesoEnEjecucion.getTiempoEjecucion()<=0) {
					listaFinalizados.add(procesoEnEjecucion.getNombre());
					procesoEnEjecucion = null;
				}
			}else {
				for (int i = 0; i < quantum; i++) {
					procesoEnEjecucion.setTiempoEjecucion(procesoEnEjecucion.getTiempoEjecucion()-1);
					log.add("Segundos en ejecuci�n del proceso "+procesoEnEjecucion.getNombre()+": "+(i+1));
					if (procesoEnEjecucion.getTiempoEjecucion()<=0) {
						i=quantum;
					}
				}
				if (procesoEnEjecucion.getTiempoEjecucion()<=0) {
					listaFinalizados.add(procesoEnEjecucion.getNombre());
					procesoEnEjecucion = null;
				}else {
					listaProcesos.recibirProceso(procesoEnEjecucion);
					procesoEnEjecucion=null;
				}
			}
			
		}
	}
	
	/**
	 * M�todo get de la lista de Logs
	 * @return
	 */
	public LinkedList<String> getLog() {
		return log;
	}
	
	/**
	 * M�todo set de la lista de Logs
	 * @param log
	 */
	public void setLog(LinkedList<String> log) {
		this.log = log;
	}
	
	/**
	 * M�todo get de la lista de procesos
	 * @return
	 */
	public ListaProcesos getListaProcesos() {
		return listaProcesos;
	}
	
	/**
	 * M�todo set de la lista de procesos
	 * @param listaProcesos
	 */
	public void setListaProcesos(ListaProcesos listaProcesos) {
		this.listaProcesos = listaProcesos;
	}
	
	/**
	 * M�todo get de la lista de finalizados
	 * @return
	 */
	public LinkedList<String> getListaFinalizados() {
		return listaFinalizados;
	}
	
	/**
	 * M�todo set de la lista de finalizados
	 * @param listaFinalizados
	 */
	public void setListaFinalizados(LinkedList<String> listaFinalizados) {
		this.listaFinalizados = listaFinalizados;
	}
	
	/**
	 * M�todo get de la lista de procesos en ejecuci�n
	 * @return
	 */
	public LinkedList<String> getListaEnProceso() {
		return listaEnProceso;
	}
	
	/**
	 * M�todo set de la lista de procesos en ejecuci�n
	 * @param listaEnProceso
	 */
	public void setListaEnProceso(LinkedList<String> listaEnProceso) {
		this.listaEnProceso = listaEnProceso;
	}
	
	/**
	 * M�todo get de la lista de bloqueo
	 * @return
	 */
	public LinkedList<String> getListaBloqueo() {
		return listaBloqueo;
	}
	
	/**
	 * M�todo set de la lista de bloqueo
	 * @param listaBloqueo
	 */
	public void setListaBloqueo(LinkedList<String> listaBloqueo) {
		this.listaBloqueo = listaBloqueo;
	}
	
	/**
	 * M�todo get del quantum
	 * @return
	 */
	public int getQuantum() {
		return quantum;
	}
	
	/**
	 * M�todo set del quantum
	 * @param quantum
	 */
	public void setQuantum(int quantum) {
		this.quantum = quantum;
	}
}
