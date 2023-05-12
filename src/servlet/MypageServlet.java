package servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.DiaryLogic;

@WebServlet("/MypageServlet")
public class MypageServlet extends HttpServlet {

    private final String mypageJsp = "/WEB-INF/jsp/mypage.jsp";
    private final String calenderJsp = "/WEB-INF/jsp/calender.jsp";
    private final String diaryJsp = "/WEB-INF/jsp/diary.jsp";
    private final String unknownErrorJsp = "/WEB-INF/jsp/unknown_error.jsp";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String page = req.getParameter("page");
        String action = req.getParameter("action");
        RequestDispatcher requestDispatcher;

        if (page == null || page.isEmpty() || action == null || action.isEmpty()) {

            requestDispatcher = req.getRequestDispatcher(unknownErrorJsp);
            requestDispatcher.forward(req, resp);
            return;
        }

        if (action.equals("display") && page.equals("calender")) {

            requestDispatcher = req.getRequestDispatcher(calenderJsp);

        } else if (action.equals("display") && page.equals("mypage")) {

            requestDispatcher = req.getRequestDispatcher(mypageJsp);

        } else if (action.equals("display") && page.equals("diary")) {

            String username = req.getParameter("username");
            String year = req.getParameter("year");
            String month = req.getParameter("month");
            String day = req.getParameter("day");

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDate = LocalDate.parse(year + "-" + month + "-" + day, formatter);
            Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

            DiaryLogic diaryLogic = new DiaryLogic();
            Map<String, Double> nutritionalIntake = diaryLogic.execute(username, date);

            HttpSession session = req.getSession(true);
            session.setAttribute("year", year);
            session.setAttribute("month", month);
            session.setAttribute("day", day);
            session.setAttribute("nutritional_intake", nutritionalIntake);

            requestDispatcher = req.getRequestDispatcher(diaryJsp);

        } else {

            requestDispatcher = req.getRequestDispatcher(unknownErrorJsp);
        }

        requestDispatcher.forward(req, resp);
        return;
    }

}
