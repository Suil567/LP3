import java.util.Scanner;


public class AppBanco {
    public static void main(String[] args) {


        Cuenta[] cuentas = new Cuenta[10];


        cuentas[0] = new CuentaAhorro(0, 1000, 0.02);
        cuentas[1] = new CuentaAhorro(1, 1500, 0.02);
        cuentas[2] = new CuentaAhorro(2, 2000, 0.02);
        cuentas[3] = new CuentaAhorro(3, 2500, 0.02);
        cuentas[4] = new CuentaAhorro(4, 3000, 0.02);


        cuentas[5] = new CuentaCorriente(5, 1000);
        cuentas[6] = new CuentaCorriente(6, 1500);
        cuentas[7] = new CuentaCorriente(7, 2000);
        cuentas[8] = new CuentaCorriente(8, 2500);
        cuentas[9] = new CuentaCorriente(9, 3000);


        Scanner in = new Scanner(System.in);
        boolean done = false;


        while (!done) {
            System.out.print("D)epositar R)etirar C)onsultar S)alir: ");
            String op = in.next();


            if (op.equalsIgnoreCase("D") || op.equalsIgnoreCase("R")) {


                System.out.print("Ingrese un numero de cuenta y un monto: ");
                int num = in.nextInt();
                double monto = in.nextDouble();


                if (op.equalsIgnoreCase("D")) {
                    cuentas[num].depositar(monto);
                } else {
                    cuentas[num].retirar(monto);
                }


                System.out.println("Saldo: " + cuentas[num].getSaldo());


            } else if (op.equalsIgnoreCase("C")) {


                for (int n = 0; n < cuentas.length; n++) {
                    cuentas[n].consultar();
                    System.out.println(n + " - " + cuentas[n].getSaldo());
                }


            } else if (op.equalsIgnoreCase("S")) {
                done = true;
            }
        }


        in.close();
    }
}
