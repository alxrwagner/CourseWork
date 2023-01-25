import dailyPlanner.TaskService;
import enums.Type;
import errors.IncorrectArgumentExeption;
import errors.TaskNotFoundExeption;
import org.w3c.dom.ls.LSOutput;
import tasks.*;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.Arrays;
import java.util.Scanner;


public class Main {
    public static TaskService dailyPlanner = new TaskService();

    public static void main(String[] args) {
        startWork();
    }

    public static void startWork() {
        boolean isWork = true;
        Scanner reader = new Scanner(System.in);
        while (isWork) {
            System.out.println("Доступные команды: " +
                    "\n1 - Добавить задачу" +
                    "\n2 - Распечатать команды на день" +
                    "\n3 - Удалить задачу по ID" +
                    "\n4 - Посмотреть список удаленных задач" +
                    "\n5 - Редактировать описание задачи" +
                    "\n6 - Редактировать заголовок задачи" +
                    "\n7 - Посмотреть все задачи, сгруппированные по датам" +
                    "\n8 - Завершить программу");
            System.out.println();
            System.out.println("Введите номер команды: ");
            String userInput = reader.nextLine();

            switch (userInput) {
                case "1":
                    Task task = createTask();
                    dailyPlanner.addTask(task);
                    System.out.println((char) 27 + "[36mЗадача на " + task.getDateTime() + " добавлена " + (char)27 + "[0m");
                    break;
                case "2":
                    LocalDate date = addDate();
                    System.out.println("Задачи на " + date + "\n");
                    dailyPlanner.getAllByDate(date).forEach(System.out::println);
                    break;
                case "3":
                    System.out.println("Введите ID задачи: ");
                    Scanner scanner = new Scanner(System.in);
                    if (scanner.hasNextInt()) {
                        int input = scanner.nextInt();
                        try {
                            dailyPlanner.remove(input);
                            System.out.println((char) 27 + "[33mЗадача с ID: " + input + " удалена" + (char)27 + "[0m");
                        } catch (TaskNotFoundExeption e) {
                            System.out.println(e.getMessage());
                        }
                    } else {
                        System.err.println("Неверный формат ввода. Необходимо ввести число");
                    }
                    break;
                case "4":
                    dailyPlanner.getRemovedTasks().forEach(System.out::println);
                    break;
                case "5":
                    System.out.println("Введите ID задачи: ");
                    Scanner scanner2 = new Scanner(System.in);
                    String descriptor;
                    if (scanner2.hasNextInt()) {
                        int input = scanner2.nextInt();
                        System.out.println("Введите новое описание задачи: ");
                        descriptor = new Scanner(System.in).nextLine();
                        try {
                            System.out.println("Изменено: " + dailyPlanner.updateDescription(input, descriptor));
                        } catch (TaskNotFoundExeption | IncorrectArgumentExeption e) {
                            System.out.println(e.getMessage());
                        }
                    } else {
                        System.err.println("Неверный формат ввода. Необходимо ввести число");
                    }
                    break;
                case "6":
                    System.out.println("Введите ID задачи: ");
                    Scanner scanner3 = new Scanner(System.in);
                    String title;
                    if (scanner3.hasNextInt()) {
                        int input = scanner3.nextInt();
                        System.out.println("Введите новый заголовок задачи: ");
                        title = new Scanner(System.in).nextLine();
                        try {
                            System.out.println("Изменено: " + dailyPlanner.updateTitle(input, title));
                        } catch (TaskNotFoundExeption | IncorrectArgumentExeption e) {
                            System.out.println(e.getMessage());
                        }
                    } else {
                        System.err.println("Неверный формат ввода. Необходимо ввести число");
                    }
                    break;
                case "7":
                    dailyPlanner.getAllGroupeByDate()
                            .forEach((key, value) -> System.out.println(key + " : " + value));
                    break;
                case "8":
                    System.out.println("Завершение работы программы...");
                    isWork = false;
                    break;
                default:
                    System.err.println("Такой команды не существует.");
                    System.out.println("Введите другую команду: ");
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
        Scanner reader = new Scanner(System.in);

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
        Type type;
        Scanner reader = new Scanner(System.in);

        if (reader.hasNextInt()) {
            userInput = reader.nextInt();
            type = Arrays.stream(Type.values())
                    .filter(t -> t.ordinal() == userInput - 1)
                    .findFirst()
                    .orElse(null);
            while (type == null) {
                System.err.println("Такого типа не существует");
                type = setType();
            }
        } else {
            System.err.print("Необходимо ввести число!\n");
            type = setType();
        }

        return type;
    }

    public static String setText() {
        Scanner reader = new Scanner(System.in);
        String text = reader.nextLine();
        while (text == null || text.isBlank()) {
            System.err.println("Поле не может быть пустым");
            System.out.println("Введите текст: ");
            text = reader.nextLine();
        }
        return text;
    }

    public static LocalDate addDate() {
        Scanner reader = new Scanner(System.in);
        System.out.println("Введите дату в формате dd.MM.yyyy: ");
        LocalDate date = null;
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.uuuu");
        boolean isSuccess = false;

        while (!isSuccess) {
            try {
                date = LocalDate.parse(reader.nextLine(), dateFormatter.withResolverStyle(ResolverStyle.STRICT));
                isSuccess = true;
            } catch (NumberFormatException | NullPointerException | DateTimeException e) {
                System.err.println(e.getMessage());
                System.out.println("Попробуйте еще раз: ");
            }
        }
        return date;
    }

    public static LocalTime addTime() {
        Scanner reader = new Scanner(System.in);
        System.out.println("Введите время в формате HH:mm: ");
        LocalTime time = null;
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        boolean isSuccess = false;

        while (!isSuccess) {
            try {
                time = LocalTime.parse(reader.nextLine(), timeFormatter);
                isSuccess = true;
            } catch (DateTimeException e) {
                System.err.println("Неверный формат времени!");
                System.out.println("Попробуйте еще раз: ");
            }
        }
        return time;
    }
}