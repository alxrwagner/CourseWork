package tasks;

import enums.Type;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class YearlyTask extends Task {
    public YearlyTask(StringBuilder title, StringBuilder description, Type type, LocalDateTime dateTime) {
        super(title, description, type, dateTime);
    }

    @Override
    public boolean appearsIn(LocalDate date) {
         if (dateTime.toLocalDate().isLeapYear() && !date.isLeapYear()) {
            return dateTime.toLocalDate().isBefore(date) && dateTime.toLocalDate().getDayOfYear() == date.getDayOfYear() + 1
                    || dateTime.toLocalDate().isEqual(date);
        } else if(!dateTime.toLocalDate().isLeapYear() && date.isLeapYear()){
            return dateTime.toLocalDate().isBefore(date) && dateTime.toLocalDate().getDayOfYear() == date.getDayOfYear() - 1
                    || dateTime.toLocalDate().isEqual(date);
        }else {
            return dateTime.toLocalDate().isBefore(date) && dateTime.toLocalDate().getDayOfYear() == date.getDayOfYear()
                    || dateTime.toLocalDate().isEqual(date);
        }

    }
}
