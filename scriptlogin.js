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
            if (response.ok) {
                console.log('Login bem-sucedido');
                window.location.href="home.html";
            } else {
                throw new Error('Erro no login: ' + response.statusText);
            }
        })
        .catch(error => {
        console.error(error.message);
        });
    };
};