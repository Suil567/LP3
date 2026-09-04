package ejercicio4;

public class ImpresoraMultifuncional implements Imprimible, Escaneable {
	public void imprimir() {
		System.out.println("Imprimiendo documento");
	}

	public void escanear() {
		System.out.println("Escaneando documento");
	}
}