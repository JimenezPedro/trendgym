
document.getElementById('updateForm').addEventListener('submit', function(e) {
    let estatura = document.getElementById('estatura').value;
    let peso = document.getElementById('peso').value;

    if (estatura <=0 || peso <=0) {
        e.preventDefault();
        Swal.fire({
            title:  'Ingrese una estatura y un peso válido.',
            text: 'La altura y el peso no pueden ser valores negativos o cero.',
            icon: 'error',
            confirmButtonText: 'Aceptar',
        });
    }
});

function validateImageSize() {
    var input = document.getElementById('img');
    var errorMessage = document.getElementById('errorMessage');

    // Verificar si se seleccionó un archivo
    if (input.files.length > 0) {
        var fileSize = input.files[0].size;
        var maxSizeInBytes = 1024 * 1024;


        if (fileSize > maxSizeInBytes) {
            errorMessage.textContent = 'La imagen seleccionada es demasiado grande. Por favor, selecciona una imagen más pequeña.';
            input.value = '';
        } else {
            errorMessage.textContent = '';
        }
    }
}