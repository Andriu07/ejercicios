using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Ejercico407
{
    internal class Program
    {
        static void Main(string[] args)
        {

            Console.WriteLine("Ingrese la primera nota: ");
            float note1 = float.Parse(Console.ReadLine());

            Console.WriteLine("Ingrese la segunda nota: ");
            float note2 = float.Parse(Console.ReadLine());

            Console.WriteLine("Ingrese la tercer nota: ");
            float note3 = float.Parse(Console.ReadLine());

            float average = (note1 + note2 + note3) / 3;

            if (average >= 3.5)
            {
                Console.WriteLine("Aprobado felicidades!");
            }
            else
            {
                Console.WriteLine("Reprobado :( ");
            }
        }
    }
}
