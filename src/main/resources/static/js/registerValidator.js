document.querySelector('form').addEventListener('submit', function(e) {
    let contrasenia = document.getElementById('password').value;

    if (contrasenia.length < 8 || !contieneMayusculas(contrasenia) || !contieneMinusculas(contrasenia) || !contieneNumeros(contrasenia)) {
        e.preventDefault();
        Swal.fire({
            text: 'La contraseña debe tener como mínimo 8 caracteres, usar mayúsuculas, minúsculas y números.',
            icon: 'error',
            confirmButtonText: 'Aceptar',
        });
    }

    function contieneMayusculas(str) {
        return /[A-Z]/.test(str);
    }

    function contieneMinusculas(str) {
        return /[a-z]/.test(str);
    }

    function contieneNumeros(str) {
        return /[0-9]/.test(str);
    }

});



