package com.servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.model.DiaryLogic;
import com.model.DietRecordLogic;

@WebServlet("/MypageServlet")
public class MypageServlet extends HttpServlet {

    private final String mypageJsp = "/WEB-INF/jsp/mypage.jsp";
    private final String calenderJsp = "/WEB-INF/jsp/calender.jsp";
    private final String diaryJsp = "/WEB-INF/jsp/diary.jsp";
    private final String dietRecord = "/WEB-INF/jsp/dietRecord.jsp";
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

            DiaryLogic diaryLogic = new DiaryLogic();
            Boolean isSuccess = diaryLogic.execute(req);

            if (!isSuccess) {
                requestDispatcher = req.getRequestDispatcher(unknownErrorJsp);
                requestDispatcher.forward(req, resp);
                return;
            }

            requestDispatcher = req.getRequestDispatcher(diaryJsp);

        } else if (action.equals("display") && page.equals("dietRecord")) {

            requestDispatcher = req.getRequestDispatcher(dietRecord);

        } else if (action.equals("register") && page.equals("dietRecord")) {

            DietRecordLogic dietRecordLogic = new DietRecordLogic();
            dietRecordLogic.execute();
            requestDispatcher = req.getRequestDispatcher(calenderJsp);

        } else {

            requestDispatcher = req.getRequestDispatcher(unknownErrorJsp);
        }

        requestDispatcher.forward(req, resp);
        return;
    }

}