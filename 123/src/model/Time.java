/** 
 * 
 * Dicle Bölek 
 * 14.04.2025 
 *  
 *  Time sınıfı, zaman yönetimiyle ilgili tüm verileri ve işlemleri içeren sınıftır. 
 *  Bu sınıf, saat, gün, ay ve yıl bilgilerini içerir ve zaman dilimi üzerinde çeşitli 
 *  işlemler yapılmasına olanak tanır.
 * 
 */

package model;

public class Time {
    private int hour;
    private int day;
    private int month;
    private int year;

    public Time(int day, int month, int year) {
        this.hour = 0;
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public void incrementHour(int planetHoursInADay) {
        hour++;
        if (hour >= planetHoursInADay) {
            hour = 0;
            day++;
            if (day > getDaysInMonth(month, year)) {
                day = 1;
                month++;
                if (month > 12) {
                    month = 1;
                    year++;
                }
            }
        }
    }

    public int getHour() {
        return hour;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public String toString() {
        return String.format("%02d.%02d.%04d", day, month, year);
    }

    private int getDaysInMonth(int month, int year) {
        if (month == 2) {
            if (isLeapYear(year)) {
                return 29;
            } else {
                return 28;
            }
        } else if (month == 4 || month == 6 || month == 9 || month == 11) {
            return 30;
        } else {
            return 31;
        }
    }

    public int compareTo(Time other) {
        if (year != other.year) return Integer.compare(year, other.year);
        if (month != other.month) return Integer.compare(month, other.month);
        if (day != other.day) return Integer.compare(day, other.day);
        return Integer.compare(hour, other.hour);
    }

    private boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }
}
