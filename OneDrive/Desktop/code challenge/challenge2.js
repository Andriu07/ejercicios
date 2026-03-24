// vamos a usar un constructor
// Lógica: encapsulación  definimos una plantilla para crear objetos con propiedades específicas
let Image = function(title, artist, date) {
    this.title = title;   // asignamos el título
    this.artist = artist; // asignamos el artista
    this.date = date;     // asignamos la fecha
}

// Función que devuelve un objeto literal.
// Lógica: creación directa de objetos 
let getImage = function(title, artist, date) {
    return {
        title,   // propiedad título
        artist,  // propiedad artista
        date     // propiedad fecha
    }
}

// Creamos dos arreglos vacíos para almacenar las imágenes en diferentes formatos
let images1 = []; // contendrá objetos creados con el constructor "Image"
let images2 = []; // contendrá objetos creados con la función "getImage"

// Recorremos el arreglo original "images" 
// Lógica: iteración/ instanciación usamos forEach para recorrer y "new Image" para crear instancias
images.forEach(image => images1.push(new Image(image.title, image.artist, image.date)));

// Recorremos el arreglo "images1".
// Lógica: transformación convertimos cada objeto tipo "Image" en un objeto literal con getImage
images1.forEach(image => images2.push(getImage(image.title, image.artist, image.date)));

// Finalmente recorremos "images2".
// Lógica: presentación mostramos cada objeto en consola con formato legible.
images2.forEach(image => {
    console.log(`${image.title} (${image.artist}, ${image.date})`);
    // Ejemplo de salida: Mona Lisa (Leonardo da Vinci, 1503)
});