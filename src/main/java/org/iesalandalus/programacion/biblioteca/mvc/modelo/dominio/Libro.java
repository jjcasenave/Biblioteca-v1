/*Crea la clase Libro, en el paquete adecuado, con los atributos y métodos especificados en el diagrama y la visibilidad adecuada.
 *Crea el constructor con parámetros y el constructor copia. Crea los métodos get y set con la visibilidad adecuada. 
 *En todos los casos se debe comprobar la validez de los parámetros pasados antes de asignarlos y en caso de que no sean válidos 
 *lanzar la excepción adecuada. Ten en cuenta los requisitos comentados al principio. El método getLibroFicticio simplemente 
 *devolverá un libro con un número de páginas válido y el título y autor pasados por parámetros y que luego utilizaremos para
 *las búsquedas y borrados. Crea los métodos hashCode, equals y toString. Asegurate de que se pasan todos los tests asociados a esta clase.
 *Realiza el commit correspondiente.
 */

package org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio;

public class Libro {

	//Atributos
	private static int PAGINAS_PARA_RECOMPENSA = 25;
	private static float PUNTOS_PREMIO = 0.5f;
	private String titulo;
	private String autor;
	private int numPaginas;
	
	//Constructor con parametros
	public Libro(String titulo, String autor, int numeroDePaginas) {
		setTitulo(titulo);
		setAutor(autor);
		setNumPaginas(numeroDePaginas);
	}
	
	//Constructor copia
	public Libro(Libro copia) {
		if (copia == null) {
			throw new NullPointerException("ERROR: No es posible copiar un libro nulo.");
		}
		setTitulo(copia.getTitulo());
		setAutor(copia.getAutor());
		setNumPaginas(copia.getNumPaginas());
	}
	
	
	//Metodo getLibroFicticio
	public static Libro getLibroFicticio(String titulo, String autor) {
		if (titulo == null) {
			throw new NullPointerException("ERROR: El título no puede ser nulo.");
		}
		if (autor == null) {
			throw new NullPointerException("ERROR: El autor no puede ser nulo.");
		}
		Libro libroFicticio = new Libro(titulo, autor, 100);
		return libroFicticio;
	}

	//Getters y Setters
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		if (titulo == null) {
			throw new NullPointerException("ERROR: El título no puede ser nulo.");
		}
		int tamanoTitulo = titulo.length();
		boolean vacio = true;
		for (int letra = 0; letra < tamanoTitulo; letra++) {
			if (titulo.charAt(letra) != ' ') {
				vacio = false;
			}
		}
		if (vacio == true) {
			throw new IllegalArgumentException("ERROR: El título no puede estar vacío.");
		} else {
			this.titulo = titulo;
		}
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		if (autor == null) {
			throw new NullPointerException("ERROR: El autor no puede ser nulo.");
		}
		int tamanoAutor = autor.length();
		boolean vacio = true;
		for (int letra = 0; letra < tamanoAutor; letra++) {
			if (autor.charAt(letra) != ' ') {
				vacio = false;
			}
		}
		if (vacio == true) {
			throw new IllegalArgumentException("ERROR: El autor no puede estar vacío.");
		} else {
			this.autor = autor;
		}
	}

	public int getNumPaginas() {
		return numPaginas;
	}

	public void setNumPaginas(int numPaginas) {
		if (numPaginas <= 0) {
			throw new IllegalArgumentException("ERROR: El número de páginas debe ser mayor que cero.");
		}
		this.numPaginas = numPaginas;
	}

	public float getPuntos() {
		int tramoPuntos = numPaginas / PAGINAS_PARA_RECOMPENSA + 1;
		float puntos = tramoPuntos * PUNTOS_PREMIO;
		return puntos;
	}

	//Hash
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((autor == null) ? 0 : autor.hashCode());
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
		return result;
	}

	//Equals
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Libro other = (Libro) obj;
		if (autor == null) {
			if (other.autor != null)
				return false;
		} else if (!autor.equals(other.autor))
			return false;
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
			return false;
		return true;
	}

	//toString
	@Override
	public String toString() {
		return "título=" + titulo + ", autor=" + autor + ", número de páginas=" + numPaginas;
	}

}
