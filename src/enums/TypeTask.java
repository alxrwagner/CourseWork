package enums;

public enum TypeTask {
    PERSONAL("Личная"),
    WORKING("Рабочая");

    private final String type;

    TypeTask(String type) {
        this.type = type;
    }
}
