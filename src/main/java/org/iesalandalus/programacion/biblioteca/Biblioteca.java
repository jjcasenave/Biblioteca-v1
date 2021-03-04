/*Crea la clase MainApp que simplemente creará un objeto de la clase Modelo, otro de la clase Vista y otro de la clase Controlador
 *pasándole los dos anteriores y luego llamará al método comenzar. Realiza el commit correspondiente.
 */

/*prueba*/
package org.iesalandalus.programacion.biblioteca;

import org.iesalandalus.programacion.biblioteca.mvc.controlador.Controlador;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.Modelo;
import org.iesalandalus.programacion.biblioteca.mvc.vista.Vista;

public class Biblioteca {

	public static void main(String[] args) {
		Modelo modelo = new Modelo();
		Vista vista = new Vista();
		Controlador controlador = new Controlador(modelo, vista);
		controlador.comenzar();
	}

}
