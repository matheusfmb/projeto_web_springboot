document.addEventListener('DOMContentLoaded', function() {
    const button = document.getElementById('button');

    button.addEventListener('click', function(event) {
        event.preventDefault(); 
        const form = document.getElementById('form');
        if (form.checkValidity()) {
            const nome = document.getElementById('nome').value;
            const email = document.getElementById('email').value;
            const senha = document.getElementById('senha').value;
            const telefone = document.getElementById('telefone').value;

            const dados = {
                nome:nome,
                email:email,
                senha:senha,
                telefone:telefone
            };
            cadastrar();
            limpar();
            alert("Cadastro Realizado com Sucesso!")
        } else {
            alert("Por favor, preencha todos os campos corretamente.");
        }
    });

    function cadastrar(){
        fetch("http://localhost:8050/cadastrar", {
            headers: {
                'Accept': 'application/json',
                'content-Type': 'application/json'
            },
            method: "POST",
            body: JSON.stringify({
                nome: nome,
                email: email,
                senha: senha,
                telefone: telefone
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

