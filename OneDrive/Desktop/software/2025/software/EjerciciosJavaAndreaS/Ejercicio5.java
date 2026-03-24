/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package descuento.ejercicio5;

import java.util.Scanner;

/**
 *
 * @author andre
 */
public class Ejercicio5 {

    public static void main(String[] args) {
        int MontoT;
        boolean Validar;
        Double MontoPago;
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el monto total de compra:");
        MontoT = sc.nextInt();
        sc.close();
        if(MontoT > 0){
            Validar = VerificarCompra.ValidarDescuento(MontoT);
            if(Validar == true){
                MontoPago = MontoT*(10.0 / 100);
                System.out.println("El monto a pagar es : "+MontoPago);
             }else{
            System.out.println("El monto a pagar es: $"+MontoT);
        }}else{
            System.out.println("El monto no puede ser menor que 0");
}
    }
}

class VerificarCompra{
      public static boolean ValidarDescuento(int MontoT){
      if(MontoT > 100){
      return true;
      }else{
       return false;
      }
  }
}