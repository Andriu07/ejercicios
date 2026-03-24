
// Creamos un arreglo llamado "images" que serian las imagenes que nos piden
// Lógica: organización de datos usamos un array de objetos para estructurar información
// Cada objeto representa una obra de arte es decir cada registro que tenemos con sus propiedades título, artista y fecha
let images = [
    { 
        title: 'Mona Lisa', 
        artist: 'Leonardo da Vinci',
         date: 1503
         },
    { 
        title: 'The Last Supper',
         artist: 'Leonardo da Vinci',
          date: 1495 
        },
    { 
        title: 'The Starry Night',
         artist: 'Vincent van Gogh',
          date: 1889 
        },
    { 
        title: 'The Scream', 
        artist: 'Edvard Munch',
         date: 1893 
        },
    { 
        title: 'Guernica', 
        artist: 'Pablo Picasso', 
        date: 1937
     },
    { 
        title: 'The Kiss', 
        artist: 'Gustav Klimt', 
        date: 1907 },
    {
         title: 'Girl With a Pearl Earring', 
         artist: 'Johannes Vermeer', 
         date: 1665 },
    { 
        title: 'The Birth of Venus',
         artist: 'Sandro Botticelli',
          date: 1485 },
    {
         title: 'Las Meninas', 
         artist: 'Diego Velázquez', 
         date: 1656 },
    { 
        title: 'Creation of Adam', 
        artist: 'Michelangelo', 
        date: 1512
     }
];

// Recorremos el arreglo con forEach
// Lógica: iteración aplicamos una acción sobre cada elemento del array
images.forEach(image => {
   
    // Lógica: interpolación de cadenas insertar valores de las propiedades dentro de un texto
    console.log(`${image.title}, ${image.artist}, (${image.date})`);
    // Ejemplo de como se va mostrar  Mona Lisa, Leonardo da Vinci, (1503)
});