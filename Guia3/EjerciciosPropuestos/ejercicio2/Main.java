package ejercicio2;

public class Main {
	public static void main(String[] args) {
		Lienzo lienzo = new Lienzo();

		lienzo.agregarForma(new Circulo());
		lienzo.agregarForma(new Rectangulo());
		lienzo.agregarForma(new Triangulo());

		System.out.println("--- Dibujando formas desde el lienzo ---");
		lienzo.dibujarTodas();

		System.out.println("\n--- Dibujando desde arreglo nativo directo ---");
		Forma[] formasDirectas = { new Circulo(), new Rectangulo(), new Triangulo() };
		Lienzo.dibujarArreglo(formasDirectas, formasDirectas.length);
	}
}