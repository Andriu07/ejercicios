/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package diadelasemana.ejercicio4;

import java.util.Scanner;

/**
 *
 * @author andre
 */
public class Ejercicio4 {

    public static void main(String[] args) {
        int Valor;
        String dia;
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese un número dependiendo del dia de la semana:");
        Valor = sc.nextInt();
        if(Valor >= 1 && Valor <= 7){
        dia = Verificacion.VerificarDia(Valor);
            System.out.println("El dia es: "+dia);
        }else{
            System.out.println("Valor invalido");
        }
    }
}

class Verificacion{
 public static String VerificarDia(int Valor){
     String dia;
 if(Valor == 1){
 dia = "Lunes";
      return dia;
 }else if (Valor == 2){
     dia = "Martes";
      return dia;
 }else if(Valor == 3){
     dia = "Miercoles";
      return dia;
 }else if(Valor == 4){
     dia = "Jueves";
      return dia;
 }else if(Valor == 5){
     dia = "Viernes";
      return dia;
 }else if(Valor == 6){
     dia = "Sabado";
      return dia;
 }else{
     dia = "Domingo";
      return dia;
     }
 }
}