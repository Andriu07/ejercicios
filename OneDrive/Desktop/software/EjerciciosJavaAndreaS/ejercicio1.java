/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.ejerciciopracti;

import java.util.Scanner;

/**
 *
 * @author Estudiante
 */
public class Ejerciciopracti {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese un nùmero: ");
        int num = sc.nextInt();
        VerifivdorNumero.VerificarPositivo(num);
        sc.close();
    }
}   
    class VerifivdorNumero{
     public static void VerificarPositivo(int num){
     if(num > 0){
         System.out.println("El numero es positivo");
     }else{
         System.out.println("El numero es negativo o Cero");
          }
       }
 }

 