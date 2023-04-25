window.onload = function(){

    const dados = JSON.parse(localStorage.getItem('user'));
    console.log(dados);

    document.getElementById('button-perfil').addEventListener('click', function() {
        document.getElementById('perfil').style.display = 'block';
        document.getElementById('content-home').style.display = 'none';
        document.getElementById('table-usuarios').style.display = 'none';
        document.getElementById("nome-usuario").value = dados.nome;
        document.getElementById("email-usuario").value = dados.email;
        document.getElementById("telefone-usuario").value = dados.telefone;
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

    document.getElementById('content-button-home').addEventListener('click', function() {
      document.getElementById('perfil').style.display = 'none';
      document.getElementById('content-home').style.display = 'block';
      document.getElementById('table-usuarios').style.display = 'none';
    });

    document.getElementById('button-sair').addEventListener('click', function() {
        localStorage.clear();
        window.location.href="index.html"
      });
}