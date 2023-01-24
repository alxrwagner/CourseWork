package tasks;

import enums.Type;
import errors.IncorrectArgumentExeption;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class WeeklyTask extends Task{
    public WeeklyTask(String title, String description, Type type, LocalDateTime dateTime) throws IncorrectArgumentExeption {
        super(title, description, type, dateTime);
    }

    @Override
    public boolean appearsIn(LocalDate date) {
        return dateTime.toLocalDate().isAfter(date) || dateTime.toLocalDate().isEqual(date)
                && dateTime.toLocalDate().getDayOfWeek().equals(date.getDayOfWeek());
    }
}
