package sit707_tasks;

public enum Month {
    JANUARY(1, "January", 31),
    FEBRUARY(2, "February", 28),
    MARCH(3, "March", 31),
    APRIL(4, "April", 30),
    MAY(5, "May", 31),
    JUNE(6, "June", 30),
    JULY(7, "July", 31),
    AUGUST(8, "August", 31),
    SEPTEMBER(9, "September", 30),
    OCTOBER(10, "October", 31),
    NOVEMBER(11, "November", 30),
    DECEMBER(12, "December", 31);

    public final int value;
    public final String name;
    public final int defaultDays;

    Month(int value, String name, int defaultDays) {
        this.value = value;
        this.name = name;
        this.defaultDays = defaultDays;
    }

    public static Month fromInt(int month) {
        for (Month m : Month.values()) {
            if (m.value == month) return m;
        }
        throw new IllegalArgumentException("Invalid month: " + month);
    }
}
