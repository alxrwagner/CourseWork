package tasks;

import enums.Type;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;

public class MonthlyTask extends Task{
    public MonthlyTask(StringBuilder title, StringBuilder description, Type type, LocalDateTime dateTime) {
        super(title, description, type, dateTime);
    }

    @Override
    public boolean appearsIn(LocalDate date) {
        YearMonth yearMonth = YearMonth.of(date.getYear(), date.getMonth());
        return dateTime.toLocalDate().isBefore(date) || dateTime.toLocalDate().isEqual(date)
                && dateTime.toLocalDate().getDayOfMonth() == date.getDayOfMonth() || dateTime.toLocalDate().getDayOfMonth() >= yearMonth.lengthOfMonth();
    }
}
