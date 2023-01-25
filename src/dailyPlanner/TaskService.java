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
import java.util.stream.Collectors;

public class TaskService {

    private final Map<Integer, Task> tasks;

    public TaskService() {
        this.tasks = new HashMap<>();
    }

    public List<Task> getAllByDate(LocalDate date) {
        Collection<Task> values = tasks.values();
        return values.stream().filter(task -> task.appearsIn(date)).collect(Collectors.toList());
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
