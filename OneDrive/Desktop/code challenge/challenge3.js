// La idea es que "images" sea un objeto con tres responsabilidades:
// add agregar una nueva obra al listado
// show  mostrar todas las obras almacenadas
// clear vaciar el listado

// Ejemplo conceptual de cómo podría estar definido:
let images = {
    data: [], // aquí se guardan las obras

    // Lógica: encapsulación de datos se agrega un nuevo objeto al arreglo interno
    add: function(title, artist, date) {
        this.data.push({ title, artist, date });
    },

    // Lógica: iteración /presentación  recorre el arreglo y muestra cada obra en demanre mas facil de leer
    show: function() {
        this.data.forEach(image => {
            console.log(`${image.title} (${image.artist}, ${image.date})`);
        });
    },

    // Lógica: limpieza vacía el arreglo interno para reiniciar la coleccion.
    clear: function() {
        this.data = [];
    }
};

// Uso del objeto
images.add('Mona Lisa', 'Leonardo da Vinci', 1503);
images.add('The Last Supper', 'Leonardo da Vinci', 1495);
images.add('The Starry Night', 'Vincent van Gogh', 1889);
images.show();
// Mona Lisa (Leonardo da Vinci, 1503)
// The Last Supper (Leonardo da Vinci, 1495)
// The Starry Night (Vincent van Gogh, 1889)

images.clear(); // vaciamos la colección
images.show();  // ya no muestra nada porque el arreglo está vacío