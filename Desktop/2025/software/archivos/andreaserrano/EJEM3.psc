Algoritmo EJEM3
	Definir nombre, apellido, dirección, género Como Caracter;
	Definir edad Como Real
	Escribir "Ingrese su nombre: ";
	leer nombre;
	escribir "Ingrese su apellido: ";
	leer apellido;
	escribir "Ingrese su sirección: ";
	leer dirección;
	escribir "Ingrese género: ";
	leer genero;
	escribir "Ingrese su edad : ";
	leer edad;
	Si edad>18 Entonces
		imprimir "nombre del empleado: ", nombre;
		Imprimir "apellido del empleado: ", apellido;
		Imprimir "dirección del empelado: ", dirección;
		Imprimir "género del empleado: ",genero;
		imprimir "edad del empleado: ", edad;
	SiNo
		Escribir "No permitido"
	Fin Si
	
	FinAlgoritmo
