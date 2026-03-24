/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package clasificaciondedad.mavenproject1;

import java.util.Scanner;

/**
 *
 * @author andre
 */
public class Mavenproject1 {

    public static void main(String[] args) {
     Scanner sc = new Scanner(System.in);
      System.out.println("Ingrese su edad: ");
        int edad;
        edad = sc.nextInt();
        String categoria;
        categoria = VerificarEdad.VerificarAños(edad);
        System.out.println("La categoria es: "+categoria);
  }
}


class VerificarEdad{
     public static String VerificarAños(int edad){
        String categoria;
        if (edad < 0 || edad > 120) {
           categoria = "Edad inválida";
            return categoria;
        } else if (edad >= 0 && edad <= 12) {
           categoria = "Niño";
            return categoria;
        } else if (edad >= 13 && edad <= 19) {
           categoria = "Adolescente";
            return categoria;
        } else if (edad >= 20 && edad <= 64) {
           categoria = "Adulto";
            return categoria;
        } else {
           categoria = "Adulto Mayor";
           return categoria;
        }
   }
}