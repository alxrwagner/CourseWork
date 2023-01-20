package tasks;

import enums.TypeRepeatTask;
import enums.TypeTask;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Task {
    private final Integer id;
    private final TypeRepeatTask typeRepeat;

    private String title;
    private TypeTask type;
    private LocalDate date;
    private LocalTime time;
    private StringBuilder description;

    private static Integer idGenerator;

    static {idGenerator = 1;}

    public Task(TypeRepeatTask typeRepeat, String title, TypeTask type, LocalDate date, LocalTime time) {
        this.id = idGenerator;
        idGenerator++;
        this.typeRepeat = typeRepeat;
        this.title = title;
        this.type = type;
        this.date = date;
        this.time = time;
    }

    public Integer getId() {
        return id;
    }

    public TypeRepeatTask getTypeRepeat() {
        return typeRepeat;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public TypeTask getType() {
        return type;
    }

    public LocalDateTime getTotalTime(){
        return LocalDateTime.of(date, time);
    }

    public void setType(TypeTask type) {
        this.type = type;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public StringBuilder getDescription() {
        return description;
    }

    public void setDescription(StringBuilder description) {
        this.description = description;
    }
}
