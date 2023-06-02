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
    const checkLoginForm = function () {

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

    function isAccountRegistered() {

        let username = document.getElementById("username").value;
        let password = document.getElementById("password").value;

    }

    document.getElementById('login_form').addEventListener('submit', checkLoginForm);

}
