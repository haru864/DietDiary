<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%
List<String> errorMessageList = (List<String>)session.getAttribute("errorMessageList");
%>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>ユーザー登録エラー画面</title>
        </head>

        <body>
            <div>ユーザー登録時にエラーが発生しました。</div>
            <% if (errorMessageList != null) { %>
            <%   for (String errorMessage : errorMessageList) { %>
                    <div><%= errorMessage %></div>
            <%   } %>
            <% } %>
            <a href="/DietDiary/RegisterServlet?action=display">登録画面へ戻る</a>
        </body>

        </html>