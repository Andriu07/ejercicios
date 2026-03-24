using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Ejerciciio405
{
    internal class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine("Ingrese el precio nicial del producto: ");
            float price = float.Parse(Console.ReadLine());

            Console.WriteLine("El vendedore reclama impuesto: (Y/N)");
            char option = char.Parse(Console.ReadLine());

            float iva = 0f;
            if (option == 'Y')
            {
                iva = price + 0.19f;
            }
            else if (option != 'N')
            {
                Console.WriteLine("Reponde de manera correcta");
                return;
            }
            float new_price = price + iva;
            Console.WriteLine($"El precio inicial del producto es: ${price}");
            Console.WriteLine($"El IVA es : ${iva}");
            Console.WriteLine($"El nuevo precio es: ${new_price}");
            Console.ReadKey();
        }
    }
}
