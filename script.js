window.onload = function() {
    const buttoncadastro = document.getElementById('buttoncadastro');
    const nome = document.getElementById('nome');
    const email = document.getElementById('email');
    const senha = document.getElementById('senha');
    const telefone = document.getElementById('telefone');

    buttoncadastro.addEventListener('click', function(event) {
        event.preventDefault(); 
        cadastrar();
        nome.value = "";
        senha.value = "";
        email.value ="";
        telefone.value ="";
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
        .then(response => {
            if(response.status === 201) {
              alert('Usuário Cadastrado com sucesso! API responde com 201.');
            } else {
              alert('Ocorreu um erro ao cadastrar o usuário.');
            }
          })
          .catch(error => {
            console.error('Ocorreu um erro:', error);
          });
    };
};
