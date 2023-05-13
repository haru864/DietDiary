<!-- <%@ page contentType="text/html;charset=UTF-8" %>
<% String username = (String)session.getAttribute("username"); %>
<% String year = (String)session.getAttribute("year"); %>
<% String month = (String)session.getAttribute("month"); %>
<% String day = (String)session.getAttribute("day"); %> -->
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>食事登録画面</title>
</head>

<body>
    <div>
        <!-- <% for (int i=0; i < N; i++) { %>
        <% } %> -->
    </div>
    <div>
        <form action="/DietDiary/MypageServlet" method="post" id="go_to_mypage">
            <button>マイページへ</button>
            <input type="hidden" name="action" value="display">
            <input type="hidden" name="page" value="mypage">
        </form>
    </div>
</body>

</html>