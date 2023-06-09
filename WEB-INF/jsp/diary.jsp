<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="com.model.UserIntakeNutrition" %>
<%@ page import="com.model.Nutrition" %>
<% String username = (String)session.getAttribute("username"); %>
<% String genderKanji = (String)session.getAttribute("gender"); %>
<% int age = (Integer)session.getAttribute("age"); %>
<% int activityLevel = 1; %>
<% String year = (String)session.getAttribute("year"); %>
<% String month = (String)session.getAttribute("month"); %>
<% String day = (String)session.getAttribute("day"); %>
<% List<UserIntakeNutrition> userIntakeNutritionList = (List<UserIntakeNutrition>)session.getAttribute("user_intake_nutrition_list"); %>
<% Map<String, Double> totalNutritionalIntakeMap = null; %>
<% Map<String, Double> recommendedIntakeMap = (Map<String, Double>)session.getAttribute("recommended_intake_map"); %>

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
        <p>栄養摂取量(トータル) ※修正中</p>
        <% if (totalNutritionalIntakeMap != null) { %>
            <%= totalNutritionalIntakeMap %>
        <% } else { %>
            <p>--食事内容が登録されていません--</p>
        <% } %>
    </div>
    <div>
        <p>推奨摂取量</p>
        <p>性別：<%= genderKanji %>, 年齢：<%= age %>歳, 活動レベル(仮)：<%= activityLevel %></p>
        <%= recommendedIntakeMap %>
    </div>
    <div>
        <p>栄養摂取量(食事別) ※修正中</p>
        <% if (userIntakeNutritionList != null) { %>
            <% for (var userIntakeNutrition : userIntakeNutritionList) { %>
                <div>
                    <p><%= userIntakeNutrition.dietNumber %>食目 <%= userIntakeNutrition.foodName %>:</p>
                    <% for (var nutritionName : Nutrition.NUTRITION_LIST ) { %>
                        <p>&emsp;<%= nutritionName %>: <%= userIntakeNutrition.getUserIntakeNutrition(nutritionName) %></p>
                    <% } %>
                </div>
            <% } %>
        <% } else { %>
            <p>--食事内容が登録されていません--</p>
        <% } %>
    </div>
    <div>
        <form action="/DietDiary/MypageServlet" method="get" id="go_to_dietRecord">
            <button>食事を登録する</button>
            <input type="hidden" name="action" value="display">
            <input type="hidden" name="page" value="dietRecord">
        </form>
    </div>
    <div>
        <form action="/DietDiary/MypageServlet" method="get" id="go_to_calender">
            <button>カレンダーへ</button>
            <input type="hidden" name="action" value="display">
            <input type="hidden" name="page" value="calender">
        </form>
    </div>
    <div>
        <form action="/DietDiary/MypageServlet" method="get" id="go_to_mypage">
            <button>マイページへ</button>
            <input type="hidden" name="action" value="display">
            <input type="hidden" name="page" value="mypage">
        </form>
    </div>
</body>

</html>