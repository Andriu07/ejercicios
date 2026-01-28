Algoritmo ejercicio2
		Definir sociales, ciencias, matematicas, lenguaje, ingles Como real;
		Definir modulo1, modulo2 Como real;
		Definir promedio, notafinal, promediomodulo,notamodulo Como real;
		Definir nombre Como Caracter;
		
		Escribir "Nombre del estudiante: ";
		Leer nombre;
		Escribir "Ingrese la nota de la materia Sociales: ";
		Leer sociales;
		Escribir "Ingrese la nota de Ciencias: ";
		Leer ciencias;
		Escribir "Ingrese la nota de Matemáticas: ";
		Leer matematicas;
		Escribir "Ingrese la nota de Lenguaje : ";
		Leer lenguaje;
		Escribir "Ingrese la nota de Ingles: ";
		Leer ingles;
		Imprimir "Notas finales"
		Imprimir "Nombre del estudiante: " ,nombre;
		
		Si sociales>=6 Entonces
			Imprimir "La nota de Sociales fue de: ",sociales  " Aprobado";
		SiNo
			Imprimir "La nota de Sociales fue de: ",sociales  " Reprobado";
		fin si
		Si ciencias>=6 Entonces
			Imprimir "La nota de Ciencias fue: ",ciencias  " Aprobado"
		SiNo
			Imprimir "La nota de Ciencias fue: ",ciencias  " Reprobado"
		Fin Si
		
		Si matematicas>=6 Entonces
			Imprimir "La nota de Matemáticas fue de: ",matemáticas  " Aprobado"
		SiNo
			Imprimir "La nota de Matemáticas fue de: ",matemáticas  " Reprobado"
		Fin Si
		Si Lenguaje>=6 Entonces
			Imprimir "La nota de Lenguaje fue: ",lenguaje  " Aprobado"
		SiNo
			Imprimir "La nota de Lenguaje fue: ",lenguaje  " Reprobado"
		Fin Si
		Si ingles>=6 Entonces
			Imprimir "La nota de Ingles fue: ",ingles  " Aprobado"
		SiNo
			Imprimir "La nota de Ingles fue: ",ingles  " Reprobado"
		Fin Si
		Imprimir "Promedio final de Academicas"
		promedio= sociales + ciencias + matematicas + lenguaje + ingles
		notafinal= promedio/5
		
		Si notafinal>=6 Entonces
			Imprimir "El periodo lo ha aprobado con: ",notafinal;
		SiNo
			Imprimir "El periodo no lo ha aprobado con: ",notafinal;
		Fin Si 
		
		Imprimir "Nota obtenida en los Modulos";
		Escribir "Ingrese nota del módulo de Elaboracion de algoritmos usando lógica de programación: ";
		Leer modulo1;
		Escribir "Ingrese nota del módulo de Diseño de paginas Web: ";
		Leer modulo2;
		Imprimir "Nota Final de los Módulos";
		
		Si modulo1>=4.0 Entonces
			Imprimir "La nota de ElaboraciÓn de algoritmos fue: ",modulo1 " Aprobado";
		SiNo
			Imprimir "La nota de ElaboraciÓn de algoritmos fue: ",modulo1  " Reprobado";
		Fin Si
		Si modulo2>=4.0 Entonces
			imprimir "La nota de Diseño web: ",modulo2  " Aprobado";
		SiNo
			Imprimir "La nota de Diseño web fue : ",modulo2  " Reprobado";
		Fin Si
FinAlgoritmo