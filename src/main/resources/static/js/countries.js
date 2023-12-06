document.addEventListener('DOMContentLoaded', function() {
    fetch('https://restcountries.com/v3.1/all')
        .then(response => response.json())
        .then(data => {
            // Ordenar los países alfabéticamente por su nombre en español
            data.sort((a, b) => (a.translations.spa.common).localeCompare(b.translations.spa.common, 'es'));

            llenarSelectConPaises(data);
        })
        .catch(error => console.error('Error al cargar los países:', error));
});

function llenarSelectConPaises(paises) {
    var select = document.getElementById('listaPaises');

    paises.forEach(pais => {
        var opcion = document.createElement('option');
        opcion.value = pais.cca2; // Usa el código del país como valor
        opcion.textContent = pais.translations.spa.common; // Usa el nombre en español del país como texto
        select.appendChild(opcion);
    });
}

