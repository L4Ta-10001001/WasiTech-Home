async function iniciarSesion() {
  try {
    const datos = {
      email: document.getElementById('txtEmail').value,
      password: document.getElementById('txtPassword').value
    };

    const request = await fetch('login', {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(datos)
    });

    if (!request.ok) {
      throw new Error('Error en la solicitud');
    }

    const respuesta = await request.text();
    if (respuesta !== 'FAIL') {
      localStorage.token = respuesta;
      localStorage.email = datos.email;
      window.location.href = 'usuarios.html';
    } else {
      alert("Las credenciales son incorrectas. Por favor intente nuevamente.");
    }
  } catch (error) {
    console.error("Error en iniciar sesión:", error);
    alert("Ocurrió un error al iniciar sesión. Inténtelo de nuevo más tarde.");
  }
}
