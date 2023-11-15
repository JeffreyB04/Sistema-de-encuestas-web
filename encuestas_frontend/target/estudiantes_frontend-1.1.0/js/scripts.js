
function init() {
}

function borrarRegistro(id) {
    window.location = `ServletBorrado?id=${id}`;
}

window.addEventListener('load', init);
