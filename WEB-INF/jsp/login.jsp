<%@ page contentType="text/html;charset=UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <title>ログイン画面</title>
    </head>

    <body>
        <form action="/DietDiary/LoginServlet" method="post">
            <div>ユーザー名：<input type="text" name="username"></div>
            <div>パスワード：<input type="text" name="password"></div>
            <input type="hidd" name="action" value="login">
        </form>
        <a href="/DietDiary/WelcomeServlet">トップ画面へ</a>
    </body>

    </html>