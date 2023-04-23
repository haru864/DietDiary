package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");
        String registerJsp = "/WEB-INF/jsp/register.jsp";
        String mypageJsp = "/WEB-INF/jsp/mypage.jsp";
        String errorJsp = "/WEB-INF/jsp/error.jsp";
        RequestDispatcher requestDispatcher;

        if (action.equals("display")) {
            requestDispatcher = req.getRequestDispatcher(registerJsp);
        } else if (action.equals("register")) {
            requestDispatcher = req.getRequestDispatcher(mypageJsp);
        } else {
            requestDispatcher = req.getRequestDispatcher(errorJsp);
        }

        requestDispatcher.forward(req, resp);
    }
}
