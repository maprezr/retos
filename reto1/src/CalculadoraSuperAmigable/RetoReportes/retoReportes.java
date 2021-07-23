package CalculadoraSuperAmigable.RetoReportes;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class retoReportes {

    public static void menu() {

        Path filePath = Paths.get("c:\\Datos.csv");
        ArrayList<ArrayList<String>> datos = new ArrayList<>();
        datos = readSCV(filePath);
        Scanner in = new Scanner(System.in);
        String opc = "0";
        String opc1 = "0";
        String opc2 = "0";
        String codigoTienda;
        String codigosTienda[] = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"};
        boolean starProgram = true;

        while (starProgram) {
            System.out.println("BIENVENIDO AL SOFTWARE DE REPORTES DE Supertiendas S.A");
            System.out.println("POR FAVOR INGRESE LA OPCION DE REPORTE QUE DESEA GENERAR");
            System.out.println("");
            System.out.println("***********************************************");
            System.out.println("");
            System.out.println("1.INFORME FINANCIERO");
            System.out.println("2.INFORME DE STOCK");
            System.out.println("3.SALIR");
            System.out.print("OPCION: ");
            opc = in.next();

            if (opc.equals("1")) {
                System.out.println("***********************************************");
                System.out.println("");
                System.out.println("1.INFORME DE TIENDA EN ESPECIFICO");
                System.out.println("2.INFORME DE TODAS LAS TIENDAS");
                System.out.print("OPCION: ");
                opc1 = in.next();
                if (opc1.equals("1")) {
                    System.out.println("***********************************************");
                    System.out.println("INGRESE EL CODIGO DE LA TIENDA");
                    System.out.print("OPCION: ");
                    codigoTienda = in.next();
                    Controler(datos, codigoTienda, true);

                } else if (opc1.equals("2")) {
                    for (String i : codigosTienda) {
                        Controler(datos, i, true);

                    }

                } else if (opc1.equals("3")) {
                    starProgram = false;
                } else {
                    System.out.println("LA OPCION NO ES VALIDAcfdf");
                }

            } else if (opc.equals("2")) {
                System.out.println("***********************************************");
                System.out.println("");
                System.out.println("1.INFORME DE TIENDA EN ESPECIFICO");
                System.out.println("2.INFORME DE TODAS LAS TIENDAS");
                System.out.println("3.SALIR");
                System.out.print("OPCION: ");
                opc2 = in.next();
                if (opc2.equals("1")) {
                    System.out.println("***********************************************");
                    System.out.println("INGRESE EL CODIGO DE LA TIENDA");
                    System.out.print("OPCION:");
                    codigoTienda = in.next();
                    Controler(datos, codigoTienda, false);

                } else if (opc2.equals("2")) {
                    for (String i : codigosTienda) {
                        Controler(datos, i, false);
                    }
                    }else if (opc2.equals("3")) {
                    starProgram = false;
                } else {
                    System.out.println("LA OPCION NO ES VALIDA");
                }
                } else if (opc.equals("3")) {
                    starProgram = false;
                } else {
                    System.out.println("LA OPCION NO ES VALIDA654");
                }

            }
        }

    

    public static ArrayList<ArrayList<String>> readSCV(Path filePath) {
        ArrayList<ArrayList<String>> datos = new ArrayList<>();
        try {
            BufferedReader br = Files.newBufferedReader(filePath);
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datosDeLinea = linea.split(";");
                ArrayList<String> datosTemporal = new ArrayList<String>();
                for (String dato : datosDeLinea) {
                    datosTemporal.add(dato);
                }
                datos.add(datosTemporal);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        datos.get(0).set(11, "impuesto");

        for (int i = 1; i < datos.size(); i++) {
            // cambia el valor "#N/D" por 0 en la columna impuesto 
            if ("#N/D".equals(datos.get(i).get(11))) {
                datos.get(i).set(11, "0");

            }
        }
        return datos;
    }

    public static void Controler(ArrayList<ArrayList<String>> datos, String codigoTienda, boolean opcion) {
        double valorTotalFinal = 0;
        double valorFinalSinI = 0;
        double valorImpuestoFinal = 0;
        String fecha = "";
        DecimalFormat df = new DecimalFormat("#.00");
        String codigotienda = codigoTienda;
        String tienda = "";
        String tipo = "", producto = "", codigo = "";

        //arrays necesarios para operaciones       
        ArrayList<Float> valorImpuesto = new ArrayList<>();
        ArrayList<Double> valor = new ArrayList<>();
        ArrayList<Double> valorTotal = new ArrayList();
        ArrayList<Integer> paquetesVendidos = new ArrayList<Integer>();
        ArrayList<Integer> stockInicial = new ArrayList<Integer>();
        ArrayList<Integer> StockMinimo = new ArrayList<Integer>();
        ArrayList<Integer> stockDisponible = new ArrayList<Integer>();
        ArrayList<Double> valorSinImpuesto = new ArrayList();
        ArrayList<Object> stock = new ArrayList();

        /*
        * llena los array list con los datos de las columnas, 
        * impuesto(Float) y valorint, para poder hacer las operaciones
        * matematicas
         */
        for (int i = 1; i < datos.size(); i++) {
            valorImpuesto.add((Float.parseFloat(datos.get(i).get(11)) / 100));
            valor.add(Double.parseDouble(datos.get(i).get(12)));
            valorSinImpuesto.add(valor.get(i - 1) * (Integer.parseInt(datos.get(i).get(14))));
            valorTotal.add((valorImpuesto.get(i - 1) * valor.get(i - 1) + valor.get(i - 1)) * (Integer.parseInt(datos.get(i).get(14))));
            paquetesVendidos.add(Integer.parseInt(datos.get(i).get(14)));
            stockInicial.add(Integer.parseInt(datos.get(i).get(13)));
            StockMinimo.add(Integer.parseInt(datos.get(i).get(15)));
            stockDisponible.add(Integer.parseInt(datos.get(i).get(13)) - (Integer.parseInt(datos.get(i).get(14))));

            if (opcion) {

                if (datos.get(i).get(2).substring(0, 2).equals(codigotienda)) {
                    valorTotalFinal = valorTotal.get(i - 1) + valorTotalFinal;
                    valorFinalSinI = valorSinImpuesto.get(i - 1) + valorFinalSinI;
                    valorImpuestoFinal = valorTotalFinal - valorFinalSinI;
                    tienda = datos.get(i).get(1);
                    fecha = datos.get(i).get(0);

                }
            } else {
                if (datos.get(i).get(2).substring(0, 2).equals(codigotienda)) {
                    if (stockDisponible.get(i - 1) < StockMinimo.get(i - 1)) {
                        tienda = datos.get(i).get(1);
                        fecha = datos.get(i).get(0);
                        tipo = datos.get(i).get(4);
                        producto = datos.get(i).get(6);
                        codigo = datos.get(i).get(2).substring(3, 8);
                        StockMinimo.get(i - 1);
                        stockDisponible.get(i - 1);

                        System.out.println(fecha);
                        System.out.println(tienda);
                        System.out.println("TIPO: " + tipo);
                        System.out.println("PRODUCTO: " + producto);
                        System.out.println("CODIGO: " + codigo);
                        System.out.println(("***********************************************"));
                        System.out.println("");

                    }
                }

            }
        }
        if (opcion) {
        System.out.println(fecha);
        System.out.println(tienda);
        System.out.println("VALOR TOTAL VENDIDO: " + (df.format(valorTotalFinal)));
        System.out.println("VALOR TOTAL VENDIDO SIN IMPUESTOS: " + (df.format(valorFinalSinI)));
        System.out.println("VALOR IMPUESTOS: " + (df.format(valorImpuestoFinal)));
        System.out.println(("***********************************************"));
        System.out.println("");
        }
//
//        System.out.println(fecha);
//        System.out.println(tienda);
//        System.out.println("TIPO: " + tipo);
//        System.out.println("PRODUCTO: " + producto);
//        System.out.println("CODIGO: " + codigo);
//        System.out.println(("***********************************************"));
//        System.out.println("");

    }

    public static void main(String[] args) {
        menu();

        // variables
//        Path filePath = Paths.get("c:\\Datos.csv");
//        ArrayList<ArrayList<String>> datos = new ArrayList<>();
//        datos = readSCV(filePath);
//        double valorTotalFinal = 0;
//        double valorFinalSinI = 0;
//        double valorImpuestoFinal = 0;
//        String fecha = "";
//        DecimalFormat df = new DecimalFormat("#.00");
//        String codigotienda ="01";
//        String tienda="";
//           
//
//        //arrays necesarios para operaciones       
//        ArrayList<Float> valorImpuesto = new ArrayList<>();
//        ArrayList<Double> valor = new ArrayList<>();
//        ArrayList<Double> valorTotal = new ArrayList();
//        ArrayList<Integer> paquetesVendidos = new ArrayList<Integer>();
//        ArrayList<Integer> stockInicial = new ArrayList<Integer>();
//        ArrayList<Integer> StockMinimo = new ArrayList<Integer>();
//        ArrayList<Integer> stockDisponible = new ArrayList<Integer>();
//        ArrayList<Double> valorSinImpuesto = new ArrayList();
//
//
//        /*
//        * llena los array list con los datos de las columnas, 
//        * impuesto(Float) y valorint, para poder hacer las operaciones
//        * matematicas
//         */
//        for (int i = 1; i < datos.size(); i++) {
//            valorImpuesto.add((Float.parseFloat(datos.get(i).get(11)) / 100));
//            valor.add(Double.parseDouble(datos.get(i).get(12)));
//            valorSinImpuesto.add(valor.get(i - 1) * (Integer.parseInt(datos.get(i).get(14))));
//            valorTotal.add((valorImpuesto.get(i - 1) * valor.get(i - 1) + valor.get(i - 1)) * (Integer.parseInt(datos.get(i).get(14))));
//            paquetesVendidos.add(Integer.parseInt(datos.get(i).get(14)));
//            stockInicial.add(Integer.parseInt(datos.get(i).get(13)));
//            StockMinimo.add(Integer.parseInt(datos.get(i).get(15)));
//            stockDisponible.add(Integer.parseInt(datos.get(i).get(13)) - (Integer.parseInt(datos.get(i).get(14))));
//           
//            if (datos.get(i).get(2).substring(0, 2).equals(codigotienda)) {
//                valorTotalFinal = valorTotal.get(i - 1) + valorTotalFinal;
//                valorFinalSinI = valorSinImpuesto.get(i - 1) + valorFinalSinI;
//                valorImpuestoFinal = valorTotalFinal - valorFinalSinI;
//                tienda = datos.get(i).get(1);
//                fecha = datos.get(i).get(0);
//                System.out.println("este es"+valorTotal.get(i - 1));
//                System.out.println(datos.get(i).get(2).substring(0, 2));
//
//            }
//
//        }
//        
//        System.out.println(fecha);
//        System.out.println(tienda);
//        System.out.println((df.format(valorTotalFinal)));
//        System.out.println((df.format(valorFinalSinI)));
//        System.out.println((df.format(valorImpuestoFinal)));
//        impuesto ventas;
//        System.out.println(valorTotal);
//        System.out.println(impuesto);
//        float valor_impuesto = datos.get(1).get(11) + 2;
    }
}
