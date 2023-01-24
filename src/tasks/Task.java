package tasks;

import enums.Type;
import errors.IncorrectArgumentExeption;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public abstract class Task{
    protected final Integer id;
    protected final Type type;
    protected final LocalDateTime dateTime;

    protected String title;
    protected String description;

    private static Integer idGenerator;

    static {idGenerator = 1;}

    public Task(String title, String description, Type type, LocalDateTime dateTime) throws IncorrectArgumentExeption {
        this.id = idGenerator;
        idGenerator++;
        setTitle(title);
        if (type == null){
            throw new  IncorrectArgumentExeption("Такого объекта не сщесутвует");
        }else {this.type = type;}
        this.dateTime = dateTime;
        setDescription(description);
    }

    public abstract boolean appearsIn(LocalDate date);

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) throws IncorrectArgumentExeption {
        if (title == null || title.isBlank()){
            throw new IncorrectArgumentExeption("Заголовок не может быть пустым!");
        }else {
            this.title = title;
        }
    }

    public Type getType() {
        return type;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) throws IncorrectArgumentExeption {
        if (description == null || description.isBlank()){
            throw new IncorrectArgumentExeption("Описание не может быть пустым!");
        }else {
            this.description = title;
        }
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
