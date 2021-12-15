import java.util.ArrayList;
import java.util.HashMap;

public class StepTracker {

    HashMap<String, ArrayList<Integer>> statistic;
    int defaultPurpose = 10000;

    StepTracker() {
        statistic = new HashMap<>();
    }

    void saveQuantitySteps(String month, int bew, int web) { //Сохраняем шаги за день
        if (statistic.containsKey(month)) {
            ArrayList<Integer> stepsByMonth = statistic.get(month);
            if (stepsByMonth.get(bew-1) != null) {
                stepsByMonth.remove(bew-1);
                stepsByMonth.add((bew - 1), web);
            }
        } else {
            ArrayList<Integer> stepsByMonth = new ArrayList<>(30);
            for (int i = 0; i < 30; i++) {
                stepsByMonth.add(i, 0);
            }
            stepsByMonth.add((bew - 1), web);
            statistic.put(month, stepsByMonth);
        }
    }

    int changePurpose(int purpose) { //Меняем целевое количество шагов
        defaultPurpose = purpose;
        return defaultPurpose;
    }

    int getSumOfStepsByMonth(String month) { //Считаем сумму шагов за определенный месяц
        int sum = 0;

        for (Integer listOfSteps : statistic.get(month)) {
            sum += listOfSteps;
        }
        return sum;
    }

    int findMaxQuantitySteps(String month) { //Находим максимальное количество шагов
        int maxSteps = 0;

        ArrayList<Integer> maxis = statistic.get(month);
        for (Integer max : maxis) {
            if (max > maxSteps) {
                maxSteps = max;
            }
        }
        return maxSteps;
    }

    void getStepsPerMonth(String month) { //Количество шагов по дням за месяц
        ArrayList<Integer> list = statistic.get(month);
        if (!list.contains(null)) {
            System.out.println("Статистика за " + month);
            System.out.println("Количество пройденных шагов по дням:");
            for (int i = 0; i < 30; i++) {
                System.out.print((i + 1) + " День: " + list.get(i) + ", ");
            }
        }
    }

    double getAverageAmount(String month) { //Получаем среднее значение шагов за месяц
        double amount = getSumOfStepsByMonth(month);
        double average = amount/30;
        return average;
    }

    int getBestRange(String month) { //Получаем лучшую серию
        int amountOfDays = 0;
        int bestRange = 0;

        ArrayList<Integer> list = statistic.get(month);
        for (int i = 0; i < 30; i++) {
            if (list.get(i) > defaultPurpose) {
                amountOfDays++;
            } else if (amountOfDays > bestRange) {
                bestRange = amountOfDays;
                amountOfDays = 0;
            } else {
                amountOfDays = 0;
            }
        }
        return bestRange;
    }
}