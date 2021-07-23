package picasyfijas;

import java.util.ArrayList;
import java.util.Scanner;

public class PicasYFijas {

    public static char[] generarCodigo() {
        String codigo = "";
        String codigoTemp = "";

        while (codigo.length() < 4) {
            codigoTemp = String.valueOf((int) (Math.random() * 10));

            if (!codigo.contains(codigoTemp)) {
                codigo += String.valueOf(codigoTemp);
                codigoTemp = "";
            }
        }
        final char CODIGOFINAL[] = codigo.toCharArray();
        System.out.println(CODIGOFINAL);
        return CODIGOFINAL;
    }

    public static void main(String[] args) {
        String opcion;
        int i;
        char a[];
        final char CODIGOFINAL[];
        int acierto = 0;
        int coincidencia = 0;
        int intentos = 0;
        boolean iniciaJuego = true;
        CODIGOFINAL = (generarCodigo());
        Scanner in = new Scanner(System.in);
        System.out.println("INICIA EL JUEGO \nTienes que adivinar un numero de 4 cifras distintas");

        while (iniciaJuego) {
            intentos++;
            coincidencia = 0;
            acierto = 0;
            i = 0;
            System.out.print("que codigo propones: ");
            opcion = in.next();
            a = opcion.toCharArray();
            if (a.length == 4) {
                while (i < CODIGOFINAL.length) {
                    if (CODIGOFINAL[i] == a[i]) {
                        acierto++;
                    } else {
                        for (char j : a) {
                            if (CODIGOFINAL[i] == j) {
                                coincidencia++;
                            }
                        }
                    }
                    i++;
                }
                System.out.println("");
                System.out.println("tu propuesta "+opcion +" tiene "+ acierto + " aciertos, y "  + coincidencia+ " coincidencias ");
                if (acierto == 4) {
                    System.out.println("\n¡FELICIDADES! HAS DESCUBIERTO EL CODIGO\n");
                    System.out.println("tu numero de intentos fue "+ intentos);
                    iniciaJuego = false;
                }                
            } else {
                System.out.println("el numero debe tener 4 digitos");
            }
        }
    }
}
