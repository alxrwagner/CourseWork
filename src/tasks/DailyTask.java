package tasks;

import enums.Type;
import errors.IncorrectArgumentExeption;

import javax.sound.sampled.FloatControl;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class DailyTask extends Task{
    public DailyTask(String title, String description, Type type, LocalDateTime dateTime) throws IncorrectArgumentExeption {
        super(title, description, type, dateTime);
    }

    @Override
    public boolean appearsIn(LocalDate date) {
        return dateTime.toLocalDate().isBefore(date) || dateTime.toLocalDate().isEqual(date);
    }
}
