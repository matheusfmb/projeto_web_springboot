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
      fetch("projetowebspringboot-production.up.railway.app/usuarios", 
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
          alert('Usuário Cadastrado com sucesso!');
        } else if (response.status === 400) {
          response.json().then(data => {
            if (data.nome) {
              alert(data.nome);
            } else if (data.email) {
              alert(data.email);
            } else if (data.senha) {
              alert(data.senha);
            } else if (data.telefone) {
              alert(data.telefone);
            } else {
              alert('Erro ao processar a requisição');
            }
          });
        } else {
          alert('Erro ao processar a requisição');
        }
      })
      .catch(error => {
          console.error('Ocorreu um erro:', error);
      });
  };
    Inputmask({
      mask: '(99) 9999-9999',
      greedy: false,
      definitions: {
        '#': {
          validator: "[0-9]",
          cardinality: 1
        }
      }
    }).mask(telefone);

    Inputmask({
      mask: '*{1,50}',
      greedy: false,
      placeholder: '',
      definitions: {
        '*': {
          validator: "[A-Za-zÀ-ÿ ]",
          cardinality: 1
        }
      }
    }).mask(nome);
};
