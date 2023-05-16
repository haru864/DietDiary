<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<% String username = (String)session.getAttribute("username"); %>
<% String genderKanji = (String)session.getAttribute("gender"); %>
<% int age = (Integer)session.getAttribute("age"); %>
<% int activityLevel = (Integer)session.getAttribute("activity_level"); %>
<% String year = (String)session.getAttribute("year"); %>
<% String month = (String)session.getAttribute("month"); %>
<% String day = (String)session.getAttribute("day"); %>
<% Map<String, Double> totalNutritionalIntakeMap = (Map<String, Double>)session.getAttribute("total_nutritional_intake"); %>
<% List<Map<String, Double>> dietList = (List<Map<String, Double>>)session.getAttribute("diet_list"); %>

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
        <p>栄養摂取量(トータル)</p>
        <% if (totalNutritionalIntakeMap != null) { %>
            <%= totalNutritionalIntakeMap %>
        <% } else { %>
            <p>--食事内容が登録されていません--</p>
        <% } %>
    </div>
    <div>
        <p>推奨摂取量</p>
        <p>性別：<%= genderKanji %>, 年齢：<%= age %>歳, 活動レベル：<%= activityLevel %></p>
    </div>
    <div>
        <p>栄養摂取量(食事別)</p>
        <% if (totalNutritionalIntakeMap != null) { %>
            <% for (int i = 0; i < dietList.size(); i++) { %>
                <% Map<String, Double> nutritionalIntakeMap = dietList.get(i); %>
                <div>
                    <%= i + 1 %>食目: <%= nutritionalIntakeMap %>
                </div>
            <% } %>
        <% } else { %>
            <p>--食事内容が登録されていません--</p>
        <% } %>
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