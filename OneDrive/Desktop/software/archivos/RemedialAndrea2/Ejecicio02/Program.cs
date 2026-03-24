using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Ejecicio02
{
    internal class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine("Ingrese un número");
            float num = float.Parse(Console.ReadLine());

            if (num > 0)
            {
                Console.WriteLine("El número es positivo");
            }
            else if (num < 0)
            {
                Console.WriteLine("El número es negativo");
            }
            else
            {
                Console.WriteLine("El número es nulo");
            }
            Console.ReadKey();
        }
    }
}
