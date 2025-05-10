/** 
 * 
 * Dicle Bölek 
 * 14.04.2025 
 *  
 *  Person sınıfı, kişiye ait tüm verileri ve işlemleri içeren sınıftır. 
 *  Bu sınıf, kişinin adı, yaşadığı gezegen, uzay aracındaki durumu gibi bilgileri 
 *  içerir ve kişinin seyahati ile ilgili işlemleri yönetir.
 * 
 */

package model;
public class Person {
    private String name;
    private int age;
    private int remainingLife;
    private String spaceshipName;

    public Person(String name, int age, int remainingLife, String spaceshipName) {
        this.name = name;
        this.setAge(age);
        this.remainingLife = remainingLife;
        this.spaceshipName = spaceshipName;
    }
    public int getRemainingLife() {
        return remainingLife;
    }

    public void decrementLife() {
        remainingLife--;
    }

    public boolean isAlive() {
        return remainingLife > 0;
    }

    public String getName() {
        return name;
    }

    public String getSpaceshipName() {
        return spaceshipName;
    }
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
}