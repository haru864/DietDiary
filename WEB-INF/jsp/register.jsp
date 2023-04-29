<%@ page contentType="text/html;charset=UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <title>ユーザー登録画面</title>
    </head>

    <body>
        <form action="/DietDiary/RegisterServlet" method="post">
            <div>
                <label for="username">ユーザー名：</label><br>
                <input type="text" name="username" id="username">
            </div>
            <div>
                <label for="password">パスワード：</label><br>
                <input type="password" name="password" id="password">
            </div>
            <div>
                <label for="email">メールアドレス：</label><br>
                <input type="email" name="email" id="email">
            </div>
            <input type="hidden" name="action" value="register">
            <input type="submit" value="登録">
        </form>
        <a href="/DietDiary/WelcomeServlet">トップ画面へ</a>

        <script>
            function isUsernameValid() {
                // 半角英数字10文字
            }
            function isPasswordValid() {
                // 半角英数字記号10文字
            }
        </script>
    </body>

    </html>