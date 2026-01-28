/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package aprobadoreprobado.ejericio3;

import java.util.Scanner;

/**
 *
 * @author andre
 */
public class Ejericio3 {

    public static void main(String[] args) {
        int cali;
        boolean Validar;
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese calificacion: ");
        cali = sc.nextInt();
        sc.close();
        if(cali <=10 && cali > 0){
        Validar = VerificarCalificaciones.ValidarCalificacion(cali);
         if(Validar == true){
             System.out.println("Aprobado");
         }else{
             System.out.println("Reprobado");
         }}else{
              System.out.println("La nota no puede ser menor que 0 ni mayor que 10"); 
                 }
         }             

}

class VerificarCalificaciones{
   public static boolean ValidarCalificacion(int cali){
   if(cali >= 6){
     return true;
   }else{
   return false;
   }
  
       
 }



}