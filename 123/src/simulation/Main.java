/** 
 * 
 * Dicle Bölek 
 * 14.04.2025 
 *  
 *  Main sınıfı, programın başlangıç noktasıdır. Bu sınıf, simülasyonun 
 *  başlatılmasından sorumludur ve gerekli verileri okuduktan sonra 
 *  simülasyonun çalışmasını sağlar. Simülasyonun ilerlemesi ve durumu 
 *  burada kontrol edilir ve kullanıcıya görsel çıktı sağlanır.
 * 
 */
package simulation;


public class Main {
    public static void main(String[] args) {
        Simulation1 simulation = new Simulation1();
        simulation.run();
    }
}