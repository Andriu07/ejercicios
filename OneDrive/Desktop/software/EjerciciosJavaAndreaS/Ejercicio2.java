/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package imparopar.ejercicio2;

import java.util.Scanner;

/**
 *
 * @author andre
 */
public class Ejercicio2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese un numero: ");
        int num;
        num = sc.nextInt();
        
        VerificadorNumero.VerificarImpar(num);
        sc.close();
    }
    
    class VerificadorNumero{
    public static void VerificarImpar(int num){
    if (num %2 == 0){
        System.out.println("El numero es par");
    }else{
        System.out.println("El numero es impar");
    }
    }
    
}
