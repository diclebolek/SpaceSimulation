/** 
 * 
 * Dicle Bölek 
 * 14.04.2025 
 *  
 *  Simulation sınıfı, uzay seyahati simülasyonunun genel mantığını 
 *  yöneten sınıftır. Bu sınıf, uzay araçlarının ve yolcularının hareketlerini 
 *  simüle eder, gezegenlerin zamanını ve nüfusunu günceller, ve simülasyonun 
 *  ilerlemesini takip eder. Ayrıca, simülasyonun başlangıcından sonuna kadar 
 *  durum bilgilerini kullanıcıya gösterir.
 * 
 */

package simulation;

import java.util.*;
import model.Person;
import model.Spaceship;
import model.Planet;
import model.Time;

public class Simulation1 {
    private List<Person> persons;
    private List<Spaceship> spaceships;
    private List<Planet> planets;
    private FileReading fileReading;

    public Simulation1() {
        fileReading = new FileReading();
        try {
        	persons = fileReading.readPersons("data/Persons.txt");
            spaceships = fileReading.readSpaceships("data/Vehicles.txt");
            planets = fileReading.readPlanets("data/Planets.txt");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while (!allArrived()) {
            update();
            printStatus();
            try {
                Thread.sleep(1000);  // Simülasyon hızını ayarlıyoruz
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void clearConsole() {
        try {
            String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                if (System.console() == null) {
                    for (int i = 0; i < 50; i++) System.out.println();
                } else {
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                }
            } else {
                if (System.console() == null) {
                    for (int i = 0; i < 50; i++) System.out.println();
                } else {
                    Runtime.getRuntime().exec("clear");
                }
            }
        } catch (Exception e) {
            for (int i = 0; i < 50; i++) System.out.println();
        }
    }

    private void update() {
        List<Person> deadPersons = new ArrayList<>();
        Map<String, Integer> spaceshipPassengerCount = new HashMap<>();

        // Yolcu sayısını takip et
        for (Person person : persons) {
            if (person.getSpaceshipName() != null) {
                spaceshipPassengerCount.put(person.getSpaceshipName(),
                    spaceshipPassengerCount.getOrDefault(person.getSpaceshipName(), 0) + 1);
            }
        }

        // Kişilerin ömrünü azalt
        for (Person person : persons) {
            person.decrementLife();
            if (!person.isAlive()) {
                deadPersons.add(person);
                if (person.getSpaceshipName() != null) {
                    spaceshipPassengerCount.put(person.getSpaceshipName(),
                        spaceshipPassengerCount.get(person.getSpaceshipName()) - 1);
                }
            }
        }
        persons.removeAll(deadPersons);

        // Araç durumu güncelle
        for (Spaceship spaceship : spaceships) {
            if (spaceship.getStatus().equals("Yolda")) {
                spaceship.decrementRemainingHours();
                if (spaceship.getRemainingHours() <= 0) {
                    spaceship.setStatus("Vardı");
                }
            } else if (spaceship.getStatus().equals("Bekliyor")) {
                for (Planet planet : planets) {
                    if (planet.getName().equals(spaceship.getDeparturePlanet()) &&
                        planet.getTime().toString().equals(spaceship.getDepartureTime().toString())) {
                        if (spaceshipPassengerCount.getOrDefault(spaceship.getName(), 0) > 0) {
                            spaceship.setStatus("Yolda");
                        }
                    }
                }
            }
        }

        // Araçlarda yolcu kalmamışsa İMHA yap
        for (Spaceship spaceship : spaceships) {
            if (!spaceship.getStatus().equals("Bekliyor")) {
                int count = spaceshipPassengerCount.getOrDefault(spaceship.getName(), 0);
                if (count == 0) {
                    spaceship.setStatus("İMHA");
                }
            }
        }

        // Gezegenlerin zamanını artır
        for (Planet planet : planets) {
            planet.incrementTime();
        }

        // Yolcuları gezegen nüfusuna yansıt
        for (Person person : persons) {
            for (Planet planet : planets) {
                for (Spaceship spaceship : spaceships) {
                    if (spaceship.getName().equals(person.getSpaceshipName())) {
                        if (spaceship.getStatus().equals("Vardı") &&
                            spaceship.getArrivalPlanet().equals(planet.getName())) {
                            planet.incrementPopulation();  // Yolcuları gezegen nüfusuna ekle
                        } else if (spaceship.getStatus().equals("Bekliyor") &&
                                   spaceship.getDeparturePlanet().equals(planet.getName())) {
                            planet.incrementPopulation();  // Yolcuları gezegen nüfusuna ekle
                        }
                    }
                }
            }
        }
    }


    private void printStatus() {
        clearConsole();
        // Gezegenler, uzay araçları ve diğer bilgiler ekrana yazdırılıyor
        System.out.println("Gezegenler:");
        System.out.printf("%-15s", "");
        for (Planet planet : planets) {
            System.out.printf("%-15s", "--- " + planet.getName() + " ---");
        }
        System.out.println();

        System.out.printf("%-15s", "Tarih");
        for (Planet planet : planets) {
            System.out.printf("%-15s", planet.getTime().toString());
        }
        System.out.println();

        System.out.printf("%-15s", "Nüfus");
        for (Planet planet : planets) {
            System.out.printf("%-15d", planet.getPopulation());
        }
        System.out.println("\n");

        System.out.println("Uzay Araçları:");
        System.out.printf("%-15s%-15s%-15s%-15s%-25s%s\n", "Araç Adı", "Durum", "Çıkış", "Varış", "Hedefe Kalan Saat", "Hedefe Varacağı Tarih");

        for (Spaceship spaceship : spaceships) {
            String status = spaceship.getStatus();
            System.out.printf("%-15s%-15s%-15s%-15s%-25s",
                spaceship.getName(),
                status,
                spaceship.getDeparturePlanet(),
                spaceship.getArrivalPlanet(),
                status.equals("İMHA") ? "--" : spaceship.getRemainingHours());

            if (status.equals("Yolda")) {
                Time arrivalTime = new Time(
                    spaceship.getDepartureTime().getDay(),
                    spaceship.getDepartureTime().getMonth(),
                    spaceship.getDepartureTime().getYear()
                );
                for (int i = 0; i < spaceship.getRemainingHours(); i++) {
                    for (Planet planet : planets) {
                        if (planet.getName().equals(spaceship.getArrivalPlanet())) {
                            arrivalTime.incrementHour(planet.getHoursInADay());
                        }
                    }
                }
                System.out.printf("%s\n", arrivalTime.toString());
            } else {
                System.out.println("--");
            }
        }
    }

    private boolean allArrived() {
        for (Spaceship spaceship : spaceships) {
            if (!(spaceship.getStatus().equals("Vardı") || spaceship.getStatus().equals("İMHA"))) {
                return false;
            }
        }
        return true;
    }
}
