<%@ page contentType="text/html;charset=UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <title>ユーザー登録画面</title>
    </head>

    <body>
        <div>
            ユーザー情報を登録します。<br>
            ここで入力した情報を当アプリにおける健康管理目的以外に使用することはありません。
        </div>
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
            <div>
                <label for="activity_level">活動レベル：</label><br>
                <select name="activity_level" size="1" id="activity_level">
                    <option value="1">ほぼ運動しない</option>
                    <option value="2">軽い運動（週に1〜3日間運動）</option>
                    <option value="3">中程度の運動（週に3〜5日間運動）</option>
                    <option value="4">激しい運動（週に6〜7日間運動）</option>
                    <option value="5">非常に激しい（日常的に運動、日に2回以上）</option>
                </select>
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