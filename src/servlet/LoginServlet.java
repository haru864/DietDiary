package servlet;

import java.io.IOException;

import com.mysql.cj.log.Log;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Login;
import model.LoginLogic;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");
        String loginJsp = "/WEB-INF/jsp/login.jsp";
        String errorJsp = "/WEB-INF/jsp/error.jsp";
        RequestDispatcher requestDispatcher;

        if (action.equals("display")) {
            requestDispatcher = req.getRequestDispatcher(loginJsp);
        } else {
            requestDispatcher = req.getRequestDispatcher(errorJsp);
        }

        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");
        String mypageJsp = "/WEB-INF/jsp/mypage.jsp";
        String errorJsp = "/WEB-INF/jsp/error.jsp";
        RequestDispatcher requestDispatcher;

        if (action.equals("login")) {

            String username = req.getParameter("username");
            String password = req.getParameter("password");
            Login login = new Login(username, password);

            LoginLogic loginLogic = new LoginLogic();
            Boolean isLoginSuccess = loginLogic.execute(login);

            if (!isLoginSuccess) {
                requestDispatcher = req.getRequestDispatcher(errorJsp);
            } else {
                HttpSession session = req.getSession(true);
                session.setAttribute("username", username);
                requestDispatcher = req.getRequestDispatcher(mypageJsp);
            }

        } else {

            requestDispatcher = req.getRequestDispatcher(errorJsp);
        }

        requestDispatcher.forward(req, resp);
    }
}
