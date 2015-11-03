package com.logica;

import com.logica.EstadoProceso;

/**
 * Clase que contiene toda la información de los procesos
 * 
 * @author Alfonso Calero, Fabio Rivera, Yeisson Rojas
 *
 */
public class Proceso {

	/**
	 * Atributo de tipo String para el nombre del proceso
	 */
	private String nombre;
	
	/**
	 * Atributo de tipo EstadoProceso para el proceso
	 */
	private EstadoProceso estado;
	
	/**
	 * Atributo de tipo Integer para el tiempo de ejecución del proceso
	 */
	private int tiempoEjecucion;
	
	/**
	 * Atributo de tipo Boolean para el estado de bloqueo del proceso
	 */
	private boolean bloqueo;
	
	/**
	 * Atributo de tipo Integer para la duración de bloqueo del proceso
	 */
	private int duracionBloq;
	
	/**
	 * Atributo de tipo Integer para el tiempo en el que se lanza el bloqueo después de la ejecución del proceso
	 */
	private int lanzamientoBloq;
	
	/**
	 * Constructor al que le llegan los parámetros:
	 * @param nombre
	 * @param estado
	 * @param tiempoEjecucion
	 * @param bloqueo
	 */
	public Proceso(String nombre, EstadoProceso estado, int tiempoEjecucion, boolean bloqueo) {
		this.nombre=nombre;
		this.estado=estado;
		this.tiempoEjecucion=tiempoEjecucion;
		this.bloqueo=bloqueo;
	}
	
	/**
	 * Constructor al que le llegan todos los parámetros
	 * @param nombre
	 * @param estado
	 * @param tiempoEjecucion
	 * @param bloqueo
	 * @param duracionBloq
	 * @param lanzamientoBloq
	 */
	public Proceso(String nombre, EstadoProceso estado, int tiempoEjecucion, boolean bloqueo, int duracionBloq, int lanzamientoBloq){
		this.nombre=nombre;
		this.estado=estado;
		this.tiempoEjecucion=tiempoEjecucion;
		this.bloqueo=bloqueo;
		this.duracionBloq = duracionBloq;
		this.lanzamientoBloq = lanzamientoBloq;
	}

	/**
	 * Método get del nombre del proceso
	 * @return
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Método set del nombre del proceso
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Método get del estado del proceso
	 * @return
	 */
	public EstadoProceso getEstado() {
		return estado;
	}

	/**
	 * Método set del estado de los procesos
	 * @param estado
	 */
	public void setEstado(EstadoProceso estado) {
		this.estado = estado;
	}

	/**
	 * Método get del tiempo de ejecución del proceso
	 * @return
	 */
	public int getTiempoEjecucion() {
		return tiempoEjecucion;
	}

	/**
	 * Método set del tiempo de ejecución del proceso
	 * @param tiempoEjecucion
	 */
	public void setTiempoEjecucion(int tiempoEjecucion) {
		this.tiempoEjecucion = tiempoEjecucion;
	}

	/**
	 * Método boolean para el estado de bloqueo del proceso
	 * @return
	 */
	public boolean isBloqueo() {
		return bloqueo;
	}

	/**
	 * Método set del boolean del estado de bloqueo del proceso
	 * @param bloqueo
	 */
	public void setBloqueo(boolean bloqueo) {
		this.bloqueo = bloqueo;
	}

	/**
	 * Método get de la duración del bloqueo del proceso
	 * @return
	 */
	public int getDuracionBloq() {
		return duracionBloq;
	}

	/**
	 * Método set del tiempo de duración del bloqueo del proceso
	 * @param duracionBloq
	 */
	public void setDuracionBloq(int duracionBloq) {
		this.duracionBloq = duracionBloq;
	}
	
	/**
	 * Método get del tiempo del lanzamiento de bloqueo del proceso
	 * @return
	 */
	public int getLanzamientoBloq() {
		return lanzamientoBloq;
	}

	/**
	 * Método set del tiempo del lanzamiento de bloqueo del proceso
	 * @param lanzamientoBloq
	 */
	public void setLanzamientoBloq(int lanzamientoBloq) {
		this.lanzamientoBloq = lanzamientoBloq;
	}
	
}
