
var url = 'http://localhost:8080/ejemplo28_rest_ws/api';
var cantidadPreguntas = 10; // Declaración global

function init() {
    cargarLista();

    mostrarNombreEncuesta(1);
    cargarPreguntas();
    // Obtiene una referencia del cuerpo de la tabla, que contiene
    // los datos de cada ítem y asocia un manejador de eventos.

    var t = document.getElementById('datosItems');

    if (t) {
        // t.onclick = function(evt) {}; 
        t.addEventListener('click', (evt) => {

            // Obtiene la referencia de la fila (objeto TR) que ha sido
            // seleccionada. El evento es recibido por la celda (objeto TD).
            // Puede usar el evento 'dblclick' en lugar de 'click' para
            // seleccionar usando un doble clic.
            //
            // https://www.w3schools.com/jsref/prop_node_parentelement.asp
            // https://www.w3schools.com/jsref/coll_table_cells.asp
            //

            let r = evt.target.parentElement;
            let k = r.cells[0].textContent;

            var refId = document.getElementById('itemId');
            if (refId) {
                refId.value = k;
                consultar();
            }
        });
    }



}

function validar(campo) {
    var rx = new RegExp(/^\d*$/);
    return (rx.test(campo));
}



function cargarLista() {
    fetch(`${url}/items`, {
        method: 'GET'
    })
            .then(r => {
                console.log(r);
                return r.json();
            })
            .then(d => actualizarTabla(d));
}

function actualizarTabla(datos) {
    var refTabla = document.getElementById('datosItems');
    if (refTabla) {
        while (refTabla.rows.length > 0) {
            refTabla.deleteRow(0);
        }
        datos.forEach(e => {
            let fila = refTabla.insertRow(-1);
            for (var prop in e) {
                fila.insertCell(-1).textContent = e[prop];
            }
        });
    }
}

function consultar() {
    var refId = document.getElementById('itemId');
    if (refId) {
        let itemId = refId.value.trim();
        if (itemId.length > 0) {
            fetch(`${url}/items/${itemId}`, {
                method: 'GET'
            })
                    .then(r => r.json())
                    .then(d => actualizarFormulario(d))
                    .catch(e => mostrarMensaje(`<em>${e}</em>`));
        }
    }
}

function agregar() {
    var refId = document.getElementById('itemId');
    if (refId) {
        let itemId = refId.value.trim();
        if (itemId.length > 0) {

            fetch(`${url}/items`, {
                method: 'POST',
                body: JSON.stringify(capturarFormulario(itemId))
            })
                    .then(r => r.json())
                    .then(d => {
                        console.log(d);
                        actualizarFormulario(d);
                    })
                    .catch(e => mostrarMensaje(`<em>${e}</em>`));
        }
    }
}

function actualizar() {
    var refId = document.getElementById('itemId');
    if (refId) {
        let itemId = refId.value.trim();
        if (itemId.length > 0) {

            fetch(`${url}/items`, {
                method: 'PUT',
                body: JSON.stringify(capturarFormulario(itemId))
            })
                    .then(r => r.json())
                    .then(d => {
                        console.log(d);
                        actualizarFormulario(d);
                    })
                    .catch(e => mostrarMensaje(`<em>${e}</em>`));
        }
    }
}

function eliminar() {
    var refId = document.getElementById('itemId');
    if (refId) {
        let itemId = refId.value.trim();
        if (itemId.length > 0) {
            fetch(`${url}/items/${itemId}`, {
                method: 'DELETE'
            })
                    .then(r => r.json())
                    .then(d => actualizarFormulario())
                    .catch(e => mostrarMensaje(`<em>${e}</em>`));
        }
    }
}

function capturarFormulario(itemId) {
    var datos = {
        id: itemId,
        campo1: document.getElementById('campo1').value,
        campo2: document.getElementById('campo2').value,
        campo3: document.getElementById('campo3').value,
        campo4: document.getElementById('campo4').value
    };
    return datos;
}

function actualizarFormulario(e) {
    var refForm = document.getElementById('formulario');
    if (refForm) {
        refForm.reset();
        if (e) {
            document.getElementById('itemId').value = (e['id']) ? e['id'] : '';
            document.getElementById('campo1').value = (e['campo1']) ? e['campo1'] : '';
            document.getElementById('campo2').value = (e['campo2']) ? e['campo2'] : '';
            document.getElementById('campo3').value = (e['campo3']) ? e['campo3'] : '';
            document.getElementById('campo4').value = (e['campo4']) ? e['campo4'] : '';
            mostrarMensaje('');
        } else {
            mostrarMensaje('No existe el registro consultado');
        }
    }

    cargarLista();
}

function mostrarMensaje(error) {
    var refForm = document.getElementById('formulario');
    if (refForm) {
        // refForm.reset();
    }
    var refMsg = document.getElementById('mensaje');
    if (refMsg) {
        refMsg.innerHTML = error;
    }
}

function mostrarNombreEncuesta(encuestaId) {
    // Solicitud para obtener el nombre de la encuesta
    fetch(`${url}/encuestas/nombre/${encuestaId}`, {
        method: 'GET'
    })
            .then(r => r.text())
            .then(nombreEncuesta => {
                // Muestra el nombre de la encuesta en tu página HTML
                document.getElementById('nombreEncuesta').innerText = `Nombre de la Encuesta: ${nombreEncuesta}`;
            })
            .catch(e => mostrarMensaje(`<em>${e}</em>`));
}

// Llama a la función al cargar la página o cuando sea necesario
mostrarNombreEncuesta(1); // Puedes pasar el ID de la encuesta deseada aquí

function manejarEncuesta() {
    function realizarEncuesta() {
        var encuestaSeleccionUnicaRealizada = false;

        if (encuestaSeleccionUnicaRealizada) {
            alert('Ya has completado esta encuesta.');
        } else {
            window.location.href = "paginaEncuesta/paginaEncuesta.html";
        }
    }

    document.getElementById('encuestaForm').onsubmit = function (event) {
        event.preventDefault();
        realizarEncuesta();
    };
}


function cargarPreguntas() {
    const enunciadoContainer = document.getElementById('enunciado-container');

    const cantidadPreguntas = 10;

    for (let preguntaId = 1; preguntaId <= cantidadPreguntas; preguntaId++) {
        fetch(`${url}/preguntas/${preguntaId}`, {
            method: 'GET'
        })
                .then(response => {
                    if (!response.ok) {
                        throw new Error(`HTTP error! Status: ${response.status}`);
                    }
                    return response.json();
                })
                .then(data => {
                    const preguntaElement = document.createElement('div');
                    preguntaElement.classList.add('pregunta');

                    if (data && data.enunciado) {
                        preguntaElement.innerHTML = `<p>${data.enunciado}</p>`;

                        if (data.tipo_pregunta === 'seleccion_unica') {
                            // Agregar opciones para selección única (radiobuttons)
                            const opciones = ['Opción 1', 'Opción 2', 'Opción 3']; // Reemplaza con tus opciones reales
                            opciones.forEach(opcion => {
                                const radioInput = document.createElement('input');
                                radioInput.type = 'radio';
                                radioInput.name = `pregunta_${preguntaId}`;
                                const label = document.createElement('label');
                                label.textContent = opcion;

                                preguntaElement.appendChild(radioInput);
                                preguntaElement.appendChild(label);
                            });
                        } else if (data.tipo_pregunta === 'seleccion_multiple') {
                            // Agregar opciones para selección múltiple (checkboxes)
                            const opciones = ['Opción 1', 'Opción 2', 'Opción 3']; // Reemplaza con tus opciones reales
                            opciones.forEach(opcion => {
                                const checkboxInput = document.createElement('input');
                                checkboxInput.type = 'checkbox';
                                checkboxInput.name = `pregunta_${preguntaId}`;
                                const label = document.createElement('label');
                                label.textContent = opcion;

                                preguntaElement.appendChild(checkboxInput);
                                preguntaElement.appendChild(label);
                            });
                        } else if (data.tipo_pregunta === 'valoracion_cualitativa') {
                            // Agregar opciones para valoración cualitativa
                            const textareaInput = document.createElement('textarea');
                            textareaInput.name = `pregunta_${preguntaId}`;
                            preguntaElement.appendChild(textareaInput);
                        } else if (data.tipo_pregunta === 'respuesta_abierta') {
                            // Agregar input de texto para respuesta abierta
                            const textInput = document.createElement('input');
                            textInput.type = 'text';
                            textInput.name = `pregunta_${preguntaId}`;
                            preguntaElement.appendChild(textInput);
                        }
                    } else {
                        preguntaElement.innerHTML = '<p>No se pudo obtener el enunciado de la pregunta.</p>';
                    }

                    enunciadoContainer.appendChild(preguntaElement);
                })
                .catch(error => {
                    console.error(`Error al obtener la pregunta ${preguntaId}:`, error);
                    const preguntaElement = document.createElement('div');
                    preguntaElement.innerHTML = `<p>Error: ${error.message}</p>`;
                    enunciadoContainer.appendChild(preguntaElement);
                });
    }
}



//ENVIAR RESPUESTAS

function capturarRespuestas() {
    const respuestas = [];

    // Itera sobre las preguntas y captura las respuestas
    for (let preguntaId = 1; preguntaId <= cantidadPreguntas; preguntaId++) {
        const preguntaElement = document.querySelector(`#enunciado-container .pregunta:nth-child(${preguntaId})`);

        if (preguntaElement) {
            const preguntaText = preguntaElement.querySelector('p').textContent;
            const respuesta = obtenerRespuesta(preguntaElement);

            respuestas.push(`${preguntaText},"${respuesta}"`);
        }
    }

    return respuestas;
}

function obtenerRespuesta(preguntaElement) {
    const tipoPregunta = obtenerTipoPregunta(preguntaElement);

    switch (tipoPregunta) {
        case 'seleccion_unica':
            return obtenerRespuestaSeleccionUnica(preguntaElement);
        case 'seleccion_multiple':
            return obtenerRespuestaSeleccionMultiple(preguntaElement);
        case 'valoracion_cualitativa':
            return obtenerRespuestaValoracionCualitativa(preguntaElement);
        case 'respuesta_abierta':
            return obtenerRespuestaAbierta(preguntaElement);
        default:
            return null;
    }
}

function obtenerRespuestaSeleccionUnica(preguntaElement) {
    const radioInputs = preguntaElement.querySelectorAll('input[type="radio"]');
    const seleccionada = Array.from(radioInputs).find(input => input.checked);
    const respuesta = seleccionada ? seleccionada.nextElementSibling.textContent : '';
    console.log('Respuesta para pregunta de selección única:', respuesta);
    return respuesta;
}

function obtenerRespuestaSeleccionMultiple(preguntaElement) {
    const checkboxInputs = preguntaElement.querySelectorAll('input[type="checkbox"]');
    const seleccionadas = Array.from(checkboxInputs)
        .filter(input => input.checked)
        .map(input => input.nextElementSibling.textContent);
    const respuesta = seleccionadas.length > 0 ? seleccionadas.join(', ') : '';
    console.log('Respuesta para pregunta de selección múltiple:', respuesta);
    return respuesta;
}

function obtenerRespuestaValoracionCualitativa(preguntaElement) {
    const textareaInput = preguntaElement.querySelector('textarea');
    const respuesta = textareaInput ? textareaInput.value : '';
    console.log('Respuesta para pregunta de valoración cualitativa:', respuesta);
    return respuesta;
}

function obtenerRespuestaAbierta(preguntaElement) {
    const textInput = preguntaElement.querySelector('input[type="text"]');
    const respuesta = textInput ? textInput.value : '';
    console.log('Respuesta para pregunta de respuesta abierta:', respuesta);
    return respuesta;
}

function obtenerTipoPregunta(preguntaElement) {
    const tipoPreguntaMatch = preguntaElement.querySelector('p').textContent.match(/"([^"]+)"/);
    return tipoPreguntaMatch ? tipoPreguntaMatch[1] : null;
}

function enviarRespuestas() {
    console.log('Función enviarRespuestas() ejecutada');
    // Captura las respuestas del formulario
    const respuestas = capturarRespuestas();

    // Genera el contenido CSV
    const csvContent = respuestas.join('\n');
    
    // Crea un Blob (Binary Large Object) con el contenido CSV
    const blob = new Blob([csvContent], {type: 'text/csv;charset=utf-8;'});

    // Crea un objeto URL para el Blob
    const blobUrl = window.URL.createObjectURL(blob);

    // Crea un enlace oculto
    const link = document.createElement('a');
    link.href = blobUrl;
    link.setAttribute('download', 'respuestas.csv');

    // Agrega el enlace al cuerpo del documento
    document.body.appendChild(link);

    // Simula el clic en el enlace para iniciar la descarga
    link.click();

    // Elimina el enlace y libera el objeto URL
    document.body.removeChild(link);
    window.URL.revokeObjectURL(blobUrl);
}

window.onload = init;
