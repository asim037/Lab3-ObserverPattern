/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wikipediaexample;

/**
 *
 * @author FA20-BSE-037
 */
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

interface Observer {
    void update(String event);
}
class Observer1 implements Observer {
    @Override
    public void update(String event) {
        System.out.println("Received response: " + event);
    }
}
public class Wikipediaexample {

    /**
     * @param args the command line arguments
     */
    List<Observer> observers = new ArrayList<>();
  
   public void notifyObservers(String event) {
        observers.forEach(observer -> observer.update(event));
    }
  
    public void addObserver(Observer observer) {
        observers.add(observer);
    }
  
    public void scanSystemIn() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            notifyObservers(line);
        }
    }
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("Enter Text : ");
        Wikipediaexample ob = new Wikipediaexample();
        Observer co = new Observer1();
        ob.addObserver(co);

        ob.scanSystemIn();
        
        
    }
    
}

  

    
