package tasks;

import enums.TypeRepeatTask;
import enums.TypeTask;

import java.util.Date;

public class Task {
    private final Integer id;
    private final TypeRepeatTask typeRepeat;

    private String title;
    private TypeTask type;
    private Date date;
    private Date time;
    private StringBuilder description;

    private static Integer idGenerator;

    static {idGenerator = 1;}



}
