package servlet;

import java.io.IOException;
import java.util.Date;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.CalculateCaloriesLogic;
import model.Gender;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {

    private final String registerJsp = "/WEB-INF/jsp/register.jsp";
    private final String mypageJsp = "/WEB-INF/jsp/mypage.jsp";
    private final String registerErrorJsp = "/WEB-INF/jsp/register_error.jsp";
    private final String unknownErrorJsp = "/WEB-INF/jsp/unknown_error.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");
        RequestDispatcher requestDispatcher;

        if (action.equals("display")) {
            requestDispatcher = req.getRequestDispatcher(registerJsp);
        } else {
            requestDispatcher = req.getRequestDispatcher(unknownErrorJsp);
        }

        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");
        RequestDispatcher requestDispatcher;

        if (action.equals("register")) {

            // String username = req.getParameter("action");
            // String password = req.getParameter("action");
            // String email = req.getParameter("action");
            // Date updated = req.getParameter("action");
            // Gender gender = req.getParameter("action");
            // Date birth = req.getParameter("action");
            // double height = req.getParameter("action");
            // double weight = req.getParameter("action");
            // double burnedCalories;

            // CalculateCaloriesLogic calcCalories = new CalculateCaloriesLogic();


            requestDispatcher = req.getRequestDispatcher(mypageJsp);

        } else {

            requestDispatcher = req.getRequestDispatcher(registerErrorJsp);
        }

        requestDispatcher.forward(req, resp);
    }
}
