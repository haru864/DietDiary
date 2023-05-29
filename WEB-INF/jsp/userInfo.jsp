<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.model.Account" %>
<%@ page import="com.fasterxml.jackson.databind.ObjectMapper" %>
<% String username = (String)session.getAttribute("username"); %>
<% Map<String, String> userInfoMap = (Map<String, String>)session.getAttribute("user_info_map"); %>
<%
ObjectMapper mapper = new ObjectMapper();
String userModifiableInformationListJson = mapper.writeValueAsString(Account.USER_MODIFIABLE_INFORMATION);
String userInfoMapJson = mapper.writeValueAsString(userInfoMap);
%>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8" http-equiv="Prag" content="no-cache">
    <title>ユーザー情報</title>
    <script type="text/javascript">
        window.userModifiableInformationListJson = '<%= userModifiableInformationListJson %>';
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
        <form action="/DietDiary/MypageServlet" method="post" id="go_to_calender">
            <button>修正する ※実装中</button>
            <input type="hidden" name="action" value="display">
            <input type="hidden" name="page" value="calender">
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