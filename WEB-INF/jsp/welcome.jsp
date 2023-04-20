<%@ page contentType="text/html;charset=UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <title>チェックボックス</title>
    </head>

    <body>
        <form method="POST" action="check2.jsp">
            <fieldset>
                <legend>あなたがよく使用するサーバーサイド技術は？</legend>
                <div>
                    <input id="jsp" type="checkbox" name="arch" value="JSP＆サーブレット">
                    <label for="jsp">JSP＆サーブレット</label>
                </div>
                <div>
                    <input id="aspnet" type="checkbox" name="arch" value="ASP.NET">
                    <label for="aspnet">ASP.NET</label>
                </div>
                <div>
                    <input id="php" type="checkbox" name="arch" value="PHP">
                    <label for="php">PHP</label>
                </div>
                <div>
                    <input type="submit" value="送信">
                </div>
            </fieldset>
        </form>
    </body>

    </html>