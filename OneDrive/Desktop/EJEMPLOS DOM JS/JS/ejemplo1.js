/*primero creamos una variable en donde se almacenara el elemento que queremos seleccionar*/
const divElement = document.getElementById('miDiv');

/*Seleccionammos el elemento por medio del metodo del DOM  getElementById*/ 

divElement.innerHTML = <p>Este parrafo se agrego desde Javascript utilizando la propiedad innerHTML</p>;
/*codigo para obtener elementos por su clase */
const parrafos = document.getElementsByClassName('parrafo');

/*ya que al obtener mas de un elemnto ,para poder accder a cada uno de los elemntps que nos devuelve el metodo getElementByClassName debmos de igterar estos y lo podemos hacer mediante un FOR */

for(let i= 0; i < parrafos.length; i++){
    parrafos[i].style.color = 'red'
    parrafos[i].style.fontWeight = 'bold';
}

//lo que estamos hacineo con este for es acceder a los elementos guardados en las varibales parrafos y cambiando la propiedad de style del color y el tamaño de la letra

//seleccionamps los elemntos que guardamos en las variables
const listaItems = document.getElementsByTagName('li');

//al igual que el anterior el metodo nos devolvera mas de un elemnto por lo que volveremos a utilizar un for patra recorrer los elementos que se nos devuelven

for(let i = 0; i<listaItems.length; i++){
    listaItems[i].style.fontWeight ='bold';
    listaItems[i].style.color ='blue';
    listaItems[i].style.backroundColor ='grey';
} 

//lo que hacemos es acceder a los elemntos que guardamos en ñas varibles de lista cambiando el style del color el tamaño de letra y el fondo del elemento