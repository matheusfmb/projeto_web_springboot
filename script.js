document.addEventListener('DOMContentLoaded', function() {
    const button = document.getElementById('button');
    const nome = document.getElementById('nome')
    const email = document.getElementById('email')
    const senha = document.getElementById('senha')
    const telefone = document.getElementById('telefone')

    button.addEventListener('click', function(event) {
        event.preventDefault(); 
        const form = document.getElementById('form');
        if (form.checkValidity()) {
            cadastrar();
            limpar();
        } else {
            alert("Por favor, preencha todos os campos corretamente.");
        }
    });

    function cadastrar(){
        fetch("http://localhost:8050/usuarios", 
        {
            headers: {
                'Accept': 'application/json',
                'content-Type': 'application/json'
            },
            method: "POST",
            body: JSON.stringify({
                nome: nome.value,
                email: email.value,
                senha: senha.value,
                telefone: telefone.value
            })
        })
        .then(function(res) {
        console.log(res);
        })
        .catch(function(res) {
        console.log(res);
        })
    };
        
    function limpar(){
        nome.value = "";
        senha.value = "";
        email.value ="";
        telefone.value ="";
    }
});

