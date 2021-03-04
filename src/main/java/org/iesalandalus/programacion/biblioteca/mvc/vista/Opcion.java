/*Crea el enumerado Opcion, en el paquete adecuado, cuyas instancias corresponderán con cada una de las opciones del menú de nuestra aplicación.
 *Cada instancia aceptará en su constructor una cadena correspondiente al mensaje que se mostrará por pantalla para dicha opción. 
 *Debes crear los atributos y métodos que se especifican en el diagrama, teniendo en cuenta que el método ejecutar es abstracto por
 *lo que en la declaración general no tendrá implementación, pero que deberá ser implementado en cada una de las instancias haciendo 
 *una llamada al correspondiente método de la clase Vista. El método getOpcionSegunOrdinal devolverá la instancia de Opcion que ocupe
 *el ordinal pasado por parámetro. El método esOrdinalValido devolverá un boolean indicando si el ordinal pasado por parámetro está 
 *dentro de los posibles ordinales. El método toString devolverá la cadena correspondiente a la concatenación del ordinal de la opción,
 *la cadena " .- " y el mensaje a mostrar por la opción en cuestión. Por último, el método setVista asignará el valor pasado al atributo
 *vista, que aunque aún no exista, crearás en el siguiente paso. Realiza el commit correspondiente.
 */

package org.iesalandalus.programacion.biblioteca.mvc.vista;

public enum Opcion {

	INSERTAR_ALUMNO("Insertar alumno") {
		public void ejecutar() {
			vista.insertarAlumno();
		}
	},
	BUSCAR_ALUMNO("Buscar alumno") {
		public void ejecutar() {
			vista.buscarAlumno();
		}
	},
	BORRAR_ALUMNO("Borrar alumno") {
		public void ejecutar() {
			vista.borrarAlumno();
		}
	},
	LISTAR_ALUMNOS("Listar alumnos") {
		public void ejecutar() {
			vista.listarAlumnos();
		}
	},
	INSERTAR_LIBRO("Insertar libro") {
		public void ejecutar() {
			vista.insertarLibro();
		}
	},
	BUSCAR_LIBRO("Buscar libro") {
		public void ejecutar() {
			vista.buscarLibro();
		}
	},
	BORRAR_LIBRO("Borrar libro") {
		public void ejecutar() {
			vista.borrarLibro();
		}
	},
	LISTAR_LIBROS("Listar libros") {
		public void ejecutar() {
			vista.listarLibros();
		}
	},
	PRESTAR_LIBRO("Prestar libro") {
		public void ejecutar() {
			vista.prestarLibro();
		}
	},
	DEVOLVER_LIBRO("Devolver libro") {
		public void ejecutar() {
			vista.devolverLibro();
		}
	},
	BUSCAR_PRESTAMO("Buscar préstamo") {
		public void ejecutar() {
			vista.buscarPrestamo();
		}
	},
	BORRAR_PRESTAMO("Borrar préstamo") {
		public void ejecutar() {
			vista.borrarPrestamo();
		}
	},
	LISTAR_PRESTAMOS("Listar préstamos") {
		public void ejecutar() {
			vista.listarPrestamos();
		}
	},
	LISTAR_PRESTAMOS_ALUMNOS("Listar préstamos de un alumno") {
		public void ejecutar() {
			vista.listarPrestamosAlumno();
		}
	},
	LISTAR_PRESTAMOS_LIBROS("Listar préstamos de un libro") {
		public void ejecutar() {
			vista.listarPrestamosLibro();
		}
	},
	LISTAR_PRESTAMOS_FECHAS("Listar préstamos de un mes") {
		public void ejecutar() {
			vista.listarPrestamosFecha();
		}
	},
	SALIR("Salir") {
		public void ejecutar() {
			vista.terminar();
		}
	};

	private String mensaje;
	private static Vista vista;

	private Opcion(String opcion) {
		this.mensaje = opcion;
	}

	public abstract void ejecutar();

	protected static void setVista(Vista vista) {
		Opcion.vista = vista;
	}

	public static Opcion getOpcionSegunOrdinal(int opcion) {
		if (esOrdinalValido(opcion))
			return values()[opcion];
		else
			throw new IllegalArgumentException("Ordinal de la opción no válido");
	}

	public static boolean esOrdinalValido(int opcion) {
		return opcion <= values().length - 1 && opcion >= 0;
	}

	public String toString() {
		return String.format("%d.- %s", ordinal(), mensaje);
	}
}