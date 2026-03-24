using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Ejericcio05
{
    internal class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine("Ingrese el número que desee");
            float num = float.Parse(Console.ReadLine());

            Console.WriteLine("Ingrse el exponente que desee");
            int exponent = int.Parse(Console.ReadLine());

            float result = num;

            if (exponent == 0)
            {
                result = 1;
                Console.WriteLine($"El resultado de elevar {num} al {exponent} es {result}");
                return;
            }

            int repeats = exponent;
            if (repeats < 0)
            {
                repeats *= -1;
            }

            for (int i = 1; i < repeats; i++)
            {
                result = result * num;
            }

            if (exponent < 0)
            {
                result = 1 / result;
            }
            Console.WriteLine($"El resultado de elevar {num} al {exponent} es {result}");
            Console.ReadKey();
        }
    }
}
