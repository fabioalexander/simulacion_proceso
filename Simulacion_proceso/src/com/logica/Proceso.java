package com.logica;

public class Proceso {
	private String nombre;
	private int tiempoEjecucion;
	
	public Proceso(String nombre, int tiempoEjecucion) {
		this.nombre=nombre;
		this.tiempoEjecucion=tiempoEjecucion;
	}
	
	public Proceso(){
		this.nombre=null;
		this.tiempoEjecucion=0;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getTiempoEjecucion() {
		return tiempoEjecucion;
	}

	public void setTiempoEjecucion(int tiempoEjecucion) {
		this.tiempoEjecucion = tiempoEjecucion;
	}
}
