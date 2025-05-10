/** 
 * 
 * Dicle Bölek 
 * 14.04.2025 
 *  
 *  FileReading sınıfı, dosya okuma işlemlerini gerçekleştiren sınıftır. 
 *  Bu sınıf, verilen dosya yolundaki verileri okur ve bu verileri uygun 
 *  biçimde işleyerek programın diğer bileşenlerine aktarır. 
 *  Özellikle, kişileri, gezegenleri ve uzay araçlarını içeren verileri okumak için kullanılır.
 * 
 */

package simulation;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.Person;
import model.Spaceship;
import model.Planet;
import model.Time;
public class FileReading {
	public List<Person> readPersons(String filePath) throws IOException {
	    List<Person> persons = new ArrayList<>();
	    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
	        String line;
	        while ((line = reader.readLine()) != null) {
	            if (line.trim().isEmpty()) continue;  // Boş satırları atla
	            String[] data = line.split("#");
	            if (data.length == 4) {  // Gerekli veri sayısını kontrol et
	                try {
	                    persons.add(new Person(data[0], Integer.parseInt(data[1]), Integer.parseInt(data[2]), data[3]));
	                } catch (NumberFormatException e) {
	                    System.err.println("Geçersiz veri formatı: " + line);  // Hatalı veriler için uyarı
	                }
	            } else {
	                System.err.println("Geçersiz satır: " + line);
	            }
	        }
	    }
	    return persons;
	}


	public List<Spaceship> readSpaceships(String filePath) throws IOException {
	    List<Spaceship> spaceships = new ArrayList<>();
	    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
	        String line;
	        while ((line = reader.readLine()) != null) {
	            if (line.trim().isEmpty()) continue;  // Boş satırları atla
	            String[] data = line.split("#");
	            if (data.length == 5) {
	                try {
	                    String[] date = data[3].split("\\.");
	                    Time time = new Time(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]));
	                    spaceships.add(new Spaceship(data[0], data[1], data[2], time, Integer.parseInt(data[4])));
	                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
	                    System.err.println("Geçersiz veri formatı: " + line);
	                }
	            } else {
	                System.err.println("Geçersiz satır: " + line);
	            }
	        }
	    }
	    return spaceships;
	}

	public List<Planet> readPlanets(String filePath) throws IOException {
	    List<Planet> planets = new ArrayList<>();
	    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
	        String line;
	        while ((line = reader.readLine()) != null) {
	            if (line.trim().isEmpty()) continue;  // Boş satırları atla
	            String[] data = line.split("#");
	            if (data.length == 3) {
	                try {
	                    String[] date = data[2].split("\\.");
	                    Time time = new Time(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]));
	                    planets.add(new Planet(data[0], Integer.parseInt(data[1]), time));
	                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
	                    System.err.println("Geçersiz veri formatı: " + line);
	                }
	            } else {
	                System.err.println("Geçersiz satır: " + line);
	            }
	        }
	    }
	    return planets;
	}

}