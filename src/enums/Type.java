package enums;

public enum Type {
    PERSONAL("Личная"),
    WORKING("Рабочая");

    private final String type;

    Type(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return type;
    }
}
