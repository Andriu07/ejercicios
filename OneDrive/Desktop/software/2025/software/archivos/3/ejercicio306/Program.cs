using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ejercicio306
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

            if (num1 < num2 && num2 < num3)
            {
                Console.WriteLine("Los números estan ordenados ascendentemente");
            }
            else if (num1 > num2 && num2 > num3)
            {
                Console.WriteLine("Los números estan ordenados desendentemente");
            }
            else
            {
                Console.WriteLine("Los números están desordenados");
            }
            Console.ReadKey();
        }
    }
}
