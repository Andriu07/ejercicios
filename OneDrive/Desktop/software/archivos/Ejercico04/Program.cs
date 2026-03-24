using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Ejercico04
{
    internal class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine("Ingrese el número del mes ");
            int month = 1;
            string month_name = "";

            if (!int.TryParse(Console.ReadLine(), out month))
            {
                Console.WriteLine("El número debe ser entero");
                return;
            }

            if (month < 1 || month > 12)
            {
                Console.WriteLine("los meses nom estan correctamente");
                return;
            }

            switch (month)
            {
                case 1:

                    month_name = "Enero";



                    break;

                case 2:

                    month_name = "Febrero";

                    break;

                case 3:

                    month_name = "Marzo";

                    break;

                case 4:

                    month_name = "Abril";

                    break;

                case 5:

                    month_name = "Mayo";

                    break;

                case 6:

                    month_name = "Junio";

                    break;

                case 7:

                    month_name = "Julio";

                    break;

                case 8:

                    month_name = "Agosto";

                    break;

                case 9:

                    month_name = "Septiembre";
                    break;

                case 10:

                    month_name = "Octubre";

                    break;

                case 11:

                    month_name = "Noviembre";

                    break;

                case 12:
                    month_name = "Diciembre";

                    break;

                default:
                    Console.WriteLine("El número de este mes no existe");
                    return;
            }
            Console.WriteLine($"El número {month} corresponde al mes de {month_name}");
        }
    }
}
