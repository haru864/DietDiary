<%@ page contentType="text/html;charset=UTF-8" %>
<% String username=(String)session.getAttribute("username"); %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>カレンダー</title>
    <link rel="stylesheet" href="/DietDiary/CSS/calenderStyle.css">
</head>

<body>
    <div>
        <%= username %>さんの食事記録
    </div>
    <div id="calendar-header">
        <button id="view-prev-month">&lt; 前の月</button>
        <h3 id="current-year-month"></h3>
        <button id="view-next-month">次の月 &gt;</button>
    </div>
    <table id="calendar"></table>
    <div>
        <form action="/DietDiary/MypageServlet" method="get" id="go_to_mypage">
            <button>マイページへ</button>
            <input type="hidden" name="action" value="display">
            <input type="hidden" name="page" value="mypage">
        </form>
    </div>
    <script src="/DietDiary/JavaScript/calenderLogic.js">
    </script>
</body>

</html>