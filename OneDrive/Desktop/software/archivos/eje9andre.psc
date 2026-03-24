Algoritmo EJerciciov9
	definir promedio ,lenguaje, sociales, ciencias,matematticas ,ingles Como Real
	escribir "Ingrese la nota de lenguaje y literatura: ";
	leer lenguaje;
	escribir "Ingrese la nota de sociales: ";
	leer sociales;
	escribir "Ingrese la nota de matematicas: ";
	leer matematicas;
	escribir "Ingrese ingles: ";
	leer ingles;
	escribir "Ingresar nota de ciencias: ";
	leer ciencias;
	Si lenguaje <1 o lenguaje >10 o sociales <1 o sociales >10 o ciencias <1 o ciencias >10 o matematicas <1 o matematicas >10 o ingles <1 o  ingles >10 Entonces
		imprimir "Una o varias son incorrectas";
	SiNo
		Promedio = (lenguaje + sociales + ciencias + matematicas +ingles)/5;
		Si promedio >=6 Entonces
			imprimir "APROBADO";
		SiNo
			imprimir "REPROBADO";
		Fin Si
	Fin Si
FinAlgoritmo
