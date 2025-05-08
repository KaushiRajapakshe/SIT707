package sit707_tasks;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * @author Ahsan Habib
 */
public class DateUtil {
	
	private int day, month, year;
	
	public DateUtil(int day, int month, int year) {
        if (!isValidDate(day, month, year)) {
            throw new IllegalArgumentException("Invalid date: " + day + "/" + month + "/" + year);
        }
        this.day = day;
        this.month = month;
        this.year = year;
    }
	
	public int getDay() { return day; }
    public int getMonth() { return month; }
    public int getYear() { return year; }

    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    public static boolean isValidDate(int day, int month, int year) {
        if (year < 1700 || year > 2024 || month < 1 || month > 12) return false;
        int maxDays = monthDuration(month, year);
        return day >= 1 && day <= maxDays;
    }
    
    /**
	 * Calculate duration of current month of year.
	 * @param month
	 * @param year
	 * @return
	 */
    public static int monthDuration(int month, int year) {
        Month m = Month.fromInt(month);
        if (m == Month.FEBRUARY && isLeapYear(year)) {
            return 29;
        }
        return m.defaultDays;
    }

    /**
	 * Increment one day.
	 */
    public void increment() {
        if (day < monthDuration(month, year)) {
            day++;
        } else if (month < 12) {
            day = 1;
            month++;
        } else {
            day = 1;
            month = 1;
            year++;
        }
    }

    /**
	 * Decrement one day from current date.
	 */
    public void decrement() {
        if (day > 1) {
            day--;
        } else if (month > 1) {
            month--;
            day = monthDuration(month, year);
        } else {
            year--;
            month = 12;
            day = monthDuration(month, year);
        }
    }

    /**
	 * Get the day of week.
	 * @return MONDAY, TUESDAY, ...
	 */
    public String getDayOfWeek() {
        LocalDate date = LocalDate.of(year, month, day);
        return date.getDayOfWeek().toString();
    }
    
  
    /**
	 * Get days of between two days.
	 * 
	 */
    public long daysBetween(DateUtil other) {
        LocalDate thisDate = LocalDate.of(this.year, this.month, this.day);
        LocalDate otherDate = LocalDate.of(other.year, other.month, other.day);
        return Math.abs(ChronoUnit.DAYS.between(thisDate, otherDate));
    }

    /**
	 * User friendly output.
	 */
    public String toString() {
        Month m = Month.fromInt(month);
        return String.format("%d %s %d", day, m.name, year);
    }
	
}
