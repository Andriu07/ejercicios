using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Ejercicio403
{
    internal class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine("Ingrese caulquier número: ");
            int num = int.Parse(Console.ReadLine());

            if (num == 3)
            {
                Console.WriteLine("Tarjetón Rojo");
            }
            else
            {
                Console.WriteLine("Tarjetón no existe");
            }
        }
    }
}
