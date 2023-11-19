package model;

import java.util.ArrayList;
import java.util.Iterator;

import structures.Lista;
import structures.Nodo;

public class Proceso {
	private String id; 
	private String nombre; 
	private String descripcion; 
	private Lista<Actividad> actividades;
	
	public Proceso(String id, String nombre, Lista<Actividad> actividades, String descripcion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.actividades = actividades;
		this.descripcion = descripcion;
		
	}
	
	public Proceso(){
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Lista<Actividad> getActividades() {
		return actividades;
	}

	public void setActividades(Lista<Actividad> actividades) {
		this.actividades = actividades;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

//	public ArrayList<String> obtenerActividades() {
//		ArrayList<String> listaActividades = new ArrayList <String>();
//		Nodo<Actividad> actual = actividades.getNodoPrimero();
//		while (actual != null) {
//			Actividad actividad = (Actividad) actual.getValorNodo();
//			listaActividades.add(actividad.getNombre());
//			actual = actual.getSiguienteNodo();
//		}
//		return listaActividades;
//	}
	
	public ArrayList<String> obtenerActividades() {
	    ArrayList<String> listaActividades = new ArrayList<>();
	    if (actividades != null) {
	        Nodo<Actividad> actual = actividades.getNodoPrimero();
	        while (actual != null) {
	            Actividad actividad = actual.getValorNodo();
	            if (actividad != null) {
	                listaActividades.add(actividad.getNombre());
	            } else {
	                System.out.println("Se encontró una actividad nula.");
	            }
	            actual = actual.getSiguienteNodo();
	        }
	    } else {
	        System.out.println("La lista de actividades es null.");
	    }
	    return listaActividades;
	}

	public boolean crearActividad(String nombreA, String descripcion2, String preceder, String seleccion) {
		Actividad actividad = new Actividad();
		actividad.setNombre(nombreA);
		actividad.setDescripcion(descripcion2);
		actividad.setObligatoria(verificarObligatoria(seleccion));
		if (actividades == null) {
	        actividades = new Lista<Actividad>(); 
	    }
		if(verificarActividad(nombreA)== true){
			return false;
		}else{
			insertarAntesDe(preceder, actividad);
			imprimirLista();
			return true;
		}
	}
	public void imprimirLista() {

		Nodo<Actividad> aux = actividades.getNodoPrimero();

		while (aux != null) {
			System.out.print(aux.getValorNodo().getNombre() + "\t");
			aux = aux.getSiguienteNodo();
		}

		System.out.println();
	}
	
//	public void insertarAntesDe(String actividadExistente, Actividad nuevaActividad) {
//		Nodo<Actividad> cabeza = actividades.getNodoPrimero();
//		Nodo<Actividad> nuevo = new Nodo<Actividad>(nuevaActividad);
//		if (actividadExistente == null) {
//	        nuevo.setSiguienteNodo(cabeza);
//	        actividades.setNodoPrimero(nuevo);
//		}
//	    if (cabeza == null) {
//	        cabeza = nuevo;
//	    } else if (cabeza.getValorNodo().getNombre().equals(actividadExistente)) {
//	        nuevo.setSiguienteNodo(cabeza);
//	        cabeza = nuevo;
//	    } else {
//	        Nodo<Actividad> previo = null;
//	        Nodo<Actividad> actual = cabeza;
//
//	        while (actual != null && !actual.getValorNodo().getNombre().equals(actividadExistente)) {
//	            previo = actual;
//	            actual = actual.getSiguienteNodo();
//	        }
//
//	        if (actual != null) {
//	            nuevo.setSiguienteNodo(actual);
//	            previo.setSiguienteNodo(nuevo);
//	        } else {
//	            System.out.println("La actividad " + actividadExistente + " no existe en la lista.");
//	        }
//	    }
//	}
//	
	public void insertarAntesDe(String actividadExistente, Actividad nuevaActividad) {
	    Nodo<Actividad> cabeza = actividades.getNodoPrimero();
	    Nodo<Actividad> nuevo = new Nodo<>(nuevaActividad);

	    if (cabeza == null) {
	        actividades.setNodoPrimero(nuevo);
	    } else if (cabeza.getValorNodo().getNombre().equals(actividadExistente)) {
	        nuevo.setSiguienteNodo(cabeza);
	        actividades.setNodoPrimero(nuevo);
	    } else {
	        Nodo<Actividad> previo = null;
	        Nodo<Actividad> actual = cabeza;

	        while (actual != null && !actual.getValorNodo().getNombre().equals(actividadExistente)) {
	            previo = actual;
	            actual = actual.getSiguienteNodo();
	        }

	        if (actual != null) {
	            nuevo.setSiguienteNodo(actual);
	            previo.setSiguienteNodo(nuevo);
	        } else {
	            System.out.println("La actividad " + actividadExistente + " no existe en la lista.");
	        }
	    }
	}

	private boolean verificarActividad(String nombreA) {
		if(nombreA== null || actividades.estaVacia()){
			return false;
		}else{
			Iterator<Actividad> iterator = actividades.iterator();
	        while (iterator.hasNext()) {
	            Actividad aux = iterator.next();
	            if (aux.getNombre().equals(nombreA)) {
	                return true;
	            }
	        }
		}
		return false;
	}

	private boolean verificarObligatoria(String seleccion) {
		if(seleccion.equals("Si")){
			return true;
		}
		else if(seleccion.equals("No")){
			return false;
		}
		return false;
	}
}
