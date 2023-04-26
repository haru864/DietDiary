<%@ page contentType="text/html;charset=UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <title>ログイン画面</title>
    </head>

    <body>
        <form action="/DietDiary/LoginServlet" method="post" onsubmit="return checkLoginForm()">
            <div>
                ユーザー名：<br>
                <input type="text" name="username" id="username"><br>
                <font color="red" id="warningUnfilledUsername">ユーザー名を入力してください</font>
            </div>
            <div>
                パスワード：<br>
                <input type="password" name="password" id="password">
                <button id="btn_passview">表示</button><br>
                <font color="red" id="warningUnfilledPassword">パスワードを入力してください</font>
            </div>
            <input type="hidden" name="action" value="login">
            <input type="submit" value="ログイン">
        </form>
        <a href="/DietDiary/WelcomeServlet">トップ画面へ</a>

        <script>
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
        </script>
    </body>

    </html>