class Motor {


    private int numMotor;
    private int revPorMin;


    // Constructor
    public Motor(int numMotor, int revPorMin) {
        this.numMotor = numMotor;
        this.revPorMin = revPorMin;
    }
    // Getters
    public int getNumMotor() {
        return numMotor;
    }
    public int getRevoluciones() {
        return revPorMin;
    }
    // Setters
    public void setNumMotor(int numMotor) {
        this.numMotor = numMotor;
    }
    public void setRevoluciones(int revPorMin) {
        this.revPorMin = revPorMin;
    }
    // toString
    public String toString() {
        return "Número de motor: " + numMotor +
               ", Revoluciones por minuto: " + revPorMin;
    }
}


class Automovil {


    private String placa;
    private int numPuertas;
    private String marca;
    private String modelo;
    private Motor motor;




    // Constructor
    public Automovil(String placa, int numPuertas, String marca, String modelo) {
        this.placa = placa;
        this.numPuertas = numPuertas;
        this.marca = marca;
        this.modelo = modelo;
    }
    // Getters
    public String getPlaca() {
        return placa;
    }
    public int getNumPuertas() {
        return numPuertas;
    }
    public String getMarca() {
        return marca;
    }
    public String getModelo() {
        return modelo;
    }
    public Motor getMotor() {
        return motor;
    }
    // Setters
    public void setPlaca(String placa) {
        this.placa = placa;
    }
    public void setNumPuertas(int numPuertas) {
        this.numPuertas = numPuertas;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    public void setMotor(Motor motor) {
        this.motor = motor;
    }
    // toString
    public String toString() {
        return "Placa: " + placa +
               ", Número de puertas: " + numPuertas +
               ", Marca: " + marca +
               ", Modelo: " + modelo +
               ", Motor: " + motor;
    }
}




public class TestAgregacion {
    public static void main(String[] args) {


        // Crear motores
        Motor motor1 = new Motor(1001, 3000);
        Motor motor2 = new Motor(1002, 2500);
        // Crear automóviles
        Automovil auto1 = new Automovil(
                "ABC-123", 4, "Toyota", "Corolla"
        );


        Automovil auto2 = new Automovil(
                "XYZ-456", 4, "Hyundai", "Tucson"
        );
        // Asignar motores a los automóviles
        auto1.setMotor(motor1);
        auto2.setMotor(motor2);
        // Mostrar información
        System.out.println("Datos del primer automóvil:");
        System.out.println(auto1);
        System.out.println();
        System.out.println("Datos del segundo automóvil:");
        System.out.println(auto2);
    }
}
