package com.logica;

import java.util.LinkedList;

public class AdministradorProcesos {

	private int quantum;
	private Proceso procesoEnEjecucion;
	private ListaProcesos listaProcesos;
	private LinkedList<String> listaFinalizados;
	private LinkedList<String> listaEnProceso;
	private LinkedList<String> listaBloqueo;	
	private LinkedList<String> log;
	
	public AdministradorProcesos() {
		this.quantum=10000;
		this.listaProcesos=new ListaProcesos();
		this.procesoEnEjecucion=null;	
		log = new LinkedList<String>();
		listaFinalizados = new LinkedList<String>();
		listaEnProceso = new LinkedList<String>();
		listaBloqueo = new LinkedList<String>();
	}
		
	public void enEjecucion(){
		while (!listaProcesos.estadoLista()) {
			procesoEnEjecucion=listaProcesos.enviarProceso();
			listaEnProceso.add(procesoEnEjecucion.getNombre());
			if (procesoEnEjecucion.isBloqueo()) {
				for (int i = 0; i < quantum; i++) {
					if (procesoEnEjecucion.getLanzamientoBloq()!= 0) {
						procesoEnEjecucion.setTiempoEjecucion(procesoEnEjecucion.getTiempoEjecucion()-1);
						log.add("Segundos en ejecución del proceso "+procesoEnEjecucion.getNombre()+": "+(i+1));
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
						log.add("Segundos en ejecución del proceso "+procesoEnEjecucion.getNombre()+": "+(i+1));
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
					log.add("Segundos en ejecución del bloqueo "+procesoEnEjecucion.getNombre()+": "+(i+1));
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
	
	public LinkedList<String> getLog() {
		return log;
	}
	public void setLog(LinkedList<String> log) {
		this.log = log;
	}
	public ListaProcesos getListaProcesos() {
		return listaProcesos;
	}
	public void setListaProcesos(ListaProcesos listaProcesos) {
		this.listaProcesos = listaProcesos;
	}
	public LinkedList<String> getListaFinalizados() {
		return listaFinalizados;
	}
	public void setListaFinalizados(LinkedList<String> listaFinalizados) {
		this.listaFinalizados = listaFinalizados;
	}
	public LinkedList<String> getListaEnProceso() {
		return listaEnProceso;
	}
	public void setListaEnProceso(LinkedList<String> listaEnProceso) {
		this.listaEnProceso = listaEnProceso;
	}
	public LinkedList<String> getListaBloqueo() {
		return listaBloqueo;
	}
	public void setListaBloqueo(LinkedList<String> listaBloqueo) {
		this.listaBloqueo = listaBloqueo;
	}
	public int getQuantum() {
		return quantum;
	}
	public void setQuantum(int quantum) {
		this.quantum = quantum;
	}
}
