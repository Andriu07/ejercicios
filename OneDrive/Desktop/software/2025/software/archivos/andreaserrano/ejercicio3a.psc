Algoritmo Ejercicio3
	definir Rmay, rmen, area Como Real;
	Escribir"Ingrese el radio mayor:";
	leer Rmay;
	Escribir "Ingrese el radio menor: ";
	leer rmen;
	si Rmay > rmen Entonces
			area=3.1416*(Rmay^2 -rmen^2);
			imprimir "El area de la coronaes:",area;
	SiNo
			imprimir "Error";
	Fin Si
FinAlgoritmo
