import dailyPlanner.TaskService;
import enums.Type;
import errors.IncorrectArgumentExeption;
import errors.TaskNotFoundExeption;
import tasks.*;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Arrays;
import java.util.Scanner;


public class Main {
    public static TaskService dailyPlanner = new TaskService();
    public static Scanner reader = new Scanner(System.in);

    public static void main(String[] args) {
        startWork();
    }

    public static void startWork() {
        boolean isWork = true;

        while (isWork) {
            System.out.println("Доступные команды: " +
                    "\n1 - Добавить задачу" +
                    "\n2 - Распечатать команды на день" +
                    "\n3 - Удалить задачу по ID" +
                    "\n4 - Завершить программу");
            System.out.println();
            System.out.println("Введите номер команды: ");
            String userInput = reader.nextLine();

            switch (userInput) {
                case "1":
                    Task task = createTask();
                    dailyPlanner.addTask(task);
                    break;
                case "2":
                    LocalDate date = addDate();
                    dailyPlanner.getAllByDate(date);
                    break;
                case "3":
                    System.out.println("Введите ID задачи: ");
                    int input = reader.nextInt();
                    try {
                        dailyPlanner.remove(input);
                    } catch (TaskNotFoundExeption e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "4":
                    System.out.println("Завершение работы программы...");
                    isWork = false;
                    break;
                default:
                    System.out.println("Такой команды не существует. Попробуйте еще раз: ");
                    break;
            }
        }
    }

    public static Task createTask() {
        String title;
        String description;
        LocalDateTime date;
        LocalDate dateNotify;
        LocalTime timeNotify;
        Type typeTask;
        String typeRepeat;

        System.out.println("Введите заголовок задачи: ");
        title = setText();

        System.out.println("Введите описание задачи: ");
        description = setText();

        typeTask = setType();

        dateNotify = addDate();
        timeNotify = addTime();
        date = LocalDateTime.of(dateNotify, timeNotify);

        System.out.println("Выберите повторяемость задачи: ");
        System.out.println("1 - Однократная \n2 - Ежедневная \n3 - Еженедельная \n4 - Ежемесячная \n5 - Ежегодная");
        typeRepeat = reader.nextLine();
        switch (typeRepeat) {
            case "1":
                try {
                    return new OneTimeTask(title, description, typeTask, date);
                } catch (IncorrectArgumentExeption e) {
                    System.out.println(e.getMessage());
                }
            case "2":
                try {
                    return new DailyTask(title, description, typeTask, date);
                } catch (IncorrectArgumentExeption e) {
                    System.out.println(e.getMessage());
                }
            case "3":
                try {
                    return new WeeklyTask(title, description, typeTask, date);
                } catch (IncorrectArgumentExeption e) {
                    System.out.println(e.getMessage());
                }
            case "4":
                try {
                    return new MonthlyTask(title, description, typeTask, date);
                } catch (IncorrectArgumentExeption e) {
                    System.out.println(e.getMessage());
                }
            case "5":
                try {
                    return new YearlyTask(title, description, typeTask, date);
                } catch (IncorrectArgumentExeption e) {
                    System.out.println(e.getMessage());
                }
        }
        return null;
    }
    public static Type setType() {
        System.out.println("Введите номер типа задачи: ");
        System.out.println();
        Arrays.stream(Type.values()).forEach(type -> System.out.println((type.ordinal() + 1) + ": " + type));
        int userInput;
        Type type = null;

        if(reader.hasNextInt()) {
            userInput = reader.nextInt();
            type = Arrays.stream(Type.values())
                    .filter(t -> t.ordinal() == userInput - 1)
                    .findFirst()
                    .orElse(null);
            while ( type == null){
                System.out.println("Такого типа не существует");
                type = setType();
            }
        }else {
            System.out.println("Введите число!");
            setType();
        }

        return type;
    }

    public static String setText(){
        String text = reader.nextLine();
        while (text == null || text.isBlank()) {
            System.out.println("Поле не может быть пустым");
            text = reader.nextLine();
        }

        return text;
    }
    public static LocalDate addDate() {
        System.out.println("Введите дату в формате dd.MM.yyyy: ");
        LocalDate date = null;
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        //boolean isSuccess = false;

       // while (!isSuccess) {
            try {
                date = LocalDate.parse(reader.nextLine(), dateFormatter);
                //isSuccess = true;
            } catch (DateTimeException e) {
                System.out.println(e.getMessage());
                //date = reader.nextLine();
            }
       // }

        return date;
    }

    public static LocalTime addTime() {
        System.out.println("Введите время в формате HH:mm: ");
        String time = reader.nextLine();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        boolean isSuccess = false;

        while (!isSuccess) {
            try {
                LocalTime.parse(time, timeFormatter);
                isSuccess = true;
            } catch (DateTimeException e) {
                System.out.println(e.getMessage());
                time = reader.nextLine();
            }
        }

        return LocalTime.parse(time, timeFormatter);
    }
}