/*Haz que en la clase Modelo se cumplan las restricciones de integridad pedidas en los requisitos. Revisa que tu código pasa todas las pruebas.
 *Realiza el último commit y sube los cambios a tu repositorio remoto en GitHub.
 */

package org.iesalandalus.programacion.biblioteca.mvc.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Alumno;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Curso;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Libro;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Prestamo;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.negocio.Alumnos;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.negocio.Libros;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.negocio.Prestamos;

public class Modelo {

	private Prestamos prestamos;
	private Libros libros;
	private Alumnos alumnos;
	
	public Modelo() {
		prestamos = new Prestamos();
		libros = new Libros();
		alumnos = new Alumnos();
	}
	
	public void insertar(Alumno alumno) throws OperationNotSupportedException , IllegalArgumentException, NullPointerException {
		if (alumno == null ) {
			throw new NullPointerException("ERROR: No se puede insertar un alumno nulo.");
		}
		alumnos.insertar(alumno);
	}
	
	public void insertar(Libro libro) throws OperationNotSupportedException, IllegalArgumentException, NullPointerException {
		if (libro == null ) {
			throw new NullPointerException("ERROR: No se puede insertar un libro nulo.");
		}
		libros.insertar(libro);
	}
	
	public void prestar(Prestamo prestamo) throws OperationNotSupportedException, IllegalArgumentException, NullPointerException {
		if (prestamo == null ) {
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
	
	public void devolver(Prestamo prestamo, LocalDate fechaDevolucion) throws OperationNotSupportedException, IllegalArgumentException, NullPointerException {
		if (prestamos.buscar(prestamo) == null) {
			throw new OperationNotSupportedException("ERROR: No se puede devolver un préstamo no prestado.");
		}
		prestamos.devolver(prestamo, fechaDevolucion);
	}
	
	public  Alumno buscar(Alumno alumno) throws IllegalArgumentException, NullPointerException {
		return alumnos.buscar(alumno);
	}
	
	public Libro buscar(Libro libro) throws IllegalArgumentException, NullPointerException {
		return libros.buscar(libro);
	}
	
	public Prestamo buscar(Prestamo prestamo) throws IllegalArgumentException, NullPointerException {
		return prestamos.buscar(prestamo);
	}
	
	public void borrar(Alumno alumno) throws OperationNotSupportedException {
		alumnos.buscar(alumno);
		List<Prestamo> alumnosParaBorrar = prestamos.get(alumno);
		for (Prestamo prestamo : alumnosParaBorrar) {
			prestamos.borrar(prestamo);
		}
		alumnos.borrar(alumno);
	}
	
	public void borrar(Libro libro) throws OperationNotSupportedException {
		libros.buscar(libro);
		List<Prestamo> librosParaBorrar = prestamos.get(libro);
		for (Prestamo prestamo : librosParaBorrar) {
			prestamos.borrar(prestamo);
		}
		libros.borrar(libro);
	}
	
	public void borrar(Prestamo prestamo) throws OperationNotSupportedException {
		prestamos.borrar(prestamo);
	}
	
	public List<Alumno> getAlumnos() {
		return alumnos.get();
	}
	
	public List<Libro> getLibros() {
		return libros.get();
	}
	
	public List<Prestamo> getPrestamos() {
		return prestamos.get();
	}
	
	public List<Prestamo> getPrestamos(Alumno alumno) {
		return prestamos.get(alumno);
	}
	
	public List<Prestamo> getPrestamos(Libro libro) {
		return prestamos.get(libro);
	}
	
	public List<Prestamo> getPrestamos(LocalDate fechaPrestamo) {
		return prestamos.get(fechaPrestamo);
	}
	
	public Map<Curso, Integer> getEstadisticaMensualPorCurso(LocalDate fecha) {
		return prestamos.getEstadisticaMensualPorCurso(fecha);
	}
}