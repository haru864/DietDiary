 <%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>ログイン画面</title>
    <link rel="stylesheet" type="text/css" href="/DietDiary/CSS/login.css">
</head>

<body>
    <div>
        <form id="login_form" action="/DietDiary/LoginServlet" method="post">
            <div>
                <label for="username">ユーザー名：</label><br>
                <input type="text" name="username" id="username"><br>
                <font color="red" id="warningUnfilledUsername">ユーザー名を入力してください</font>
            </div>
            <div>
                <label for="password">パスワード：</label><br>
                <input type="password" name="password" id="password">
                <button type="button" id="btn_passview">表示</button><br>
                <font color="red" id="warningUnfilledPassword">パスワードを入力してください</font>
            </div>
            <input type="hidden" name="action" value="transition">
            <input type="submit" value="ログイン">
        </form>
    </div>
    <div>
        <a href="/DietDiary/WelcomeServlet">トップ画面へ</a>
    </div>
    <script type="text/javascript" src="${pageContext.request.contextPath}/JavaScript/loginLogic.js">
    </script>
</body>

</html>