window.onload = function() {
    const buttonlogin = document.getElementById('buttonlogin');
    const emaillogin = document.getElementById('emaillogin');
    const senhalogin = document.getElementById('senhalogin');

    buttonlogin.addEventListener('click', function(event) {
        event.preventDefault(); 
        if (emaillogin.value === ("") && senhalogin.value ===("")){
            alert("Campo email e Senha Vazios! validação vinda do front-End");
        }else if (senhalogin.value ===("")){
            alert("Campo senha Vazio! validação vinda do front-End");
        }else if (emaillogin.value ===("")){
            alert ("Campo email Vazio! validação vinda do front-End");
        }else{
            login();
            emaillogin.value="";
            senhalogin.value="";
        }
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
            if(response.status === 200) {
              alert('Usuário Cadastrado com sucesso!');
            } else if (response.status === 400) {
              response.json().then(data => {
                  if (data.email) {
                    alert(data.email);
                } else if (data.senha) {
                    alert(data.senha)
                } else {
                    alert('Erro ao processar a requisição');
                }
              });
            } else if (response.status === 401) {
                alert('Usuário ou senha incorretos');
            } else {
                alert("Erro ao processar requesição");
            }
          })
        .catch(error => {
        console.error(error.message);
        });
    };
};
