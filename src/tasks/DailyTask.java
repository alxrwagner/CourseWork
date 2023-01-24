package tasks;

import enums.Type;

import javax.sound.sampled.FloatControl;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class DailyTask extends Task{
    public DailyTask(StringBuilder title, StringBuilder description, Type type, LocalDateTime dateTime) {
        super(title, description, type, dateTime);
    }

    @Override
    public boolean appearsIn(LocalDate date) {
        return dateTime.toLocalDate().isBefore(date) || dateTime.toLocalDate().isEqual(date);
    }
}
