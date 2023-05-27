<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="com.model.FoodGroup" %>
<%@ page import="com.fasterxml.jackson.databind.ObjectMapper" %>
<% List<FoodGroup> foodGroupList = (List<FoodGroup>)request.getAttribute("food_group_list"); %>
<% Map<Integer, List<String>> foodNameMap = (Map<Integer, List<String>>)request.getAttribute("food_name_map"); %>
<%
ObjectMapper mapper = new ObjectMapper();
String foodNameMapJson = mapper.writeValueAsString(foodNameMap);
%>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8" http-equiv="Prag" content="no-cache">
    <title>食事登録画面</title>
    <script type="text/javascript">
        window.foodNameMapJson = '<%= foodNameMapJson %>';
    </script>
</head>

<body>
    <div>
        <form action="/DietDiary/MypageServlet" method="post" id="register_diet">
            <label for="food_group">分類を選択してください:</label>
            <br>
            <select name="food_group" id="food_group">
                <option value="" selected>--選択してください--</option>
                <% for (var foodGroup : foodGroupList) { %>
                    <option value="<%= foodGroup.foodGroupNumber %>"><%= foodGroup.foodGroupName %></option>
                <% } %>
            </select>
            <br><br>

            <div id="food_name_div" style="display:none;">
                <label for="food_name">食事を選択してください:</label>
                <br>
                <select name="food_name" id="food_name">
                </select>
            </div>
            <br><br>

            <div id="grams_input_field_div" style="display:none;">
                <label for="grams_input_field">グラム数を入力してください:</label>
                <br>
                <input type="text" name="food_weight_gram" id="grams_input">
                g(グラム)
            </div>
            <br><br>

            <input type="hidden" name="action" value="register">
            <input type="hidden" name="page" value="dietRecord">
            <button id="submit_inputs">登録</button>
        </form>
    </div>
    <div>
        <form action="/DietDiary/MypageServlet" method="post" id="go_to_mypage">
            <button>マイページへ</button>
            <input type="hidden" name="action" value="display">
            <input type="hidden" name="page" value="mypage">
        </form>
    </div>

    <script type="text/javascript" src="${pageContext.request.contextPath}/JavaScript/dietRecordLogic.js">
    </script>
</body>

</html>