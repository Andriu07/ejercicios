//Coloca tu endpoint de Retool API
const API_URL = 'https://retoolapi.dev/qbETjR/tbProveedores'; 

//tarjetas 
const container = document.getElementById('cards-container');

async function CargarPersonas(){
    try{
        const res = await fetch(API_URL);
        const data = await res.json();
        CargarTarjetas(data);
      }catch(e){
         console.error('Error al cargar datos: ', e);
         container.innerHTML = '<p>Error al cargar las personas</p>';
      }
};

function CargarTarjetas(personas){
    container.innerHTML = '';

    if(personas.length == 0){
        //si personas esta vacio
        container.innerHTML = "<p>No hay personas registradas</p>";
        return;
    }

    personas.forEach(personas => {
        container.innerHTML += `
        <div class="card">
        <img src="${personas.imagen}" alt="foto de perfil">
        <h2>${personas.nombre}</h2>
        <p>${personas.telefono}</p>
        </div>
        `;
    });
};


//al  cargar la pagina
window.addEventListener('DOMContentLoaded', CargarPersonas);