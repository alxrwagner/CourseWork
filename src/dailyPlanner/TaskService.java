package dailyPlanner;

import enums.Type;
import errors.IncorrectArgumentExeption;
import errors.TaskNotFoundExeption;
import tasks.*;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class TaskService {

    private final Map<Integer, Task> tasks;

    public TaskService() {
        this.tasks = new HashMap<>();
    }

    public void getAllByDate(LocalDate date) {
        Collection<Task> values = tasks.values();
        values.stream().filter(task -> task.appearsIn(date)).forEach(System.out::println);
    }

    public void addTask(Task task) {
            tasks.put(task.getId(), task);
    }

    public void remove(int id) throws TaskNotFoundExeption {
        Collection<Task> values = tasks.values();

        if (values.stream().anyMatch(task -> task.getId() == id)) {
            tasks.remove(id);
        } else {
            throw new TaskNotFoundExeption("Задача с таким ID не существует");
        }
    }
}
