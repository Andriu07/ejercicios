using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Ejercio401
{
    internal class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine("Ingrese su edad: ");
            int years = int.Parse(Console.ReadLine());

            if (years >= 18)
            {
                Console.WriteLine("Eres una persona mayor de edad");
            }
        }
    }
}
