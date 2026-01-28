create database Canciones_20240349
Go

use Canciones_20240349
Go

Create table Generos
(
idGenero int primary key,
nombreGenero varchar(100)
)
Go

Create table Bandas
(
idBanda int primary key,
nombreBanda varchar(100)
)
Go

Create table Canciones
(
idCancion int primary key,
nombreCancion varchar(100),
idBanda int foreign key references Bandas(idBanda),
idGenero int foreign key references Generos(idGenero)
)
Go

insert into Generos (idGenero, nombreGenero)
values ('1', 'Rock')
Go

insert into Generos (idGenero, nombreGenero)
values ('2', 'Pop')
Go

insert into Generos (idGenero, nombreGenero)
values ('3', 'Corridos tumbados')
Go

insert into Bandas (idBanda, nombreBanda)
values ('1', 'Queen')
Go

insert into Bandas (idBanda, nombreBanda)
values ('2', 'ABBA')
Go

insert into Bandas (idBanda, nombreBanda)
values ('3', 'Eslabón Armado')
Go

insert into Canciones (idCancion, nombreCancion, idBanda, idGenero)
values ('1', 'Bohemian Rhapsody', '1', '1')
Go

insert into Canciones (idCancion, nombreCancion, idBanda, idGenero)
values ('2', 'Dancing queen', '2', '2')
Go

insert into Canciones (idCancion, nombreCancion, idBanda, idGenero)
values ('3', 'The Winner Takes It All', '2', '2')
Go

insert into Canciones (idCancion, nombreCancion, idBanda, idGenero)
values ('4', 'Ella Baila Sola', '3', '3')
Go

insert into Canciones (idCancion, nombreCancion, idBanda, idGenero)
values ('5', 'Jugaste y sufrí', '3', '3')
Go

Select Canciones.nombreCancion, Generos.nombreGenero, Bandas.nombreBanda from Canciones
inner join Generos on Canciones.idGenero = Generos.idGenero
inner join Bandas on Canciones.idBanda = Bandas.idBanda
go

select idCancion as '# de Canción', 
nombreCancion as 'Nombre de la canción' from Canciones
Go

select  Canciones.nombreCancion as 'Nombre de la Canción', Generos.nombreGenero as 'Género', Bandas.nombreBanda as 'Banda' from Canciones
inner join Generos on Canciones.idGenero = Generos.idGenero
Inner join Bandas on Canciones.idBanda = Bandas.idBanda
Go