package tasks;

import enums.Type;
import errors.IncorrectArgumentExeption;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;

public class YearlyTask extends Task {
    public YearlyTask(String title, String description, Type type, LocalDateTime dateTime) throws IncorrectArgumentExeption {
        super(title, description, type, dateTime);
    }

    @Override
    public boolean appearsIn(LocalDate date) {
        return ChronoUnit.DAYS.between(dateTime.toLocalDate(), date) >= 0
                && dateTime.toLocalDate().getDayOfYear() == date.getDayOfYear();

    }
}
