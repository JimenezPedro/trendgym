function validateImageSize() {
    var input = document.getElementById('img');
    var errorMessage = document.getElementById('errorMessage');

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