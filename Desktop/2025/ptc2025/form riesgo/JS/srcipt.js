const API_URL = "https://tu-api.com/reportes"; // ← reemplaza con tu URL real
let editId = null;

// Inicialización
document.addEventListener("DOMContentLoaded", () => {
  generarCodigo();
  cargarReportes();
});

// Generar código único
function generarCodigo() {
  document.getElementById("codigo").value = "RSK-" + String(Date.now()).slice(-5);
}

// Cargar reportes desde la API
async function cargarReportes() {
  const tbody = document.querySelector("#tablaReportes tbody");
  tbody.innerHTML = `<tr><td colspan="5" class="text-center">Cargando...</td></tr>`;

  try {
    const res = await fetch(API_URL);
    const reportes = await res.json();

    tbody.innerHTML = "";

    if (reportes.length === 0) {
      tbody.innerHTML = `<tr><td colspan="5" class="text-center text-muted">No hay reportes registrados.</td></tr>`;
      return;
    }

    reportes.forEach(r => {
      const tr = document.createElement("tr");
      tr.innerHTML = `
        <td>${r.codigo}</td>
        <td>${r.tipo}</td>
        <td>${r.nivel}</td>
        <td>${r.probabilidad}%</td>
        <td>
          <button class="btn btn-sm btn-info me-1" onclick="verReporte(${r.id})"><i class="bi bi-eye"></i></button>
          <button class="btn btn-sm btn-warning me-1" onclick="editarReporte(${r.id})"><i class="bi bi-pencil"></i></button>
          <button class="btn btn-sm btn-danger" onclick="eliminarReporte(${r.id})"><i class="bi bi-trash"></i></button>
        </td>`;
      tbody.appendChild(tr);
    });
  } catch (error) {
    console.error("Error al cargar reportes:", error);
    tbody.innerHTML = `<tr><td colspan="5" class="text-center text-danger">Error al cargar datos.</td></tr>`;
  }
}

// Guardar o editar reporte
document.getElementById("formRiesgo").addEventListener("submit", async e => {
  e.preventDefault();

  const evidencia = document.getElementById("evidencia");
  if (!evidencia.files.length) {
    alert("Debe adjuntar al menos una imagen como evidencia.");
    return;
  }

  const data = {
    codigo: document.getElementById("codigo").value,
    tipo: document.getElementById("tipoRiesgo").value,
    probabilidad: document.getElementById("probabilidad").value,
    nivel: document.getElementById("nivelRiesgo").value,
    ubicacion: document.getElementById("ubicacion").value,
    evidencias: Array.from(evidencia.files).map(f => f.name)
  };

  try {
    if (editId) {
      await fetch(`${API_URL}/${editId}`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(data)
      });
      editId = null;
    } else {
      await fetch(API_URL, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(data)
      });
    }

    e.target.reset();
    generarCodigo();
    document.getElementById("preview").innerHTML = "";
    cargarReportes();
  } catch (error) {
    console.error("Error al guardar:", error);
    alert("Hubo un error al guardar el reporte.");
  }
});

// Ver reporte
async function verReporte(id) {
  try {
    const res = await fetch(`${API_URL}/${id}`);
    const r = await res.json();

    document.getElementById("detalleReporte").innerHTML = `
      <p><strong>Código:</strong> ${r.codigo}</p>
      <p><strong>Tipo:</strong> ${r.tipo}</p>
      <p><strong>Nivel:</strong> ${r.nivel}</p>
      <p><strong>Probabilidad:</strong> ${r.probabilidad}%</p>
      <p><strong>Ubicación:</strong> ${r.ubicacion}</p>
      <p><strong>Evidencias:</strong></p>
      <ul>${r.evidencias.map(ev => `<li>${ev}</li>`).join("")}</ul>
    `;
    new bootstrap.Modal(document.getElementById("modalVer")).show();
  } catch (error) {
    console.error("Error al ver reporte:", error);
  }
}

// Editar reporte
async function editarReporte(id) {
  try {
    const res = await fetch(`${API_URL}/${id}`);
    const r = await res.json();

    editId = id;
    document.getElementById("codigo").value = r.codigo;
    document.getElementById("tipoRiesgo").value = r.tipo;
    document.getElementById("probabilidad").value = r.probabilidad;
    document.getElementById("nivelRiesgo").value = r.nivel;
    document.getElementById("ubicacion").value = r.ubicacion;
    document.getElementById("formRiesgo").scrollIntoView({ behavior: "smooth" });
  } catch (error) {
    console.error("Error al editar reporte:", error);
  }
}

// Eliminar reporte
async function eliminarReporte(id) {
  if (!confirm("¿Eliminar este reporte?")) return;

  try {
    await fetch(`${API_URL}/${id}`, { method: "DELETE" });
    cargarReportes();
  } catch (error) {
    console.error("Error al eliminar reporte:", error);
  }
}

// Previsualizar evidencias
document.getElementById("evidencia").addEventListener("change", e => {
  const preview = document.getElementById("preview");
  preview.innerHTML = "";
  Array.from(e.target.files).forEach(file => {
    const reader = new FileReader();
    reader.onload = ev => {
      const img = document.createElement("img");
      img.src = ev.target.result;
      img.className = "rounded shadow-sm";
      img.style.width = "100px";
      preview.appendChild(img);
    };
    reader.readAsDataURL(file);
  });
});