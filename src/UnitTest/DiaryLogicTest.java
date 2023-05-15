package UnitTest;

import java.util.Date;
import java.util.List;
import java.util.Map;

import debug.Debugger;
import model.DiaryLogic;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class DiaryLogicTest {

    public static void main(String[] args) {

        String username = "user";
        String year = "2023";
        String month = "5";
        String day = "13";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
        LocalDate localDate = LocalDate.parse(year + "-" + month + "-" + day, formatter);
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        System.out.println(date);

        Map<String, Double> totalNutritionalIntakeMap = testCalcTotalNutritionIntake(username, date);
        List<Map<String, Double>> dietList = testGetDietList(username, date);
        System.out.println(totalNutritionalIntakeMap);
        System.out.println(dietList);
    }

    private static Map<String, Double> testCalcTotalNutritionIntake(String username, Date date) {

        DiaryLogic diaryLogic = new DiaryLogic();
        var total = diaryLogic.calcTotalNutritionIntake(username, date);
        return total;
    }

    private static List<Map<String, Double>> testGetDietList(String username, Date date) {

        DiaryLogic diaryLogic = new DiaryLogic();
        var list = diaryLogic.getDietList(username, date);
        return list;
    }

}
