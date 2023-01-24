package dailyPlanner;

import enums.Type;
import tasks.Task;
import tasks.WeeklyTask;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Stream;

public class TaskService {
    private final Scanner reader;
    private final Map<Integer, Task> tasks;

    public TaskService() {
        this.tasks = new HashMap<>();
        this.reader = new Scanner(System.in);
    }

    public void startWork(){
        boolean isWork = true;

        while (isWork){
            System.out.println("Доступные команды: " +
                    "\n1 - Добавить задачу" +
                    "\n2 - Распечатать команды на день" +
                    "\n3 - Завершить программу");
            System.out.println();
            System.out.println("Введите номер команды: ");
            String userInput = reader.nextLine();

            switch (userInput){
                case "1":
                    addTask();
                    break;
                case "2":
                    getAllByDate();
                    break;
                case "3":
                    System.out.println("Завершение работы программы...");
                    isWork = false;
                    break;
                default:
                    System.out.println("Такой команды не существует. Попробуйте еще раз: ");
                    break;
            }
        }
    }
    public void getAllByDate(){
        LocalDate date = addDate();
    }
    public void addTask(){

    }
    private Task createTask(){
        StringBuilder title;
        StringBuilder description;
        LocalDateTime date;
        LocalDate dateNotify;
        LocalTime timeNotify;
        Type typeTask;

        System.out.println("Введите заголовок задачи по умолчанию \"Без заголовка\": ");
        title = setText(20);

        System.out.println("Введите описание задачи по умолчанию \"Без описания\": ");
        description = setText(150);

        typeTask = setType();

        dateNotify = addDate();
        timeNotify = addTime();
        date = LocalDateTime.of(dateNotify, timeNotify);

        return new WeeklyTask(title, description, typeTask, date);
    }

    private StringBuilder setText(int maxLenght){
        StringBuilder text;

        text = new StringBuilder(reader.nextLine());
        if (text.toString().isBlank()){
            text.append("Без описания");
        }

        return text;
    }

    private Type setType(){
        boolean isSuccess = false;
        Type typeTask = null;
        System.out.println("Введите тип задачи: ");
        System.out.println();
        Arrays.stream(Type.values()).forEach(System.out::println);

        while (!isSuccess){
            String userInput = reader.nextLine();
            if(Arrays.stream(Type.values()).anyMatch(typeTask1 -> typeTask1.getType().equalsIgnoreCase(userInput))){
                typeTask = Arrays.stream(Type.values())
                        .filter(typeTask1 -> typeTask1.toString().equalsIgnoreCase(userInput))
                        .findFirst().orElse(null);
                isSuccess = true;
            }else {
                System.out.println("Такого типа не существует. Попробуйте еще раз: ");
            }
        }
        return typeTask;
    }

    private LocalDate addDate(){
        System.out.println("Введите дату в формате dd.MM.yyyy: ");
        String date = reader.nextLine();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyy");
        boolean isSuccess = false;

        while (!isSuccess){
            try{
                LocalDate.parse(date, dateFormatter);
                isSuccess = true;
            }catch (DateTimeException e){
                System.out.println("Неверный формат. Введите дату формате dd.MM.yyyy: ");
                date = reader.nextLine();
            }
        }

        return LocalDate.parse(date, dateFormatter);
    }

    private LocalTime addTime(){
        System.out.println("Введите время: ");
        String time = reader.nextLine();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("H:mm");

        boolean isSuccess = false;

        while (!isSuccess){
            try{
                LocalTime.parse(time, timeFormatter);
                isSuccess = true;
            }catch (DateTimeException e){
                System.out.println("Неверный формат. Введите дату формате dd.MM.yyyy: ");
                time = reader.nextLine();
            }
        }

        return LocalTime.parse(time, timeFormatter);
    }

    public Map<Integer, Task> getTasks() {
        return tasks;
    }
}
