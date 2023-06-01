<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.model.Account" %>
<%@ page import="com.fasterxml.jackson.databind.ObjectMapper" %>
<% String username = (String)session.getAttribute("username"); %>
<% Map<String, String> userInfoMap = (Map<String, String>)session.getAttribute("user_info_map"); %>
<%
ObjectMapper mapper = new ObjectMapper();
String visibleUserInformationListJson = mapper.writeValueAsString(Account.VISIBLE_USER_INFORMATION);
String modifiableUserInformationMapJson = mapper.writeValueAsString(Account.MODIFIABLE_USER_INFORMATION);
String userInfoMapJson = mapper.writeValueAsString(userInfoMap);
%>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8" http-equiv="Prag" content="no-cache">
    <title>ユーザー情報</title>
    <link rel="stylesheet" href="/DietDiary/CSS/userInfo.css">
    <script type="text/javascript">
        window.visibleUserInformationListJson = '<%= visibleUserInformationListJson %>';
        window.modifiableUserInformationMapJson = '<%= modifiableUserInformationMapJson %>';
        window.userInfoMapJson = '<%= userInfoMapJson %>';
    </script>
</head>

<body>
    <div>
        <p><%= username %>さんの登録情報</p>
        <div id="user_info_display_div">
        </div>
    </div>
    <div>
        <form action="/DietDiary/MypageServlet" method="post" id="submit_userInfo">
            <button id="submit">修正する ※実装中</button>
            <input type="hidden" name="action" value="register">
            <input type="hidden" name="page" value="userInfo">
        </form>
        <form action="/DietDiary/MypageServlet" method="get" id="go_to_calender">
            <button>カレンダーへ</button>
            <input type="hidden" name="action" value="display">
            <input type="hidden" name="page" value="calender">
        </form>
    </div>

    <script type="text/javascript" src="${pageContext.request.contextPath}/JavaScript/userInfoLogic.js">
    </script>
</body>

</html>