<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>ユーザー登録画面</title>
    <link rel="stylesheet" type="text/css" href="/DietDiary/CSS/register.css">
</head>

<body>
    <div>
        ユーザー情報を登録します。<br>
        ここで入力した情報を当アプリにおける健康管理目的以外に使用することはありません。<br>
        継続的な利用を前提としており、一定期間ログインされない場合は、アカウントが削除されます。<br>
        メールアドレスを登録している場合は、アカウント削除前に通知を受け取ることができます。<br>
    </div>

    <div>
        <form action="/DietDiary/RegisterServlet" method="post" id="register_form" >
            <div>
                <label for="username">ユーザー名：</label><br>
                <input type="text" name="username" id="username">
            </div>
            <div>
                <label for="password">パスワード：</label><br>
                <input type="password" name="password" id="password">
                <button type="button" id="btn_passview">表示</button><br>
            </div>
            <div>
                <label for="email">メールアドレス：</label><br>
                <input type="email" name="email" id="email">
            </div>
            <div>
                <label for="gender_men">性別：</label>
                <input type="radio" name="gender" value="men" id="gender_men" checked>
                <label for="gender_men">男性</label>
                <input type="radio" name="gender" value="women" id="gender_women">
                <label for="gender_women">女性</label>
            </div>
            <div>
                <label for="birth">生年月日：</label><br>
                <input type="date" name="birth" id="birth">
            </div>
            <div>
                <label for="height">身長：</label><br>
                <input type="number" name="height" id="height" placeholder="100.0" step="0.1" min="0" max="250">
            </div>
            <div>
                <label for="weight">体重：</label><br>
                <input type="number" name="weight" id="weight" placeholder="40.0" step="0.1" min="0" max="200">
            </div>
            <input type="hidden" name="action" value="register">
            <input type="submit" value="登録">
        </form>
    </div>

    <div>
        <a href="/DietDiary/WelcomeServlet">トップ画面へ</a>
    </div>

    <script type="text/javascript" src="${pageContext.request.contextPath}/JavaScript/registerLogic.js">
    </script>
</body>

</html>