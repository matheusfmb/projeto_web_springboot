window.onload = function() {
    const buttonlogin = document.getElementById('buttonlogin');
    const emaillogin = document.getElementById('emaillogin');
    const senhalogin = document.getElementById('senhalogin');

    buttonlogin.addEventListener('click', function(event) {
        event.preventDefault(); 
        login();
        emaillogin.value="";
        senhalogin.value="";
    });

    function login(){
        fetch("http://localhost:8050/login", 
        {
            headers: {
            'Accept': 'application/json',
            'content-Type': 'application/json'
            },
            method: "POST",
            body: JSON.stringify({
                email: emaillogin.value,
                senha: senhalogin.value
            })
        })
        .then(response => {
            if (response.status(202)) {
                console.log('Login bem-sucedido');
                window.location.href="home.html";
            } else {
                alert("Usuário ou Senha incorretos")
                throw new Error('Erro no login: ' + response.statusText);
            }
        })
        .catch(error => {
        console.error(error.message);
        });
    };

    function getUserData() {
        $.ajax({
          url: "http://localhost:8050/usuarios" + $("#userId").val(),
          type: "GET",
          dataType: "json",
          success: function(data) {
            // Exibir os dados do usuário na tela
            $("#nome").text(data.nome);
            $("#email").text(data.email);
            $("#telefone").text(data.telefone);
            // Adicione mais campos aqui conforme necessário
          }
        });
      }
};