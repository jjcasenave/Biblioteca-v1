
/*Crea la clase Prestamo, en el paquete adecuado, con los atributos y métodos especificados en el diagrama y la visibilidad adecuada.
 *Crea el constructor con parámetros y el constructor copia. Crea los métodos get y set con la visibilidad adecuada. 
 *En todos los casos se debe comprobar la validez de los parámetros pasados antes de asignarlos y en caso de que no sean válidos 
 *lanzar la excepción adecuada. Ten en cuenta los requisitos comentados al principio. El método getPrestamoFicticio simplemente 
 *devolverá un préstamo con una fecha de préstamo válida y el alumno y libro ficticios correspondientes al correo, título y autor
 *pasados por parámetros. Crea los métodos hashCode, equals y toString. Asegurate de que se pasan todos los tests asociados a esta clase. 
 *Realiza el commit correspondiente.
 */

package org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Prestamo {

	// Atributos
	private static int MAX_DIAS_PRESTAMO = 20;
	public static DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/YYYY");
	private LocalDate fechaPrestamo;
	private LocalDate fechaDevolucion;
	private Alumno alumno;
	private Libro libro;

	// Constructor con parametros
	public Prestamo(Alumno alumno, Libro libro, LocalDate fechaPrestamo) {
		setAlumno(alumno);
		setLibro(libro);
		setFechaPrestamo(fechaPrestamo);
	}

	// Constructor copia
	public Prestamo(Prestamo copia) throws NullPointerException, IllegalArgumentException {
		if (copia == null) {
			throw new NullPointerException("ERROR: No es posible copiar un préstamo nulo.");
		}
		setAlumno(copia.getAlumno());
		setLibro(copia.getLibro());
		setFechaPrestamo(copia.getFechaPrestamo());
		if (copia.getFechaDevolucion() != null) {
			this.fechaDevolucion = copia.getFechaDevolucion();
		}
	}

	// Metodo getPrestamoFicticio
	public static Prestamo getPrestamoFicticio(Alumno alumno, Libro libro)
			throws NullPointerException, IllegalArgumentException {
		if (alumno == null) {
			throw new NullPointerException("ERROR: El alumno no puede ser nulo.");
		}
		if (libro == null) {
			throw new NullPointerException("ERROR: El libro no puede ser nulo.");
		}
		Prestamo prestamoFicticio = new Prestamo(alumno, libro, LocalDate.now());
		return prestamoFicticio;
	}

	public void devolver(LocalDate fechaDevolucion) {
		setFechaDevolucion(fechaDevolucion);
	}

	// Getters y Setters
	public int getPuntos() {
		int puntos = 0;
		if (fechaDevolucion == null) {
			return puntos = 0;
		}
		long diasEntre = ChronoUnit.DAYS.between(getFechaPrestamo(), getFechaDevolucion());
		if (diasEntre <= MAX_DIAS_PRESTAMO && diasEntre > 0) {
			double calculo = libro.getPuntos() / diasEntre;
			puntos = (int) Math.round(calculo);
		}
		return puntos;
	}

	public Alumno getAlumno() {
		return alumno;
	}

	private void setAlumno(Alumno alumno) throws NullPointerException, IllegalArgumentException {
		if (alumno == null) {
			throw new NullPointerException("ERROR: El alumno no puede ser nulo.");
		}
		alumno = new Alumno(alumno);
		this.alumno = alumno;
	}

	public Libro getLibro() {
		return libro;
	}

	private void setLibro(Libro libro) throws NullPointerException, IllegalArgumentException {
		if (libro == null) {
			throw new NullPointerException("ERROR: El libro no puede ser nulo.");
		}
		libro = new Libro(libro);
		this.libro = libro;
	}

	public LocalDate getFechaPrestamo() {
		return fechaPrestamo;
	}

	private void setFechaPrestamo(LocalDate fechaPrestamo) {
		if (fechaPrestamo == null) {
			throw new NullPointerException("ERROR: La fecha de préstamo no puede ser nula.");
		}
		if (fechaPrestamo.isAfter(LocalDate.now())) {
			throw new IllegalArgumentException("ERROR: La fecha de préstamo no puede ser futura.");
		}
		this.fechaPrestamo = fechaPrestamo;
	}

	public LocalDate getFechaDevolucion() {
		return fechaDevolucion;
	}

	private void setFechaDevolucion(LocalDate fechaDevolucion) {
		if (this.fechaDevolucion != null) {
			throw new IllegalArgumentException("ERROR: La devolución ya estaba registrada.");
		}
		if (fechaDevolucion == null) {
			throw new NullPointerException("ERROR: La fecha de devolución no puede ser nula.");
		}
		if (fechaDevolucion.isAfter(LocalDate.now())) {
			throw new IllegalArgumentException("ERROR: La fecha de devolución no puede ser futura.");
		}
		if (fechaDevolucion.isBefore(fechaPrestamo) || fechaDevolucion.isEqual(fechaPrestamo)) {
			throw new IllegalArgumentException(
					"ERROR: La fecha de devolución debe ser posterior a la fecha de préstamo.");
		}
		this.fechaDevolucion = fechaDevolucion;
	}

	// hash
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((alumno == null) ? 0 : alumno.hashCode());
		result = prime * result + ((libro == null) ? 0 : libro.hashCode());
		return result;
	}

	// Equals
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Prestamo other = (Prestamo) obj;
		if (alumno == null) {
			if (other.alumno != null)
				return false;
		} else if (!alumno.equals(other.alumno))
			return false;
		if (libro == null) {
			if (other.libro != null)
				return false;
		} else if (!libro.equals(other.libro))
			return false;
		return true;
	}

	// toString
	@Override
	public String toString() {

		if (fechaDevolucion == null) {
			return String.format("alumno=(%s), libro=(%s), fecha de préstamo=%s, puntos=%d", alumno, libro,
					fechaPrestamo.format(FORMATO_FECHA), getPuntos());
		} else {
			return String.format("alumno=(%s), libro=(%s), fecha de préstamo=%s, fecha de devolución=%s, puntos=%d",
					alumno, libro, fechaPrestamo.format(FORMATO_FECHA), fechaDevolucion.format(FORMATO_FECHA),
					getPuntos());
		}
	}

}
