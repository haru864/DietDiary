<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="com.fasterxml.jackson.databind.ObjectMapper" %>
<% Map<String, Integer> foodGroupMap = (Map<String, Integer>)request.getAttribute("food_group_map"); %>
<% Map<Integer, List<String>> foodNameMap = (Map<Integer, List<String>>)request.getAttribute("food_name_map"); %>
<%
ObjectMapper mapper = new ObjectMapper();
String jsonMap = mapper.writeValueAsString(foodNameMap);
%>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>食事登録画面</title>
</head>

<body>
    <div>
        <%= jsonMap %>
    </div>
    <div>
        <form action="/DietDiary/MypageServlet" method="post" id="register_diet">
            <label for="food_group">分類を選択してください:</label>
            <br>
            <select name="food_group" id="food_group" onchange="showFoodName()">
                <option value="">--選択してください--</option>
                <% for (Map.Entry<String, Integer> entry : foodGroupMap.entrySet()) { %>
                    <option value="<%= entry.getValue() %>"><%= entry.getKey() %></option>
                <% } %>
            </select>
            <br><br>

            <div id="food_name_div" style="display:none;">
                <label for="food_name">食事を選択してください:</label>
                <br>
                <select name="food_name" id="food_name">
                    <option value="">--選択してください--</option>

                </select>
            </div>
            <br><br>

            <input type="hidden" name="action" value="register">
            <input type="hidden" name="page" value="dietRecord">
            <button>登録</button>
        </form>
    </div>
    <div>
        <form action="/DietDiary/MypageServlet" method="post" id="go_to_mypage">
            <button>マイページへ</button>
            <input type="hidden" name="action" value="display">
            <input type="hidden" name="page" value="mypage">
        </form>
    </div>

    <script src="/DietDiary/JavaScript/dietRecordLogic.js">
    </script>
</body>

</html>