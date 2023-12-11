
document.getElementById('username').addEventListener('focus', function (){
    document.getElementById('errorNombreUsuario').style.display='none';
});

document.getElementById('password').addEventListener('keypress', function() {
    let contrasenia = document.getElementById('password').value;

    if (contrasenia.length < 8 || !contieneMayusculas(contrasenia) || !contieneMinusculas(contrasenia) || !contieneNumeros(contrasenia)) {
        document.getElementById('errorContrasenia').style.display = "block";
        document.getElementById('verificarBtn').disabled=true;
    }else{
        document.getElementById('errorContrasenia').style.display = "none";
        document.getElementById('verificarBtn').disabled=false;
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

document.getElementById('passwordRe').addEventListener('keypress', function (){
    let contrasenia = document.getElementById('password').value;
    let contraseniaRe = document.getElementById('passwordRe').value;

    if (contrasenia === contraseniaRe) {
        document.getElementById('verificarBtn').disabled = false;
        document.getElementById('errorContraseniaRe').style.display='none';
    }else{
        document.getElementById('verificarBtn').disabled = true;
        document.getElementById('errorContraseniaRe').style.display='block';
    }

})

