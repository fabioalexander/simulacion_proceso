package com.logica;

import java.util.ArrayList;

/**
 * Clase que guarda la informaci�n del proceso que se ejecuta
 * @author Alfonso Calero, Fabio Rivera, Yeisson Rojas
 *
 */
public class Proceso {
	
	/**
	 * Atributo de tipo String del nombre del proceso
	 */
	private String nombre;
	
	/**
	 * Atributo de tipo Integer del tiempo que se va a ejecutar el proceso
	 */
	private int tiempoEjecucion;
	
	/**
	 * Constructor de la clase Proceso. Recibe por par�metro el nombre y el tiempo de ejecuci�n
	 * @param nombre
	 * @param tiempoEjecucion
	 */
	public Proceso(String nombre, int tiempoEjecucion) {
		this.nombre=nombre;
		this.tiempoEjecucion=tiempoEjecucion;
	}
	
	/**
	 * Constructor por defecto de la clase Proceso
	 */
	public Proceso(){
		this.nombre=null;
		this.tiempoEjecucion=0;
	}

	public String toString(){
		return "Nombre: "+nombre+" Tiempo de Ejecuci�n: "+tiempoEjecucion;
	}
	/**
	 * M�todo get del nombre del proceso
	 * @return nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * M�todo set del nombre del proceso
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * M�todo get del tiempo de ejecuci�n del proceso
	 * @return nombre
	 */
	public int getTiempoEjecucion() {
		return tiempoEjecucion;
	}

	/**
	 * M�todo set del tiempo de ejecuci�n del proceso
	 * @param tiempoEjecucion
	 */
	public void setTiempoEjecucion(int tiempoEjecucion) {
		this.tiempoEjecucion = tiempoEjecucion;
	}
}
