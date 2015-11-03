package com.logica;

import com.logica.EstadoP;

public class Proceso {

	private String nombre;
	private EstadoP estado;
	private int tiempoEjecucion;
	private boolean bloqueo;
	
	private int duracionBloq;
	private int lanzamientoBloq;
	
	public Proceso(String nombre, EstadoP estado, int tiempoEjecucion, boolean bloqueo) {
		this.nombre=nombre;
		this.estado=estado;
		this.tiempoEjecucion=tiempoEjecucion;
		this.bloqueo=bloqueo;
	}
	
	public Proceso(String nombre, EstadoP estado, int tiempoEjecucion, boolean bloqueo, int duracionBloq, int lanzamientoBloq){
		this.nombre=nombre;
		this.estado=estado;
		this.tiempoEjecucion=tiempoEjecucion;
		this.bloqueo=bloqueo;
		this.duracionBloq = duracionBloq;
		this.lanzamientoBloq = lanzamientoBloq;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public EstadoP getEstado() {
		return estado;
	}

	public void setEstado(EstadoP estado) {
		this.estado = estado;
	}

	public int getTiempoEjecucion() {
		return tiempoEjecucion;
	}

	public void setTiempoEjecucion(int tiempoEjecucion) {
		this.tiempoEjecucion = tiempoEjecucion;
	}

	public boolean isBloqueo() {
		return bloqueo;
	}

	public void setBloqueo(boolean bloqueo) {
		this.bloqueo = bloqueo;
	}


	public int getDuracionBloq() {
		return duracionBloq;
	}

	public void setDuracionBloq(int duracionBloq) {
		this.duracionBloq = duracionBloq;
	}

	public int getLanzamientoBloq() {
		return lanzamientoBloq;
	}

	public void setLanzamientoBloq(int lanzamientoBloq) {
		this.lanzamientoBloq = lanzamientoBloq;
	}
	
	
}
