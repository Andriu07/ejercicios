using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Ejerico406
{
    internal class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine("Ingrese su sueldo: ");
            float salary = float.Parse(Console.ReadLine());

            float increase = 0;
            if (salary <= 500.00f)
            {
                increase = salary * 0.15f;
            }
            else if (salary <= 600.00f)
            {
                increase = salary * 0.10f;
            }

            float new_salary = salary + increase;

            Console.WriteLine($"Recibio un aumento de: $ {increase}. Su nuevo salario es de : $ {new_salary}");
            Console.ReadKey();
        }
    }
}
