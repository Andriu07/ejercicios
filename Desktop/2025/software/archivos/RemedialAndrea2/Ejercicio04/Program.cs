using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Ejercicio04
{
    internal class Program
    {
        static void Main(string[] args)
        {

            Console.WriteLine("Favor ingrese un numero");
            float num = float.Parse(Console.ReadLine());

            float abs = num;

            if (num < 0)
            {
                abs = num * -1;
            }
            Console.WriteLine($"El valor absoluto de {num} es {abs}");
            Console.ReadKey();
        }
    }
}
