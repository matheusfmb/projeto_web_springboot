window.onload = function(){

    const dados = JSON.parse(localStorage.getItem('user'));
    console.log(dados);

    document.getElementById('button-perfil').addEventListener('click', function() {
        document.getElementById('perfil').style.display = 'block';
        document.getElementById('content-home').style.display = 'none';
        document.getElementById("nome-usuario").value = dados.nome;
        document.getElementById("email-usuario").value = dados.email;
        document.getElementById("telefone-usuario").value = dados.telefone;
      });

    document.getElementById('content-button-home').addEventListener('click', function() {
      document.getElementById('perfil').style.display = 'none';
      document.getElementById('content-home').style.display = 'block';
    });

    document.getElementById('button-sair').addEventListener('click', function() {
        localStorage.clear();
        window.location.href="index.html"
      });

}