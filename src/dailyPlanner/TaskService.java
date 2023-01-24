package dailyPlanner;

import enums.Type;
import enums.TypeRepeatTask;
import errors.IncorrectArgumentExeption;
import errors.TaskNotFoundExeption;
import tasks.*;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class TaskService {
    private final Scanner reader;
    private final Map<Integer, Task> tasks;

    public TaskService() {
        this.tasks = new HashMap<>();
        this.reader = new Scanner(System.in);
    }

    public void startWork() {
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
                    addTask();
                    break;
                case "2":
                    getAllByDate();
                    break;
                case "3":
                    remove();
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

    private void getAllByDate() {
        LocalDate date = addDate();
        Collection<Task> values = tasks.values();
        values.stream().filter(task -> task.appearsIn(date)).forEach(System.out::println);
    }

    private void addTask() {
        Task task = createTask();

        tasks.put(task.getId(), task);

    }

    private void remove() {
        int id = 0;
        Collection<Task> values = tasks.values();
        System.out.println("Введите ID задачи, которую хотите удалить: ");
        String userInput = reader.nextLine();
        try {
            id = Integer.parseInt(userInput);
        } catch (NumberFormatException e) {
            IncorrectArgumentExeption error = new IncorrectArgumentExeption("Необходимо ввести число!");
            System.out.println(error.getMessage());
            remove();
        }

        int finalId = id;
        if (values.stream().anyMatch(task -> task.getId() == finalId)) {
            tasks.remove(id);
        } else {
            TaskNotFoundExeption exeption = new TaskNotFoundExeption("Задача с таким ID не существует");
            System.out.println(exeption.getMessage());
        }
    }

    private Task createTask() {
        StringBuilder title;
        StringBuilder description;
        LocalDateTime date;
        LocalDate dateNotify;
        LocalTime timeNotify;
        Type typeTask;
        TypeRepeatTask typeRepeat;

        System.out.println("Введите заголовок задачи: ");
        title = setText();

        System.out.println("Введите описание задачи: ");
        description = setText();

        typeTask = setType();

        dateNotify = addDate();
        timeNotify = addTime();
        date = LocalDateTime.of(dateNotify, timeNotify);

        typeRepeat = chooseTypeRepeat();

        if (typeRepeat.equals(TypeRepeatTask.ONE_TIME)) {
            return new OneTimeTask(title, description, typeTask, date);
        } else if (typeRepeat.equals(TypeRepeatTask.DAILY)) {
            return new DailyTask(title, description, typeTask, date);
        } else if (typeRepeat.equals(TypeRepeatTask.WEEKLY)) {
            return new WeeklyTask(title, description, typeTask, date);
        } else if (typeRepeat.equals(TypeRepeatTask.MONTHLY)) {
            return new MonthlyTask(title, description, typeTask, date);
        } else if (typeRepeat.equals(TypeRepeatTask.YEARLY)) {
            return new YearlyTask(title, description, typeTask, date);
        } else {
            return null;
        }
    }

    private TypeRepeatTask chooseTypeRepeat() {
        TypeRepeatTask type = TypeRepeatTask.ONE_TIME;
        System.out.println("Выберите повторяемость задачи: ");
        Arrays.stream(TypeRepeatTask.values()).forEach(System.out::println);

        String userInput = reader.nextLine();
        if (Arrays.stream(TypeRepeatTask.values()).anyMatch(typeRepeatTask -> typeRepeatTask.getType().equalsIgnoreCase(userInput))) {
            type = Arrays.stream(TypeRepeatTask.values())
                    .filter(typeRepeatTask -> typeRepeatTask.getType().equalsIgnoreCase(userInput))
                    .findFirst().orElse(null);
        } else {
            IncorrectArgumentExeption e = new IncorrectArgumentExeption("Такого типа не существует. Попробуйте еще раз: ");
            System.out.println(e.getMessage());
            chooseTypeRepeat();
        }
        return type;
    }

    private StringBuilder setText() {
        StringBuilder text;

        text = new StringBuilder(reader.nextLine());
        if (text.toString().isBlank()) {
            IncorrectArgumentExeption e = new IncorrectArgumentExeption("Это поле не может быть пустым");
            System.out.println(e.getMessage());
            setText();
        }

        return text;
    }

    private Type setType() {
        Type typeTask = null;
        System.out.println("Введите тип задачи: ");
        System.out.println();
        Arrays.stream(Type.values()).forEach(System.out::println);

        String userInput = reader.nextLine();
        if (Arrays.stream(Type.values()).anyMatch(typeTask1 -> typeTask1.getType().equalsIgnoreCase(userInput))) {
            typeTask = Arrays.stream(Type.values())
                    .filter(typeTask1 -> typeTask1.toString().equalsIgnoreCase(userInput))
                    .findFirst().orElse(null);
        } else {
            IncorrectArgumentExeption e = new IncorrectArgumentExeption("Такого типа не существует. Попробуйте еще раз: ");
            System.out.println(e.getMessage());
            setType();
        }

        return typeTask;
    }

    private LocalDate addDate() {
        System.out.println("Введите дату в формате dd.MM.yyyy: ");
        String date = reader.nextLine();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyy");
        boolean isSuccess = false;

        while (!isSuccess) {
            try {
                LocalDate.parse(date, dateFormatter);
                isSuccess = true;
            } catch (DateTimeException e) {
                System.out.println("Неверный формат. Введите дату формате dd.MM.yyyy: ");
                date = reader.nextLine();
            }
        }

        return LocalDate.parse(date, dateFormatter);
    }

    private LocalTime addTime() {
        System.out.println("Введите время: ");
        String time = reader.nextLine();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("H:mm");

        boolean isSuccess = false;

        while (!isSuccess) {
            try {
                LocalTime.parse(time, timeFormatter);
                isSuccess = true;
            } catch (DateTimeException e) {
                System.out.println("Неверный формат. Введите дату формате dd.MM.yyyy: ");
                time = reader.nextLine();
            }
        }

        return LocalTime.parse(time, timeFormatter);
    }

}
