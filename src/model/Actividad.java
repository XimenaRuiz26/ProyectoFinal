package model;

import structures.Cola;

public class Actividad {
	private String nombre; 
	private String descripcion; 
	private boolean obligatoria; 
	private Cola<Tarea> tareas;
	
	public Actividad(String nombre, String descripcion, boolean obligatoria, Cola<Tarea> tareas) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.obligatoria = obligatoria;
		this.tareas = tareas;
	}
	
	public Actividad(){
		super();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public boolean isObligatoria() {
		return obligatoria;
	}

	public void setObligatoria(boolean obligatoria) {
		this.obligatoria = obligatoria;
	}

	public Cola<Tarea> getTareas() {
		return tareas;
	}

	public void setTareas(Cola<Tarea> tareas) {
		this.tareas = tareas;
	}
	
	
	
	
}
