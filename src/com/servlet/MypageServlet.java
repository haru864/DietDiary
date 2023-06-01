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
import com.model.UserInfoLogic;

@WebServlet("/MypageServlet")
public class MypageServlet extends HttpServlet {

    private final String mypageJsp = "/WEB-INF/jsp/mypage.jsp";
    private final String calenderJsp = "/WEB-INF/jsp/calender.jsp";
    private final String diaryJsp = "/WEB-INF/jsp/diary.jsp";
    private final String dietRecord = "/WEB-INF/jsp/dietRecord.jsp";
    private final String userInfo = "/WEB-INF/jsp/userInfo.jsp";
    private final String unknownErrorJsp = "/WEB-INF/jsp/unknown_error.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String page = req.getParameter("page");
        String action = req.getParameter("action");
        RequestDispatcher requestDispatcher;

        if (page == null || page.isEmpty() || action == null || action.isEmpty()
                || action.equals("display") == false) {

            requestDispatcher = req.getRequestDispatcher(unknownErrorJsp);
            requestDispatcher.forward(req, resp);
            return;
        }

        if (page.equals("calender")) {

            requestDispatcher = req.getRequestDispatcher(calenderJsp);

        } else if (page.equals("mypage")) {

            requestDispatcher = req.getRequestDispatcher(mypageJsp);

        } else if (page.equals("diary")) {

            if (new DiaryLogic().execute(req) == false) {
                requestDispatcher = req.getRequestDispatcher(unknownErrorJsp);
                requestDispatcher.forward(req, resp);
                return;
            }

            requestDispatcher = req.getRequestDispatcher(diaryJsp);

        } else if (page.equals("dietRecord")) {

            if (new DietRecordLogic().setSelectElement(req) == false) {
                requestDispatcher = req.getRequestDispatcher(unknownErrorJsp);
                requestDispatcher.forward(req, resp);
                return;
            }

            requestDispatcher = req.getRequestDispatcher(dietRecord);

        } else if (page.equals("userInfo")) {

            if (new UserInfoLogic().setUserInfo(req) == false) {
                requestDispatcher = req.getRequestDispatcher(unknownErrorJsp);
                requestDispatcher.forward(req, resp);
                return;
            }

            requestDispatcher = req.getRequestDispatcher(userInfo);

        } else {

            requestDispatcher = req.getRequestDispatcher(unknownErrorJsp);
        }

        requestDispatcher.forward(req, resp);
        return;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String page = req.getParameter("page");
        String action = req.getParameter("action");
        RequestDispatcher requestDispatcher;

        if (page == null || page.isEmpty() || action == null || action.isEmpty()
                || action.equals("register") == false) {

            requestDispatcher = req.getRequestDispatcher(unknownErrorJsp);
            requestDispatcher.forward(req, resp);
            return;
        }

        if (page.equals("dietRecord")) {

            if (new DietRecordLogic().registerDiet(req) == false) {
                requestDispatcher = req.getRequestDispatcher(unknownErrorJsp);
                requestDispatcher.forward(req, resp);
                return;
            }

            requestDispatcher = req.getRequestDispatcher(calenderJsp);

        } else if (page.equals("userInfo")) {

            if (new UserInfoLogic().registerModifiedUserInfo(req) == false) {
                requestDispatcher = req.getRequestDispatcher(unknownErrorJsp);
                requestDispatcher.forward(req, resp);
                return;
            }

            requestDispatcher = req.getRequestDispatcher(mypageJsp);

        } else {

            requestDispatcher = req.getRequestDispatcher(unknownErrorJsp);
        }

        requestDispatcher.forward(req, resp);
        return;
    }

}
