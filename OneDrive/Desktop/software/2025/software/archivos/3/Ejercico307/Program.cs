using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Ejercico307
{
    internal class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine("Ingresa el primer lado:");
            float side1 = float.Parse(Console.ReadLine());

            Console.WriteLine("Ingresa el segundo lado:");
            float side2 = float.Parse(Console.ReadLine());

            Console.WriteLine("Ingresa el tercer:");
            float side3 = float.Parse(Console.ReadLine());

            if (side1 != side2 && side1 != side3 && side2 != side3)
            {
                Console.WriteLine("El triángulo es escaleno");
            }
            else if (side1 == side2 && side1 == side3)
            {
                Console.WriteLine("El tria´ngulo es equilátero");
            }
            else
            {
                Console.WriteLine("El triángulo es isóseceles");
            }
            Console.ReadKey();
        }
    }
}
