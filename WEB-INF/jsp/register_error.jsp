<%@ page contentType="text/html;charset=UTF-8" %>
<%
String isError = (String)session.getAttribute("error");
String s = "test";
%>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>ユーザー登録エラー画面</title>
        </head>

        <body>
            <div>ユーザー登録時にエラーが発生しました。</div>
            <div><%= isError %></div>
            <div><%= s %></div>
            <a href="/DietDiary/RegisterServlet?action=display">登録画面へ戻る</a>
        </body>

        </html>