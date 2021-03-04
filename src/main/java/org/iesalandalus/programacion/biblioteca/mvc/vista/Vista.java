package org.iesalandalus.programacion.biblioteca.mvc.vista;

import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.biblioteca.mvc.controlador.Controlador;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Alumno;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Libro;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Prestamo;

public class Vista {

	Controlador controlador;

	public Vista() {
		Opcion.setVista(this);
	}

	public void setControlador(Controlador controlador) {
		if (controlador == null) {
			throw new NullPointerException("ERROR: El controlador no puede ser nulo.");
		}
		this.controlador = controlador;
	}

	public void comenzar() {
		Consola.mostrarCabecera("GESTIÓN DE RESERVAS DE LA BIBLIOTECA");
		int opcion;
		do {
			Consola.mostrarMenu();
			opcion = Consola.elegirOpcion();
			Opcion opcionElegida = Opcion.getOpcionSegunOrdinal(opcion);
			opcionElegida.ejecutar();
		} while (opcion != Opcion.SALIR.ordinal());
	}

	public void terminar() {
		controlador.terminar();
	}

	public void insertarAlumno() {
		Consola.mostrarCabecera("INSERTAR ALUMNO");
		try {
			controlador.insertar(Consola.leerAlumno());
			System.out.println("\nAlumno Insertado Correctamente");
		} catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}

	public void buscarAlumno() {
		Consola.mostrarCabecera("BUSCAR ALUMNO");
		Alumno alumno;
		try {
			alumno = controlador.buscar(Consola.leerAlumnoFicticio());
			if (alumno == null) {
				System.out.println("\nEl alumno no existe");
			} else {
				System.out.println(alumno.toString());
			}
		} catch (IllegalArgumentException | NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}

	public void borrarAlumno() {
		Consola.mostrarCabecera("BORRAR ALUMNO");
		try {
			controlador.borrar(Consola.leerAlumnoFicticio());
			System.out.println("\nAlumno borrado correctamente");
		} catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}

	public void listarAlumnos() {
		Consola.mostrarCabecera("LISTADO DE ALUMNOS");
		List<Alumno> listaAlumnos = controlador.getAlumnos();
		if (listaAlumnos != null) {
			for (Alumno alumno : listaAlumnos) {
					System.out.println(alumno);
			}
		} else {
			System.out.println("No hay alumnos que mostrar");
		}
	}

	public void insertarLibro() {
		Consola.mostrarCabecera("INSERTAR LIBRO");
		try {
			controlador.insertar(Consola.leerLibro());
			System.out.println("\nLibro Insertado Correctamente");
		} catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}

	public void buscarLibro() {
		Consola.mostrarCabecera("BUSCAR LIBRO");
		Libro libro;
		try {
			libro = controlador.buscar(Consola.leerLibroFicticio());
			if (libro == null) {
				System.out.println("\nEl libro no existe");
			} else {
				System.out.println(libro.toString());
			}
		} catch (IllegalArgumentException | NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}

	public void borrarLibro() {
		Consola.mostrarCabecera("BORRAR LIBRO");
		try {
			controlador.borrar(Consola.leerLibroFicticio());
			System.out.println("\nLibro borrado correctamente");
		} catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}

	public void listarLibros() {
		Consola.mostrarCabecera("LISTADO DE LIBROS");
		List<Libro> listaLibros = controlador.getLibros();
		if (listaLibros != null) {
			for (Libro libro : listaLibros) {
					System.out.println(libro);
			}
		} else {
			System.out.println("No hay libros que mostrar");
		}
	}

	public void prestarLibro() {
		Consola.mostrarCabecera("PRÉSTAMO DE LIBRO");
		try {
			controlador.prestar(Consola.leerPrestamo());
			System.out.println("\nLibro Prestado Correctamente");
		} catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}

	public void devolverLibro() {
		Consola.mostrarCabecera("DEVOLUCIÓN DE LIBRO");
		try {
			controlador.devolver(Consola.leerPrestamo(), Consola.leerFecha());
			System.out.println("\nLibro devuelto correctamente");
		} catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}

	public void buscarPrestamo() {
		Consola.mostrarCabecera("BUSCAR PRÉSTAMO");
		Prestamo prestamo;
		try {
			prestamo = controlador.buscar(Consola.leerPrestamoFicticio());
			if (prestamo == null) {
				System.out.println("\nEl préstamo no existe");
			} else {
				System.out.println(prestamo.toString());
			}
		} catch (IllegalArgumentException | NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}

	public void borrarPrestamo() {
		Consola.mostrarCabecera("BORRAR PRÉSTAMO");
		try {
			controlador.borrar(Consola.leerPrestamoFicticio());
			System.out.println("\nPréstamo borrado correctamente");
		} catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}

	public void listarPrestamos() {
		Consola.mostrarCabecera("LISTADO DE PRÉSTAMOS");
		List<Prestamo> prestamos = controlador.getPrestamos();
		if (prestamos != null) {
			for (Prestamo prestamo : prestamos) {
				System.out.println(prestamo);
			}
		} else {
			System.out.println("No hay préstamos para mostrar");
		}
	}

	public void listarPrestamosAlumno() {
		Consola.mostrarCabecera("LISTADO DE PRÉSTAMOS POR ALUMNO");
		List<Prestamo> prestamos = controlador.getPrestamos(Consola.leerAlumno());
		if (prestamos != null) {
			for (Prestamo prestamo : prestamos) {
				System.out.println(prestamo);
			}
		} else {
			System.out.println("No hay préstamos de dicho alumno");
		}
	}

	public void listarPrestamosLibro() {
		Consola.mostrarCabecera("LISTADO DE PRÉSTAMOS POR LIBRO");
		List<Prestamo> prestamos = controlador.getPrestamos(Consola.leerLibro());
		if (prestamos != null) {
			for (Prestamo prestamo : prestamos) {
				System.out.println(prestamo);
			}
		} else {
			System.out.println("No hay préstamos de dicho libro");
		}
	}

	public void listarPrestamosFecha() {
		Consola.mostrarCabecera("LISTADO DE PRÉSTAMOS POR FECHA");
		List<Prestamo> prestamos = controlador.getPrestamos(Consola.leerFecha());
		if (prestamos != null) {
			for (Prestamo prestamo : prestamos) {
				System.out.println(prestamo);
			}
		} else {
			System.out.println("No hay préstamos en dicha fecha");
		}
	}

	public void mostrarEstadisticaPorCurso() {
		controlador.getEstadisticasMensualPorCurso(Consola.leerFecha());
	}

}