<%@ page contentType="text/html;charset=UTF-8" %>
<% String username = (String)session.getAttribute("username"); %>
<% String year = (String)session.getAttribute("year"); %>
<% String month = (String)session.getAttribute("month"); %>
<% String day = (String)session.getAttribute("day"); %>
<% String day = (String)session.getAttribute("day"); %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>ダイアリー</title>
</head>

<body>
    <div>
        <%= year %>年<%= month %>月<%= day %>日の<%= username %>さんの食事
    </div>
    <div>
        栄養摂取量
    </div>
    <div>
        <!-- <% for (int i=0; i < N; i++) { %>
        <% } %> -->
    </div>
    <div>
        <form action="/DietDiary/MypageServlet" method="post" id="go_to_dietRecord">
            <button>食事を登録する</button>
            <input type="hidden" name="action" value="display">
            <input type="hidden" name="page" value="dietRecord">
        </form>
    </div>
    <div>
        <form action="/DietDiary/MypageServlet" method="post" id="go_to_calender">
            <button>カレンダーへ</button>
            <input type="hidden" name="action" value="display">
            <input type="hidden" name="page" value="calender">
        </form>
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