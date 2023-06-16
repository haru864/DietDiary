window.onload = function () {

    async function validateInput() {

        let username = document.getElementById('username').value;
        let password = document.getElementById('password').value;
        let email = document.getElementById('email').value;
        let genderRadios = document.getElementsByName('gender');
        let gender = null;
        for (var i = 0; i < genderRadios.length; i++) {
            if (genderRadios[i].checked) {
                gender = genderRadios[i].value;
                break;
            }
        }
        let birth = document.getElementById('birth').value;
        let height = document.getElementById('height').value;
        let weight = document.getElementById('weight').value;
        console.log('gender: ' + gender);

        let url = 'http://localhost:8080/DietDiary/RegisterServlet';
        let params = new URLSearchParams();
        params.append('action', 'validate');
        params.append('username', username);
        params.append('password', password);
        params.append('email', email);
        params.append('gender', gender);
        params.append('birth', birth);
        params.append('height', height);
        params.append('weight', weight);

        try {

            let response = await fetch(url, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                },
                body: params
            });
            console.log(response);
            let errorMessageListJson = await response.json();
            console.log(errorMessageListJson);

            if (errorMessageListJson == null) {
                return true;
            }
            return false;

        } catch (error) {

            console.log('FETCH ERROR');
            console.error(error);
            return false;
        }
    }

    document.getElementById('submit').addEventListener('click', async function (event) {

        event.preventDefault();
        let isValid = await validateInput();
        if (isValid == false) {
            alert('invalid');
            return;
        }
        alert('validation ok!');
    });

}
