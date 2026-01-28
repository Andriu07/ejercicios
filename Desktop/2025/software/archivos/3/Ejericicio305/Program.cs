using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Ejericicio305
{
    internal class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine("Ingresa su nota de teoría: ");
            float theory = float.Parse(Console.ReadLine());

            Console.WriteLine("Ingresa nota de prácticas:");
            float practices = float.Parse(Console.ReadLine());

            float note = (theory * 0.6f) + (practices * 0.4f);
            string rating = "Sobresaliente";

            if (note >= 7 && note < 9)
            {
                rating = "Notable";
            }
            else if (note >= 5 && note < 7)
            {
                rating = "Aprobado";
            }
            else if (note >= 0 && note < 5)
            {
            }
            rating = "Suspenso";
            Console.WriteLine($"su nota final es {note}, su calificación es {rating}");
            Console.ReadKey();
        }
    }
}
