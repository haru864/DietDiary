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

    // 未入力項目に対する警告メッセージの設定（デフォルト非表示）
    document.getElementById("warningUnfilledUsername").style.display = "none";
    document.getElementById("warningUnfilledPassword").style.display = "none";

    // ログインボタン押下時の入力内容チェック
    function checkLoginForm() {

        let isAllFilled = true;

        if (isUsernameFilled() == false) {
            document.getElementById("warningUnfilledUsername").style.display = "block";
            isAllFilled = false;
        } else {
            document.getElementById("warningUnfilledUsername").style.display = "none";
        }

        if (isPasswordFilled() == false) {
            document.getElementById("warningUnfilledPassword").style.display = "block";
            isAllFilled = false;
        } else {
            document.getElementById("warningUnfilledPassword").style.display = "none";
        }

        return isAllFilled;
    }

    async function loginAsynchronously() {

        let url = 'http://localhost:8080/DietDiary/LoginServlet';
        let username = document.getElementById('username').value;
        let password = document.getElementById('password').value;
        console.log('username: ' + username);
        console.log('password: ' + password);

        let params = new URLSearchParams();
        params.append('action', 'login');
        params.append('username', username);
        params.append('password', password);

        try {

            let response = await fetch(url, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                },
                body: params
            });
            console.log(response);
            let json = await response.json();
            console.log(json);

            if (json.result === 'success') {
                return true;
            }
            return false;

        } catch (error) {

            console.log('FETCH ERROR');
            console.error(error);
            return false;
        }
    }

    function isUsernameFilled() {

        let username = document.getElementById("username").value;
        if (username == "") {
            return false;
        }
        return true;
    }

    function isPasswordFilled() {

        let password = document.getElementById("password").value;
        if (password == "") {
            return false;
        }
        return true;
    }

    document.getElementById('login_form').addEventListener('submit', async function (event) {

        let form = document.getElementById('login_form');
        event.preventDefault();

        if (checkLoginForm() == false) {
            alert('入力項目を確認してください');
            event.stopPropagation();
            return;
        }

        console.log('calling loginAsynchronously');
        let isLoginSuccess = await loginAsynchronously();

        console.log('isLoginSuccess: ' + isLoginSuccess);
        if (isLoginSuccess == false) {
            alert('ユーザー名またはパスワードが誤っています');
            event.stopPropagation();
            event.preventDefault();
            return;
        }

        // 一時的なイベントハンドラを削除
        form.removeEventListener('submit', arguments.callee);

        // 再度フォーム送信をトリガー
        form.submit();

    });

}
