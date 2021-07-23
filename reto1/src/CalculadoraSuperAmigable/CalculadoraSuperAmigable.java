package CalculadoraSuperAmigable;

import java.util.Scanner;

public class CalculadoraSuperAmigable {

    public static int suma(int numero1, int numero2) {
        return numero1 + numero2;
    }

    public static int resta(int numero1, int numero2) {
        return numero1 - numero2;
    }

    public static int multiplicacion(int numero1, int numero2) {
        return numero1 * numero2;
    }

    public static int divicion(int numero1, int numero2) {
        return numero1 / numero2;
    }
    
    public static void main(String[] args) {

        String entrada;
        int resultado = 0;
        Scanner in = new Scanner(System.in);
        System.out.print("buenvenido a la calucadora super amigable\nIngrese la operacion a resolver utilizando el siguente formato numero1 operador numero2: ");
        entrada = in.nextLine();
        String[] sep = entrada.split(" ");
        String operador;
        int numero1 ;
        int numero2 ;

        if (sep.length != 3) {
            System.out.println("EL FORMATO DE ENTRADA NO ES VALIDO");
        } else {
            numero1 = Integer.parseInt(sep[0]);
            operador = sep[1];
            numero2 = Integer.parseInt(sep[2]);

            switch (operador) {
                case "+":
                    resultado = suma(numero1, numero2);
                    break;
                case "-":
                    resultado = resta(numero1, numero2);
                    break;
                case "*":
                    resultado = multiplicacion(numero1, numero2);
                    break;
                case "/":
                    resultado = divicion(numero1, numero2);
                    break;
                default:
                    System.out.println("la operacion no es valida");
                    break;
            }
            System.out.println("La respuesta es:" + resultado);

        }
    }
}
