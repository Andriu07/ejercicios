using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Ejercicio01
{
    internal class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine("Favor ingrese su nombre: ");
            string name = (Console.ReadLine());

            Console.WriteLine("Ingrese su salario: ");
            float salary = float.Parse(Console.ReadLine());

            Console.WriteLine("Ingrese el número de años que ha trabajado: ");
            int years = int.Parse(Console.ReadLine());

            Console.WriteLine("Ingrese su categoria: ");
            int category = int.Parse(Console.ReadLine());

            float increase_years = (salary * 0.05f) * years;

            float increase_category = (salary * 0.15f);
            if (category == 2)
            {
                increase_category = salary * 0.10f;
            }
            else if (category == 3)
            {
                increase_category = salary * 0.05f;
            }
         
            float increase = increase_years + increase_category;
            float new_salary = salary + increase;

            Console.WriteLine($"{name}, su nuevo slario es de {new_salary}");
            Console.ReadKey();
        }
    }
}
