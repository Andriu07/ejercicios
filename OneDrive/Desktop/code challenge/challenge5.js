// Creamos varios objetos con la misma estructura: tienen un array "x", un número "y" y un objeto "z"
let a={x:[1,2,3,4,5], y:0, z: {m:'test', n:false}};
let b={x:[1,2,3,4,5], y:0, z: {m:'test', n:false}};
let c={x:[1,2,3,4,5,6], y:0, z: {m:'test', n:false}};
let d={x:[1,2,3,4], y:0, z: {m:'test', n:false}};
let e={x:[1,2,3,4,5], y:0, z: {m:'test', n:true}};
let f={x:[1,2,3,4,5], y:-1, z: {m:'test', n:false}};

// usamos una función llamada deepComp (comparación profunda)
// Logica: esta función revisa si dos objetos son iguales en todas sus propiedades,
// incluyendo los arrays y los objetos internos, no solo en la superficie

console.log(deepComp(a,b)); // true porque son iguales en todo
console.log(deepComp(a,c)); // false porque c tiene un número extra en el array
console.log(deepComp(a,d)); // false porque d tiene un número menos en el array
console.log(deepComp(a,e)); // false porque en e la propiedad "n" es true en vez de false
console.log(deepComp(a,f)); // false porque en f la propiedad "y" es -1 en vez de 0