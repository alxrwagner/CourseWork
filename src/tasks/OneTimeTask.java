package tasks;

import enums.Type;
import errors.IncorrectArgumentExeption;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class OneTimeTask extends Task{
    public OneTimeTask(String title, String description, Type type, LocalDateTime dateTime) throws IncorrectArgumentExeption {
        super(title, description, type, dateTime);
    }

    @Override
    public boolean appearsIn(LocalDate date) {
        return dateTime.toLocalDate().isEqual(date);
    }
}
