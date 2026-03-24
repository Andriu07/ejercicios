using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Ejercicio302
{
    internal class Program
    {
        static void Main(string[] args)
        {

            Console.WriteLine("Ingrese un número: ");
            float num = float.Parse(Console.ReadLine());

            string message = "Nulo";
            if (num > 0)
            {
                message = "Este número es positivo";
            }
            else if (num < 0)
            {
                message = "Este número es negativo";
            }
            Console.WriteLine($"El número {num} es {message}");
            Console.ReadKey();

        }
    }
}
