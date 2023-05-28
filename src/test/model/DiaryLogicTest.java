package test.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.model.DiaryLogic;
import com.model.UserIntakeNutrition;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class DiaryLogicTest {

    public static void main(String[] args) {

        String username = "user";
        String year = "2023";
        String month = "5";
        String day = "28";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
        LocalDate localDate = LocalDate.parse(year + "-" + month + "-" + day, formatter);
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        System.out.println(date);

        var allDietAndNutrition = testListDietAndNutritionOnSpecifiedDate(username, date);
        System.out.println(allDietAndNutrition);
    }

    private static List<UserIntakeNutrition> testListDietAndNutritionOnSpecifiedDate(String username, Date date) {

        DiaryLogic diaryLogic = new DiaryLogic();
        var allDietAndNutrition = diaryLogic.listDietAndNutritionOnSpecifiedDate(username, date);
        return allDietAndNutrition;
    }

}
