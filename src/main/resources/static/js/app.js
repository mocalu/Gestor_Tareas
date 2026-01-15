const API_URL = '/tareas';
const listaTareas = document.getElementById('listaTareas');
const btnCrear = document.getElementById('btnCrear');
const inputTitulo = document.getElementById('tituloTarea');

// Función para mostrar todas las tareas en la página
async function listarTareas() {
    listaTareas.innerHTML = ''; // Limpiar lista
    try {
        const response = await fetch(API_URL);
        const tareas = await response.json();

        tareas.forEach(tarea => {
            const li = document.createElement('li');
            // Crear nodo de texto solo para el título
            const texto = document.createTextNode(`${tarea.id}: ${tarea.titulo} `);
            li.appendChild(texto);

            // Botón para eliminar tarea
            const btnEliminar = document.createElement('button');
            btnEliminar.textContent = 'Eliminar';
            btnEliminar.onclick = async () => {
                await eliminarTarea(tarea.id);
                listarTareas();
            };

            // Botón para actualizar
            const btnActualizar = document.createElement('button');
            btnActualizar.textContent = 'Actualizar';
            btnActualizar.onclick = () => {
                const nuevoTitulo = prompt('Introduce el nuevo título:', tarea.titulo);
                if (nuevoTitulo) {
                    actualizarTarea(tarea.id, nuevoTitulo);
                }
            };

            li.appendChild(btnEliminar);
            li.appendChild(btnActualizar);
            listaTareas.appendChild(li);
        });

    } catch (error) {
        console.error('Error al listar tareas:', error);
    }
}

// Función para crear tarea
async function crearTarea() {
    const titulo = inputTitulo.value.trim();
    if (!titulo) return alert('El título no puede estar vacío');

    try {
        const response = await fetch(API_URL, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ titulo })
        });

        if (!response.ok) {
            const errorData = await response.json();
            console.error('Error al crear tarea:', errorData);
            return;
        }

        inputTitulo.value = ''; // Limpiar input
        listarTareas(); // Refrescar lista

    } catch (error) {
        console.error('Error al crear tarea:', error);
    }
}

// Función para eliminar tarea
async function eliminarTarea(id) {
    try {
        const response = await fetch(`${API_URL}/${id}`, { method: 'DELETE' });
        if (!response.ok) console.error('Error al eliminar tarea:', response.status);
    } catch (error) {
        console.error('Error al eliminar tarea:', error);
    }
}

async function actualizarTarea(id, titulo) {
    try {
        const response = await fetch(API_URL, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ id, titulo })
        });

        if (!response.ok) {
            console.error('Error al actualizar tarea:', response.status);
            return;
        }

        console.log(`Tarea con id ${id} actualizada a "${titulo}"`);
        listarTareas(); // refrescar lista
    } catch (error) {
        console.error('Error al actualizar tarea:', error);
    }
}


// Eventos
btnCrear.addEventListener('click', crearTarea);

// Cargar lista al inicio
listarTareas();
