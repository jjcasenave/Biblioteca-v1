/*Crea la clase Modelo, en el paquete adecuado, con los atributos y métodos especificados en el diagrama y la visibilidad adecuada. 
 *Cada método deberá hacer una llamada al método homólogo del objeto adecuado. Asegurate de que se pasan todos los tests asociados a esta clase.
 *Realiza el commit correspondiente.
 */

package org.iesalandalus.programacion.biblioteca.mvc.modelo;

import java.time.LocalDate;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Alumno;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Libro;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Prestamo;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.negocio.Alumnos;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.negocio.Libros;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.negocio.Prestamos;

public class Modelo {

	private static int CANTIDAD = 30;
	private Prestamos prestamos;
	private Libros libros;
	private Alumnos alumnos;

	public Modelo() {
		prestamos = new Prestamos(CANTIDAD);
		libros = new Libros(CANTIDAD);
		alumnos = new Alumnos(CANTIDAD);
	}

	public void insertar(Alumno alumno)
			throws OperationNotSupportedException, IllegalArgumentException, NullPointerException {
		if (alumno == null) {
			throw new NullPointerException("ERROR: No se puede insertar un alumno nulo.");
		}
		alumnos.insertar(alumno);
	}

	public void insertar(Libro libro)
			throws OperationNotSupportedException, IllegalArgumentException, NullPointerException {
		if (libro == null) {
			throw new NullPointerException("ERROR: No se puede insertar un libro nulo.");
		}
		libros.insertar(libro);
	}

	public void prestar(Prestamo prestamo)
			throws OperationNotSupportedException, IllegalArgumentException, NullPointerException {
		if (prestamo == null) {
			throw new NullPointerException("ERROR: No se puede prestar un préstamo nulo.");
		}
		if (alumnos.buscar(prestamo.getAlumno()) == null) {
			throw new OperationNotSupportedException("ERROR: No existe el alumno del préstamo.");
		}
		if (libros.buscar(prestamo.getLibro()) == null) {
			throw new OperationNotSupportedException("ERROR: No existe el libro del préstamo.");
		} else {
			prestamos.prestar(prestamo);
		}
	}

	public void devolver(Prestamo prestamo, LocalDate fechaDevolucion)
			throws OperationNotSupportedException, IllegalArgumentException, NullPointerException {
		if (prestamos.buscar(prestamo) == null) {
			throw new OperationNotSupportedException("ERROR: No se puede devolver un préstamo no prestado.");
		}
		prestamos.devolver(prestamo, fechaDevolucion);
	}

	public Alumno buscar(Alumno alumno) throws IllegalArgumentException, NullPointerException {
		return alumnos.buscar(alumno);
	}

	public Libro buscar(Libro libro) throws IllegalArgumentException, NullPointerException {
		return libros.buscar(libro);
	}

	public Prestamo buscar(Prestamo prestamo) throws IllegalArgumentException, NullPointerException {
		return prestamos.buscar(prestamo);
	}

	public void borrar(Alumno alumno) throws OperationNotSupportedException {
		alumnos.borrar(alumno);
	}

	public void borrar(Libro libro) throws OperationNotSupportedException {
		libros.borrar(libro);
	}

	public void borrar(Prestamo prestamo) throws OperationNotSupportedException {
		prestamos.borrar(prestamo);
	}

	public Alumno[] getAlumnos() {
		return alumnos.get();
	}

	public Libro[] getLibros() {
		return libros.get();
	}

	public Prestamo[] getPrestamos() {
		return prestamos.get();
	}

	public Prestamo[] getPrestamos(Alumno alumno) {
		return prestamos.get(alumno);
	}

	public Prestamo[] getPrestamos(Libro libro) {
		return prestamos.get(libro);
	}

	public Prestamo[] getPrestamos(LocalDate fechaPrestamo) {
		return prestamos.get(fechaPrestamo);
	}

}