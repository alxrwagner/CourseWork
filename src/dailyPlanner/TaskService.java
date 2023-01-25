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
    private final List<Task> removedTasks;

    public TaskService() {
        this.tasks = new HashMap<>();
        this.removedTasks = new ArrayList<>();
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
            removedTasks.add(tasks.remove(id));
        } else {
            throw new TaskNotFoundExeption("Задача с таким ID не существует");
        }
    }

    public List<Task> getRemovedTasks() {
        return Collections.unmodifiableList(removedTasks);
    }

    public Task updateDescription(int id, String description) throws IncorrectArgumentExeption, TaskNotFoundExeption {
        Task task;
        if (tasks.values().stream().anyMatch(t -> t.getId() == id)) {
            tasks.get(id).setDescription(description);
            task = tasks.get(id);
        } else {
            throw new TaskNotFoundExeption("Задача с таким ID не существует");
        }
        return task;
    }

    public Task updateTitle(int id, String title) throws IncorrectArgumentExeption, TaskNotFoundExeption {
        Task task;
        if (tasks.values().stream().anyMatch(t -> t.getId() == id)) {
            tasks.get(id).setTitle(title);
            task = tasks.get(id);
        } else {
            throw new TaskNotFoundExeption("Задача с таким ID не существует");
        }
        return task;
    }

    public Map<LocalDate, List<Task>> getAllGroupeByDate(){
        return tasks.values().stream()
                .collect(Collectors.groupingBy(task -> task.getDateTime().toLocalDate()));
    }
}
