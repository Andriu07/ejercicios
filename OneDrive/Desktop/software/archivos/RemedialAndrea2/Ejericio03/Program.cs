using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Ejericio03
{
    internal class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine("Ingrese los grados Farengheit:");
            float farengheit = float.Parse(Console.ReadLine());

            float celsius = (5 * (farengheit - 32)) / 9;

            string word = "Aceptable";

            if (celsius <= 0)
            {
                word = "Baja";
            }
            else if (celsius >= 100)
            {
                word = "Alta";
            }

            Console.WriteLine($"La temperatura es de {celsius}°C, es una temperatura {word}");
            Console.ReadKey();
        }
    }
}
