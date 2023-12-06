
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
