// Call the dataTables jQuery plugin
$(document).ready(function () {
  cargarUsuarios();
  $("#usuarios").DataTable();
  actualizarEmailDelUsuario();
});

function actualizarEmailDelUsuario() {
  const elemento = document.getElementById("txt-email-usuario");
  if (elemento) {
    elemento.textContent = localStorage.email;
  }
}

async function cargarUsuarios() {
  try {
    const request = await fetch("usuarios", {
      method: "GET",
      headers: getHeaders(),
    });
    if (!request.ok) {
      throw new Error("Error al cargar usuarios");
    }
    const usuarios = await request.json();

    let listadoHtml = "";
    for (let usuario of usuarios) {
      let botonEliminar =
        '<a href="#" onclick="eliminarUsuario(' +
        usuario.id +
        ')" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a>';
      let telefonoTexto = usuario.telefono == null ? "-" : usuario.telefono;
      let usuarioHtml =
        "<tr><td>" +
        usuario.id +
        "</td><td>" +
        usuario.nombre +
        " " +
        usuario.apellido +
        "</td><td>" +
        usuario.email +
        "</td><td>" +
        telefonoTexto +
        "</td><td>" +
        botonEliminar +
        "</td></tr>";
      listadoHtml += usuarioHtml;
    }
    document.querySelector("#usuarios tbody").innerHTML = listadoHtml;
  } catch (error) {
    console.error("Error al cargar usuarios:", error);
    alert("Ocurrió un error al cargar los usuarios.");
  }
}

function getHeaders() {
  return {
    Accept: "application/json",
    "Content-Type": "application/json",
    Authorization: localStorage.token,
  };
}

async function eliminarUsuario(id) {
  if (!confirm("¿Desea eliminar este usuario?")) {
    return;
  }

  const request = await fetch("usuarios/" + id, {
    method: "DELETE",
    headers: getHeaders(),
  });

  location.reload();
}
