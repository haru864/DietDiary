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
                <form action="/DietDiary/MypageServlet" method="post" id="go_to_mypage">
                    <button>カレンダーへ</button>
                    <input type="hidden" name="page" value="calender">
                </form>
            </div>
        </body>

        </html>