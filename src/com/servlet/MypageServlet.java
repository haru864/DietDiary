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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        super.doGet(req, resp);
    }

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

        } else if ((action.equals("display") || action.equals("register")) && page.equals("dietRecord")) {

            DietRecordLogic dietRecordLogic = new DietRecordLogic();
            requestDispatcher = req.getRequestDispatcher(unknownErrorJsp);

            if (action.equals("display") && dietRecordLogic.setSelectElement(req)) {

                // log(req.getAttribute("food_group_map").toString());
                // log(req.getAttribute("food_name_map").toString());
                requestDispatcher = req.getRequestDispatcher(dietRecord);

            } else if (action.equals("register") && dietRecordLogic.registerDiet(req)) {

                String foodGroup = req.getParameter("food_group");
                String foodName = req.getParameter("food_name");
                String foodWeightGram = req.getParameter("food_weight_gram");
                log("foodGroup: " + foodGroup + ", foodName: " + foodName + ", foodWeightGram: " + foodWeightGram);
                requestDispatcher = req.getRequestDispatcher(calenderJsp);
            }

        } else {

            requestDispatcher = req.getRequestDispatcher(unknownErrorJsp);
        }

        requestDispatcher.forward(req, resp);
        return;
    }

}
