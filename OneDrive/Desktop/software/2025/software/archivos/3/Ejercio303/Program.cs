using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Ejercio303
{
    internal class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine("Ingresa un número: ");
            float num1 = float.Parse(Console.ReadLine());
            Console.WriteLine("Ingrese otro número: ");
            float num2 = float.Parse(Console.ReadLine());

            Console.WriteLine("Eliga la operación que desea realizar:");
            Console.WriteLine("1. Suma");
            Console.WriteLine("2. Resta");
            Console.WriteLine("3. Multiplicación");
            Console.WriteLine("4. División");
            int operation = int.Parse(Console.ReadLine());

            float result = 0;

            if (operation == 1)
            {
                result = num1 + num2;
            }
            else if (operation == 2)
            {
                result = num1 - num2;
            }
            else if (operation == 3)
            {
                result = num1 * num2;
            }
            else if (operation == 4)
            {
                result = num1 / num2;
            }
            else
            {
                Console.WriteLine("Ninguna operación escogida es correcta");
            }

            Console.WriteLine($"El reultado final es: {result}");
            Console.ReadKey();
        }
    }
}
