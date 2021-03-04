/*Crea la clase Prestamos, en el paquete adecuado, con los atributos y métodos especificados en el diagrama y la visibilidad adecuada.
 *Recuerda que los métodos get devolverán una copia profunda de la colección resultante. Se permitirá prestar(insertar) elementos al final
 *de la colección sin admitir repetidos, devolver, buscar y borrar desplazando los elementos hacia la izquierda para dejar el array compactado.
 *Asegurate de que se pasan todos los tests asociados a esta clase. Realiza el commit correspondiente.
 */

package org.iesalandalus.programacion.biblioteca.mvc.modelo.negocio;

import java.time.LocalDate;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Alumno;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Libro;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Prestamo;

public class Prestamos {

	//Atributos
	private int capacidad;
	private int tamano;
	private Prestamo[] coleccionPrestamos;

	//Metodos
	public Prestamos(int capacidad) throws IllegalArgumentException, NullPointerException {
		if (capacidad <= 0) {
			throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
		}
		this.capacidad = capacidad;
		coleccionPrestamos = new Prestamo[capacidad];
		this.tamano = 0;
	}

	public Prestamo[] get() {
		return copiaProfundaPrestamos();
	}

	private Prestamo[] copiaProfundaPrestamos() throws IllegalArgumentException, NullPointerException {
		Prestamo[] copiaPrestamos = new Prestamo[capacidad];
		for (int i = 0; !tamanoSuperado(i); i++) {
			copiaPrestamos[i] = new Prestamo(coleccionPrestamos[i]);
		}
		return copiaPrestamos;
	}

	public Prestamo[] get(Alumno alumno) {
		if (alumno == null) {
			throw new NullPointerException("ERROR: El alumno no puede ser nulo.");
		}
		Prestamo[] copiaPrestamos = new Prestamo[capacidad];
		for (int i = 0; !tamanoSuperado(i); i++) {
			if (coleccionPrestamos[i].getAlumno().equals(alumno)) {
				copiaPrestamos[i] = new Prestamo(coleccionPrestamos[i]);
			}
		}
		return copiaPrestamos;
	}

	public Prestamo[] get(Libro libro) {
		if (libro == null) {
			throw new NullPointerException("ERROR: El libro no puede ser nulo.");
		}
		int indiceCopia = 0;
		Prestamo[] copiaPrestamos = new Prestamo[capacidad];
		for (int i = 0; !tamanoSuperado(i); i++) {
			if (coleccionPrestamos[i].getLibro().equals(libro)) {
				copiaPrestamos[indiceCopia] = new Prestamo(coleccionPrestamos[i]);
				indiceCopia++;
			}
		}
		return copiaPrestamos;
	}

	public Prestamo[] get(LocalDate fecha) {
		if (fecha == null) {
			throw new NullPointerException("ERROR: La fecha no puede ser nula.");
		}
		int indiceCopia = 0;
		Prestamo[] copiaPrestamos = new Prestamo[capacidad];
		for (int i = 0; !tamanoSuperado(i); i++) {
			if (mismoMes(coleccionPrestamos[i].getFechaPrestamo(), fecha)) {
				copiaPrestamos[indiceCopia] = new Prestamo(coleccionPrestamos[i]);
				indiceCopia++;
			}
		}
		return copiaPrestamos;
	}

	private boolean mismoMes(LocalDate fechaUno, LocalDate fechaDos) {
		return fechaUno.equals(fechaDos);
	}

	public int getTamano() {
		return tamano;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void prestar(Prestamo prestamo)
			throws OperationNotSupportedException, NullPointerException, IllegalArgumentException {
		if (prestamo == null) {
			throw new NullPointerException("ERROR: No se puede prestar un préstamo nulo.");
		}
		int indice = buscarIndice(prestamo);
		if (capacidadSuperada(indice)) {
			throw new OperationNotSupportedException("ERROR: No se aceptan más préstamos.");
		}
		if (tamanoSuperado(indice)) {
			coleccionPrestamos[indice] = new Prestamo(prestamo);
			tamano++;
		} else {
			throw new OperationNotSupportedException("ERROR: Ya existe un préstamo igual.");
		}
	}

	private int buscarIndice(Prestamo prestamo) {
		int indice = 0;
		boolean prestamoEncontrado = false;
		while (!tamanoSuperado(indice) && !prestamoEncontrado) {
			if (coleccionPrestamos[indice].equals(prestamo)) {
				prestamoEncontrado = true;
			} else {
				indice++;
			}
		}
		return indice;
	}

	private boolean tamanoSuperado(int indice) {
		return indice >= tamano;
	}

	private boolean capacidadSuperada(int indice) {
		return indice >= capacidad;
	}

	public void devolver(Prestamo prestamo, LocalDate fechaDevolucion)
			throws OperationNotSupportedException, IllegalArgumentException, NullPointerException {
		if (prestamo == null) {
			throw new NullPointerException("ERROR: No se puede devolver un préstamo nulo.");
		}
		if (fechaDevolucion == null) {
			throw new NullPointerException("ERROR: 12");
		}
		int indice = buscarIndice(prestamo);
		if (tamanoSuperado(indice)) {
			throw new OperationNotSupportedException("ERROR: No existe ningún préstamo igual.");
		} else {
			coleccionPrestamos[indice].devolver(fechaDevolucion);
		}

	}

	public Prestamo buscar(Prestamo prestamo) throws IllegalArgumentException, NullPointerException {
		if (prestamo == null) {
			throw new IllegalArgumentException("ERROR: No se puede buscar un préstamo nulo.");
		}
		int indice = buscarIndice(prestamo);
		if (tamanoSuperado(indice)) {
			return null;
		} else {
			Prestamo prestamoBuscado = new Prestamo(coleccionPrestamos[indice]);
			return prestamoBuscado;
		}
	}

	public void borrar(Prestamo prestamo) throws OperationNotSupportedException {
		if (prestamo == null) {
			throw new IllegalArgumentException("ERROR: No se puede borrar un préstamo nulo.");
		}
		int indice = buscarIndice(prestamo);
		if (tamanoSuperado(indice)) {
			throw new OperationNotSupportedException("ERROR: No existe ningún préstamo igual.");
		} else {
			desplazarUnaPosicionHaciaIzquierda(indice);
		}
	}

	private void desplazarUnaPosicionHaciaIzquierda(int indice) {
		int i;
		for (i = indice; !tamanoSuperado(i); i++) {
			coleccionPrestamos[i] = coleccionPrestamos[i + 1];
		}
		coleccionPrestamos[i] = null;
		tamano--;
	}
}
