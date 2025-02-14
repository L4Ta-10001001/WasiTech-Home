$(document).ready(function () {
  cargarConsumos();
});

async function cargarConsumos() {
  try {
    const request = await fetch("consumos", {
      method: "GET",
      headers: getHeaders(),
    });

    if (!request.ok) {
      throw new Error("Error al cargar consumos");
    }

    const consumos = await request.json();
    let listadoHtml = "";

    for (let consumo of consumos) {
      let fechaRegistro = new Date(consumo.fechaRegistro).toLocaleDateString();

      let cantidadConsumida = consumo.cantidadConsumida !== undefined ? consumo.cantidadConsumida : "-";
      let costoEstimado = consumo.costoEstimado !== null ? consumo.costoEstimado : "-";
      let fuente = consumo.fuente !== null ? consumo.fuente : "-";

      let consumoHtml = `
        <tr data-id='${consumo.id}'>
          <td>${consumo.tipoRecurso}</td>
          <td>${cantidadConsumida}</td>
          <td>${consumo.unidadMedida}</td>
          <td>${costoEstimado}</td>
          <td>${fuente}</td>
          <td>${fechaRegistro}</td>
          <td>
            <button class='btn btn-warning btn-sm' onclick='editarConsumo(${consumo.id})'>Editar</button>
            <button class='btn btn-danger btn-sm' onclick='eliminarConsumo(${consumo.id})'>Eliminar</button>
          </td>
        </tr>`;
      listadoHtml += consumoHtml;
    }

    document.querySelector("#consumos tbody").innerHTML = listadoHtml;

    // Inicializar DataTable después de cargar los datos
    $("#consumos").DataTable();
  } catch (error) {
    console.error("Error al cargar consumos:", error);
    alert("Ocurrió un error al cargar los consumos.");
  }
}

function getHeaders() {
  const token = localStorage.token || "";
  return {
    Accept: "application/json",
    "Content-Type": "application/json",
    Authorization: token ? `Bearer ${token}` : "",
  };
}

async function eliminarConsumo(id) {
  if (!confirm("¿Desea eliminar este Consumo?")) {
    return;
  }

  try {
    const request = await fetch(`consumos/${id}`, {
      method: "DELETE",
      headers: getHeaders(),
    });

    if (request.ok) {
      document.querySelector(`#consumos tr[data-id='${id}']`).remove();
    } else {
      alert("Error al eliminar el consumo.");
    }
  } catch (error) {
    console.error("Error al eliminar consumo:", error);
    alert("Ocurrió un error al eliminar el consumo.");
  }
}

function editarConsumo(id) {
  window.location.href = `editar-consumo.html?id=${id}`;
}
