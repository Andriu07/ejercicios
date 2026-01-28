/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package bisiesto.ejercicio8;

import java.util.Scanner;

/**
 *
 * @author andre
 */
public class Ejercicio8 {

    public static void main(String[] args) {
        System.out.println("Calcular año bisisesto");
        int Año;
        boolean Verificar;
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingresa el Año");
        Año = sc.nextInt();
        if(Año < 1582){
            System.out.println("Año no valido");
        }else{
            Verificar = AñoBisiesto.CalcularAñoBisiesto(Año);
            if(Verificar == true){
                System.out.println("Si es Bisiesto");
            }else{
                System.out.println("No es Bisiesto");
            }

        }

    }

}
 
class AñoBisiesto {
    public static boolean CalcularAñoBisiesto(int Año){
        if((Año % 4 == 0 && Año % 100 != 0) || (Año % 400 == 0)){
            return true;
        }else{
            return false;
        }

    }

}
