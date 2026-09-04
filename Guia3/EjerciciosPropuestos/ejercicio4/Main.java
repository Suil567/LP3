package ejercicio4;

public class Main {
	public static void main(String[] args) {
		GestorImpresion gestorImpresion = new GestorImpresion();
		GestorEscaneo gestorEscaneo = new GestorEscaneo();

		Impresora impresora = new Impresora();
		ImpresoraMultifuncional multi = new ImpresoraMultifuncional();

		gestorImpresion.agregarEquipo(impresora);
		gestorImpresion.agregarEquipo(multi);

		gestorEscaneo.agregarEquipo(multi);

		System.out.println("--- Proceso de Impresión ---");
		gestorImpresion.imprimirTodos();

		System.out.println("\n--- Proceso de Escaneo ---");
		gestorEscaneo.escanearTodos();

		System.out.println("\n--- Proceso mediante arreglos directos ---");
		Imprimible[] listaImprimibles = { impresora, multi };
		GestorImpresion.imprimirArreglo(listaImprimibles, listaImprimibles.length);
	}
}