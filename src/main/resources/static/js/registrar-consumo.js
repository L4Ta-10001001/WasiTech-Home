document.getElementById('form-consumo').addEventListener('submit', async function (event) {
    event.preventDefault(); // Evitar que el formulario se envíe de manera tradicional

    // Recoger los valores de los campos
    const tipoRecurso = document.getElementById('tipoRecurso').value;
    const cantidad = document.getElementById('cantidad').value;
    const unidadMedida = document.getElementById('unidadMedida').value;
    const costoEstimado = document.getElementById('costoEstimado').value;
    const fuente = document.getElementById('fuente').value;

    // Validar que todos los campos tengan datos
    if (!tipoRecurso || !cantidad || !unidadMedida || !costoEstimado || !fuente) {
        alert("Por favor, complete todos los campos.");
        return;
    }

    // Crear un objeto con los datos del consumo
    const consumo = {
        tipoRecurso,
        cantidad: parseFloat(cantidad),
        unidadMedida,
        costoEstimado: parseFloat(costoEstimado),
        fuente,
        fechaRegistro: new Date().toISOString(), // Agregar la fecha de registro
        usuario: {
            id: 5, // Usar el ID del usuario autenticado
        },
    };

    try {
        const response = await fetch('consumos', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'Authorization': localStorage.token, // Asumiendo que el token se guarda en el localStorage
            },
            body: JSON.stringify(consumo),
        });

        if (response.ok) {
            alert("Consumo registrado correctamente.");
            // Redirigir al listado de consumos o limpiar el formulario si es necesario
            window.location.href = 'index.html'; // Redirigir a la página de listado de consumos
        } else {
            throw new Error('Error al registrar el consumo');
        }
    } catch (error) {
        console.error('Error:', error);
        alert('Hubo un error al registrar el consumo. Por favor intente de nuevo.');
    }
});
