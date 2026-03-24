const data = {
  empleados: [
    { id: "EMP001", nombre: "Carlos Méndez", NIT: "123456789", departamento: "Mantenimiento", rol: "Mantenimiento" },
    { id: "EMP002", nombre: "Lucía Torres", NIT: "987654321", departamento: "Mantenimiento", rol: "Mantenimiento" },
    { id: "EMP003", nombre: "Jorge Ramírez", NIT: "456789123", departamento: "Mantenimiento", rol: "Mantenimiento" },
    { id: "EMP004", nombre: "Ana López", NIT: "789123456", departamento: "Mantenimiento", rol: "Mantenimiento" },
    { id: "EMP005", nombre: "Mario González", NIT: "321654987", departamento: "Mantenimiento", rol: "Mantenimiento" },
    { id: "EMP006", nombre: "Sofía Martínez", NIT: "654987321", departamento: "Mantenimiento", rol: "Mantenimiento" },
    { id: "EMP007", nombre: "Luis Herrera", NIT: "159753486", departamento: "Mantenimiento", rol: "Mantenimiento" },
    { id: "EMP008", nombre: "Patricia Díaz", NIT: "357159486", departamento: "Mantenimiento", rol: "Mantenimiento" },
    { id: "EMP009", nombre: "Fernando Cruz", NIT: "753159486", departamento: "Mantenimiento", rol: "Mantenimiento" },
    { id: "EMP010", nombre: "Gabriela Salazar", NIT: "951753486", departamento: "Mantenimiento", rol: "Mantenimiento" }
  ],
  tiposDeMantenimiento: ["General", "Extintor", "Alarma de Emergencia", "Detector de Humo"],
  ubicaciones: ["Laboratorio Cisco", "Taller Automotriz", "Oficinas de Psicología", "Sala de Servidores", "Recepción"]
};

const tipoSelect = document.getElementById("tipoMantenimiento");
const ubicacionSelect = document.getElementById("ubicacion");
const tarjetasContainer = document.getElementById("tarjetasEmpleados");
const tarjetaSeleccionada = document.getElementById("tarjetaSeleccionada");
const tablaBody = document.querySelector("#tablaAsignaciones tbody");

function cargarSelects() {
  data.tiposDeMantenimiento.forEach(tipo => {
    tipoSelect.innerHTML += `<option value="${tipo}">${tipo}</option>`;
  });
  data.ubicaciones.forEach(ubic => {
    ubicacionSelect.innerHTML += `<option value="${ubic}">${ubic}</option>`;
  });
}

function cargarTarjetas() {
  tarjetasContainer.innerHTML = "";
  tarjetaSeleccionada.innerHTML = "";

  data.empleados.filter(e => e.rol === "Mantenimiento").forEach(emp => {
    const tarjeta = document.createElement("div");
    tarjeta.className = "tarjeta";
    tarjeta.innerHTML = `
      <strong>${emp.nombre}</strong><br>
      NIT: ${emp.NIT}<br>
      ID: ${emp.id}
    `;
    tarjeta.onclick = () => seleccionarEmpleado(emp);
    tarjetasContainer.appendChild(tarjeta);
  });
}

function seleccionarEmpleado(emp) {
  tarjetasContainer.innerHTML = "";

  tarjetaSeleccionada.innerHTML = `
    <div class="tarjeta">
      <strong>${emp.nombre}</strong><br>
      NIT: ${emp.NIT}<br>
      ID: ${emp.id}
      <button onclick="cancelarSeleccion()">❌</button>
    </div>
  `;
}

function cancelarSeleccion() {
  tarjetaSeleccionada.innerHTML = "";
  cargarTarjetas();
}

function asignarInspeccion() {
  const tipo = tipoSelect.value;
  const ubicacion = ubicacionSelect.value;
  const tarjeta = tarjetaSeleccionada.querySelector(".tarjeta");
  if (!tarjeta) {
    alert("Selecciona un empleado antes de asignar.");
    return;
  }

  const nombre = tarjeta.querySelector("strong").textContent;
  const fecha = new Date().toLocaleDateString();
  const codigo = "ASG-" + Math.floor(Math.random() * 10000);

  tablaBody.innerHTML += `
    <tr>
      <td>${codigo}</td>
      <td>${tipo}</td>
      <td>${fecha}</td>
      <td>${nombre}</td>
      <td>${ubicacion}</td>
      <td>
        <button onclick="eliminarFila(this)">Eliminar</button>
        <button onclick="actualizarFila(this)">Actualizar</button>
      </td>
    </tr>
  `;
}

function eliminarFila(btn) {
  btn.closest("tr").remove();
}

function actualizarFila(btn) {
  alert("Función de actualización pendiente de implementación");
}

// Inicializar
cargarSelects();
cargarTarjetas();