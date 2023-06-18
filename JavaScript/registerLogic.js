window.onload = function () {

    // パスワードの表示・非表示の切り替え
    let btn_passview = document.getElementById("btn_passview");
    let input_password = document.getElementById("password");

    btn_passview.addEventListener("click", () => {
        if (input_password.type == "password") {
            input_password.type = "text";
            btn_passview.textContent = "非表示";
        } else {
            input_password.type = "password";
            btn_passview.textContent = "表示";
        }
    });

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
            // console.log(response);
            let errorMessageListJson = await response.json();
            // console.log(errorMessageListJson);
            // console.log(errorMessageListJson.length);

            if (errorMessageListJson == null || errorMessageListJson.length == 0) {
                return true;
            } else {
                alert(errorMessageListJson.join('\n'));
                return false;
            }

        } catch (error) {

            console.log('FETCH ERROR');
            console.error(error);
            return false;
        }
    }

    document.getElementById('register_form').addEventListener('submit', async function (event) {

        let form = document.getElementById('register_form');
        event.preventDefault();

        let isValid = await validateInput();
        if (isValid == false) {
            return;
        }

        // 一時的なイベントハンドラを削除
        form.removeEventListener('submit', arguments.callee);

        // 再度フォーム送信をトリガー
        form.submit();
    });

}
