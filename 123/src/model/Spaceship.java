/** 
 * 
 * Dicle Bölek 
 * 14.04.2025 
 *  
 *  Spaceship sınıfı, uzay aracıyla ilgili tüm verileri ve işlemleri içeren sınıftır. 
 *  Bu sınıf, uzay aracının adı, durumu, seyahat saati ve yolcularıyla ilgili bilgileri 
 *  yönetir ve seyahat süreçlerini takip eder.
 * 
 */
package model;

public class Spaceship {
    private String name;
    private String departurePlanet;
    private String arrivalPlanet;
    private Time departureTime;
    private int distanceInHours;
    private int remainingHours;
    private String status;  // "Bekliyor", "Vardı", "Yolda" gibi durumlar olabilir

    public Spaceship(String name, String departurePlanet, String arrivalPlanet, Time departureTime, int distanceInHours) {
        this.name = name;
        this.departurePlanet = departurePlanet;
        this.arrivalPlanet = arrivalPlanet;
        this.departureTime = departureTime;
        this.setDistanceInHours(distanceInHours);
        this.remainingHours = distanceInHours;
        this.status = "Bekliyor";  // Başlangıçta uzay aracı "Bekliyor" durumunda
    }

    public void decrementRemainingHours() {
        if (remainingHours > 0) {
            remainingHours--;
        }
    }

    public String getName() {
        return name;
    }

    public String getDeparturePlanet() {
        return departurePlanet;
    }

    public String getArrivalPlanet() {
        return arrivalPlanet;
    }

    public Time getDepartureTime() {
        return departureTime;
    }

    public int getRemainingHours() {
        return remainingHours;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isDestroyed() {
        return getStatus().equalsIgnoreCase("İMHA");
    }

    public boolean isArrived() {
        return getStatus().equalsIgnoreCase("Vardı");
    }

    // Uzay aracının yolda olup olmadığını kontrol eden fonksiyon
    public boolean isTraveling() {
        return status.equalsIgnoreCase("Yolda");
    }

    // Uzay aracını yolda olarak başlatan fonksiyon
    public void startTravel() {
        status = "Yolda";
    }

    // Uzay aracını varmış olarak işaretleyen fonksiyon
    public void arriveAtDestination() {
        status = "Vardı";
    }

    // Uzay aracını "Bekliyor" olarak işaretleyen fonksiyon
    public void setToWaiting() {
        status = "Bekliyor";
    }

	public int getDistanceInHours() {
		return distanceInHours;
	}

	public void setDistanceInHours(int distanceInHours) {
		this.distanceInHours = distanceInHours;
	}
}
