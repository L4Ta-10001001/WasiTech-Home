// Call the dataTables jQuery plugin
$(document).ready(function () {
  cargarConsumos();
  $("#consumos").DataTable();
  //actualizarEmailDelUsuario();
});

//function actualizarEmailDelUsuario() {
//  const elemento = document.getElementById("txt-email-usuario");
//  if (elemento) {
//    elemento.textContent = localStorage.email;
//  }
//}

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
      let botonEliminar =
        '<a href="#" onclick="eliminarConsumo(' +
        consumo.id +
        ')" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a>';
      let telefonoTexto = consumo.telefono == null ? "-" : consumo.telefono;
      let consumoHtml =
        "<tr><td>" +
        consumo.id +
        "</td><td>" +
        consumo.tipoRecurso+
        "</td><td> " +
        consumo.cantidad+
        "</td><td>" +
        consumo.fechaRegistro +
        "</td></tr>";
      listadoHtml += consumoHtml;
    }
    document.querySelector("#consumos tbody").innerHTML = listadoHtml;
  } catch (error) {
    console.error("Error al cargar consumos:", error);
    alert("Ocurrió un error al cargar los consumos.");
  }
}

function getHeaders() {
  return {
    Accept: "application/json",
    "Content-Type": "application/json",
    Authorization: localStorage.token,
  };
}

async function eliminarConsumo(id) {
  if (!confirm("¿Desea eliminar este Consumo?")) {
    return;
  }

  const request = await fetch("consumos/" + id, {
    method: "DELETE",
    headers: getHeaders(),
  });

  location.reload();
}
