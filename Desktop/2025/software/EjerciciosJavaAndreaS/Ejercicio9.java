/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package saludplan.ejercicio9;

import java.util.Scanner;

/**
 *
 * @author andre
 */
public class Ejercicio9 {

    public static void main(String[] args) {
        System.out.println("Registra tu plan de Salud");
        int edad;
        String Nombre, Condicion, Plan;
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingresa tu Nombre");
        Nombre = sc.next();
        System.out.println("********************");
        System.out.println("Ingresa tu edad");
        edad = sc.nextInt();
        System.out.println("********************");
        System.out.println("Padece de alguna Condicion Cronicas?");
        System.out.println("SI/NO?");
        Condicion = sc.next();
        if(edad > 0){
           if(Condicion.equalsIgnoreCase("no") || Condicion.equalsIgnoreCase("si")){
            Plan = Plan_Salud.AsignarPlanPorEdadYCondicion(edad, Condicion);
            System.out.println("El paciente: "+Nombre+", le Corresonde el plan de: "+Plan);
           }
           else{
            System.out.println("Especifique si SI tiene Condicion o NO tiene condicion");  
           }
        }else{
            System.out.println("No puede tener menos de 0 Años");
        }

    }
}
 
class Plan_Salud{
    public static String AsignarPlanPorEdadYCondicion(int edad, String Condicion){
        String PlanSalud;
        if(edad <= 30 && Condicion.equalsIgnoreCase("no")){
           PlanSalud = "Basico"; 
           return PlanSalud;
        }
        else if(edad <= 30 && Condicion.equalsIgnoreCase("si")
        || edad > 30 && Condicion.equalsIgnoreCase("no")){
            PlanSalud = "Estandar";
            return PlanSalud;
        }
        else{
            PlanSalud = "Premium";
            return PlanSalud;
        }
    }
}
   
