using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Ejercicio301
{
    internal class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine("Ingrese 2 números: ");
            float num1 = float.Parse(Console.ReadLine());
            float num2 = float.Parse(Console.ReadLine());

            if (num1 > num2)
            {
                Console.WriteLine($"{num1} es mayor que {num2}");
            }
            else if (num1 < num2)
            {
                Console.WriteLine($"{num1} es menor que {num2}");
            }
            else
            {
                Console.WriteLine($"{num1} es igual que {num2}");
                Console.ReadKey();
            }
    }
}
