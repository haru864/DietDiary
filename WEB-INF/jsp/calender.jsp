<%@ page contentType="text/html;charset=UTF-8" %>
    <% String username=(String)session.getAttribute("username"); %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>カレンダー</title>
        </head>

        <body>
            <div>
                <%= username %>さんの食事記録
            </div>


            <div>
                <button form="go_to_mypage">マイページへ</button>
                <form action="/DietDiary/MypageServlet" method="post" id="go_to_mypage"></form>
            </div>
        </body>

        </html>