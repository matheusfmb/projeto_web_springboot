window.onload = function() {
  // VARIÁVEIS DE LOGIN
    const buttonlogin = document.getElementById('buttonlogin');
    const emaillogin = document.getElementById('emaillogin');
    const senhalogin = document.getElementById('senhalogin');

    // VARIÁVEIS DE CADASTRO
    const buttoncadastro = document.getElementById('buttoncadastro');
    const nome = document.getElementById('nome');
    const email = document.getElementById('email');
    const senha = document.getElementById('senha');
    const telefone = document.getElementById('telefone');


    // LISTENERS DA PÁGINA - CONTEUDO PRINCIPAL
    document.getElementById('content-button').addEventListener('click', function() {
      document.getElementById('content').style.display = 'block';
      document.getElementById('cadastro-form').style.display='none';
      document.getElementById('login-form').style.display = 'none';
    });
  
    // LISTENER PARA APARECER O FORMULÁRIO DE LOGIN
    document.getElementById('button-login-formulario').addEventListener('click', function() {
        document.getElementById('content').style.display = 'none';
        document.getElementById('cadastro-form').style.display='none';
        document.getElementById('login-form').style.display = 'block';
    });
    
    // LISTENER PARA APARECER O FORMULÁRIO DE CADASTRO
    document.getElementById('button-cadastro-formulario').addEventListener('click', function() {
        document.getElementById('content').style.display = 'none';
        document.getElementById('login-form').style.display = 'none';
        document.getElementById('cadastro-form').style.display ="block";  
    });

    // BOTÃO DE EFETUAR CADASTRO 
    buttoncadastro.addEventListener('click', function(event) {
        event.preventDefault();
        cadastrar();
        nome.value = "";
        senha.value = "";
        email.value ="";
        telefone.value ="";
    });
    
    // BOTÃO DE EFETUAR LOGIN
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

    // FUNÇÃO DE LOGIN
    function login() {
      localStorage.clear();
      fetch("http://localhost:8050/usuarios/login", 
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
        if(response.ok) {
          response.json().then(data => {
            localStorage.setItem('user', JSON.stringify(data));
            localStorage.setItem('token', data.token);
            window.location.href="home.html";
          });
        } else if (response.status === 400) {
          response.text().then(data => {
            if(data.email){
              alert(data.email)
            }else if(data.senha){
              alert(data.senha)
            }else{
              alert(data.email,data.senha)
            }
          });

        }else if (response.status === 401){
          response.text().then(data => {
            alert(data)
          });

        } else if(response.status === 404) {
          response.json().then(data => {
            console.log(data)
          });
        } else{
          response.text().then(data => {
            alert(data);
          });   
        }
      })
      .catch(error => {
          console.error('Ocorreu um erro:', error);
      });
  };

    // FUNÇÃO DE CADASTRO
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
          response.text().then(data => {
            alert(data);
          });
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
            } 
          });
        } else if (response.status === 409) {
          response.text().then(data => {alert(data)});
        } else {
          response.text().then(data => {alert(data)});
        }
      })
      .catch(error => {
          console.error('Ocorreu um erro:', error);
      });
  };

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
