package tasks;

import enums.Type;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public abstract class Task{
    private final Integer id;
    private final Type type;

    private StringBuilder title;
    private StringBuilder description;
    private LocalDateTime dateTime;

    private static Integer idGenerator;

    static {idGenerator = 1;}

    public Task(StringBuilder title, StringBuilder description, Type type, LocalDateTime dateTime) {
        this.id = idGenerator;
        idGenerator++;
        this.title = title;
        this.type = type;
        this.dateTime = dateTime;
        this.description = description;
    }

    public abstract void appearsIn(LocalDate date);

    public Integer getId() {
        return id;
    }

    public StringBuilder getTitle() {
        return title;
    }

    public void setTitle(StringBuilder title) {
        this.title = title;
    }

    public Type getType() {
        return type;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public StringBuilder getDescription() {
        return description;
    }

    public void setDescription(StringBuilder description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(getId(), task.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Задача: " +
                "id: " + id +
                ", Тип: " + type.toString() +
                ", Заголовок: " + title +
                ", Описание: " + description +
                ", Дата и время уведомления: " + dateTime;
    }
}
