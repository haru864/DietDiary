<%@ page contentType="text/html;charset=UTF-8" %>
<% String username=(String)session.getAttribute("username"); %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>マイページ</title>
</head>

<body>
    <div>ようこそ、<%= username %>さん</div>
    <div>
        <form action="/DietDiary/MypageServlet" method="get" id="go_to_calender">
            <button>カレンダーへ</button>
            <input type="hidden" name="action" value="display">
            <input type="hidden" name="page" value="calender">
        </form>
    </div>
    <div>
        <form action="/DietDiary/MypageServlet" method="get" id="go_to_userInfo">
            <button>登録情報を確認・修正する</button>
            <input type="hidden" name="action" value="display">
            <input type="hidden" name="page" value="userInfo">
        </form>
    </div>
    <div>
        <form action="/DietDiary/LogoutServlet" method="get" id="logout">
            <button>ログアウト</button>
        </form>
    </div>
</body>

</html>