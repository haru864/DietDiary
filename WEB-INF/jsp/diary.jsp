<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<% String username = (String)session.getAttribute("username"); %>
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
            <% for (String nutritionName : totalNutritionalIntakeMap.keySet()) { %>
                <div>
                    <%= nutritionName %>: <%= totalNutritionalIntakeMap.get(nutritionName) %>
                </div>
            <% } %>
        <% } else { %>
            <p>--食事内容が登録されていません--</p>
        <% } %>
    </div>
    <div>
        <p>栄養摂取量(食事別)</p>
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