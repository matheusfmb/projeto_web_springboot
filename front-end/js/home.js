window.onload = function(){

    const dados = JSON.parse(localStorage.getItem('user'));

    // VER DADOS PERFIL
    document.getElementById('button-perfil').addEventListener('click', function() {
        document.getElementById('perfil').style.display = 'block';
        document.getElementById('content-home').style.display = 'none';
        document.getElementById('table-usuarios').style.display = 'none';
        document.getElementById("Alterar_senha").style.display = 'none';
        document.getElementById("nome-usuario").value = dados.nome;
        document.getElementById("email-usuario").value = dados.email;
        document.getElementById("telefone-usuario").value = dados.telefone;
        console.log("Printando dados e Token")
        console.log(dados)
      });

    document.getElementById('button-ver-usuarios').addEventListener('click', function() { 
      fetch('http://localhost:8050/api/usuarios', {
        headers: {
          'Authorization': localStorage.getItem('token')
        }
      })
      .then(response => {
        if (response.ok) {
           response.text().then(html => {
            const div_table = document.getElementById('table-usuarios')
            div_table.innerHTML = html;
            document.getElementById('table-usuarios').style.display = 'block';
            document.getElementById('content-home').style.display = 'none';
            document.getElementById('perfil').style.display = 'none';
            document.getElementById("Alterar_senha").style.display = 'none';
          });
        } else {
          alert("Você não tem autorização")
          throw new Error('Não foi possível acessar a página.');
        }
      })
      .catch(error => {
        console.error(error);
      });
    });

    // DISPLAY CONTEUDO
    document.getElementById('content-button-home').addEventListener('click', function() {
      document.getElementById('perfil').style.display = 'none';
      document.getElementById('content-home').style.display = 'block';
      document.getElementById('table-usuarios').style.display = 'none';
      document.getElementById("Alterar_senha").style.display = 'none';
    });

    // SAIR DA PÁGINA
    document.getElementById('button-sair').addEventListener('click', function() {
        localStorage.clear();
        window.location.href="index.html"
      });
    
    // TROCAR DADOS
    document.getElementById('editar_dados').addEventListener('click', function() {
      document.getElementById("nome-usuario").removeAttribute("readonly");
      document.getElementById("email-usuario").removeAttribute("readonly");
      document.getElementById("telefone-usuario").removeAttribute("readonly");
    });

    // Alterar Senha
    document.getElementById('alterar_senha').addEventListener('click', function() {
      document.getElementById("perfil").style.display = 'none';
      document.getElementById("Alterar_senha").style.display = 'block';
      
    });
    // MASCARAS PARA O FORMULÁRIO DE CADASTRO
    Inputmask({
      mask: '(99) 9999-9999',
      greedy: false,
      definitions: {
        '#': {
          validator: "[0-9]",
          cardinality: 1
        }
      }
    }).mask("telefone-usuario");

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
    }).mask("nome-usuario");
}