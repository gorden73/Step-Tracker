import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StepTracker stepTracker = new StepTracker();
        Converter converter = new Converter();
        ArrayList<String> namesOfMonths = new ArrayList<>();
        namesOfMonths.add("Январь");
        namesOfMonths.add("Февраль");
        namesOfMonths.add("Март");
        namesOfMonths.add("Апрель");
        namesOfMonths.add("Май");
        namesOfMonths.add("Июнь");
        namesOfMonths.add("Июль");
        namesOfMonths.add("Август");
        namesOfMonths.add("Сентябрь");
        namesOfMonths.add("Октябрь");
        namesOfMonths.add("Ноябрь");
        namesOfMonths.add("Декабрь");
        String month;
        int day = 0;
        int steps = 0;
        int purpose = 0;
        int newDay;
        int newSteps = 0;

        while(true) {
            printMenu();
            String command = scanner.next();
            if (command.equals("1")) {
                System.out.println("За какой месяц Вы хотите добавить информацию?");
                do {
                    System.out.println("Введите название месяца с большой буквы на русском языке");
                    month = scanner.next();
                }
                while (!namesOfMonths.contains(month));
                do {
                    newDay = parsingDays(scanner, day);
                }
                while (!((newDay > 0) && (newDay < 31)));
                do {
                    newSteps = parsingSteps(scanner, steps);
                }
                while (!(newSteps > 0));
                stepTracker.saveQuantitySteps(month, newDay, newSteps);
                System.out.println("Данные успешно добавлены");
            } else if (command.equals("2")) {
                System.out.println("За какой месяц хотите увидеть статистику?");
                do {
                    System.out.println("Введите название месяца с большой буквы на русском языке");
                    month = scanner.next();
                }
                while (!namesOfMonths.contains(month));
                try {
                    stepTracker.getStepsPerMonth(month);
                    System.out.println("\n" + "Общее количество пройденных шагов: "
                            + stepTracker.getSumOfStepsByMonth(month));
                    System.out.println("Максимальное количество пройденных шагов за 1 день: "
                            + stepTracker.findMaxQuantitySteps(month));
                    System.out.println("Среднее количество шагов за 1 день: "
                            + stepTracker.getAverageAmount(month));
                    System.out.println("Всего пройдено " + converter.getDistance(month,
                            stepTracker.statistic) + " км.");
                    System.out.println("Всего сожжено " + converter.getSumOfCalories(month,
                            stepTracker.statistic) + " килокалорий");
                    System.out.println("Лучшая серия дней "
                            + stepTracker.getBestRange(month));
                } catch (Exception e) {
                    System.out.println("Статистика за этот месяц отсутствует");
                }
            } else if (command.equals("3")) {
                System.out.println("Текущая цель " + stepTracker.defaultPurpose + " шагов");
                int newPurpose;
                do {
                    newPurpose = parsingPurpose(scanner, purpose);
                }
                while (!(newPurpose>0));
                System.out.println("Новая цель - "+ stepTracker.changePurpose(newPurpose)
                        +" шагов.");
            } else if (command.equals("4")) {
                System.out.println("Выход"+"\n"+"До свидания!");
                break;
            } else {
                System.out.println("Извините, такой команды нет. Попробуйте повторить ввод.");
            }
        }
    }

    public static void printMenu() {
        System.out.println("Рады приветствовать Вас в нашем приложении - Счётчик шагов!");
        System.out.println("Чего желаете?");
        System.out.println("1. Ввести количество шагов за определенный день"+"\n"
                +"2. Напечатать статистику за определенный месяц"+"\n"
                +"3. Изменить цель по количеству шагов в день"+"\n"
                +"4. Выйти из приложения");
    }

    static int parsingDays(Scanner scanner, int day) {
        System.out.println("Введите число от 1 до 30");
        try {
            day = Integer.parseInt(scanner.next());
            return day;
        } catch (NumberFormatException e) {
            System.out.println("Номер должен быть целым числом ");
            return parsingDays(scanner, day);
        }
    }

    static int parsingSteps(Scanner scanner, int steps) {
        System.out.println("Введите количество пройденных шагов");
        try {
            steps = Integer.parseInt(scanner.next());
            return steps;
        } catch (NumberFormatException e) {
            System.out.println("Количество шагов должно быть больше 0");
            return parsingSteps(scanner, steps);
        }
    }

    static int parsingPurpose(Scanner scanner, int purpose) {
        System.out.println("Введите новое целевое количество шагов");
        try {
            purpose = Integer.parseInt(scanner.next());
            return purpose;
        } catch (NumberFormatException e) {
            System.out.println("Целевое значение должно быть целым числом больше 0");
            return parsingPurpose(scanner, purpose);
        }
    }
}
