// Creamos un objeto "images" que encapsula un arreglo interno y métodos para manipularlo
// Logica: orientación a objetos agrupamos datos y operaciones en un mismo objeto
let images = {
    data: [], // aquí se almacenan las obras

    // Metodo add: agrega una nueva obra al arreglo interno.
    // Legica: inserción  de un objeto con las propiedades dadas
    add: function(title, artist, date) {
        this.data.push({ title, artist, date });
    },

    // Metodo edit: busca una obra por título y actualiza sus datos
    // Logica: búsqueda actualización recorremos el arreglo y modificamos el objeto encontrado
    edit: function(title, artist, date) {
        let item = this.data.find(img => img.title === title);
        if (item) {
            item.artist = artist;
            item.date = date;
        }
    },

    // mettodo delete: elimina una obra por titulo
    // Logica: filtrado creamos un nuevo arreglo sin el elemento que coincide
    delete: function(title) {
        this.data = this.data.filter(img => img.title !== title);
    },

    // Metodo show: muestra todas las obras almacenadas
    // Logica: iteración presentación recorremos el arreglo y formateamos la salida
    show: function() {
        this.data.forEach(img => {
            console.log(`${img.title} (${img.artist}, ${img.date})`);
        });
    }
};

// Uso del objeto
images.add('Mona Lisa', 'Leonardo da Vinci', 1503);
images.add('The Last Supper', 'Leonardo da Vinci', 1495);
images.add('The Starry Night', 'Vincent van Gogh', 1889);

images.edit('Mona Lisa', 'Leonardo da Vinci', 1504); // actualizamos la fecha
images.delete('The Last Supper'); // eliminamos esta obra

images.show();
// -> Mona Lisa (Leonardo da Vinci, 1504)
// -> The Starry Night (Vincent van Gogh, 1889)