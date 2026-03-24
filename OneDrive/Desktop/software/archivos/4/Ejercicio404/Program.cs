using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Ejercicio404
{
    internal class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine("Ingrese el primer número: ");
            float num1 = float.Parse(Console.ReadLine());

            Console.WriteLine("Ingrese el segundo número: ");
            float num2 = float.Parse(Console.ReadLine());

            Console.WriteLine("Ingrese el tercer número: ");
            float num3 = float.Parse(Console.ReadLine());

            float max = num1;

            if (num2 >= max)
            {
                max = num2;
            }

            if (num3 > max)
            {
                max = num3;
            }
            Console.WriteLine($"El número mayor es: {max}");
            Console.ReadKey();
        }
    }
}
