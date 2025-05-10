/** 
 * 
 * Dicle Bölek 
 * 14.04.2025
 *  
 *  Planet sınıfı, gezegenle ilgili tüm verileri ve işlemleri içeren sınıftır. 
 *  Bu sınıf, gezegenin adı, günü, nüfusu, saat bilgileri gibi özellikleri yönetir 
 *  ve gezegenin zamanının ilerlemesini, nüfus değişimlerini kontrol eder.
 * 
 */

package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;

public class Planet {
    private String name;
    private int hoursInADay;
    private Time time;
    private int population;
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    public Planet(String name, int hoursInADay, Time time) {
        this.name = name;
        this.hoursInADay = hoursInADay;
        this.time = time;
        this.population = 0;
    }

    public void incrementTime() {
        time.incrementHour(hoursInADay);
    }

    public String getName() {
        return name;
    }

    public Time getTime() {
        return time;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public void incrementPopulation() {
    	//Spaceship spaceship parametre
        //if (!spaceship.isTraveling()) {
            population++;
        //}
    }

    public void decrementPopulation() {
        population--;
    }

    public int getHoursInADay() {
        return hoursInADay;
    }

    // İki tarih arasındaki farkı saat cinsinden hesaplayan fonksiyon
    public static int calculateHoursBetweenDates(String startDate, String endDate, int hoursInDay) throws ParseException {
        Date date1 = dateFormat.parse(startDate);
        Date date2 = dateFormat.parse(endDate);
        
        long diffInMillies = Math.abs(date2.getTime() - date1.getTime());
        long diffInDays = diffInMillies / (24 * 60 * 60 * 1000);
        
        return (int) (diffInDays * hoursInDay);
    }

    // Varış gezegenindeki tarihi hesaplamak için fonksiyon
    public static String calculateArrivalDate(String departurePlanetDate, int departureHoursInDay,
                                              double distanceInHours, int destinationHoursInDay, String destinationCurrentDate) throws ParseException {
        Date destDate = dateFormat.parse(destinationCurrentDate);
        Calendar destCalendar = Calendar.getInstance();
        destCalendar.setTime(destDate);
        
        int totalHours = (int) Math.floor(distanceInHours);
        int daysToAdd = totalHours / destinationHoursInDay;
        
        destCalendar.add(Calendar.DAY_OF_MONTH, daysToAdd);
        
        return dateFormat.format(destCalendar.getTime());
    }

    // Tam seyahat süresi hesaplaması
    public static String calculateCompleteJourneyDate(String departurePlanetDate, String spacecraftDepartureDate,
                                                     int departureHoursInDay, double distanceInHours,
                                                     String destinationCurrentDate, int destinationHoursInDay) throws ParseException {
        int waitingHours = calculateHoursBetweenDates(departurePlanetDate, spacecraftDepartureDate, departureHoursInDay);
        int totalJourneyHours = waitingHours + (int) Math.floor(distanceInHours);
        
        int daysToAdd = totalJourneyHours / destinationHoursInDay;
        
        Date destDate = dateFormat.parse(destinationCurrentDate);
        Calendar destCalendar = Calendar.getInstance();
        destCalendar.setTime(destDate);
        destCalendar.add(Calendar.DAY_OF_MONTH, daysToAdd);
        
        return dateFormat.format(destCalendar.getTime());
    }
}
