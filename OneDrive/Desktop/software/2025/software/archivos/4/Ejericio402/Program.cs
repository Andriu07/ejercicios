using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Ejericio402
{
    internal class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine("Ingrese su edad: ");
            int years = int.Parse(Console.ReadLine());

            if (years < 18)
            {
                Console.WriteLine("Eres Menor de edad :(");
                return;
            }

            Console.WriteLine("Eres mayor de edad :D");
        }
    }
    }
}
