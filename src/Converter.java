import java.util.ArrayList;
import java.util.HashMap;

public class Converter {

    static int SIZE_OF_STEPS = 75; //Средняя длина шага, см
    static int CALORIES_BY_STEP = 50;//Количество сожженных калорий за один шаг

    double getDistance(String month, HashMap statistic) { //Считаем количество пройденных километров
        int sum = 0;

        HashMap <String, ArrayList<Integer>> statistic1 = statistic;
        for (Integer list : statistic1.get(month)) {
            sum+=list;
        }
        double distance = (SIZE_OF_STEPS * sum)/100000.0;
        return distance;
    }

    double getSumOfCalories(String month, HashMap statistic) { //Считаем потраченные килокалории
        int sum = 0;

        HashMap <String, ArrayList<Integer>> statistic1 = statistic;
        for (Integer list : statistic1.get(month)) {
            sum+=list;
        }
        double calories = (CALORIES_BY_STEP * sum)/1000.0;
        return calories;
    }
}

