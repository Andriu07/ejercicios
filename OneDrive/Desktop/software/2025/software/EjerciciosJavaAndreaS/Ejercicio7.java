/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package calculadorasimple.ejercicio7;

import java.util.Scanner;

/**
 *
 * @author andre
 */
public class Ejercicio7 {

    public static void main(String[] args) {
        System.out.println("Calculadora Simple");
        int Num1,Num2;
        int resultado;
        String Operador;
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingresa el Primer Numero");
        Num1 = sc.nextInt();
        System.out.println("Ingresa el Segundo Numero");
        Num2 = sc.nextInt();
        System.out.println("+ = suma");
        System.out.println("- = resta");
        System.out.println("* = multiplicacion");
        System.out.println("/ = division");
        System.out.println("Que operacion desea realizar?");
        Operador = sc.next();
        if(Operador.equalsIgnoreCase("suma") || Operador.equalsIgnoreCase("resta")
        || Operador.equalsIgnoreCase("multiplicacion") || Operador.equalsIgnoreCase("division"))
        {
            System.out.println("escriba el signo de la operacion que quiere realizar");
        }else if(Operador.equalsIgnoreCase("+")){
            resultado = Calculadora.SumarNumeros(Num1,Num2);
            System.out.println("El resultado es = "+resultado);
        }else if(Operador.equalsIgnoreCase("-")){
            resultado = Calculadora.RestarNumeros(Num1,Num2);
            System.out.println("El resultado es = "+resultado);
        }else if(Operador.equalsIgnoreCase("*")){
            resultado = Calculadora.MultiplicarNumeros(Num1,Num2);
            System.out.println("El resultado es = "+resultado);
        }   else if(Operador.equalsIgnoreCase("/")){
            resultado = Calculadora.DividirNumeros(Num1,Num2);
            System.out.println("El resultado es = "+resultado);
        }else{
            System.out.println("Operador no soportado");
        }
       }
    }
 
       class Calculadora{
 
       public static int SumarNumeros(int num1, int num2){
     int Valor;
     Valor = num1 + num2;
     return Valor;
    }

  public static int RestarNumeros(int num1, int num2){
      int Valor;
      Valor = num1 - num2;
      return Valor;
  }
   public static int MultiplicarNumeros(int num1, int num2){
      int Valor;
      Valor = num1 * num2;
      return Valor;
  }

    public static int DividirNumeros(int num1, int num2){
      int Valor;
      if(num1 == 0 || num2 == 0){
          System.out.println("Error Division por cero");
          return 0;
      }else{
          Valor = num1 / num2;
      return Valor;
      } 

  }

}
 

