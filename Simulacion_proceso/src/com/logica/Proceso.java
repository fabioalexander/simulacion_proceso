package com.logica;

public class Proceso {
	private String nombre;
	private int tiempoEjecucion;
	private boolean bloqueo;
	
	public Proceso(String nombre, int tiempoEjecucion, boolean bloqueo) {
		this.nombre=nombre;
		this.tiempoEjecucion=tiempoEjecucion;
		this.bloqueo=bloqueo;
		
	}
	
	public Proceso(){
		
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

	public boolean isBloqueo() {
		return bloqueo;
	}

	public void setBloqueo(boolean bloqueo) {
		this.bloqueo = bloqueo;
	}	
	
}
