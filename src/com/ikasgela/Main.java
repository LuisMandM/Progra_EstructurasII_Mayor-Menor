package com.ikasgela;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static List<Amigo> amigos_Added = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        boolean running = true;

        do {
            System.out.print("\n---Programa de amigos---\n1. Nuevo amigo\n" +
                    "2. Listado de amigos y edad de cada uno\n" +
                    "3. ¿Mayor o menor?\n" +
                    "4. Salir\nOpcion: ");
            try {
                int entrada = Integer.parseInt(br.readLine());

                switch (entrada) {

                    case 1:
                        CrearAmigo(br);
                        break;
                    case 2:
                        if (amigos_Added.size() > 0) {
                            VerAmigosIngresados();
                        } else {
                            System.out.println("Sin amigos añadidos aún :(");
                        }
                        break;
                    case 3:
                        if (amigos_Added.size() >= 2) {
                            CompararEdades(br);
                        } else {
                            System.out.println("Sin amigos suficientes aún");
                            VisualizarNombresAmigos();
                        }
                        break;
                    case 4:
                        System.out.println("Saliendo...");
                        running = false;
                        break;

                    default:
                        System.out.println("Opcion Invalida");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Tipo de dato ingresado INVALIDO");
            }

        } while (running);

    }

    private static void CompararEdades(BufferedReader br) throws IOException {
        VisualizarNombresAmigos();
        System.out.print("Amigo 1: ");

        try {
            int amigo_1 = Integer.parseInt(br.readLine()) - 1;
            if (amigo_1 >= amigos_Added.size() || amigo_1 < 0) {
                System.out.println("Error: Opcion fuera de los limites");
                return;
            }
            System.out.print("Amigo 2: ");
            int amigo_2 = Integer.parseInt(br.readLine()) - 1;
            if (amigo_2 >= amigos_Added.size() || amigo_2 < 0) {
                System.out.println("Error: Opcion fuera de los limites");
                return;
            }

            Amigo amigo_Compare1 = amigos_Added.get(amigo_1);
            Amigo amigo_Compare2 = amigos_Added.get(amigo_2);
            int comparacion = amigo_Compare1.compareTo(amigo_Compare2);

            if (comparacion > 0) {
                System.out.println(amigo_Compare1.getNombre() + " es mayor que " +
                        amigo_Compare2.getNombre());
            } else if (comparacion < 0) {
                System.out.println(amigo_Compare1.getNombre() + " es menor que " +
                        amigo_Compare2.getNombre());
            } else {
                System.out.println(amigo_Compare1.getNombre() + " y " +
                        amigo_Compare2.getNombre() + " tienen la misma edad");
            }


        } catch (NumberFormatException e) {
            System.out.println("ERROR: DATO INVALIDO");
        }
    }

    private static void VerAmigosIngresados() {
        System.out.printf("%-25s | %-5s\n", "Nombre", "Edad");
        for (int i = 0; i <= 35; i++) {
            System.out.print("-");
        }
        System.out.println();
        for (Amigo panita : amigos_Added) {
            System.out.println(panita);
        }
        System.out.println();
    }

    private static void VisualizarNombresAmigos() {
        for (int i = 0; i < amigos_Added.size(); i++) {
            System.out.println((i + 1) + "- " + amigos_Added.get(i).getNombre());
        }
    }

    private static void CrearAmigo(BufferedReader br) throws IOException {
        System.out.print("Ingrese el nombre de su amigo: ");
        String nombre_actual = br.readLine();

        try {

            System.out.print("Ingrese el dia de nacimiento, formato DD: ");
            int dia_Nac = Integer.parseInt(br.readLine());
            if (dia_Nac <= 0 || dia_Nac > 31) {
                System.out.println("Error: dato de DIA fuera de los valores validos\n");
                return;
            }

            System.out.print("Ingrese el mes de nacimiento, formato MM: ");
            int mes_Nac = Integer.parseInt(br.readLine());
            if (mes_Nac <= 0 || mes_Nac > 12) {
                System.out.println("Error: dato de MES fuera de los valores validos\n");
                return;
            }

            System.out.print("Ingrese el año de nacimiento, formato YYYY: ");
            int anio_Nac = Integer.parseInt(br.readLine());
            if (anio_Nac <= 0) {
                System.out.println("Error: dato de AÑO fuera de los valores validos\n");
                return;
            }
            try {
                LocalDate fecha_nac = LocalDate.of(anio_Nac, mes_Nac, dia_Nac);

                Amigo amigo_Actual = new Amigo(nombre_actual, fecha_nac);
                if (amigos_Added.contains(amigo_Actual)) {
                    System.out.println("Amigo ya registrado");
                } else {
                    amigos_Added.add(amigo_Actual);
                    System.out.println("Amigo añadido correctamente");
                }
            } catch (DateTimeException e) {
                System.out.println("Error: Fecha invalida\n");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Tipo de entrada invalida\n");
        }
    }
}
